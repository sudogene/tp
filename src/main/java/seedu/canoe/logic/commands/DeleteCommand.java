package seedu.canoe.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.canoe.model.Model.PREDICATE_SHOW_ALL_TRAININGS;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import seedu.canoe.commons.core.LogsCenter;
import seedu.canoe.commons.core.Messages;
import seedu.canoe.commons.core.index.Index;
import seedu.canoe.logic.commands.exceptions.CommandException;
import seedu.canoe.model.Model;
import seedu.canoe.model.student.Attendance;
import seedu.canoe.model.student.Student;
import seedu.canoe.model.training.Training;
import seedu.canoe.model.util.StudentTrainingSessionUtil;

/**
 * Deletes a student identified using it's displayed index from the canoe book.
 */
public class DeleteCommand extends Command {

    public static final Logger LOGGER = LogsCenter.getLogger(DeleteCommand.class);

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the student identified by the index number used in the displayed student list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_STUDENT_SUCCESS = "Deleted Student: %1$s";

    private final Index targetIndex;

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        LOGGER.info("=============================[ Executing DeleteCommand ]===========================");
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            LOGGER.warning("Index is invalid.");
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToDelete = lastShownList.get(targetIndex.getZeroBased());
        List<Attendance> trainingAttendances = new ArrayList<>(studentToDelete.getTrainingAttendances());
        List<Training> studentTrainings = StudentTrainingSessionUtil
                .getTrainingListFromTrainingAttendances(trainingAttendances, model);
        for (Training training: studentTrainings) {
            Training editedTraining = new Training(training.getDateTime(), training.getStudents());
            editedTraining.removeStudent(studentToDelete);
            model.setTraining(training, editedTraining);
        }

        model.deleteStudent(studentToDelete);
        model.updateFilteredTrainingList(PREDICATE_SHOW_ALL_TRAININGS);
        return new CommandResult(String.format(MESSAGE_DELETE_STUDENT_SUCCESS, studentToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }
}
