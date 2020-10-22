package seedu.canoe.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.canoe.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.canoe.commons.core.index.Index;
import seedu.canoe.logic.commands.AddAllStudentToTrainingCommand;
import seedu.canoe.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AddAllStudentToTrainingCommand object
 */
public class AddAllStudentCommandParser implements Parser<AddAllStudentToTrainingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddAllStudentToTrainingCommand
     * and returns an AddAllStudentToTrainingCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddAllStudentToTrainingCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddAllStudentToTrainingCommand.MESSAGE_USAGE), pe);
        }

        return new AddAllStudentToTrainingCommand(index);
    }
}
