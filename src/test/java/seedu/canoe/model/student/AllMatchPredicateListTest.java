package seedu.canoe.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.canoe.testutil.StudentBuilder;

class AllMatchPredicateListTest {

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstNamePredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        PhoneMatchesPredicate firstPhonePredicate = new PhoneMatchesPredicate(new Phone("123"));
        NameContainsKeywordsPredicate secondNamePredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));
        PhoneMatchesPredicate secondPhonePredicate = new PhoneMatchesPredicate(new Phone("321"));
        List<Predicate<Student>> firstListOfPredicates = Arrays.asList(firstNamePredicate, firstPhonePredicate);
        List<Predicate<Student>> secondListOfPredicates = Arrays.asList(secondNamePredicate, secondPhonePredicate);
        AllMatchPredicateList firstAllMatchPredicateList = new AllMatchPredicateList(firstListOfPredicates);
        AllMatchPredicateList secondAllMatchPredicateList = new AllMatchPredicateList(secondListOfPredicates);

        // same object -> returns true
        assertTrue(firstAllMatchPredicateList.equals(firstAllMatchPredicateList));

        // same values -> returns true
        AllMatchPredicateList firstAllMatchPredicateListCopy = new AllMatchPredicateList(firstListOfPredicates);
        assertTrue(firstAllMatchPredicateList.equals(firstAllMatchPredicateListCopy));

        // different types -> returns false
        assertFalse(firstAllMatchPredicateList.equals(1));

        // null -> returns false
        assertFalse(firstAllMatchPredicateList.equals(null));

        // different predicate list -> returns false
        assertFalse(firstAllMatchPredicateList.equals(secondAllMatchPredicateList));
    }

    @Test
    public void test_studentContainsNameKeywords_returnsTrue() {
        // One name keyword
        NameContainsKeywordsPredicate namePredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("Alice"));
        AllMatchPredicateList allMatchPredicateList = new AllMatchPredicateList(Arrays.asList(namePredicate));
        assertTrue(allMatchPredicateList.test(new StudentBuilder().withName("Alice Bob").build()));

        // Multiple name keywords
        namePredicate = new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        allMatchPredicateList = new AllMatchPredicateList(Arrays.asList(namePredicate));
        assertTrue(allMatchPredicateList.test(new StudentBuilder().withName("Alice Bob").build()));

        // Only one matching name keyword
        namePredicate = new NameContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        allMatchPredicateList = new AllMatchPredicateList(Arrays.asList(namePredicate));
        assertTrue(allMatchPredicateList.test(new StudentBuilder().withName("Alice Carol").build()));

        // Mixed-case name keywords
        namePredicate = new NameContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        allMatchPredicateList = new AllMatchPredicateList(Arrays.asList(namePredicate));
        assertTrue(allMatchPredicateList.test(new StudentBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainNameKeywords_returnsFalse() {
        // Zero keywords
        NameContainsKeywordsPredicate namePredicate = new NameContainsKeywordsPredicate(Collections.emptyList());
        AllMatchPredicateList allMatchPredicateList = new AllMatchPredicateList(Arrays.asList(namePredicate));
        assertFalse(allMatchPredicateList.test(new StudentBuilder().withName("Alice").build()));

        // Non-matching keyword
        namePredicate = new NameContainsKeywordsPredicate(Arrays.asList("Carol"));
        allMatchPredicateList = new AllMatchPredicateList(Arrays.asList(namePredicate));
        assertFalse(allMatchPredicateList.test(new StudentBuilder().withName("Alice Bob").build()));

        // Keywords match phone, email and canoe, but does not match name
        namePredicate = new NameContainsKeywordsPredicate(Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        allMatchPredicateList = new AllMatchPredicateList(Arrays.asList(namePredicate));
        assertFalse(allMatchPredicateList.test(new StudentBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").build()));
    }

    @Test
    public void test_studentContainsPhoneValue_returnsTrue() {
        PhoneMatchesPredicate phonePredicate = new PhoneMatchesPredicate(new Phone("123"));
        AllMatchPredicateList allMatchPredicateList = new AllMatchPredicateList(Arrays.asList(phonePredicate));
        assertTrue(allMatchPredicateList.test(new StudentBuilder().withPhone("123").build()));
    }

    @Test
    public void test_studentDoesNotContainPhoneValue_returnsFalse() {
        // Non-matching value
        PhoneMatchesPredicate phonePredicate = new PhoneMatchesPredicate(new Phone("123"));
        AllMatchPredicateList allMatchPredicateList = new AllMatchPredicateList(Arrays.asList(phonePredicate));
        assertFalse(allMatchPredicateList.test(new StudentBuilder().withPhone("321").build()));
    }

    @Test
    public void test_studentContainsNameKeywordAndPhoneValue_returnsTrue() {
        // One name keyword one phone value
        NameContainsKeywordsPredicate namePredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("Alice"));
        PhoneMatchesPredicate phonePredicate = new PhoneMatchesPredicate(new Phone("123"));
        AllMatchPredicateList allMatchPredicateList = new AllMatchPredicateList(
                Arrays.asList(namePredicate, phonePredicate)
        );
        assertTrue(allMatchPredicateList.test(new StudentBuilder().withName("Alice").withPhone("123").build()));

        // Multiple name keywords one phone value
        namePredicate = new NameContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        phonePredicate = new PhoneMatchesPredicate(new Phone("456789"));
        allMatchPredicateList = new AllMatchPredicateList(Arrays.asList(namePredicate, phonePredicate));
        assertTrue(allMatchPredicateList.test(new StudentBuilder().withName("Bob").withPhone("456789").build()));
    }

    @Test
    public void test_studentDoesNotContainNameKeywordOrPhoneValue_returnsFalse() {
        // Does not contain one name keyword, contains one phone value
        NameContainsKeywordsPredicate namePredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("Alice"));
        PhoneMatchesPredicate phonePredicate = new PhoneMatchesPredicate(new Phone("123"));
        AllMatchPredicateList allMatchPredicateList = new AllMatchPredicateList(
                Arrays.asList(namePredicate, phonePredicate)
        );
        assertFalse(allMatchPredicateList.test(new StudentBuilder().withName("Bob").withPhone("123").build()));

        // Does not contain multiple name keywords, contains one phone value
        namePredicate = new NameContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        phonePredicate = new PhoneMatchesPredicate(new Phone("456789"));
        allMatchPredicateList = new AllMatchPredicateList(Arrays.asList(namePredicate, phonePredicate));
        assertFalse(allMatchPredicateList.test(new StudentBuilder().withName("Alice").withPhone("456789").build()));

        // Contains one name keyword, does not contain phone value
        namePredicate = new NameContainsKeywordsPredicate(Collections.singletonList("Alice"));
        phonePredicate = new PhoneMatchesPredicate(new Phone("123"));
        allMatchPredicateList = new AllMatchPredicateList(Arrays.asList(namePredicate, phonePredicate));
        assertFalse(allMatchPredicateList.test(new StudentBuilder().withName("Alice").withPhone("456").build()));

        // Contains multiple name keywords, does not contain phone value
        namePredicate = new NameContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        phonePredicate = new PhoneMatchesPredicate(new Phone("456789"));
        allMatchPredicateList = new AllMatchPredicateList(Arrays.asList(namePredicate, phonePredicate));
        assertFalse(allMatchPredicateList.test(new StudentBuilder().withName("Carol").withPhone("123").build()));
    }

    @Test
    public void test_studentDoesNotContainNameKeywordAndPhoneValue_returnsFalse() {
        // One name keyword one phone value
        NameContainsKeywordsPredicate namePredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("Alice"));
        PhoneMatchesPredicate phonePredicate = new PhoneMatchesPredicate(new Phone("123"));
        AllMatchPredicateList allMatchPredicateList = new AllMatchPredicateList(
                Arrays.asList(namePredicate, phonePredicate)
        );
        assertFalse(allMatchPredicateList.test(new StudentBuilder().withName("Lisa").withPhone("456").build()));

        // Multiple name keywords one phone value
        namePredicate = new NameContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        phonePredicate = new PhoneMatchesPredicate(new Phone("456789"));
        allMatchPredicateList = new AllMatchPredicateList(Arrays.asList(namePredicate, phonePredicate));
        assertFalse(allMatchPredicateList.test(new StudentBuilder().withName("Boo").withPhone("123").build()));
    }

}
