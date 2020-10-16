package seedu.canoe.model.student;

import java.util.function.Predicate;

/**
 * Tests that a {@code Student}'s {@code Email} contains the keyword given.
 */
public class EmailContainsKeywordPredicate implements Predicate<Student> {
    private final String keyword;

    public EmailContainsKeywordPredicate(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean test(Student student) {
        return student.getEmail().value.contains(keyword);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof EmailContainsKeywordPredicate // instanceof handles nulls
                && keyword.equals(((EmailContainsKeywordPredicate) other).keyword)); // state check
    }

}
