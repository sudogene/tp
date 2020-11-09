package seedu.canoe.logic.parser;

import static seedu.canoe.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.canoe.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.canoe.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.canoe.testutil.TypicalIndexes.INDEX_FIRST_TRAINING;

import org.junit.jupiter.api.Test;

import seedu.canoe.logic.commands.AddAllStudentToTrainingCommand;

public class AddAllStudentCommandParserTest {

    private AddAllStudentCommandParser parser = new AddAllStudentCommandParser();

    @Test
    public void parseNoStudentsFailure() {
        assertParseFailure(parser, " ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        AddAllStudentToTrainingCommand.MESSAGE_USAGE));
    }

    @Test
    public void parseRepeatedIdFailure() {
        assertParseFailure(parser, "1 1 1 1 1",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        AddAllStudentToTrainingCommand.MESSAGE_USAGE));
        assertParseFailure(parser, "1,1,2,2,3,3,4,4,5,5 ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        AddAllStudentToTrainingCommand.MESSAGE_USAGE));
    }

    @Test
    public void parseSingleValidIdSuccess() {
        assertParseSuccess(parser, "1",
                new AddAllStudentToTrainingCommand(INDEX_FIRST_TRAINING));
    }
}
