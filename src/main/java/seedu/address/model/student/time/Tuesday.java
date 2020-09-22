package seedu.address.model.student.time;

import java.time.LocalTime;

public class Tuesday extends Day {

    public static String DEFAULT_TUESDAY_DISMISSAL = "1500";

    /**
     * Sets the dismissal time of the {@code Day}.
     *
     * @param dismissalTime A valid dismissal time.
     */
    public Tuesday(String dismissalTime) {
        super(dismissalTime);
    }

    @Override
    public String toString() {
        return "Tuesday: " + super.toString();
    }
}
