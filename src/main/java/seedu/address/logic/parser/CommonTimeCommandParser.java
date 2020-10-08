package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ACADEMIC_YEAR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.Arrays;
import java.util.List;

import seedu.address.logic.commands.CommonTimeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.AcademicYearMatchesPredicate;
import seedu.address.model.student.NameContainsKeywordsPredicate;
import seedu.address.model.student.PredicateList;

/**
 * Parses input arguments and creates a CommonTimeCommand object
 */
public class CommonTimeCommandParser implements Parser<CommonTimeCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the CommonTimeCommand
     * and returns a CommonTimeCommand object for execution.
     */
    public CommonTimeCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ACADEMIC_YEAR);

        PredicateList predicates = new PredicateList();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            String text = argMultimap.getValue(PREFIX_NAME).get();
            predicates.add(new NameContainsKeywordsPredicate(getKeywordsFromString(text)));
        }

        if (argMultimap.getValue(PREFIX_ACADEMIC_YEAR).isPresent()) {
            String academicYearValue = argMultimap.getValue(PREFIX_ACADEMIC_YEAR).get();
            predicates.add(new AcademicYearMatchesPredicate(academicYearValue));
        }

        if (predicates.isEmpty()) {
            throw new ParseException(CommonTimeCommand.MESSAGE_NO_QUERY);
        }

        return new CommonTimeCommand(predicates);
    }

    private List<String> getKeywordsFromString(String str) {
        return Arrays.asList(str.split(" "));
    }
}
