package seedu.canoe.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.canoe.commons.core.Messages;
import seedu.canoe.logic.commands.exceptions.CommandException;
import seedu.canoe.model.Model;
import seedu.canoe.model.student.IdMatchesPredicate;
import seedu.canoe.model.student.TrainingMatchesIdPredicate;

/**
 * Finds and lists all trainings in canoe book which match the student id specified.
 */
public class FindAllTrainingCommand extends Command {

    public static final String COMMAND_WORD = "find-training";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all trainings whose ID matches "
            + "the specified ID and displays them as a list.\n"
            + "Parameters: STUDENT_INDEX \n"
            + "Example: " + COMMAND_WORD + " id/1";

    public static final String MESSAGE_NO_STUDENT_QUERY = "A student ID is required to find a student's "
            + "trainings.";
    public static final String MESSAGE_ONE_STUDENT_QUERY = "Only ONE student ID without extra characters should be "
            + "provided to find a student's trainings.";

    private final IdMatchesPredicate studentPredicates;
    private final TrainingMatchesIdPredicate trainingPredicates;

    /**
     * Creates an FindTrainingCommand to find all the specified {@code Student}'s trainings.
     */
    public FindAllTrainingCommand(IdMatchesPredicate studentPredicates, TrainingMatchesIdPredicate trainingPredicates) {
        this.studentPredicates = studentPredicates;
        this.trainingPredicates = trainingPredicates;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.updateFilteredStudentList(studentPredicates);
        model.updateFilteredTrainingList(trainingPredicates);

        if (model.getFilteredStudentList().size() < 1) {
            throw new CommandException("Please provide a student index that exists in the student list!");
        }

        //Make sure ONLY one student in the filtered student list
        assert model.getFilteredStudentList().size() <= 1;

        return new CommandResult(
                String.format(Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW,
                        model.getFilteredStudentList().size()) + "\n"
                        + String.format(Messages.MESSAGE_TRAININGS_LISTED_OVERVIEW,
                        model.getFilteredTrainingList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindAllTrainingCommand // instanceof handles nulls
                && studentPredicates.equals(((FindAllTrainingCommand) other).studentPredicates)
                && trainingPredicates.equals(((FindAllTrainingCommand) other).trainingPredicates)); // state check
    }
}
