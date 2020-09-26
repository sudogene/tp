package seedu.address.model.student.time;

import java.time.LocalTime;
import java.util.function.Predicate;

import seedu.address.model.student.Student;

/**
 * Tests that a {@code Student}'s dismissal time for Wednesday is before the query time.
 */
public class WednesdayDismissalPredicate implements Predicate<Student> {
    private final LocalTime queryTime;

    public WednesdayDismissalPredicate(LocalTime queryTime) {
        this.queryTime = queryTime;
    }

    @Override
    public boolean test(Student student) {
        return student.getWednesdayDismissal().dismissalTime.isBefore(queryTime);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof WednesdayDismissalPredicate // instanceof handles nulls
                && queryTime.equals(((WednesdayDismissalPredicate) other).queryTime)); // state check
    }
}
