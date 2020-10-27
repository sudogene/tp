package seedu.canoe.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;

import seedu.canoe.logic.commands.exceptions.CommandException;
import seedu.canoe.model.Model;
import seedu.canoe.model.training.Training;

public class TrainingCommand extends Command {

    public static final String COMMAND_WORD = "training";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates a Training Session "
            + "Parameters: Date and Time (yyyy-MM-dd HHmm) "
            + "Example: "
            + COMMAND_WORD
            + " 2020-10-20 1800";

    public static final String MESSAGE_SUCCESS_TRAINING = "New Training Session created at: %1$s";
    public static final String MESSAGE_DUPLICATE_TRAINING = "There "
            + "already exists a Training Session at this Date and Time";
    public static final String MESSAGE_PAST_TRAINING = "Trainings cannot be scheduled for dates that are past!";

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
        //Make sure all trainings are displayed on training panel
        model.updateFilteredTrainingList(Model.PREDICATE_SHOW_ALL_TRAININGS);

        if (getTraining().getDateTime().isBefore(LocalDateTime.now())) {
            throw new CommandException(MESSAGE_PAST_TRAINING);
        }

        if (model.hasTraining(getTraining())) {
            throw new CommandException(MESSAGE_DUPLICATE_TRAINING);
        }

        model.addTraining(getTraining());
        return new CommandResult(String.format(MESSAGE_SUCCESS_TRAINING, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TrainingCommand // instanceof handles nulls
                && getTraining().equals(((TrainingCommand) other).getTraining()));
    }
}
