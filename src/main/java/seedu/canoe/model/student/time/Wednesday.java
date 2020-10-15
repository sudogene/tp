package seedu.canoe.model.student.time;

/**
 * Represents Wednesday in a week.
 */
public class Wednesday extends Day {

    public static final String DEFAULT_WEDNESDAY_DISMISSAL = "1500";

    /**
     * Sets the dismissal time of the {@code Wednesday}.
     *
     * @param dismissalTime A valid dismissal time.
     */
    public Wednesday(String dismissalTime) {
        super(dismissalTime);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
