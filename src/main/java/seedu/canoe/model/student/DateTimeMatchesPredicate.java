package seedu.canoe.model.student;

import java.time.LocalDateTime;
import java.util.function.Predicate;

/**
 * Tests that a {@code Student}'s training schedules contain the training with the specific date time.
 */
public class DateTimeMatchesPredicate implements Predicate<Student> {
    private final LocalDateTime dateTime;

    public DateTimeMatchesPredicate(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public boolean test(Student student) {
        return student.getTrainingAttendances().stream().map(attendance -> attendance.getTrainingTime())
                .anyMatch(dateTime -> dateTime.isEqual(this.dateTime));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DateTimeMatchesPredicate // instanceof handles nulls
                && dateTime.equals(((DateTimeMatchesPredicate) other).dateTime)); // state check
    }

}
