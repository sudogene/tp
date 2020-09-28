package seedu.address.model.student;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Student}'s parameters match all the predicates in the predicate list.
 * If the predicate list is empty, it defaults to false.
 */
public class PredicateList implements Predicate<Student> {
    private final List<Predicate<Student>> predicates;

    public PredicateList(List<Predicate<Student>> predicates) {
        this.predicates = predicates;
    }

    public PredicateList() {
        predicates = new LinkedList<>();
    }

    @SafeVarargs
    public static PredicateList of(Predicate<Student>... predicates) {
        return new PredicateList(Arrays.asList(predicates));
    }

    public boolean add(Predicate<Student> predicate) {
        return predicates.add(predicate);
    }

    public boolean isEmpty() {
        return predicates.isEmpty();
    }

    @Override
    public boolean test(Student student) {
        if (predicates.isEmpty()) {
            return false;
        }
        return predicates.stream()
                .allMatch(p -> p.test(student));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PredicateList // instanceof handles nulls
                && predicates.equals(((PredicateList) other).predicates)); // state check
    }

}
