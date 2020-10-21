package seedu.canoe.logic.parser;

import static seedu.canoe.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.canoe.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.canoe.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.canoe.testutil.TypicalIndexes.INDEX_FIRST_TRAINING;
import static seedu.canoe.testutil.TypicalIndexes.INDEX_SECOND_TRAINING;
import static seedu.canoe.testutil.TypicalIndexes.INDEX_THIRD_TRAINING;

import org.junit.jupiter.api.Test;

import seedu.canoe.logic.commands.MarkAttendanceCommand;
import seedu.canoe.model.student.AnyMatchPredicateList;
import seedu.canoe.model.student.IdMatchesPredicate;

class MarkAttendanceCommandParserTest {

    private MarkAttendanceCommandParser parser = new MarkAttendanceCommandParser();

    @Test
    public void parse_validArgs_returnsMarkAttendanceCommand() {
        MarkAttendanceCommand firstExpectedMarkAttendanceCommand =
                new MarkAttendanceCommand(INDEX_THIRD_TRAINING, AnyMatchPredicateList.of(
                        new IdMatchesPredicate("2"),
                        new IdMatchesPredicate("19")
                ));
        assertParseSuccess(parser, " 3 id/2,19", firstExpectedMarkAttendanceCommand);

        MarkAttendanceCommand secondExpectedMarkAttendanceCommand =
                new MarkAttendanceCommand(INDEX_FIRST_TRAINING, AnyMatchPredicateList.of(
                        new IdMatchesPredicate("5"),
                        new IdMatchesPredicate("26")
                ));
        assertParseSuccess(parser, "  \n  \t 1 \t \n \n id/5,26", secondExpectedMarkAttendanceCommand);
    }

    @Test
    public void parse_noQuery_failure() {
        assertParseFailure(parser, " ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                MarkAttendanceCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        MarkAttendanceCommand expectedMarkAttendanceCommand = new MarkAttendanceCommand(
                INDEX_SECOND_TRAINING,
                AnyMatchPredicateList.of(new IdMatchesPredicate("3")
        ));
        assertParseSuccess(parser, "2 id/11111111 id/22222222 id/3", expectedMarkAttendanceCommand);
    }
}
