package seedu.canoe.logic.parser;

import static seedu.canoe.logic.commands.CommandTestUtil.VALID_ID_LIST_2;
import static seedu.canoe.logic.commands.CommandTestUtil.VALID_ID_LIST_6;
import static seedu.canoe.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.canoe.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.canoe.testutil.TypicalIndexes.INDEX_FIRST_TRAINING;

import org.junit.jupiter.api.Test;

import seedu.canoe.logic.commands.DeleteStudentFromTrainingCommand;
import seedu.canoe.model.student.Id;

public class DeleteStudentFromTrainingCommandParserTest {

    private DeleteStudentFromTrainingCommandParser parser = new DeleteStudentFromTrainingCommandParser();

    @Test
    public void parseNoStudentsFailure() {
        assertParseFailure(parser, "1 id/", ParserUtil.MESSAGE_NO_ID_PROVIDED);
    }

    @Test
    public void parseNonNumericStudentIdFailure() {
        assertParseFailure(parser, "1 id/abc", Id.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parseRepeatedIdFailure() {
        assertParseFailure(parser, "1 id/1,1,1,1,1,2", ParserUtil.MESSAGE_REPEATED_ID);
    }

    @Test
    public void parseSingleValidIdSuccess() {
        assertParseSuccess(parser, "1 id/2",
                new DeleteStudentFromTrainingCommand(INDEX_FIRST_TRAINING, VALID_ID_LIST_2));
    }

    @Test
    public void parseMultipleValidIdSuccess() {
        assertParseSuccess(parser, "1 id/1,2,3",
                new DeleteStudentFromTrainingCommand(INDEX_FIRST_TRAINING, VALID_ID_LIST_6));
    }
}
