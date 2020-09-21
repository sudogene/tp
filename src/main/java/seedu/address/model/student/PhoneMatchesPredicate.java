package seedu.address.model.student;

import java.util.function.Predicate;

/**
 * Tests that a {@code Student}'s {@code Phone} value matches the value given.
 */
public class PhoneMatchesPredicate implements Predicate<Student> {
    private final String phoneValue;

    public PhoneMatchesPredicate(String phoneValue) {
        this.phoneValue = phoneValue;
    }


    @Override
    public boolean test(Student student) {
        return student.getPhone().value.equals(phoneValue);
    }

    /* TODO
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NameContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((NameContainsKeywordsPredicate) other).keywords)); // state check
    }*/

}
