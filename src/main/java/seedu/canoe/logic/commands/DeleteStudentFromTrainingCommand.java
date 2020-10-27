package seedu.canoe.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.canoe.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.canoe.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.canoe.model.Model.PREDICATE_SHOW_ALL_STUDENTS;
import static seedu.canoe.model.Model.PREDICATE_SHOW_ALL_TRAININGS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import seedu.canoe.commons.core.LogsCenter;
import seedu.canoe.commons.core.Messages;
import seedu.canoe.commons.core.index.Index;
import seedu.canoe.logic.commands.exceptions.CommandException;
import seedu.canoe.model.Model;
import seedu.canoe.model.student.Attendance;
import seedu.canoe.model.student.Id;
import seedu.canoe.model.student.Student;
import seedu.canoe.model.training.Training;

/**
 * Deletes an existing student from a training.
 */
public class DeleteStudentFromTrainingCommand extends Command {
    public static final Logger LOGGER = LogsCenter.getLogger(DeleteStudentFromTrainingCommand.class);

    public static final String COMMAND_WORD = "ts-delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes the corresponding Students "
            + "from the specified Training Session\n"
            + "Parameters: Training_Session-ID " + PREFIX_ID + "Student_ID..."
            + "\nExample: "
            + COMMAND_WORD + "1 " + PREFIX_ID + "3,5,7";

    public static final String MESSAGE_INVALID_STUDENT = "One of the"
            + " Students provided is not inside of the training specified!";
    public static final String MESSAGE_DELETE_STUDENT_SUCCESS = "Deleted Student: %1$s";
    public static final String MESSAGE_STUDENT_DOES_NOT_EXIST = "One of the Ids "
            + "specified do not correspond to an existing Student!";
    public static final String MESSAGE_NO_STUDENTS_SPECIFIED = "At least one student to be deleted must be specified.";

    private final Index index;
    private final List<String> studentsToDelete;

    /**
     * @param index of the training in the filtered training list to delete
     * @param studentsToDelete corresponding Id of Students to delete
     */
    public DeleteStudentFromTrainingCommand(Index index, String[] studentsToDelete) {
        requireNonNull(index);
        requireNonNull(studentsToDelete);

        this.index = index;
        this.studentsToDelete = Arrays.asList(studentsToDelete);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        LOGGER.info("=============================[ Executing DeleteStudentFromTrainingCommand ]========"
                + "===================");
        requireNonNull(model);

        // Gets full unfiltered lists
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        model.updateFilteredTrainingList(PREDICATE_SHOW_ALL_TRAININGS);

        List<Training> trainingList = model.getFilteredTrainingList();

        // Checks if valid
        if (index.getZeroBased() >= trainingList.size()) {
            LOGGER.warning("Training index is invalid.");
            throw new CommandException(Messages.MESSAGE_INVALID_TRAINING_DISPLAYED_INDEX);
        }

        if (studentsToDelete.isEmpty()) {
            LOGGER.warning("No students specified.");
            throw new CommandException(MESSAGE_NO_STUDENTS_SPECIFIED);
        }

        Training training = trainingList.get(index.getZeroBased());
        Training editedTraining = training.cloneTraining();
        List<Student> deletedStudents = new ArrayList<>();

        for (String str : studentsToDelete) {

            // Checks if the student Id is valid.
            if (!Id.isValidId(str)) {
                LOGGER.warning("Student index is incorrect.");
                throw new CommandException(String
                        .format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteStudentFromTrainingCommand.MESSAGE_USAGE));
            }

            Student studentToEdit = getStudentWithId(model, str);

            if (!containStudentChecker(editedTraining, studentToEdit)) {
                throw new CommandException(MESSAGE_INVALID_STUDENT);
            }

            deleteStudentFromTraining(editedTraining, studentToEdit, model);
            deletedStudents.add(studentToEdit);
        }

        model.setTraining(training, editedTraining);

        return new CommandResult(String.format(MESSAGE_DELETE_STUDENT_SUCCESS,
                getStudentsMessage(deletedStudents))
                + " from Training Session " + index.getOneBased());
    }

    /**
     * Checks that the Training Specified contains the Student to be removed.
     */
    public boolean containStudentChecker(Training trainingToCheck, Student check) {
        return trainingToCheck.getStudents().stream()
                .anyMatch(student -> student.getId().equals(check.getId()));
    }

    /**
     * Returns the Student object in the model with the Id same as the specified unique Id.
     */
    public Student getStudentWithId(Model model, String id) throws CommandException {
        id = id.trim();
        Id idChecker = new Id(id);
        for (Student student : model.getFilteredStudentList()) {
            if (student.getId().equals(idChecker)) {
                return student;
            }
        }
        throw new CommandException(MESSAGE_STUDENT_DOES_NOT_EXIST);
    }

    private void deleteStudentFromTraining(Training training, Student student, Model model) {
        assert training != null && student != null && model != null;

        training.removeStudent(student);
        Student editedStudent = student.cloneStudent();
        editedStudent.removeAttendance(new Attendance(training.getDateTime()));
        model.setStudentInUniqueStudentList(student, editedStudent);
    }

    private String getStudentsMessage(List<Student> students) {
        return students.stream()
                .map(Student::getId)
                .map(Id::toString)
                .reduce((id1, id2) -> id1 + ", " + id2)
                .get();
    }

    /**
     * Return the index of the Training.
     * @return index of Training
     */
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
