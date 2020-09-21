package seedu.address.model.student;

import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Student}'s {@code Name} matches any of the keywords given.
 */
public class PredicateList implements Predicate<Student> {
    private final List<Predicate<Student>> predicates;

    public PredicateList(List<Predicate<Student>> predicates) {
        this.predicates = predicates;
    }

    @Override
    public boolean test(Student student) {
        return predicates.stream()
                .allMatch(p -> p.test(student));
    }

    /*
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsKeywordsPredicate) other).keywords)); // state check
    }*/

}
