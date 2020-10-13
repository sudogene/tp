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

/**
 * Edits the details of an existing student in the address book.
 */
public class DeleteStudentFromTrainingCommand extends Command {

    public static final String COMMAND_WORD = "ts-delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes the corresponding Students "
            + "from the specified Training Session\n"
            + "PARAMETERS: TRAINING_SESSION-ID STUDENT_ID..."
            + "\nExample: "
            + COMMAND_WORD + " 1 3,5,7";

    public static final String MESSAGE_DELETE_STUDENT_SUCCESS = "Deleted Student: %1$s";
    public static final String MESSAGE_NO_STUDENTS_SPECIFIED = "At least one student to be deleted must be specified.";

    private final Index index;
    private final String[] studentsToDelete;

    /**
     * @param index of the training in the filtered training list to delete
     * @param studentsToDelete corresponding Id of Students to delete
     */
    public DeleteStudentFromTrainingCommand(Index index, String[] studentsToDelete) {
        requireNonNull(index);
        requireNonNull(studentsToDelete);

        this.index = index;
        this.studentsToDelete = studentsToDelete;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Training> lastShownList = model.getFilteredTrainingList();

        if (studentsToDelete == null) {
            throw new CommandException(MESSAGE_NO_STUDENTS_SPECIFIED);
        }

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TRAINING_DISPLAYED_INDEX);
        }

        Training trainingToEdit = lastShownList.get(index.getZeroBased());
        Training editedTraining = new Training(trainingToEdit.getDateTime(), trainingToEdit.getStudents());

        List<Id> idList = new ArrayList<>();
        for (String str : studentsToDelete) {
            if (str.length() != 1) {
                throw new CommandException(String
                        .format(MESSAGE_INVALID_COMMAND_FORMAT, AddStudentToTrainingCommand.MESSAGE_USAGE));
            }
            idList.add(new Id(str));
        }

        for (Student student : trainingToEdit.getStudents()) {
            if (idList.contains(student.getId())) {
                editedTraining.removeStudent(student);
            }
        }

        if (editedTraining.getStudents().size() == trainingToEdit.getStudents().size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        model.setTraining(trainingToEdit, editedTraining);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);

        String result = getStudentsDeleted();

        return new CommandResult(String.format(MESSAGE_DELETE_STUDENT_SUCCESS, result));
    }

    /**
     * Returns a String with the IDs of the students deleted, removing duplicate IDs.
     * @return String with Unique Ids of Students deleted.
     */
    public String getStudentsDeleted() {
        String result = "";
        for (String str : studentsToDelete) {
            if (result.contains(str)) {
                continue;
            }
            result += str + " ";
        }
        result = result.trim();
        return result;
    }

    public Index getIndex() {
        return this.index;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteStudentFromTrainingCommand)) {
            return false;
        }

        // state check
        DeleteStudentFromTrainingCommand e = (DeleteStudentFromTrainingCommand) other;
        return index.equals(e.index)
                && studentsToDelete.equals(e.studentsToDelete);
    }
}
