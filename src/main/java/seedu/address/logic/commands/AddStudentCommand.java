package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Id;
import seedu.address.model.student.Student;
import seedu.address.model.student.Training;
import seedu.address.model.student.exceptions.DuplicateStudentException;

/**
 * Edits the details of an existing student in the address book.
 */
public class AddStudentCommand extends Command {

    public static final String COMMAND_WORD = "ts-add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds the corresponding Students "
            + " to the specified Training Session\n"
            + "PARAMETERS: TRAINING_SESSION-ID STUDENT_ID..."
            + "\nExample: "
            + COMMAND_WORD + " 1 3,5,7";

    public static final String MESSAGE_ADD_STUDENT_SUCCESS = "Added Student: %1$s";
    public static final String MESSAGE_NO_STUDENTS_SPECIFIED = "At least one student to be added must be specified.";
    public static final String MESSAGE_DUPLICATE_STUDENTS = "This Student is already in the Training Session!";

    private final Index index;
    private final String[] studentsToAdd;

    /**
     * @param index of the Training Session to add Students to.
     * @param studentsToAdd corresponding Id of Students to add.
     */
    public AddStudentCommand(Index index, String[] studentsToAdd) {
        requireNonNull(index);
        requireNonNull(studentsToAdd);

        this.index = index;
        this.studentsToAdd = studentsToAdd;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Training> lastShownList = model.getFilteredTrainingList();
        List<Student> studentList = model.getFilteredStudentList();

        if (studentsToAdd == null) {
            throw new CommandException(MESSAGE_NO_STUDENTS_SPECIFIED);
        }

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TRAINING_DISPLAYED_INDEX);
        }

        Training trainingToEdit = lastShownList.get(index.getZeroBased());
        Training editedTraining = new Training(trainingToEdit.getDateTime(), trainingToEdit.getStudents());

        List<Id> idList = new ArrayList<>();
        for (String str : studentsToAdd) {
            if (str.length() != 1) {
                throw new CommandException(String
                        .format(MESSAGE_INVALID_COMMAND_FORMAT, AddStudentCommand.MESSAGE_USAGE));
            }
            idList.add(new Id(str));
        }

        try {
            for (Student student : studentList) {
                if (idList.contains(student.getId()) && uniqueChecker(editedTraining, student)) {
                    editedTraining.addStudent(student);
                }
            }
        } catch (DuplicateStudentException e) {
            System.out.println("test");
            throw new CommandException(MESSAGE_DUPLICATE_STUDENTS);
        }

        model.setTraining(trainingToEdit, editedTraining);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);

        String result = this.getStudentsAdded();

        return new CommandResult(String.format(MESSAGE_ADD_STUDENT_SUCCESS, result));
    }

    /**
     * Checks that the Training Specified does not contain the Student to Add.
     * @param trainingToCheck
     * @param check
     * @return boolean that indicates whether Student to be added is unique.
     * @throws CommandException
     */
    public boolean uniqueChecker(Training trainingToCheck, Student check) throws CommandException {
        for (Student student : trainingToCheck.getStudents()) {
            if (student.getId().equals(check.getId())) {
                throw new CommandException(MESSAGE_DUPLICATE_STUDENTS);
            }
        }
        return true;
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
        if (!(other instanceof AddStudentCommand)) {
            return false;
        }

        // state check
        AddStudentCommand e = (AddStudentCommand) other;
        return index.equals(e.index)
                && studentsToAdd.equals(e.studentsToAdd);
    }

}
