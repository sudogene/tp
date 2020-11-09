package seedu.canoe.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.canoe.model.CanoeCoach;
import seedu.canoe.model.Model;
import seedu.canoe.model.student.Id;

/**
 * Clears the canoe coach book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Canoe Coach book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setCanoeCoach(new CanoeCoach());
        Id.resetId();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
