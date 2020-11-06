package seedu.canoe.logic.parser;

import static seedu.canoe.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.canoe.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.canoe.logic.commands.CommonTimeCommand;
import seedu.canoe.model.student.AcademicYear;
import seedu.canoe.model.student.AcademicYearMatchesPredicate;
import seedu.canoe.model.student.AnyMatchPredicateList;
import seedu.canoe.model.student.NameContainsKeywordsPredicate;

class CommonTimeCommandParserTest {

    private CommonTimeCommandParser parser = new CommonTimeCommandParser();

    @Test
    public void parse_validArgs_returnsCommonTimeCommand() {
        // no leading and trailing whitespaces
        CommonTimeCommand firstExpectedCommonTimeCommand =
                new CommonTimeCommand(AnyMatchPredicateList.of(
                        new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")),
                        new AcademicYearMatchesPredicate(new AcademicYear("3"))
                ));
        assertParseSuccess(parser, " n/Alice Bob ay/3", firstExpectedCommonTimeCommand);

        // multiple whitespaces between keywords
        CommonTimeCommand secondExpectedCommonTimeCommand =
                new CommonTimeCommand(AnyMatchPredicateList.of(
                        new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")),
                        new AcademicYearMatchesPredicate(new AcademicYear("3"))
                ));
        assertParseSuccess(parser, "  \n  \t n/Alice Bob \t \n \n ay/3", secondExpectedCommonTimeCommand);
    }

    @Test
    public void parse_noQuery_failure() {
        assertParseFailure(parser, " ", CommonTimeCommand.MESSAGE_NO_QUERY);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        CommonTimeCommand expectedCommonTimeCommand = new CommonTimeCommand(AnyMatchPredicateList.of(
                new AcademicYearMatchesPredicate(new AcademicYear("3"))
        ));
        assertParseSuccess(parser, " ay/1 ay/2 ay/3", expectedCommonTimeCommand);
    }
}
