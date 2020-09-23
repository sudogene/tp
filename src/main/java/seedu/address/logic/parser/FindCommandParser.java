package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.logic.commands.FindCommand;
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
        String trimmedArgs = " " + args.trim();
        requireNonNull(trimmedArgs);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(trimmedArgs, PREFIX_NAME, PREFIX_PHONE);

        List<Predicate<Student>> predicates = new ArrayList<>();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            String words = argMultimap.getValue(PREFIX_NAME).get();
            List<String> nameKeywords = Arrays.asList(words.split(" "));
            predicates.add(new NameContainsKeywordsPredicate(nameKeywords));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            String phoneValue = argMultimap.getValue(PREFIX_PHONE).get();
            predicates.add(new PhoneMatchesPredicate(phoneValue));
        }
        return new FindCommand(new PredicateList(predicates));
    }
}
