package seedu.canoe.model.training;

import java.util.function.Predicate;


/**
 * Tests that a {@code Training} {@code Student}'s {@code Id} matches the value given.
 */
public class TrainingMatchesIdPredicate implements Predicate<Training> {
    private final String idValue;

    public TrainingMatchesIdPredicate(String idValue) {
        this.idValue = idValue;
    }

    @Override
    public boolean test(Training training) {
        return training.hasStudentId(idValue);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TrainingMatchesIdPredicate // instanceof handles nulls
                && idValue.equals(((TrainingMatchesIdPredicate) other).idValue)); // state check
    }

}
