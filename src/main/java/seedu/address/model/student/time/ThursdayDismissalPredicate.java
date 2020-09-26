package seedu.address.model.student.time;

import java.time.LocalTime;
import java.util.function.Predicate;

import seedu.address.model.student.Student;

/**
 * Tests that a {@code Student}'s dismissal time for Thursday is before the query time.
 */
public class ThursdayDismissalPredicate implements Predicate<Student> {
    private final LocalTime queryTime;

    public ThursdayDismissalPredicate(LocalTime queryTime) {
        this.queryTime = queryTime;
    }

    @Override
    public boolean test(Student student) {
        return student.getThursdayDismissal().dismissalTime.isBefore(queryTime);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ThursdayDismissalPredicate // instanceof handles nulls
                && queryTime.equals(((ThursdayDismissalPredicate) other).queryTime)); // state check
    }
}
