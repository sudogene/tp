package seedu.canoe.model.student.time;

import java.time.LocalTime;
import java.util.function.Predicate;

import seedu.canoe.model.student.Student;

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
        LocalTime studentTime = student.getWednesdayDismissal().dismissalTime;
        return studentTime.equals(queryTime) || studentTime.isBefore(queryTime);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof WednesdayDismissalPredicate // instanceof handles nulls
                && queryTime.equals(((WednesdayDismissalPredicate) other).queryTime)); // state check
    }
}
