package seedu.canoe.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.canoe.logic.parser.CliSyntax.PREFIX_ACADEMIC_YEAR;
import static seedu.canoe.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import seedu.canoe.commons.core.LogsCenter;
import seedu.canoe.logic.commands.CommonTimeCommand;
import seedu.canoe.logic.parser.exceptions.ParseException;
import seedu.canoe.model.student.AcademicYear;
import seedu.canoe.model.student.AcademicYearMatchesPredicate;
import seedu.canoe.model.student.AnyMatchPredicateList;
import seedu.canoe.model.student.NameContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a CommonTimeCommand object
 */
public class CommonTimeCommandParser implements Parser<CommonTimeCommand> {

    private static final Logger LOGGER = LogsCenter.getLogger(CommonTimeCommandParser.class);

    /**
     * Parses the given {@code String} of arguments in the context of the CommonTimeCommand
     * and returns a CommonTimeCommand object for execution.
     */
    public CommonTimeCommand parse(String args) throws ParseException {
        LOGGER.info("=============================[ Parsing CommonTimeCommand ]===========================");
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ACADEMIC_YEAR);

        boolean checkEmptyString = false;

        AnyMatchPredicateList predicates = new AnyMatchPredicateList();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            String text = argMultimap.getValue(PREFIX_NAME).get();
            if (text.equals("")) {
                LOGGER.warning("No keyword found after name prefix!" + text);
                checkEmptyString = true;
            } else {
                predicates.add(new NameContainsKeywordsPredicate(getKeywordsFromString(text)));
            }
        }

        if (argMultimap.getValue(PREFIX_ACADEMIC_YEAR).isPresent()) {
            String academicYearValue = argMultimap.getValue(PREFIX_ACADEMIC_YEAR).get();
            if (!academicYearValue.equals("")) {
                LOGGER.warning("No keyword found after academic year prefix!");
                checkEmptyString = false;
                AcademicYear year = ParserUtil.parseAcademicYear(academicYearValue);
                predicates.add(new AcademicYearMatchesPredicate(year));
            }
        }

        if (predicates.isEmpty() || checkEmptyString) {
            LOGGER.warning("No prefixes found in the command input!" + args);
            throw new ParseException(CommonTimeCommand.MESSAGE_NO_QUERY);
        }

        return new CommonTimeCommand(predicates);
    }

    private List<String> getKeywordsFromString(String str) {
        return Arrays.asList(str.split(" "));
    }
}
