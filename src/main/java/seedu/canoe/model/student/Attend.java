package seedu.canoe.model.student;

import java.time.LocalDateTime;

/**
 * Represents an attendance for a training session.
 */
public class Attend implements Comparable<Attend> {

    private final LocalDateTime trainingTime;

    //By default, hasAttended is false when initialised.
    private boolean hasAttended = false;

    /**
     * Constructs an Attend class from the time of that the training is conducted.
     * @param trainingTime time that the training is held.
     */

    public Attend(LocalDateTime trainingTime) {
        this.trainingTime = trainingTime;
    }

    /**
     * Marks the Attend class as attended.
     */
    public void attendsTraining() {
        hasAttended = true;
    }

    /**
     * Getter method for the time that the training is held.
     * @return trainingTime
     */

    public LocalDateTime getTrainingTime() {
        return trainingTime;
    }

    /**
     * Getter method for whether the training has been attended or not.
     * @return hasAttended
     */
    public boolean getAttendance() {
        return hasAttended;
    }

    /**
     * Compares this to other Attend.
     * @param other The other instance of Attend we are comparing to.
     * @return The result of comparing the training time of this instance to the training time of the other instance.
     */
    @Override
    public int compareTo(Attend other) {
        return trainingTime.compareTo(other.getTrainingTime());
    }

    /**
     * Returns true if the training time of this instance of Attend, and the other instance of Attend are the same.
     * @param other The other instance of Attend we are comparing to.
     */
    @Override
    public boolean equals(Object other) {

        if (other instanceof Attend) {
            return trainingTime.equals(((Attend) other).getTrainingTime());
        } else {
            return false;
        }
    }
}
