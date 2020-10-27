package seedu.canoe.testutil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

import seedu.canoe.model.student.Student;
import seedu.canoe.model.training.Training;

public class TrainingBuilder {
    public static final Set<Student> DEFAULT_STUDENTS = new HashSet<>();
    private static DateTimeFormatter formatDateTime = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    public static final LocalDateTime DEFAULT_DATETIME = LocalDateTime.parse("2020-10-10 2000", formatDateTime);

    private LocalDateTime dateTime;
    private Set<Student> students;

    /**
     * Creates a {@code TrainingBuilder} with the default details.
     */
    public TrainingBuilder() {
        dateTime = DEFAULT_DATETIME;
        students = DEFAULT_STUDENTS;
    }

    /**
     * Initializes the TrainingBuilder with the data of {@code trainingToCopy}.
     */
    public TrainingBuilder(Training trainingToCopy) {
        dateTime = trainingToCopy.getDateTime();
        students = trainingToCopy.getStudents();
    }

    public DateTimeFormatter getFormatter() {
        return formatDateTime;
    }

    /**
     * Sets the {@code DateTime} of the {@code Training} that we are building.
     */
    public TrainingBuilder withDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
        return this;
    }

    /**
     * Sets the {@code students} of the {@code Training} that we are building.
     */
    public TrainingBuilder withStudents(Set<Student> students) {
        this.students = students;
        return this;
    }

    /**
     * Builds a Training Session
     * @return Training
     */
    public Training build() {
        return new Training(dateTime, students);
    }
}
