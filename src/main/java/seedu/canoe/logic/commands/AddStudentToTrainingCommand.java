package seedu.canoe.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.canoe.commons.core.Messages.MESSAGE_DUPLICATE_STUDENTS_IN_TRAINING;
import static seedu.canoe.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.canoe.commons.core.Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX;
import static seedu.canoe.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.canoe.model.Model.PREDICATE_SHOW_ALL_STUDENTS;
import static seedu.canoe.model.Model.PREDICATE_SHOW_ALL_TRAININGS;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import seedu.canoe.commons.core.LogsCenter;
import seedu.canoe.commons.core.Messages;
import seedu.canoe.commons.core.index.Index;
import seedu.canoe.commons.util.StringUtil;
import seedu.canoe.logic.commands.exceptions.CommandException;
import seedu.canoe.model.Model;
import seedu.canoe.model.student.AcademicYear;
import seedu.canoe.model.student.Attend;
import seedu.canoe.model.student.Email;
import seedu.canoe.model.student.Id;
import seedu.canoe.model.student.Name;
import seedu.canoe.model.student.Phone;
import seedu.canoe.model.student.Student;
import seedu.canoe.model.student.Training;
import seedu.canoe.model.student.time.Day;
import seedu.canoe.model.tag.Tag;

/**
 * Adds an existing student to a training.
 */
public class AddStudentToTrainingCommand extends Command {

    public static final Logger LOGGER = LogsCenter.getLogger(AddStudentToTrainingCommand.class);

    public static final String COMMAND_WORD = "ts-add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds the corresponding Students "
            + " to the specified Training Session\n"
            + "Parameters: Training_Session-ID " + PREFIX_ID + "Student_ID..."
            + "\nExample: "
            + COMMAND_WORD + "1 " + PREFIX_ID + "3,5,7";

    public static final String MESSAGE_TRAINING_CANNOT_ADD = "No more students can be added to this training";
    public static final String MESSAGE_ADD_STUDENT_SUCCESS = "Added Student: %1$s";
    public static final String MESSAGE_NO_STUDENTS_SPECIFIED = "At least one student to be added must be specified.";
    public static final String MESSAGE_STUDENT_UNAVAILABLE = "This student cannot be added to the training as "
            + "either his dismissal time on the specified day falls after the training's start time or he has a "
            + "training scheduled on the same date already!";
    private final Index index;
    private final String[] studentsToAdd;

    /**
     * @param index of the Training Session to add Students to.
     * @param studentsToAdd corresponding Id of Students to add.
     */
    public AddStudentToTrainingCommand(Index index, String[] studentsToAdd) {
        requireNonNull(index);
        requireNonNull(studentsToAdd);

        this.index = index;
        this.studentsToAdd = studentsToAdd;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        LOGGER.info("==========================[ Executing AddStudentToTrainingCommand ]==========================");

        requireNonNull(model);

        //Added in case previous command is find
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        model.updateFilteredTrainingList(PREDICATE_SHOW_ALL_TRAININGS);

        List<Training> lastShownList = model.getFilteredTrainingList();
        List<Student> studentList = model.getFilteredStudentList();

        if (studentsToAdd == null) {
            LOGGER.warning("User input invalid");
            throw new CommandException(MESSAGE_NO_STUDENTS_SPECIFIED);
        }

        if (index.getZeroBased() >= lastShownList.size()) {
            LOGGER.warning("User input invalid");
            throw new CommandException(Messages.MESSAGE_INVALID_TRAINING_DISPLAYED_INDEX);
        }

        Training trainingToEdit = lastShownList.get(index.getZeroBased());
        /* CONDITION TO CHECK IF THE CURRENT DATE TIME IS ALREADY AFTER TRAINING'S STARTING DATE TIME
        if (!trainingToEdit.canAddStudent()) {
            throw new CommandException(MESSAGE_TRAINING_CANNOT_ADD);
        }*/

        Training editedTraining = new Training(trainingToEdit.getDateTime(), trainingToEdit.getStudents());

        //Student ID Checks - not invalid index, numbered index and exists in student list and not duplicated
        List<Student> targetStudentList = new ArrayList<>();
        List<Student> editedStudentList = new ArrayList<>();
        for (String str : studentsToAdd) {
            if (str.length() != 1) {
                throw new CommandException(String
                        .format(MESSAGE_INVALID_COMMAND_FORMAT, AddStudentToTrainingCommand.MESSAGE_USAGE));
            }

            if (!StringUtil.isNonZeroUnsignedInteger(str)) {
                throw new CommandException(String
                        .format(MESSAGE_INVALID_COMMAND_FORMAT, AddStudentToTrainingCommand.MESSAGE_USAGE));
            }

            if (Integer.parseInt(str) > studentList.size() || Integer.parseInt(str) <= 0) {
                throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
            }

            Student studentToEdit = studentList.get(Integer.parseInt(str) - 1);
            Student editedStudent = createEditedStudent(studentToEdit, editedTraining);

            if (!uniqueChecker(editedTraining, studentToEdit)) {
                throw new CommandException(MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
            }

            //Ensures student is available to attend training based on dismissal time
            if (!studentToEdit.isAvailableAtDateTime(editedTraining.getDateTime())
                    || studentToEdit.hasAttendanceAtDateTime(editedTraining.getDateTime())) {
                throw new CommandException(MESSAGE_STUDENT_UNAVAILABLE);
            }

            /*
            Add students that have passed all checks to the corresponding lists first without
            modifying the model.
             */
            editedTraining.addStudent(editedStudent);
            targetStudentList.add(studentToEdit);
            editedStudentList.add(editedStudent);
        }

        //All checked passed at this point, iterate through student lists to update the model.
        for (int i = 0; i < targetStudentList.size(); i++) {
            model.setStudentInUniqueStudentList(targetStudentList.get(i), editedStudentList.get(i));
        }

        model.setTraining(trainingToEdit, editedTraining);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);

        String result = this.getStudentsAdded();

        return new CommandResult(String.format(MESSAGE_ADD_STUDENT_SUCCESS, result));
    }

