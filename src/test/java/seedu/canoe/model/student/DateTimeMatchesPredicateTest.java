package seedu.canoe.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.canoe.testutil.StudentBuilder;

public class DateTimeMatchesPredicateTest {

    @Test
    public void equals() {
        LocalDateTime firstDateTime = LocalDateTime.parse("2021-08-26 1800",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        LocalDateTime secondDateTime = LocalDateTime.parse("2022-08-26 1800",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));

        DateTimeMatchesPredicate firstPredicate = new DateTimeMatchesPredicate(firstDateTime);
        DateTimeMatchesPredicate secondPredicate = new DateTimeMatchesPredicate(secondDateTime);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        DateTimeMatchesPredicate firstPredicateCopy = new DateTimeMatchesPredicate(firstDateTime);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertNotNull(firstPredicate);

        // different idValue -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_studentContainDateTime_returnsTrue() {
        LocalDateTime firstDateTime = LocalDateTime.parse("2021-08-26 1800",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        Student testStudent = new StudentBuilder().withTrainingAttendances(new Attendance(firstDateTime)).build();
        DateTimeMatchesPredicate predicate = new DateTimeMatchesPredicate(firstDateTime);
        assertTrue(predicate.test(testStudent));
    }

    @Test
    public void test_studentDoesNotContainDateTime_returnsFalse() {
        LocalDateTime firstDateTime = LocalDateTime.parse("2021-08-26 1800",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        LocalDateTime secondDateTime = LocalDateTime.parse("2022-08-26 1800",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));

        //non-matching dateTime
        DateTimeMatchesPredicate predicate = new DateTimeMatchesPredicate(firstDateTime);
        assertFalse(predicate.test(new StudentBuilder()
                .withTrainingAttendances(new Attendance(secondDateTime)).build()));
    }

}
