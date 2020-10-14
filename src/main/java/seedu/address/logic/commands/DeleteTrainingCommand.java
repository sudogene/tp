package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Training;

public class DeleteTrainingCommand extends Command {

    public static final String COMMAND_WORD = "delete-training";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the training session identified by the index number used in the displayed training list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Deleted Training: %1$s";

    private final Index targetIndex;

    /**
     * Creates an AddCommand to add the specified {@code Student}
     */
    public DeleteTrainingCommand(Index targetIndex) {
        requireNonNull(targetIndex);
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Training> lastShownList = model.getFilteredTrainingList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TRAINING_DISPLAYED_INDEX);
        }

        Training trainingToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteTraining(trainingToDelete);
        return new CommandResult(String.format(MESSAGE_SUCCESS, trainingToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteTrainingCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteTrainingCommand) other).targetIndex));
    }

}
