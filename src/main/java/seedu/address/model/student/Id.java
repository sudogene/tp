package seedu.address.model.student;

/**
 * Represents a Student's id.
 */
public class Id {
    public static final String MESSAGE_CONSTRAINTS = "Id must be numeric.";

    /** pads the Id value with leading spaces to be converted to zeroes */
    public static final String PADDING_FORMAT = "%1$3s";

    /** validates if the string is numeric */
    public static final String VALIDATION_REGEX = "-?\\d+(\\.\\d+)?";

    public final String value;

    /**
     * Constructs an {@code Id} with unique value.
     */
    public Id(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    /**
     * Returns true if a given string is a valid id.
     */
    public static boolean isValidId(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Id // instanceof handles nulls
                && value.equals(((Id) other).value)); // state check
    }

    @Override
    public String toString() {
        return String.format(PADDING_FORMAT, value)
                .replace(' ', '0');
    }
}
