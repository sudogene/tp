package seedu.canoe.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.canoe.commons.core.LogsCenter;
import seedu.canoe.commons.core.Messages;
import seedu.canoe.commons.core.index.Index;
import seedu.canoe.logic.commands.exceptions.CommandException;
import seedu.canoe.model.Model;
import seedu.canoe.model.student.Attendance;
import seedu.canoe.model.student.Student;
import seedu.canoe.model.training.Training;

public class AddAllStudentToTrainingCommand extends Command {

    public static final Logger LOGGER = LogsCenter.getLogger(AddAllStudentToTrainingCommand.class);

    public static final String COMMAND_WORD = "ts-addall";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds all students"
            + " from the current displayed student list to the specified training session\n"
            + "Parameters: Training_Session-ID"
            + "\nExample: "
            + COMMAND_WORD + " 1";

    public static final String MESSAGE_TRAINING_CANNOT_ADD = "No more students can be added to this training";
    public static final String MESSAGE_ADD_STUDENT_SUCCESS = "Added available students: %1$s";
    public static final String MESSAGE_NO_STUDENTS = "At least one student must be be available for the training.";

    private final Index index;

    /**
     * Creates an AddAllStudentToTrainingCommand to add all students to the specified {@code Training}
     */
    public AddAllStudentToTrainingCommand(Index index) {
        requireNonNull(index);
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        LOGGER.info("==========================[ Executing AddAllStudentToTrainingCommand ]==========================");

        requireNonNull(model);

        // Gets the current displayed lists
        List<Training> trainingList = model.getFilteredTrainingList();
        List<Student> studentList = model.getFilteredStudentList();

        // Checks if valid
        if (index.getZeroBased() >= trainingList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TRAINING_DISPLAYED_INDEX);
        }

        if (studentList.isEmpty()) {
            throw new CommandException(MESSAGE_NO_STUDENTS);
        }

        Training training = trainingList.get(index.getZeroBased());
        if (!training.canAddStudent()) {
            throw new CommandException(MESSAGE_TRAINING_CANNOT_ADD);
        }

        // Adding students to the training
        Training editedTraining = training.cloneTraining();
        List<Student> addedStudents = new ArrayList<>();

        studentList.stream()
                .filter(student -> isAbleToAddStudent(student, training))
                .forEach(student -> {
                    addStudentToTraining(editedTraining, student, model);
                    addedStudents.add(student);
                });

        Optional<String> addedStudentsMessage = CommandUtil.getStudentsMessage(addedStudents);
        if (addedStudentsMessage.isEmpty()) {
            throw new CommandException(MESSAGE_NO_STUDENTS);
        }

        model.setTraining(training, editedTraining);
        return new CommandResult(String.format(MESSAGE_ADD_STUDENT_SUCCESS,
                addedStudentsMessage.get()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddAllStudentToTrainingCommand // instanceof handles nulls
                && index.equals(((AddAllStudentToTrainingCommand) other).index)); // state check
    }

    /**
     * Checks if the {@code Training} is able to add the {@code Student}
     */
    public static boolean isAbleToAddStudent(Student student, Training training) {
        LocalDateTime trainingDateTime = training.getDateTime();
        return student.isAvailableAtDateTime(trainingDateTime)
                && !student.hasAttendanceAtDateTime(trainingDateTime)
                && !training.hasStudent(student);
    }

    private void addStudentToTraining(Training training, Student student, Model model) {
        assert training != null && student != null && model != null;

        training.addStudent(student);
        Student editedStudent = student.cloneStudent();
        editedStudent.addAttendance(new Attendance(training.getDateTime()));
        model.setStudentInUniqueStudentList(student, editedStudent);
    }
}
