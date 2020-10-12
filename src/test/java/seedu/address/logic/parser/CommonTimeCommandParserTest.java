package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommonTimeCommand;
import seedu.address.model.student.AcademicYearMatchesPredicate;
import seedu.address.model.student.PredicateList;

class CommonTimeCommandParserTest {

    private CommonTimeCommandParser parser = new CommonTimeCommandParser();

//    @Test
//    public void parse_validArgs_returnsCommonTimeCommand() {
//        NameContainsKeywordsPredicate namePredicate = new NameContainsKeywordsPredicate(Arrays.asList("Alice Kunz"));
//        AcademicYearMatchesPredicate academicYearPredicate = new AcademicYearMatchesPredicate("1");
//        // no leading and trailing whitespaces
//        CommonTimeCommand firstExpectedCommonTimeCommand =
//                new CommonTimeCommand(new PredicateList(Arrays.asList(namePredicate, academicYearPredicate)));
//                new CommonTimeCommand(PredicateList.of(
//                        new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")),
//                        new AcademicYearMatchesPredicate("5")
//                ));
//        assertParseSuccess(parser, " n/Alice Bob ay/5", firstExpectedCommonTimeCommand);
//
//        // multiple whitespaces between keywords
//        CommonTimeCommand secondExpectedCommonTimeCommand =
//                new CommonTimeCommand(PredicateList.of(
//                        new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")),
//                        new AcademicYearMatchesPredicate("85355255")
//                ));
//        assertParseSuccess(parser, "  \n  \t n/Alice Bob \t \n \n ay/85355255", secondExpectedCommonTimeCommand);
//    }

    @Test
    public void parse_noQuery_failure() {
        assertParseFailure(parser, " ", CommonTimeCommand.MESSAGE_NO_QUERY);
    }

    @Test
    public void parse_multipleRepeatedFields_acceptsLast() {
        CommonTimeCommand expectedCommonTimeCommand = new CommonTimeCommand(PredicateList.of(
                new AcademicYearMatchesPredicate("3")
        ));
        assertParseSuccess(parser, " ay/11111111 ay/22222222 ay/3", expectedCommonTimeCommand);
    }
}