package seedu.canoe.model.student.time;

/**
 * Represents Thursday in a week.
 */
public class Thursday extends Day {

    public static final String DEFAULT_THURSDAY_DISMISSAL = "1500";

    /**
     * Sets the dismissal time of the {@code Thursday}.
     *
     * @param dismissalTime A valid dismissal time.
     */
    public Thursday(String dismissalTime) {
        super(dismissalTime);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
