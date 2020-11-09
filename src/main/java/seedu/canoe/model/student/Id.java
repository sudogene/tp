package seedu.canoe.model.student;

import static java.util.Objects.requireNonNull;

import java.util.HashSet;
import java.util.Set;

import seedu.canoe.commons.util.StringUtil;

/**
 * Represents a Student's id in the canoe coach book.
 */
public class Id {
    public static final String MESSAGE_CONSTRAINTS = "Id must be a non-zero unsigned integer!";

    /** validates if the string is numeric */
    public static final String VALIDATION_REGEX = "^[1-9][0-9]*$";

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
        if (!value.equals(PLACEHOLDER_VALUE) && !isUsedId(value)) {
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
     * Returns true if a given string is a valid value for Id.
     */
    public static boolean isValidId(String test) {
        return test.matches(VALIDATION_REGEX) && StringUtil.isNonZeroUnsignedInteger(test);
    }

    /**
     * Returns true if a given string has been used as an Id value.
     */
    public static boolean isUsedId(String test) {
        return Id.usedIds.contains(test);
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
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
