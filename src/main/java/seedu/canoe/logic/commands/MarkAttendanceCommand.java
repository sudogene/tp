package seedu.canoe.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.canoe.commons.core.Messages.MESSAGE_STUDENTS_NOT_FOUND;
import static seedu.canoe.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.List;
import java.util.logging.Logger;

import seedu.canoe.commons.core.index.Index;
import seedu.canoe.commons.core.LogsCenter;
import seedu.canoe.model.Model;
import seedu.canoe.model.student.AnyMatchPredicateList;
import seedu.canoe.model.student.Attend;
import seedu.canoe.model.student.Student;
import seedu.canoe.model.student.Training;

public class MarkAttendanceCommand extends Command {

    public static final Logger logger = LogsCenter.getLogger(MarkAttendanceCommand.class);

    public static final String COMMAND_WORD = "attend";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Gets all students in the training session" +
            " whose Ids corresponds to the specified Ids and marks them as attended the training session.\n"
            + "Parameters: ID [MORE_IDS]...\n"
            + "Example: " + COMMAND_WORD + " 2 id/1,4,19";

    public static final String MESSAGE_MARK_AS_ATTENDED_SUCCESS = "Marked students as attended!";

    private final Index trainingIndex;
    private final AnyMatchPredicateList predicates;

    public MarkAttendanceCommand(Index trainingIndex, AnyMatchPredicateList predicates) {
        this.trainingIndex = trainingIndex;
        this.predicates = predicates;
    }

    @Override
    public CommandResult execute(Model model) {
        logger.info("=============================[ Executing MarkAttendanceCommand ]===========================");
        requireNonNull(model);
        model.updateFilteredStudentList(predicates);

        if (model.getFilteredStudentList().isEmpty()) {
            logger.warning("Ids match zero students.");
            return new CommandResult(MESSAGE_STUDENTS_NOT_FOUND);
        }

        List<Student> attendedStudents = model.getFilteredStudentList();
        List<Training> lastShownList = model.getFilteredTrainingList();
        Training training = lastShownList.get(trainingIndex.getZeroBased());
        Attend attendedTrainingSession = new Attend(training.getDateTime());
        attendedTrainingSession.attendsTraining();

        for (Student student : attendedStudents) {
            student.attendTrainingSession(attendedTrainingSession);
        }

        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(MESSAGE_MARK_AS_ATTENDED_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MarkAttendanceCommand // instanceof handles nulls
                && predicates.equals(((MarkAttendanceCommand) other).predicates)); // state check
    }
}
