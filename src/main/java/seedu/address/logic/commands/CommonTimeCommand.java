package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;

public class CommonTimeCommand extends Command {

    public static final String COMMAND_WORD = "commonTime";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds a common time amongst all selected students "
            + "for each day and displays them as a list.\n"
            + "Parameters: STUDENT [MORE_STUDENTS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    public static final String MESSAGE_NO_QUERY = "At least one valid field is required to find a common time.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        return new CommandResult("");
    }
}
