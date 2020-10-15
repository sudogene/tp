package seedu.canoe.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.canoe.testutil.StudentBuilder;

class EmailContainsKeywordPredicateTest {

    @Test
    void equals() {
        String firstKeyword = "meow";
        String secondKeyword = "woof";

        EmailContainsKeywordPredicate firstPredicate = new EmailContainsKeywordPredicate(firstKeyword);
        EmailContainsKeywordPredicate secondPredicate = new EmailContainsKeywordPredicate(secondKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        EmailContainsKeywordPredicate firstPredicateCopy = new EmailContainsKeywordPredicate(firstKeyword);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different keyword -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    void test_emailContainsKeyword_returnsTrue() {
        EmailContainsKeywordPredicate predicate = new EmailContainsKeywordPredicate("meow");

        // domain contains
        assertTrue(predicate.test(new StudentBuilder().withEmail("JohnDoe@meow.edu").build()));

        // local part contains
        assertTrue(predicate.test(new StudentBuilder().withEmail("meow@lolmail.com").build()));

        // substring matches
        assertTrue(predicate.test(new StudentBuilder().withEmail("usermeows@woof.com").build()));
    }

    @Test
    void test_emailDoesNotContainKeyword_returnsFalse() {
        EmailContainsKeywordPredicate predicate = new EmailContainsKeywordPredicate("0");
        assertFalse(predicate.test(new StudentBuilder().withEmail("JohnDoe@meow.edu").build()));
    }
}
