package seedu.canoe.model.student.time;

import static java.util.Objects.requireNonNull;
import static seedu.canoe.commons.util.AppUtil.checkArgument;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Student's dismissal time.
 * Guarantees: immutable; is valid as declared in {@link #isValidDismissalTime(String)}
 */
public class Day {

    public enum DayOfWeek {
        MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY;
    }

    public static final String MESSAGE_CONSTRAINTS =
            "Dismissal Times should only contain numbers, and it should be in HHmm format";

    /*
     * Time must be in HHmm format.
     */
    public static final String VALIDATION_REGEX = "^(0[0-9]|1[0-9]|2[0-3])[0-5][0-9]$";

    public final LocalTime dismissalTime;

    /**
     * Constructs a {@code Day}.
     *
     * @param dismissalTime A valid dismissal time.
     */
    public Day(String dismissalTime) {
        requireNonNull(dismissalTime);
        checkArgument(isValidDismissalTime(dismissalTime), Day.MESSAGE_CONSTRAINTS);
        this.dismissalTime = formatTime(dismissalTime);
    }

    /**
     * Returns true if a given string is a valid dismissal time.
     */
    public static boolean isValidDismissalTime(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns the formatted LocalTime object from a given string time.
     * Time string passed in must be in the HHmm format.
     *
     * @param time String to be parsed into LocalTime object.
     * @return LocalTime object representing the specified time.
     */
    public static LocalTime formatTime(String time) {
        requireNonNull(time);
        checkArgument(isValidDismissalTime(time));
        return LocalTime.parse(time, DateTimeFormatter.ofPattern("HHmm"));
    }

    @Override
    public String toString() {
        return dismissalTime.format(DateTimeFormatter.ofPattern("HHmm"));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Day // instanceof handles nulls
                && dismissalTime.equals(((Day) other).dismissalTime)); // state check
    }

    @Override
    public int hashCode() {
        return dismissalTime.hashCode();
    }

}
