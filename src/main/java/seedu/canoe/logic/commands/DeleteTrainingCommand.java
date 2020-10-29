package seedu.canoe.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.canoe.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.List;
import java.util.Optional;

import seedu.canoe.commons.core.Messages;
import seedu.canoe.commons.core.index.Index;
import seedu.canoe.logic.commands.exceptions.CommandException;
import seedu.canoe.model.Model;
import seedu.canoe.model.student.Attendance;
import seedu.canoe.model.student.Id;
import seedu.canoe.model.student.Student;
import seedu.canoe.model.training.Training;

public class DeleteTrainingCommand extends Command {

    public static final String COMMAND_WORD = "delete-training";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the training session identified by the index number used in the displayed training list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Deleted Training: %1$s";

    private final Index targetIndex;

    /**
     * Creates a DeleteTrainingCommand to delete the specified {@code Training} based on the training index.
     */
    public DeleteTrainingCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);

        List<Training> lastShownList = model.getFilteredTrainingList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TRAINING_DISPLAYED_INDEX);
        }

        Training trainingToDelete = lastShownList.get(targetIndex.getZeroBased());

        // Deletes the training's date time from the students involved in that training
        List<Student> studentList = model.getFilteredStudentList();
        trainingToDelete.getStudents()
                .stream()
                .map(Student::getId)
                .forEach(id -> {
                    Student studentToEdit = getStudentById(studentList, id);
                    Student editedStudent = createEditedStudent(studentToEdit, trainingToDelete);
                    model.setStudentInUniqueStudentList(studentToEdit, editedStudent);
                });

        trainingToDelete.clearStudents();

        // Updating the model
        model.deleteTraining(trainingToDelete);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);

        return new CommandResult(String.format(MESSAGE_SUCCESS, trainingToDelete));
    }

    private Student createEditedStudent(Student studentToEdit, Training trainingToDelete) {
        requireNonNull(studentToEdit);
        Student newStudent = studentToEdit.cloneStudent();
        newStudent.removeAttendance(new Attendance(trainingToDelete.getDateTime()));
        return newStudent;
    }

    private Student getStudentById(List<Student> studentsList, Id id) {
        Optional<Student> filteredStudent = studentsList.stream()
                .filter(student -> student.getId().equals(id))
                .findFirst();

        assert filteredStudent.isPresent();

        return filteredStudent.get();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteTrainingCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteTrainingCommand) other).targetIndex));
    }

}
