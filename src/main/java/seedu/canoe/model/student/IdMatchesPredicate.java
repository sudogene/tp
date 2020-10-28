package seedu.canoe.model.student;

import java.util.function.Predicate;

/**
 * Tests that a {@code Student}'s {@code Id} value matches the value given.
 */
public class IdMatchesPredicate implements Predicate<Student> {
    private final String idValue;

    public IdMatchesPredicate(String idValue) {
        this.idValue = idValue;
    }

    @Override
    public boolean test(Student student) {
        return student.getId().getValue().equals(idValue);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof IdMatchesPredicate // instanceof handles nulls
                && idValue.equals(((IdMatchesPredicate) other).idValue)); // state check
    }

}
