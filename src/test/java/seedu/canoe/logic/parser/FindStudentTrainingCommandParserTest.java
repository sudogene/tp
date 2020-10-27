package seedu.canoe.logic.parser;

import static seedu.canoe.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.canoe.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.canoe.logic.commands.FindStudentTrainingCommand;
import seedu.canoe.model.student.Id;
import seedu.canoe.model.student.IdMatchesPredicate;
import seedu.canoe.model.training.TrainingMatchesIdPredicate;

public class FindStudentTrainingCommandParserTest {

    private FindStudentTrainingCommandParser parser = new FindStudentTrainingCommandParser();

    @Test
    public void parse_validArgs_returnsFindStudentTrainingCommand() {
        Id firstIdValue = new Id("1");
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
    public void parse_noStudent_failure() {
        assertParseFailure(parser, " ", FindStudentTrainingCommand.MESSAGE_NO_STUDENT_QUERY);
    }

    @Test
    public void parse_noStudent2_failure() {
        assertParseFailure(parser, " id/", FindStudentTrainingCommand.MESSAGE_NO_STUDENT_QUERY);
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
