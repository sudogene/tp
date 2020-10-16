package seedu.canoe.model.student;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that the searched parameters match any of the predicates in the predicate list.
 * If the predicate list is empty, it defaults to false.
 */
public class AnyMatchPredicateList implements Predicate<Student> {
    private final List<Predicate<Student>> predicates;

    public AnyMatchPredicateList(List<Predicate<Student>> predicates) {
        this.predicates = predicates;
    }

    public AnyMatchPredicateList() {
        predicates = new LinkedList<>();
    }

    @SafeVarargs
    public static AnyMatchPredicateList of(Predicate<Student>... predicates) {
        return new AnyMatchPredicateList(Arrays.asList(predicates));
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
                .anyMatch(p -> p.test(student));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AnyMatchPredicateList // instanceof handles nulls
                && predicates.equals(((AnyMatchPredicateList) other).predicates)); // state check
    }

}
