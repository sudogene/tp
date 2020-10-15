package seedu.canoe.model.student.time;

import java.time.LocalTime;
import java.util.function.Predicate;

import seedu.canoe.model.student.Student;

/**
 * Tests that a {@code Student}'s dismissal time for Friday is before the query time.
 */
public class FridayDismissalPredicate implements Predicate<Student> {
    private final LocalTime queryTime;

    public FridayDismissalPredicate(LocalTime queryTime) {
        this.queryTime = queryTime;
    }

    @Override
    public boolean test(Student student) {
        LocalTime studentTime = student.getFridayDismissal().dismissalTime;
        return studentTime.equals(queryTime) || studentTime.isBefore(queryTime);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FridayDismissalPredicate // instanceof handles nulls
                && queryTime.equals(((FridayDismissalPredicate) other).queryTime)); // state check
    }
}
