package seedu.canoe.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.canoe.logic.parser.CliSyntax.PREFIX_ID;

import java.util.logging.Logger;

import seedu.canoe.commons.core.LogsCenter;
import seedu.canoe.logic.commands.FindAllTrainingCommand;
import seedu.canoe.logic.parser.exceptions.ParseException;
import seedu.canoe.model.student.Id;
import seedu.canoe.model.student.IdMatchesPredicate;
import seedu.canoe.model.student.TrainingMatchesIdPredicate;

/**
 * Parses input arguments and creates a new FindAllTrainingCommand object
 */
public class FindAllTrainingCommandParser implements Parser<FindAllTrainingCommand> {

    private static final Logger logger = LogsCenter.getLogger(FindAllTrainingCommand.class);

    /**
     * Parses the given {@code String} of arguments in the context of the FindAllTrainingCommand
     * and returns an FindAllTrainingCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindAllTrainingCommand parse(String args) throws ParseException {
        logger.info("=============================[ Parsing FindAllTrainingCommand ]===========================");
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ID);

        if (argMultimap.getValue(PREFIX_ID).isPresent()) {
            String idValue = argMultimap.getValue(PREFIX_ID).get();
            if (!idValue.matches("\\d+")){
                throw new ParseException(FindAllTrainingCommand.MESSAGE_ONE_STUDENT_QUERY);
            }
            if (!idValue.equals("")) {
                Id id = new Id(idValue);
                IdMatchesPredicate studentIdMatchPredicate = new IdMatchesPredicate(idValue);
                TrainingMatchesIdPredicate trainingMatchPredicate = new TrainingMatchesIdPredicate(id);
                return new FindAllTrainingCommand(studentIdMatchPredicate, trainingMatchPredicate);
            }
            logger.warning("No students specified in the command argument!" + args);
            throw new ParseException(FindAllTrainingCommand.MESSAGE_NO_STUDENT_QUERY);
        }
        logger.warning("No students specified in the command!" + args);
        throw new ParseException(FindAllTrainingCommand.MESSAGE_NO_STUDENT_QUERY);
    }
}
