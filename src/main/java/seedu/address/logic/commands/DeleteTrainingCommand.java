package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Training;

import java.time.LocalDateTime;

import static java.util.Objects.requireNonNull;

public class DeleteTrainingCommand extends Command {

    public static final String COMMAND_WORD = "delete-training";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes the specified Training Session"
            + "Parameters: Date and Time (dddd-MM-dd HHmm)"
            + "Example: "
            + COMMAND_WORD
            + "12-12-2000 1800";

    public static final String MESSAGE_SUCCESS = "Training Session at: %1$s deleted";

    private final Training toDelete;

    /**
     * Creates an AddCommand to add the specified {@code Student}
     */
    public DeleteTrainingCommand(LocalDateTime dateTime) {
        requireNonNull(dateTime);
        toDelete = new Training(dateTime);
    }

    public Training getDeleteTraining() {
        return this.toDelete;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasTraining(toDelete)) {
            model.deleteTraining(toDelete);
        } else {
            System.out.println(model.getAddressBook().getTrainings().size());
            throw new CommandException("The specified Training Session does not exist");
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, toDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteTrainingCommand // instanceof handles nulls
                && toDelete.equals(((DeleteTrainingCommand) other).toDelete));
    }

}
