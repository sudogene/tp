package seedu.canoe.model.student;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that the searched parameters match all the predicates in the predicate list.
 * If the predicate list is empty, it defaults to false.
 */
public class AllMatchPredicateList implements Predicate<Student> {
    private final List<Predicate<Student>> predicates;

    public AllMatchPredicateList(List<Predicate<Student>> predicates) {
        this.predicates = predicates;
    }

    public AllMatchPredicateList() {
        predicates = new LinkedList<>();
    }

    @SafeVarargs
    public static AllMatchPredicateList of(Predicate<Student>... predicates) {
        return new AllMatchPredicateList(Arrays.asList(predicates));
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
                || (other instanceof AllMatchPredicateList // instanceof handles nulls
                && predicates.equals(((AllMatchPredicateList) other).predicates)); // state check
    }

}
