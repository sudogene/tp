package seedu.address.testutil;

import static seedu.address.testutil.TypicalStudents.ALICE;

import seedu.address.model.student.AcademicYear;
import seedu.address.model.student.Email;
import seedu.address.model.student.Id;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Student;
import seedu.address.model.student.Training;
import seedu.address.model.student.time.Friday;
import seedu.address.model.student.time.Monday;
import seedu.address.model.student.time.Thursday;
import seedu.address.model.student.time.Tuesday;
import seedu.address.model.student.time.Wednesday;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

public class TrainingBuilder {
    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public static final LocalDateTime DEFAULT_DATETIME = LocalDateTime.parse("2020-10-10 2000", formatter);
    public static final Set<Student> DEFAULT_STUDENTS = new HashSet<>();

    private LocalDateTime dateTime;
    private Set<Student> students;

    /**
     * Creates a {@code StudentBuilder} with the default details.
     */
    public TrainingBuilder() {
        dateTime = DEFAULT_DATETIME;
        students = DEFAULT_STUDENTS;
    }

    /**
     * Initializes the StudentBuilder with the data of {@code studentToCopy}.
     */
    public TrainingBuilder(Training trainingToCopy) {
        dateTime = trainingToCopy.getDateTime();
        students = trainingToCopy.getStudents();
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