    /**
     * Checks that the Training Specified does not contain the Student to Add.
     * @param trainingToCheck
     * @param check Student to check.
     * @return boolean that indicates whether Student to be added is unique.
     * @throws CommandException
     */
    public boolean uniqueChecker(Training trainingToCheck, Student check) throws CommandException {
        for (Student student : trainingToCheck.getStudents()) {
            if (student.getId().equals(check.getId())) {
                throw new CommandException(MESSAGE_DUPLICATE_STUDENTS_IN_TRAINING);
            }
        }
        return true;
    }

    private static Student createEditedStudent(Student studentToEdit, Training editedTraining) {
        assert studentToEdit != null;

        Name updatedName = studentToEdit.getName();
        Phone updatedPhone = studentToEdit.getPhone();
        Email updatedEmail = studentToEdit.getEmail();
        AcademicYear updatedAcademicYear = studentToEdit.getAcademicYear();
        Day mondayDismissal = studentToEdit.getMondayDismissal();
        Day tuesdayDismissal = studentToEdit.getTuesdayDismissal();
        Day wednesdayDismissal = studentToEdit.getWednesdayDismissal();
        Day thursdayDismissal = studentToEdit.getThursdayDismissal();
        Day fridayDismissal = studentToEdit.getFridayDismissal();
        Set<Tag> updatedTags = studentToEdit.getTags();
        List<Attend> trainingAttendances = studentToEdit.getTrainingAttendances().stream()
                .collect(Collectors.toList());
        trainingAttendances.add(new Attend(editedTraining.getDateTime()));
        Id id = studentToEdit.getId();

        Student newStudent = new Student(updatedName, updatedPhone, updatedEmail, updatedAcademicYear, updatedTags,
                mondayDismissal, tuesdayDismissal, wednesdayDismissal, thursdayDismissal, fridayDismissal, id);
        newStudent.addAllAttendances(trainingAttendances);
        return newStudent;
    }

    /**
     * Returns a String with the IDs of the students added, removing duplicate IDs.
     * @return String with Unique Ids of Students added.
     */
    public String getStudentsAdded() {
        String result = "";
        for (String str : studentsToAdd) {
            if (result.contains(str)) {
                continue;
            }
            result += str + " ";
        }
        result = result.trim();
        return result;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddStudentToTrainingCommand)) {
            return false;
        }

        // state check
        AddStudentToTrainingCommand e = (AddStudentToTrainingCommand) other;
        return index.equals(e.index)
                && studentsToAdd.equals(e.studentsToAdd);
    }

}
