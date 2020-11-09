package seedu.canoe.model.training;

import java.time.LocalDateTime;
import java.util.function.Predicate;

/**
 * Tests that a {@code Training} {@code dateTime} matches the dateTime given.
 */
public class TrainingMatchesDateTimePredicate implements Predicate<Training> {
    private final LocalDateTime dateTime;

    public TrainingMatchesDateTimePredicate(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public boolean test(Training training) {
        assert(training != null);
        return training.getDateTime().isEqual(dateTime);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TrainingMatchesDateTimePredicate // instanceof handles nulls
                && dateTime.equals(((TrainingMatchesDateTimePredicate) other).dateTime)); // state check
    }

}
