package seedu.canoe.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.canoe.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.canoe.logic.parser.CliSyntax.PREFIX_ID;

import java.util.List;

import seedu.canoe.commons.core.index.Index;
import seedu.canoe.logic.commands.AddStudentToTrainingCommand;
import seedu.canoe.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AddStudentToTrainingCommand object
 */
public class AddStudentCommandParser implements Parser<AddStudentToTrainingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddStudentToTrainingCommand
     * and returns an AddStudentToTrainingCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddStudentToTrainingCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_ID);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddStudentToTrainingCommand.MESSAGE_USAGE), pe);
        }

        String[] studentIndexes = new String[0];

        if (argMultimap.getValue(PREFIX_ID).isPresent()) {
            String text = argMultimap.getValue(PREFIX_ID).get().trim();
            if (text.isEmpty()) {
                throw new ParseException(ParserUtil.MESSAGE_NO_ID_PROVIDED);
            }
            studentIndexes = text.split(",");
        }

        if (studentIndexes.length == 0) {
            throw new ParseException(ParserUtil.MESSAGE_NO_ID_PROVIDED);
        }

        List<String> studentIds = ParserUtil.parseMultipleIds(studentIndexes);

        return new AddStudentToTrainingCommand(index, studentIds);
    }
}
