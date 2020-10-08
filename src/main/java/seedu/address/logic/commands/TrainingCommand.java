package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Training;

public class TrainingCommand extends Command {
    public static final String COMMAND_WORD = "training";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates a Training Session"
            + "Parameters: Date and Time (dddd-MM-dd HHmm)"
            + "Example: "
            + COMMAND_WORD
            + "12-12-2000 1800";

    public static final String MESSAGE_SUCCESS = "New Training Session created at: %1$s";
    public static final String MESSAGE_DUPLICATE_TRAINING = "There "
            + "already exists a Training Session at this Date and Time";

    private final Training toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Student}
     */
    public TrainingCommand(LocalDateTime dateTime) {
        requireNonNull(dateTime);
        toAdd = new Training(dateTime);
    }

    public Training getTraining() {
        return this.toAdd;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasTraining(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TRAINING);
        }

        model.addTraining(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TrainingCommand // instanceof handles nulls
                && toAdd.equals(((TrainingCommand) other).toAdd));
    }
}
