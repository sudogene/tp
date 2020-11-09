package seedu.canoe.model.training;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.canoe.testutil.TrainingBuilder;

public class TrainingMatchesDateTimePredicateTest {

    @Test
    public void equals() {
        LocalDateTime firstDateTime = LocalDateTime.parse("2021-08-26 1800",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        LocalDateTime secondDateTime = LocalDateTime.parse("2022-08-26 1800",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));

        TrainingMatchesDateTimePredicate firstPredicate = new TrainingMatchesDateTimePredicate(firstDateTime);
        TrainingMatchesDateTimePredicate secondPredicate = new TrainingMatchesDateTimePredicate(secondDateTime);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TrainingMatchesDateTimePredicate firstPredicateCopy = new TrainingMatchesDateTimePredicate(firstDateTime);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertNotNull(firstPredicate);

        // different idValue -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_trainingMatchesDateTime_returnsTrue() {
        LocalDateTime firstDateTime = LocalDateTime.parse("2021-08-26 1800",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        Training testTraining = new TrainingBuilder().withDateTime(firstDateTime).build();
        TrainingMatchesDateTimePredicate predicate = new TrainingMatchesDateTimePredicate(firstDateTime);
        assertTrue(predicate.test(testTraining));
    }

    @Test
    public void test_trainingDoesNotMatchDateTime_returnsFalse() {
        LocalDateTime firstDateTime = LocalDateTime.parse("2021-08-26 1800",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        LocalDateTime secondDateTime = LocalDateTime.parse("2022-08-26 1800",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));

        //non-matching dateTime
        TrainingMatchesDateTimePredicate predicate = new TrainingMatchesDateTimePredicate(firstDateTime);
        assertFalse(predicate.test(new TrainingBuilder().withDateTime(secondDateTime)
                .build()));
    }

}
