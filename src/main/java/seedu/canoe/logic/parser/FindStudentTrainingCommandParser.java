package seedu.canoe.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.canoe.logic.parser.CliSyntax.PREFIX_DATETIME;
import static seedu.canoe.logic.parser.CliSyntax.PREFIX_ID;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.function.Predicate;
import java.util.logging.Logger;

import seedu.canoe.commons.core.LogsCenter;
import seedu.canoe.logic.commands.FindStudentTrainingCommand;
import seedu.canoe.logic.parser.exceptions.ParseException;
import seedu.canoe.model.student.DateTimeMatchesPredicate;
import seedu.canoe.model.student.Id;
import seedu.canoe.model.student.IdMatchesPredicate;
import seedu.canoe.model.student.Student;
import seedu.canoe.model.training.Training;
import seedu.canoe.model.training.TrainingMatchesDateTimePredicate;
import seedu.canoe.model.training.TrainingMatchesIdPredicate;

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
                ArgumentTokenizer.tokenize(args, PREFIX_ID, PREFIX_DATETIME);

        if (!(argMultimap.getValue(PREFIX_ID).isPresent() || argMultimap.getValue(PREFIX_DATETIME).isPresent())) {
            logger.warning("No params specified in arguments!" + args);
            throw new ParseException(FindStudentTrainingCommand.MESSAGE_NO_PARAM_QUERY);
        }
        Predicate<Student> studentPredicate = null;
        Predicate<Training> trainingPredicate = null;

        if (argMultimap.getValue(PREFIX_ID).isPresent()) {
            String idValue = argMultimap.getValue(PREFIX_ID).get();
            if (idValue.equals("")) {
                logger.warning("No students specified in the command argument!" + args);
                throw new ParseException(FindStudentTrainingCommand.MESSAGE_NO_STUDENT_QUERY);
            }
            if (!Id.isValidId(idValue)) {
                logger.warning("Formatting of Id is wrong!" + args);
                throw new ParseException(FindStudentTrainingCommand.MESSAGE_ONE_STUDENT_QUERY);
            }

            studentPredicate = new IdMatchesPredicate(idValue);
            trainingPredicate = new TrainingMatchesIdPredicate(idValue);
        }

        if (argMultimap.getValue(PREFIX_DATETIME).isPresent()) {
            String dateTimeValue = argMultimap.getValue(PREFIX_DATETIME).get();
            if (dateTimeValue.equals("")) {
                logger.warning("Training datetime specified in the command argument is empty!" + args);
                throw new ParseException(FindStudentTrainingCommand.MESSAGE_NO_DATETIME_QUERY);
            }
            try {
                LocalDateTime trainingTime = ParserUtil.parseTraining(dateTimeValue).getDateTime();
                studentPredicate = new DateTimeMatchesPredicate(trainingTime);
                trainingPredicate = new TrainingMatchesDateTimePredicate(trainingTime);
            } catch (DateTimeParseException e) {
                throw new ParseException(FindStudentTrainingCommand.MESSAGE_WRONG_DATETIME_FORMAT_QUERY);
            }
        }

        if (argMultimap.getValue(PREFIX_ID).isPresent() && argMultimap.getValue(PREFIX_DATETIME).isPresent()) {
            String idValue = argMultimap.getValue(PREFIX_ID).get();
            String dateTimeValue = argMultimap.getValue(PREFIX_DATETIME).get();
            try {
                LocalDateTime trainingTime = ParserUtil.parseTraining(dateTimeValue).getDateTime();
                studentPredicate = new IdMatchesPredicate(idValue);
                trainingPredicate = new TrainingMatchesDateTimePredicate(trainingTime);
            } catch (DateTimeParseException e) {
                throw new ParseException(FindStudentTrainingCommand.MESSAGE_WRONG_DATETIME_FORMAT_QUERY);
            }
        }

        assert(studentPredicate != null && trainingPredicate != null);

        return new FindStudentTrainingCommand(studentPredicate, trainingPredicate);
    }
}
