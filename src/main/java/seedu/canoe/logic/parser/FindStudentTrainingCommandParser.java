package seedu.canoe.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.canoe.logic.parser.CliSyntax.PREFIX_ID;

import java.util.logging.Logger;

import seedu.canoe.commons.core.LogsCenter;
import seedu.canoe.logic.commands.FindStudentTrainingCommand;
import seedu.canoe.logic.parser.exceptions.ParseException;
import seedu.canoe.model.student.Id;
import seedu.canoe.model.student.IdMatchesPredicate;
import seedu.canoe.model.student.TrainingMatchesIdPredicate;

/**
 * Parses input arguments and creates a new FindStudentTrainingCommand object
 */
public class FindStudentTrainingCommandParser implements Parser<FindStudentTrainingCommand> {

    private static final Logger logger = LogsCenter.getLogger(FindStudentTrainingCommand.class);

    /**
     * Parses the given {@code String} of arguments in the context of the FindStudentTrainingCommand
     * and returns an FindStudentTrainingCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindStudentTrainingCommand parse(String args) throws ParseException {
        logger.info("=============================[ Parsing FindStudentTrainingCommand ]===========================");
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ID);

        if (argMultimap.getValue(PREFIX_ID).isPresent()) {
            String idValue = argMultimap.getValue(PREFIX_ID).get();
            if (idValue.equals("")) {
                logger.warning("No students specified in the command argument!" + args);
                throw new ParseException(FindStudentTrainingCommand.MESSAGE_NO_STUDENT_QUERY);
            }
            if (!idValue.matches("\\d+")) {
                logger.warning("Only one student should be specified in the command argument!" + args);
                throw new ParseException(FindStudentTrainingCommand.MESSAGE_ONE_STUDENT_QUERY);
            }

            Id id = new Id(idValue);
            IdMatchesPredicate studentIdMatchPredicate = new IdMatchesPredicate(idValue);
            TrainingMatchesIdPredicate trainingMatchPredicate = new TrainingMatchesIdPredicate(id);
            return new FindStudentTrainingCommand(studentIdMatchPredicate, trainingMatchPredicate);

        }
        logger.warning("No students specified in the command!" + args);
        throw new ParseException(FindStudentTrainingCommand.MESSAGE_NO_STUDENT_QUERY);
    }
}
