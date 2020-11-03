package seedu.canoe.model.student;

import java.util.function.Predicate;

/**
 * Tests that a {@code Student}'s {@code AcademicYear} value matches the value given.
 */
public class AcademicYearMatchesPredicate implements Predicate<Student> {
    private final AcademicYear year;

    public AcademicYearMatchesPredicate(AcademicYear year) {
        this.year = year;
    }


    @Override
    public boolean test(Student student) {
        return student.getAcademicYear().equals(year);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AcademicYearMatchesPredicate // instanceof handles nulls
                && year.equals(((AcademicYearMatchesPredicate) other).year)); // state check
    }

}
