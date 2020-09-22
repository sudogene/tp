package seedu.address.model.student.time;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Monday extends Day {

    public static String DEFAULT_MONDAY_DISMISSAL = "1500";

    /**
     * Sets the dismissal time of the {@code Day}.
     *
     * @param dismissalTime A valid dismissal time.
     */
    public Monday(String dismissalTime) {
        super(dismissalTime);
    }

    @Override
    public String toString() {
        return "Monday: " + super.toString();
    }
}
