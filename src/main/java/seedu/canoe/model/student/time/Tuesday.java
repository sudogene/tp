package seedu.canoe.model.student.time;

/**
 * Represents Tuesday in a week.
 */
public class Tuesday extends Day {

    public static final String DEFAULT_TUESDAY_DISMISSAL = "1500";

    /**
     * Sets the dismissal time of the {@code Tuesday}.
     *
     * @param dismissalTime A valid dismissal time.
     */
    public Tuesday(String dismissalTime) {
        super(dismissalTime);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
