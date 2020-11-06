package seedu.canoe.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.canoe.commons.core.Messages.MESSAGE_DUPLICATE_STUDENTS_IN_TRAINING;
import static seedu.canoe.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.canoe.commons.core.Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX;
import static seedu.canoe.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.canoe.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Logger;

import seedu.canoe.commons.core.LogsCenter;
import seedu.canoe.commons.core.Messages;
import seedu.canoe.commons.core.index.Index;
import seedu.canoe.commons.util.StringUtil;
import seedu.canoe.logic.commands.exceptions.CommandException;
import seedu.canoe.model.Model;
import seedu.canoe.model.student.Attendance;
import seedu.canoe.model.student.Student;
import seedu.canoe.model.training.Training;

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

    public static final String MESSAGE_TRAINING_CANNOT_ADD = "No more students "
            + "can be added to this past training";
    public static final String MESSAGE_STUDENT_DOES_NOT_EXIST = "One of "
            + "the Ids specified do not correspond to an existing Student!";
    public static final String MESSAGE_ADD_STUDENT_SUCCESS = "Added Student: %1$s";
    public static final String MESSAGE_NO_STUDENTS_SPECIFIED = "At least one student "
            + "to be added must be specified.";
    public static final String MESSAGE_NO_STUDENTS = "There are no Students to add!";
    public static final String MESSAGE_STUDENT_UNAVAILABLE = "One of the students cannot be added to the training as "
            + "either his dismissal time on the specified day falls after the training's start time or he has a "
            + "training scheduled on the same date already!";
    private static final String MESSAGE_REPEATED_STUDENT = "One of the Ids is repeated!";
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
        assert (model != null);

        // Checks if students to delete are repeated
        if (!hasUniqueStudentsToAdd(Arrays.asList(studentsToAdd))) {
            LOGGER.warning("Repeated Id input detected.");
            throw new CommandException(MESSAGE_REPEATED_STUDENT);
        }

        List<Training> lastShownList = model.getFilteredTrainingList();
        List<Student> studentList = model.getFilteredStudentList();

        if (studentList.isEmpty()) {
            throw new CommandException(MESSAGE_NO_STUDENTS);
        }

        //Checks if the user specified any students to add
        if (studentsToAdd.length == 0) {
            LOGGER.warning("User input invalid");
            throw new CommandException(MESSAGE_NO_STUDENTS_SPECIFIED);
        }

        //Checks if the Index of Training provided is greater than number of Trainings.
        if (index.getZeroBased() >= lastShownList.size()) {
            LOGGER.warning("User input invalid");
            throw new CommandException(Messages.MESSAGE_INVALID_TRAINING_DISPLAYED_INDEX);
        }

        Training trainingToEdit = lastShownList.get(index.getZeroBased());

        // Check if training is a past-training
        if (!trainingToEdit.canAddStudent()) {
            throw new CommandException(MESSAGE_TRAINING_CANNOT_ADD);
        }

        Training editedTraining = new Training(trainingToEdit.getDateTime(), trainingToEdit.getStudents());

        //Student ID Checks - not invalid index, numbered index and exists in student list and not duplicated
        List<Student> targetStudentList = new ArrayList<>();
        List<Student> editedStudentList = new ArrayList<>();
        for (String str : studentsToAdd) {

            // Throws a CommandException if the Student ID is zero or negative.
            if (!StringUtil.isNonZeroUnsignedInteger(str)) {
                LOGGER.warning("Student index is incorrect.");
                throw new CommandException(String
                        .format(MESSAGE_INVALID_COMMAND_FORMAT, AddStudentToTrainingCommand.MESSAGE_USAGE));
            }

            Student studentToEdit = getStudentWithID(model, str);
            Student editedStudent = createEditedStudent(studentToEdit, editedTraining);

            //Ensures that Students to add are unique
            if (!uniqueChecker(editedTraining, studentToEdit)) {
                throw new CommandException(MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
            }

            //Ensures student is available to attend training based on dismissal time
            if (!isStudentAvailable(editedTraining, studentToEdit)) {
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
        assert (!result.isEmpty());

        return new CommandResult(String.format(MESSAGE_ADD_STUDENT_SUCCESS, result)
                + " to Training Session " + index.getOneBased());
    }

    /**
     * Checks that the Training Specified does not contain the Student to Add.
     * @param trainingToCheck
     * @param check Student to check
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

    /**
     * Checks if a given list of student Id strings contains repeating values.
     */
    public static boolean hasUniqueStudentsToAdd(List<String> studentsToAdd) {
        return new HashSet<>(studentsToAdd).size() == studentsToAdd.size();
    }

    /**
     * Checks if the whether the specified Student is available at the Date
     * and Time of the specified Training
     * and whether the Student already has an Attendance at that Date and Time
     * @param training Training to be attended
     * @param student Student to check
     * @return boolean that indicates whether Student to be added is available.
     */
    public boolean isStudentAvailable(Training training, Student student) {
        if (!student.isAvailableAtDateTime(training.getDateTime())
                || student.hasAttendanceAtDateTime(training.getDateTime())) {
            return false;
        }
        return true;
    }

    /**
     * Returns the Student object in the model with the Id same as the specified Unique Id.
     * @param model
     * @param id
     * @return the Student Object that corresponds to the specified Id.
     */
    public Student getStudentWithID(Model model, String id) throws CommandException {
        id = id.trim();
        for (Student student : model.getFilteredStudentList()) {
            if (student.getId().getValue().equals(id)) {
                return student;
            }
        }
        throw new CommandException(MESSAGE_STUDENT_DOES_NOT_EXIST);
    }

    /**
     * Adds the specified Attendance of the specified Training to the specified Student
     * @param studentToEdit the Student to be added to Training
     * @param editedTraining Training to be attended by student
     * @return the Student with a new Training Attendance added
     */
    private static Student createEditedStudent(Student studentToEdit, Training editedTraining) {
        assert studentToEdit != null;

        Student newStudent = studentToEdit.cloneStudent();
        newStudent.addAttendance(new Attendance(editedTraining.getDateTime()));
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
