package seedu.canoe.logic.parser;

import java.time.LocalDateTime;

import seedu.canoe.logic.commands.TrainingCommand;
import seedu.canoe.logic.parser.exceptions.ParseException;

public class TrainingCommandParser implements Parser<TrainingCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the TrainingCommand
     * and returns a TrainingCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public TrainingCommand parse(String args) throws ParseException {
        if (args.trim().isEmpty()) {
            throw new ParseException("Datetime cannot be empty!");
        }

        LocalDateTime trainingTime = ParserUtil.parseTraining(args).getDateTime();
        return new TrainingCommand(trainingTime);
    }
}
