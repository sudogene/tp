package seedu.address.model.student.time;

import java.time.LocalTime;

public class Wednesday extends Day {

    public static String DEFAULT_WEDNESDAY_DISMISSAL = "1500";

    /**
     * Sets the dismissal time of the {@code Day}.
     *
     * @param dismissalTime A valid dismissal time.
     */
    public Wednesday(String dismissalTime) {
        super(dismissalTime);
    }

    @Override
    public String toString() {
        return "Wednesday: " + super.toString();
    }
}
