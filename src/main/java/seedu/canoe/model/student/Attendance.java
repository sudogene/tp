package seedu.canoe.model.student;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Represents an attendance for a training session.
 */
public class Attendance implements Comparable<Attendance> {

    private final LocalDateTime trainingTime;

    //By default, isMarked is false when initialised.
    private boolean isMarked = false;

    /**
     * Constructs an Attend class from the time of that the training is conducted.
     * @param trainingTime time that the training is held.
     */

    public Attendance(LocalDateTime trainingTime) {
        requireNonNull(trainingTime);
        this.trainingTime = trainingTime;
    }

    /**
     * Marks the Attendance.
     */
    public void marks() {
        isMarked = true;
    }

    /**
     * Unarks the Attendance.
     */
    public void unmarks() {
        isMarked = false;
    }

    /**
     * Getter method for the time that the training is held.
     * @return trainingTime
     */

    public LocalDateTime getTrainingTime() {
        return trainingTime;
    }

    /**
     * Getter method for whether the Attendance is marked or not.
     * @return isMarked
     */
    public boolean isMarked() {
        return isMarked;
    }

    /**
     * Compares this to other Attendance.
     * @param other The other instance of Attendance we are comparing to.
     * @return The result of comparing the training time of this instance to the training time of the other instance.
     */
    @Override
    public int compareTo(Attendance other) {
        return trainingTime.compareTo(other.getTrainingTime());
    }

    /**
     * Returns true if the training time of this instance of Attendance, and the other instance of Attendance
     * are the same.
     * @param other The other instance of Attendance we are comparing to.
     */
    @Override
    public boolean equals(Object other) {

        if (other instanceof Attendance) {
            return trainingTime.equals(((Attendance) other).getTrainingTime());
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        String result = getTrainingTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        if (isMarked) {
            result += " [\u2713]";
        } else {
            result += " [\u2717]";
        }
        return result;

    }
}
