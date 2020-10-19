package seedu.canoe.model.student;

import java.time.LocalDateTime;

public class Attend implements Comparable<Attend> {

    private final LocalDateTime trainingTime;
    private boolean hasAttended = false;

    public Attend(LocalDateTime trainingSession) {
        this.trainingTime = trainingSession;
    }

    public void attendsTraining() {
        hasAttended = true;
    }

    //Returns the date time of the training that the student is attending;
    public LocalDateTime getTrainingTime() {
        return trainingTime;
    }

    //Returns boolean indicating whether the student had attended training or not.
    public boolean getAttendance() {
        return hasAttended;
    }

    //Compare the Attends based on the time that the training was scheduled for.
    @Override
    public int compareTo(Attend other) {
        return trainingTime.compareTo(other.getTrainingTime());
    }

    @Override
    public boolean equals(Object other) {

        if (other instanceof Attend) {
            return trainingTime.equals(((Attend) other).getTrainingTime());
        } else {
            return false;
        }
    }



}
