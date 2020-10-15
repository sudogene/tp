package seedu.canoe.model.student;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Predicate;

/**
 * Tests that a {@code Student}'s {@code Name} matches any of the keywords given.
 */
public class TrainingMatchesPredicate implements Predicate<Training> {
    private final List<LocalDateTime> localDateTimes;

    public TrainingMatchesPredicate(List<LocalDateTime> localDateTimes) {
        this.localDateTimes = localDateTimes;
    }

    @Override
    public boolean test(Training training) {
        return localDateTimes.stream()
                .anyMatch(localDateTime -> localDateTime.equals(training.getDateTime()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TrainingMatchesPredicate // instanceof handles nulls
                && localDateTimes.equals(((TrainingMatchesPredicate) other).localDateTimes)); // state check
    }

}
