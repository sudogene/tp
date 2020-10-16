package seedu.canoe.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.canoe.testutil.StudentBuilder;

class IdMatchesPredicateTest {
    @Test
    void equals() {
        String firstId = "1";
        String secondId = "2";

        IdMatchesPredicate firstPredicate = new IdMatchesPredicate(firstId);
        IdMatchesPredicate secondPredicate = new IdMatchesPredicate(secondId);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        IdMatchesPredicate firstPredicateCopy = new IdMatchesPredicate(firstId);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different academic year -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    void test_idMatches_returnsTrue() {
        IdMatchesPredicate predicate = new IdMatchesPredicate("3");
        assertTrue(predicate.test(new StudentBuilder().withId("3").build()));
    }

    @Test
    void test_idDoesNotMatch_returnsFalse() {
        IdMatchesPredicate predicate = new IdMatchesPredicate("3");
        assertFalse(predicate.test(new StudentBuilder().withId("1").build()));
        assertFalse(predicate.test(new StudentBuilder().withId("2").build()));
    }
}
