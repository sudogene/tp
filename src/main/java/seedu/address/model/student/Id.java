package seedu.address.model.student;

/**
 * Represents a Student's id.
 */
public class Id {
    public static final String PADDING_FORMAT = "%1$3s";
    public final String value;

    /**
     * Constructs an {@code Id} with unique value.
     */
    public Id(String value) {
        this.value = value;
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
