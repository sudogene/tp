package seedu.canoe.model.student;

import static java.util.Objects.requireNonNull;
import static seedu.canoe.commons.util.AppUtil.checkArgument;

/**
 * Represents a Student's name in the canoe coach book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAcademicYear(String)}
 */
public class AcademicYear {

    public static final String MESSAGE_CONSTRAINTS =
            "The Academic Year should only contain a number between 1 to 5.";

    /*
     * The first character of the input must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[1-5]";

    public final String value;

    /**
     * Constructs a {@code Name}.
     *
     * @param academicYear A valid AcademicYear.
     */
    public AcademicYear(String academicYear) {
        requireNonNull(academicYear);
        checkArgument(isValidAcademicYear(academicYear), MESSAGE_CONSTRAINTS);
        value = academicYear;
    }

    /**
     * Returns true if a given string is a valid name.
     */
    public static boolean isValidAcademicYear(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AcademicYear // instanceof handles nulls
                && value.equals(((AcademicYear) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
