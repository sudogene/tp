package seedu.canoe.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.canoe.commons.core.Messages;
import seedu.canoe.logic.commands.exceptions.CommandException;
import seedu.canoe.model.Model;
import seedu.canoe.model.student.Student;
import seedu.canoe.model.training.Training;

/**
 * Finds and lists all trainings in canoe book which match the student id and/or datetime specified.
 */
public class FindStudentTrainingCommand extends Command {

    public static final String COMMAND_WORD = "find-training";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all students or trainings whose ID matches "
            + "the specified ID and/or training datetime matches the specified datetime and displays them as a list.\n"
            + "Parameters: [STUDENT_ID] [TRAINING_DATETIME] \n"
            + "Example: " + COMMAND_WORD + " id/1 dt/2021-08-26 1800";

    public static final String MESSAGE_NO_STUDENT_QUERY = "A student ID is required to find a student's "
            + "trainings.";
    public static final String MESSAGE_NO_DATETIME_QUERY = "Please input the training date-time!";
    public static final String MESSAGE_WRONG_DATETIME_FORMAT_QUERY = "Training datetime must be in yyyy-mm-dd HHmm "
            + "format! Only one datetime is allowed!";
    public static final String MESSAGE_NO_PARAM_QUERY = "A student ID and/or a training date-time is required to find "
            + "trainings.";
    public static final String MESSAGE_ONE_STUDENT_QUERY = "The input format for student ID is wrong. Please specify "
            + "only one numerical student ID.";
    public static final String MESSAGE_NO_MATCH =
            "There are no matches returned! Check your student ID or date-time and make sure they both exist.";


    private final Predicate<Student> studentPredicates;
    private final Predicate<Training> trainingPredicates;

    /**
     * Creates an FindStudentTrainingCommand to find all the specified {@code Student}'s trainings.
     */
    public FindStudentTrainingCommand(Predicate<Student> studentPredicates,
                                      Predicate<Training> trainingPredicates) {
        this.studentPredicates = studentPredicates;
        this.trainingPredicates = trainingPredicates;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredStudentList(studentPredicates);
        model.updateFilteredTrainingList(trainingPredicates);

        if (model.getFilteredStudentList().size() < 1 && model.getFilteredTrainingList().size() < 1) {
            throw new CommandException(MESSAGE_NO_MATCH);
        }

        return new CommandResult(
                String.format(Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW,
                        model.getFilteredStudentList().size()) + "\n"
                        + String.format(Messages.MESSAGE_TRAININGS_LISTED_OVERVIEW,
                        model.getFilteredTrainingList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindStudentTrainingCommand // instanceof handles nulls
                && studentPredicates.equals(((FindStudentTrainingCommand) other).studentPredicates)
                && trainingPredicates.equals(((FindStudentTrainingCommand) other).trainingPredicates)); // state check
    }
}
