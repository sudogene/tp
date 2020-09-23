package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ACADEMIC_YEAR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.student.AcademicYearMatchesPredicate;
import seedu.address.model.student.EmailContainsKeywordPredicate;
import seedu.address.model.student.NameContainsKeywordsPredicate;
import seedu.address.model.student.PhoneMatchesPredicate;
import seedu.address.model.student.PredicateList;
import seedu.address.model.student.Student;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     */
    public FindCommand parse(String args) {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ACADEMIC_YEAR, PREFIX_PHONE, PREFIX_EMAIL);

        List<Predicate<Student>> predicates = new ArrayList<>();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            String rawText = argMultimap.getValue(PREFIX_NAME).get();
            List<String> nameKeywords = Arrays.asList(rawText.split(" "));
            predicates.add(new NameContainsKeywordsPredicate(nameKeywords));
        }
        if (argMultimap.getValue(PREFIX_ACADEMIC_YEAR).isPresent()) {
            String academicYearValue = argMultimap.getValue(PREFIX_ACADEMIC_YEAR).get();
            predicates.add(new AcademicYearMatchesPredicate(academicYearValue));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            String phoneValue = argMultimap.getValue(PREFIX_PHONE).get();
            predicates.add(new PhoneMatchesPredicate(phoneValue));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            String keyword = argMultimap.getValue(PREFIX_EMAIL).get();
            predicates.add(new EmailContainsKeywordPredicate(keyword));
        }
        return new FindCommand(new PredicateList(predicates));
    }
}
