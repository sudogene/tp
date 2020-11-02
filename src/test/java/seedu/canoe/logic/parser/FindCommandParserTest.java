package seedu.canoe.logic.parser;

import static seedu.canoe.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.canoe.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.canoe.logic.commands.FindCommand;
import seedu.canoe.model.student.AcademicYear;
import seedu.canoe.model.student.AcademicYearMatchesPredicate;
import seedu.canoe.model.student.AllMatchPredicateList;
import seedu.canoe.model.student.EmailContainsKeywordPredicate;
import seedu.canoe.model.student.IdMatchesPredicate;
import seedu.canoe.model.student.NameContainsKeywordsPredicate;
import seedu.canoe.model.student.Phone;
import seedu.canoe.model.student.PhoneMatchesPredicate;
import seedu.canoe.model.student.time.Day;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand firstExpectedFindCommand =
                new FindCommand(AllMatchPredicateList.of(
                        new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")),
                        new PhoneMatchesPredicate(new Phone("123456"))
                ));
        assertParseSuccess(parser, " n/Alice Bob p/123456", firstExpectedFindCommand);

        // multiple whitespaces between keywords
        FindCommand secondExpectedFindCommand =
                new FindCommand(AllMatchPredicateList.of(
                        new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")),
                        new PhoneMatchesPredicate(new Phone("85355255"))
                ));
        assertParseSuccess(parser, "  \n  \t n/Alice Bob \t \n \n p/85355255", secondExpectedFindCommand);

        // mixture of find parameters
        FindCommand thirdExpectedFindCommand =
                new FindCommand(AllMatchPredicateList.of(
                        new EmailContainsKeywordPredicate("meow"),
                        new AcademicYearMatchesPredicate(new AcademicYear("2")),
                        new IdMatchesPredicate("1")
                ));
        assertParseSuccess(parser, " e/meow ay/2 id/1", thirdExpectedFindCommand);
    }

    @Test
    public void parse_noQuery_failure() {
        assertParseFailure(parser, " ", FindCommand.MESSAGE_NO_QUERY);
    }

    @Test
    public void parse_invalidTime_failure() {
        assertParseFailure(parser, " d1/423", Day.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_validTimeFollowedByInvalidTime_failure() {
        assertParseFailure(parser, " d1/0800 d2/432", Day.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        FindCommand expectedFindCommand = new FindCommand(AllMatchPredicateList.of(
                new PhoneMatchesPredicate(new Phone("33333333"))
        ));
        assertParseSuccess(parser, " p/11111111 p/22222222 p/33333333", expectedFindCommand);
    }
}
