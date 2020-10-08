package seedu.address.logic.parser;

import seedu.address.logic.commands.TrainingCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import java.time.LocalDateTime;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class TrainingCommandParser implements Parser<TrainingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the TrainingCommand
     * and returns a TrainingCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public TrainingCommand parse(String args) throws ParseException {
        try {
            LocalDateTime trainingTime = ParserUtil.parseTraining(args).getDateTime();
            return new TrainingCommand(trainingTime);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, TrainingCommand.MESSAGE_USAGE), pe);
        }
    }
}
