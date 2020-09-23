package seedu.address.model.student;

import org.junit.jupiter.api.Test;
import seedu.address.testutil.StudentBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.*;

class PredicateListTest {

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstNamePredicate = new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        PhoneMatchesPredicate firstPhonePredicate = new PhoneMatchesPredicate("123");
        NameContainsKeywordsPredicate secondNamePredicate = new NameContainsKeywordsPredicate(Collections.singletonList("second"));
        PhoneMatchesPredicate secondPhonePredicate = new PhoneMatchesPredicate("321");
        List<Predicate<Student>> firstPredicateList = Arrays.asList(firstNamePredicate, firstPhonePredicate);
        List<Predicate<Student>> secondPredicateList = Arrays.asList(secondNamePredicate, secondPhonePredicate);

        // same object -> returns true
        assertTrue(firstPredicateList.equals(firstPredicateList));

        // same values -> returns true
        PredicateList firstPredicateListCopy = new PredicateList(firstPredicateList);
        assertTrue(firstPredicateList.equals(firstPredicateListCopy));

        // different types -> returns false
        assertFalse(firstPredicateList.equals(1));

        // null -> returns false
        assertFalse(firstPredicateList.equals(null));

        // different predicate list -> returns false
        assertFalse(firstPredicateList.equals(secondPredicateList));
    }

    @Test
    public void test_studentContainsNameKeywords_returnsTrue() {
        // One name keyword
        NameContainsKeywordsPredicate namePredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("Alice"));
        PredicateList predicateList = new PredicateList(Arrays.asList(namePredicate));
        assertTrue(predicateList.test(new StudentBuilder().withName("Alice Bob").build()));

        // Multiple name keywords
        namePredicate = new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"));
        predicateList = new PredicateList(Arrays.asList(namePredicate));
        assertTrue(predicateList.test(new StudentBuilder().withName("Alice Bob").build()));

        // Only one matching name keyword
        namePredicate = new NameContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        predicateList = new PredicateList(Arrays.asList(namePredicate));
        assertTrue(predicateList.test(new StudentBuilder().withName("Alice Carol").build()));

        // Mixed-case name keywords
        namePredicate = new NameContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"));
        predicateList = new PredicateList(Arrays.asList(namePredicate));
        assertTrue(predicateList.test(new StudentBuilder().withName("Alice Bob").build()));
    }

    @Test
    public void test_nameDoesNotContainNameKeywords_returnsFalse() {
        // Zero keywords
        NameContainsKeywordsPredicate namePredicate = new NameContainsKeywordsPredicate(Collections.emptyList());
        PredicateList predicateList = new PredicateList(Arrays.asList(namePredicate));
        assertFalse(predicateList.test(new StudentBuilder().withName("Alice").build()));

        // Non-matching keyword
        namePredicate = new NameContainsKeywordsPredicate(Arrays.asList("Carol"));
        predicateList = new PredicateList(Arrays.asList(namePredicate));
        assertFalse(predicateList.test(new StudentBuilder().withName("Alice Bob").build()));

        // Keywords match phone, email and address, but does not match name
        namePredicate = new NameContainsKeywordsPredicate(Arrays.asList("12345", "alice@email.com", "Main", "Street"));
        predicateList = new PredicateList(Arrays.asList(namePredicate));
        assertFalse(predicateList.test(new StudentBuilder().withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").build()));
    }

    @Test
    public void test_studentContainsPhoneValue_returnsTrue() {
        PhoneMatchesPredicate phonePredicate = new PhoneMatchesPredicate("123");
        PredicateList predicateList = new PredicateList(Arrays.asList(phonePredicate));
        assertTrue(predicateList.test(new StudentBuilder().withPhone("123").build()));
    }

    @Test
    public void test_studentDoesNotContainPhoneValue_returnsFalse() {
        // No value
        PhoneMatchesPredicate phonePredicate = new PhoneMatchesPredicate("");
        PredicateList predicateList = new PredicateList(Arrays.asList(phonePredicate));
        assertFalse(predicateList.test(new StudentBuilder().withName("Alice").build()));

        // Non-matching value
        phonePredicate = new PhoneMatchesPredicate("123");
        predicateList = new PredicateList(Arrays.asList(phonePredicate));
        assertFalse(predicateList.test(new StudentBuilder().withPhone("321").build()));
    }

    @Test
    public void test_studentContainsNameKeywordAndPhoneValue_returnsTrue() {
        // One name keyword one phone value
        NameContainsKeywordsPredicate namePredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("Alice"));
        PhoneMatchesPredicate phonePredicate = new PhoneMatchesPredicate("123");
        PredicateList predicateList = new PredicateList(Arrays.asList(namePredicate, phonePredicate));
        assertTrue(predicateList.test(new StudentBuilder().withName("Alice").withPhone("123").build()));

        // Multiple name keywords one phone value
        namePredicate = new NameContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        phonePredicate = new PhoneMatchesPredicate("456789");
        predicateList = new PredicateList(Arrays.asList(namePredicate, phonePredicate));
        assertTrue(predicateList.test(new StudentBuilder().withName("Bob").withPhone("456789").build()));
    }

    @Test
    public void test_studentDoesNotContainNameKeywordOrPhoneValue_returnsFalse() {
        // Does not contain one name keyword, contains one phone value
        NameContainsKeywordsPredicate namePredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("Alice"));
        PhoneMatchesPredicate phonePredicate = new PhoneMatchesPredicate("123");
        PredicateList predicateList = new PredicateList(Arrays.asList(namePredicate, phonePredicate));
        assertFalse(predicateList.test(new StudentBuilder().withName("Bob").withPhone("123").build()));

        // Does not contain multiple name keywords, contains one phone value
        namePredicate = new NameContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        phonePredicate = new PhoneMatchesPredicate("456789");
        predicateList = new PredicateList(Arrays.asList(namePredicate, phonePredicate));
        assertFalse(predicateList.test(new StudentBuilder().withName("Alice").withPhone("456789").build()));

        // Contains one name keyword, does not contain phone value
        namePredicate = new NameContainsKeywordsPredicate(Collections.singletonList("Alice"));
        phonePredicate = new PhoneMatchesPredicate("123");
        predicateList = new PredicateList(Arrays.asList(namePredicate, phonePredicate));
        assertFalse(predicateList.test(new StudentBuilder().withName("Alice").withPhone("456").build()));

        // Contains multiple name keywords, does not contain phone value
        namePredicate = new NameContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        phonePredicate = new PhoneMatchesPredicate("456789");
        predicateList = new PredicateList(Arrays.asList(namePredicate, phonePredicate));
        assertFalse(predicateList.test(new StudentBuilder().withName("Carol").withPhone("123").build()));
    }

    @Test
    public void test_studentDoesNotContainNameKeywordAndPhoneValue_returnsFalse() {
        // One name keyword one phone value
        NameContainsKeywordsPredicate namePredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("Alice"));
        PhoneMatchesPredicate phonePredicate = new PhoneMatchesPredicate("123");
        PredicateList predicateList = new PredicateList(Arrays.asList(namePredicate, phonePredicate));
        assertFalse(predicateList.test(new StudentBuilder().withName("Lisa").withPhone("456").build()));

        // Multiple name keywords one phone value
        namePredicate = new NameContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"));
        phonePredicate = new PhoneMatchesPredicate("456789");
        predicateList = new PredicateList(Arrays.asList(namePredicate, phonePredicate));
        assertFalse(predicateList.test(new StudentBuilder().withName("Boo").withPhone("123").build()));
    }

}
