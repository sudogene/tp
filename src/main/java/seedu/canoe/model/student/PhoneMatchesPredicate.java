package seedu.canoe.model.student;

import java.util.function.Predicate;

/**
 * Tests that a {@code Student}'s {@code Phone} value matches the value given.
 */
public class PhoneMatchesPredicate implements Predicate<Student> {
    private final Phone phone;

    public PhoneMatchesPredicate(Phone phone) {
        this.phone = phone;
    }

    @Override
    public boolean test(Student student) {
        return student.getPhone().equals(phone);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PhoneMatchesPredicate // instanceof handles nulls
                && phone.equals(((PhoneMatchesPredicate) other).phone)); // state check
    }

}
