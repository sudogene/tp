package seedu.canoe.model.student;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.Set;

/**
 * Represents a Student's id in the canoe coach book.
 */
public class Id {
    public static final String MESSAGE_CONSTRAINTS = "Id must be numeric and unique.";

    /** pads the Id value with leading spaces to be converted to zeroes */
    public static final String PADDING_FORMAT = "%1$3s";

    /** validates if the string is numeric */
    public static final String VALIDATION_REGEX = "-?\\d+(\\.\\d+)?";

    /** placeholder value */
    public static final String PLACEHOLDER_VALUE = "0";

    /** fields allowing auto-assignment and uniqueness of Id value */
    private static int lastUsedId = 0;
    private static final Set<String> usedIds = new HashSet<>();

    public final String value;

    /**
     * Constructs an {@code Id} with value.
     */
    public Id(String value) {
        requireNonNull(value);
        if (!value.equals(PLACEHOLDER_VALUE)) {
            Id.usedIds.add(value);
            Id.lastUsedId = Integer.parseInt(value);
        }
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }

    /**
     * Generates a new Id with auto-assigned value.
     */
    public static Id newId() {
        lastUsedId++;
        String newIdValue = String.valueOf(lastUsedId);
        return new Id(newIdValue);
    }

    /**
     * Returns true if a given string is a valid id.
     */
    public static boolean isValidId(String test) {
        if (test.matches(VALIDATION_REGEX)) {
            return !Id.usedIds.contains(test);
        }
        return false;
    }

    public static Id getPlaceHolderId() {
        return new Id(Id.PLACEHOLDER_VALUE);
    }

    public static int getLastUsedId() {
        return lastUsedId;
    }

    public Set<String> getUsedIds() {
        return usedIds;
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
