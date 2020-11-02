package seedu.canoe.logic.parser;

import static seedu.canoe.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.canoe.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.canoe.logic.commands.FindStudentTrainingCommand;
import seedu.canoe.logic.parser.exceptions.ParseException;
import seedu.canoe.model.student.DateTimeMatchesPredicate;
import seedu.canoe.model.student.IdMatchesPredicate;
import seedu.canoe.model.training.Training;
import seedu.canoe.model.training.TrainingMatchesDateTimePredicate;
import seedu.canoe.model.training.TrainingMatchesIdPredicate;

public class FindStudentTrainingCommandParserTest {

    private FindStudentTrainingCommandParser parser = new FindStudentTrainingCommandParser();

    @Test
    public void parse_validIdWithoutDateTime_returnsFindStudentTrainingCommand() {
        String firstIdValue = "1";
        IdMatchesPredicate firstStudentPredicate = new IdMatchesPredicate("1");
        TrainingMatchesIdPredicate firstTrainingPredicate = new TrainingMatchesIdPredicate(firstIdValue);

        // no leading and trailing whitespaces
        FindStudentTrainingCommand firstExpectedFindStudentTrainingCommand =
                new FindStudentTrainingCommand(firstStudentPredicate, firstTrainingPredicate);
        assertParseSuccess(parser, " id/1", firstExpectedFindStudentTrainingCommand);

        // multiple whitespaces between keywords
        FindStudentTrainingCommand secondExpectedFindStudentTrainingCommand =
                new FindStudentTrainingCommand(firstStudentPredicate, firstTrainingPredicate);
        assertParseSuccess(parser, "  \n  \t id/1 \t \n \n ", secondExpectedFindStudentTrainingCommand);
    }

    @Test
    public void parse_validIdWithDateTime_returnsFindStudentTrainingCommand() throws ParseException {
        LocalDateTime dateTime = ParserUtil.parseTraining("2021-08-26 1800").getDateTime();

        IdMatchesPredicate firstStudentPredicate = new IdMatchesPredicate("1");
        TrainingMatchesDateTimePredicate firstTrainingPredicate = new TrainingMatchesDateTimePredicate(dateTime);

        // no leading and trailing whitespaces
        FindStudentTrainingCommand firstExpectedFindStudentTrainingCommand =
                new FindStudentTrainingCommand(firstStudentPredicate, firstTrainingPredicate);
        assertParseSuccess(parser, " id/1 dt/2021-08-26 1800", firstExpectedFindStudentTrainingCommand);

        // multiple whitespaces between keywords
        FindStudentTrainingCommand secondExpectedFindStudentTrainingCommand =
                new FindStudentTrainingCommand(firstStudentPredicate, firstTrainingPredicate);
        assertParseSuccess(parser, "  \n  \t id/1 \t \n \n dt/2021-08-26 1800 \t ",
                secondExpectedFindStudentTrainingCommand);
    }

    @Test
    public void parse_validDateTimeWithoutId_returnsFindStudentTrainingCommand() throws ParseException {
        LocalDateTime dateTime = ParserUtil.parseTraining("2021-08-26 1800").getDateTime();

        DateTimeMatchesPredicate firstStudentPredicate = new DateTimeMatchesPredicate(dateTime);
        TrainingMatchesDateTimePredicate firstTrainingPredicate = new TrainingMatchesDateTimePredicate(dateTime);

        // no leading and trailing whitespaces
        FindStudentTrainingCommand firstExpectedFindStudentTrainingCommand =
                new FindStudentTrainingCommand(firstStudentPredicate, firstTrainingPredicate);
        assertParseSuccess(parser, " dt/2021-08-26 1800", firstExpectedFindStudentTrainingCommand);

        // multiple whitespaces between keywords
        FindStudentTrainingCommand secondExpectedFindStudentTrainingCommand =
                new FindStudentTrainingCommand(firstStudentPredicate, firstTrainingPredicate);
        assertParseSuccess(parser, "  \n  \t \t \n \n dt/2021-08-26 1800 \t ",
                secondExpectedFindStudentTrainingCommand);
    }

    @Test
    public void parse_noStudent_failure() {
        assertParseFailure(parser, " ", FindStudentTrainingCommand.MESSAGE_NO_PARAM_QUERY);
    }

    @Test
    public void parse_noStudent2_failure() {
        assertParseFailure(parser, " id/", FindStudentTrainingCommand.MESSAGE_NO_STUDENT_QUERY);
    }

    @Test
    public void parse_noDateTime_failure() {
        assertParseFailure(parser, " dt/", FindStudentTrainingCommand.MESSAGE_NO_DATETIME_QUERY);
    }

    @Test
    public void parse_wrongDateFormat_failure() {
        assertParseFailure(parser, " dt/2021-26-08",
                Training.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_wrongTimeFormat_failure() {
        assertParseFailure(parser, " dt/2021-26-08 18:00",
                Training.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_multipleStudent_failure() {
        assertParseFailure(parser, " id/1,2,3", FindStudentTrainingCommand.MESSAGE_ONE_STUDENT_QUERY);
    }

    @Test
    public void parse_multipleStudent2_failure() {
        assertParseFailure(parser, " id/1 2 3", FindStudentTrainingCommand.MESSAGE_ONE_STUDENT_QUERY);
    }

    @Test
    public void parse_multipleStudent3_failure() {
        assertParseFailure(parser, " id/1  \t 2 \t3", FindStudentTrainingCommand.MESSAGE_ONE_STUDENT_QUERY);
    }
}
