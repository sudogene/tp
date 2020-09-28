package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.StudentBuilder;

class AcademicYearMatchesPredicateTest {

    @Test
    void equals() {
        String firstYear = "1";
        String secondYear = "2";

        AcademicYearMatchesPredicate firstPredicate = new AcademicYearMatchesPredicate(firstYear);
        AcademicYearMatchesPredicate secondPredicate = new AcademicYearMatchesPredicate(secondYear);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        AcademicYearMatchesPredicate firstPredicateCopy = new AcademicYearMatchesPredicate(firstYear);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different academic year -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    void test_yearMatches_returnsTrue() {
        AcademicYearMatchesPredicate predicate = new AcademicYearMatchesPredicate("3");
        assertTrue(predicate.test(new StudentBuilder().withAcademicYear("3").build()));
    }

    @Test
    void test_yearDoesNotMatch_returnsFalse() {
        AcademicYearMatchesPredicate predicate = new AcademicYearMatchesPredicate("3");
        assertFalse(predicate.test(new StudentBuilder().withAcademicYear("1").build()));
        assertFalse(predicate.test(new StudentBuilder().withAcademicYear("2").build()));
    }
}
