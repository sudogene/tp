package seedu.canoe.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.canoe.testutil.StudentBuilder;

class PhoneMatchesPredicateTest {

    @Test
    public void equals() {
        String firstPredicateValue = "123";
        String secondPredicateValue = "987";

        PhoneMatchesPredicate firstPredicate = new PhoneMatchesPredicate(new Phone(firstPredicateValue));
        PhoneMatchesPredicate secondPredicate = new PhoneMatchesPredicate(new Phone(secondPredicateValue));

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        PhoneMatchesPredicate firstPredicateCopy = new PhoneMatchesPredicate(new Phone(firstPredicateValue));
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different phone number -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_phoneValueContainsKeywords_returnsTrue() {
        PhoneMatchesPredicate predicate = new PhoneMatchesPredicate(new Phone("123456"));
        assertTrue(predicate.test(new StudentBuilder().withPhone("123456").build()));
    }

    @Test
    public void test_phoneValueDoesNotContainKeywords_returnsFalse() {
        // Non-matching keyword
        PhoneMatchesPredicate predicate = new PhoneMatchesPredicate(new Phone("123675678"));
        assertFalse(predicate.test(new StudentBuilder().withName("987654321").build()));

    }

}
