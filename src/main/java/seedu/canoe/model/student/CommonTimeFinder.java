package seedu.canoe.model.student;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

/**
 * Gets a list of students and returns the latest dismissal time of all
 * students for all days.
 */
public class CommonTimeFinder {
    private final List<Student> studentList;
    private LocalTime latestMondayDismissalTime;
    private LocalTime latestTuesdayDismissalTime;
    private LocalTime latestWednesdayDismissalTime;
    private LocalTime latestThursdayDismissalTime;
    private LocalTime latestFridayDismissalTime;

    /**
     * Constructs a {@code CommonTimeFinder} with the specified fields.
     *
     * @param studentList List of students.
     */
    public CommonTimeFinder(List<Student> studentList) {
        this.studentList = studentList;
        latestMondayDismissalTime = LocalTime.of(15, 0);
        latestTuesdayDismissalTime = LocalTime.of(15, 0);
        latestWednesdayDismissalTime = LocalTime.of(15, 0);
        latestThursdayDismissalTime = LocalTime.of(15, 0);
        latestFridayDismissalTime = LocalTime.of(15, 0);
    }

    /**
     * Gets the latest common dismissal times of all students and
     * returns them as a list.
     *
     * @return List of dismissal times.
     */
    public List<LocalTime> getCommonDismissalTimes() {
        getCommonTimes();
        return Arrays.asList(
                latestMondayDismissalTime,
                latestTuesdayDismissalTime,
                latestWednesdayDismissalTime,
                latestThursdayDismissalTime,
                latestFridayDismissalTime
        );
    }

    /**
     * Searches through every student in the student list and
     * gets the latest dismissal time for each day.
     */
    public void getCommonTimes() {
        for (Student student: studentList) {
            if (latestMondayDismissalTime.compareTo(
                    student.getMondayDismissal().dismissalTime) < 0) {
                latestMondayDismissalTime = student.getMondayDismissal().dismissalTime;
            }

            if (latestTuesdayDismissalTime.compareTo(
                    student.getTuesdayDismissal().dismissalTime) < 0) {
                latestTuesdayDismissalTime = student.getTuesdayDismissal().dismissalTime;
            }

            if (latestWednesdayDismissalTime.compareTo(
                    student.getWednesdayDismissal().dismissalTime) < 0) {
                latestWednesdayDismissalTime = student.getWednesdayDismissal().dismissalTime;
            }

            if (latestThursdayDismissalTime.compareTo(
                    student.getThursdayDismissal().dismissalTime) < 0) {
                latestThursdayDismissalTime = student.getThursdayDismissal().dismissalTime;
            }

            if (latestFridayDismissalTime.compareTo(
                    student.getFridayDismissal().dismissalTime) < 0) {
                latestFridayDismissalTime = student.getFridayDismissal().dismissalTime;
            }
        }
    }
}
