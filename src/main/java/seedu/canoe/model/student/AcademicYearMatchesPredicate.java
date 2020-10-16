package seedu.canoe.model.student;

import java.util.function.Predicate;

/**
 * Tests that a {@code Student}'s {@code AcademicYear} value matches the value given.
 */
public class AcademicYearMatchesPredicate implements Predicate<Student> {
    private final String academicYearValue;

    public AcademicYearMatchesPredicate(String academicYearValue) {
        this.academicYearValue = academicYearValue;
    }


    @Override
    public boolean test(Student student) {
        return student.getAcademicYear().value.equals(academicYearValue);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AcademicYearMatchesPredicate // instanceof handles nulls
                && academicYearValue.equals(((AcademicYearMatchesPredicate) other).academicYearValue)); // state check
    }

}
