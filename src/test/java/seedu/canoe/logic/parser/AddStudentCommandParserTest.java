package seedu.canoe.logic.parser;

import static seedu.canoe.logic.commands.CommandTestUtil.VALID_ID_LIST_2;
import static seedu.canoe.logic.commands.CommandTestUtil.VALID_ID_LIST_6;
import static seedu.canoe.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.canoe.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.canoe.testutil.TypicalIndexes.INDEX_FIRST_TRAINING;

import org.junit.jupiter.api.Test;

import seedu.canoe.logic.commands.AddStudentToTrainingCommand;
import seedu.canoe.model.student.Id;

public class AddStudentCommandParserTest {
    private AddStudentCommandParser parser = new AddStudentCommandParser();

    @Test
    public void parseNoStudentsFailure() throws Exception {
        assertParseFailure(parser, "1 id/", ParserUtil.MESSAGE_NO_ID_PROVIDED);
    }

    @Test
    public void parseNonNumericStudentFailure() {
        assertParseFailure(parser, "1 id/abc", Id.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parseRepeatedStudentsFailure() {
        assertParseFailure(parser, "1 id/1,1,1,1,1,1", ParserUtil.MESSAGE_REPEATED_ID);
    }

    @Test
    public void parseSingleValidStudentSuccess() throws Exception {
        assertParseSuccess(parser, "1 id/2", new AddStudentToTrainingCommand(INDEX_FIRST_TRAINING, VALID_ID_LIST_2));
    }

    @Test
    public void parseMultipleValidStudentsSuccess() throws Exception {
        assertParseSuccess(parser, "1 id/1,2,3",
                new AddStudentToTrainingCommand(INDEX_FIRST_TRAINING, VALID_ID_LIST_6));
    }
}
