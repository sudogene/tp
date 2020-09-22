package seedu.address.model.student.time;

import java.time.LocalTime;

public class Friday extends Day {

    public static String DEFAULT_FRIDAY_DISMISSAL = "1500";

    /**
     * Sets the dismissal time of the {@code Day}.
     *
     * @param dismissalTime A valid dismissal time.
     */
    public Friday(String dismissalTime) {
        super(dismissalTime);
    }

    @Override
    public String toString() {
        return "Friday: " + super.toString();
    }
}
