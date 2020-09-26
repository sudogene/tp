package seedu.address.model.student.time;

import java.time.LocalTime;
import java.util.function.Predicate;

import seedu.address.model.student.Student;

/**
 * Tests that a {@code Student}'s dismissal time for Tuesday is before the query time.
 */
public class TuesdayDismissalPredicate implements Predicate<Student> {
    private final LocalTime queryTime;

    public TuesdayDismissalPredicate(LocalTime queryTime) {
        this.queryTime = queryTime;
    }

    @Override
    public boolean test(Student student) {
        return student.getTuesdayDismissal().dismissalTime.isBefore(queryTime);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TuesdayDismissalPredicate // instanceof handles nulls
                && queryTime.equals(((TuesdayDismissalPredicate) other).queryTime)); // state check
    }
}
