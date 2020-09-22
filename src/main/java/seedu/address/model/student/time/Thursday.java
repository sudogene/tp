package seedu.address.model.student.time;

import java.time.LocalTime;

public class Thursday extends Day {

    public static String DEFAULT_THURSDAY_DISMISSAL = "1500";

    /**
     * Sets the dismissal time of the {@code Day}.
     *
     * @param dismissalTime A valid dismissal time.
     */
    public Thursday(String dismissalTime) {
        super(dismissalTime);
    }

    @Override
    public String toString() {
        return "Thursday: " + super.toString();
    }
}
