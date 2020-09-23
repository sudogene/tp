package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.student.NameContainsKeywordsPredicate;
import seedu.address.model.student.PhoneMatchesPredicate;
import seedu.address.model.student.PredicateList;

public class FindCommandParserTest {

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand firstExpectedFindCommand =
                new FindCommand(new PredicateList(Arrays.asList(
                        new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")),
                        new PhoneMatchesPredicate("123456"))));
        assertParseSuccess(parser, "n/Alice Bob p/123456", firstExpectedFindCommand);

        // multiple whitespaces between keywords
        FindCommand secondExpectedFindCommand =
                new FindCommand(new PredicateList(Arrays.asList(
                        new NameContainsKeywordsPredicate(Arrays.asList("Alice")),
                        new PhoneMatchesPredicate("85355255"))));
        assertParseSuccess(parser, "  \n  \t n/Alice \t \n \n p/85355255", secondExpectedFindCommand);
    }

}
