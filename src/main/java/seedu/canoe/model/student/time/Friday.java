package seedu.canoe.model.student.time;

/**
 * Represents Friday in a week.
 */
public class Friday extends Day {

    public static final String DEFAULT_FRIDAY_DISMISSAL = "1500";

    /**
     * Sets the dismissal time of the {@code Friday}.
     *
     * @param dismissalTime A valid dismissal time.
     */
    public Friday(String dismissalTime) {
        super(dismissalTime);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
