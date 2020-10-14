package seedu.address.model.student;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

import seedu.address.model.student.time.Day;
import seedu.address.model.tag.Tag;

/**
 * Represents a Student in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student {

    // Tracks total students and used for assigning unique Id
    private static int totalNumberOfStudents = 0;

    // Identity fields
    private final Id id;
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final AcademicYear academicYear;


    // Dismissal Times
    private final Day mondayDismissal;
    private final Day tuesdayDismissal;
    private final Day wednesdayDismissal;
    private final Day thursdayDismissal;
    private final Day fridayDismissal;

    private final Set<Tag> tags = new HashSet<>();

    //Collection of scheduled training dates tagged to the particular student
    private final TreeSet<LocalDateTime> trainingSchedules = new TreeSet<>();

    /**
     * Constructs the {@code Student} with a given id.
     * Every field must be present and not null.
     */
    public Student(Name name, Phone phone, Email email, AcademicYear academicYear, Set<Tag> tags, Day mondayDismissal,
                   Day tuesdayDismissal, Day wednesdayDismissal, Day thursdayDismissal,
                   Day fridayDismissal, Id id) {
        requireAllNonNull(name, phone, email, tags, academicYear, mondayDismissal, tuesdayDismissal, wednesdayDismissal,
                thursdayDismissal, fridayDismissal, id);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.academicYear = academicYear;
        this.mondayDismissal = mondayDismissal;
        this.tuesdayDismissal = tuesdayDismissal;
        this.wednesdayDismissal = wednesdayDismissal;
        this.thursdayDismissal = thursdayDismissal;
        this.fridayDismissal = fridayDismissal;
        this.tags.addAll(tags);
        this.id = id;
    }

    /**
     * Constructs the {@code Student} with a given id.
     * Every field must be present and not null.
     */
    public Student(Name name, Phone phone, Email email, AcademicYear academicYear, Set<Tag> tags, Day mondayDismissal,
                   Day tuesdayDismissal, Day wednesdayDismissal, Day thursdayDismissal,
                   Day fridayDismissal, List<LocalDateTime> trainingSchedules, Id id) {
        requireAllNonNull(name, phone, email, tags, academicYear, mondayDismissal, tuesdayDismissal, wednesdayDismissal,
                thursdayDismissal, fridayDismissal, trainingSchedules, id);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.academicYear = academicYear;
        this.mondayDismissal = mondayDismissal;
        this.tuesdayDismissal = tuesdayDismissal;
        this.wednesdayDismissal = wednesdayDismissal;
        this.thursdayDismissal = thursdayDismissal;
        this.fridayDismissal = fridayDismissal;
        this.tags.addAll(tags);
        this.trainingSchedules.addAll(trainingSchedules);
        this.id = id;
    }

    /**
     * Adds a training session to the student's schedule.
     * Training sessions are automatically sorted by their respective date and times.
     *
     * @param trainingDateTime LocalDateTime corresponding to the training's date and start time.
     * Duplicates are not allowed and will not be added.
     */
    public void addTraining(LocalDateTime trainingDateTime) {
        trainingSchedules.add(trainingDateTime);
    }

    /**
     * Adds a list of training sessions to the student's schedule.
     * Training sessions are automatically sorted by their respective date and times.
     *
     * @param trainingDateTimes List of LocalDateTime corresponding to the trainings' dates and start times.
     * Duplicates are not allowed and will not be added.
     */
    public void addAllTraining(List<LocalDateTime> trainingDateTimes) {
        trainingSchedules.addAll(trainingDateTimes);
    }


    /**
     * Checks if student has a training scheduled at the specified date and start time.
     *
     * @param trainingDateTime LocalDateTime corresponding to the training's date and start time.
     * Specified training must exist in the student's training schedule.
     * @return true If a training has been scheduled at the specified date and time.
     */
    public boolean containsTraining(LocalDateTime trainingDateTime) {
        return trainingSchedules.contains(trainingDateTime);
    }

    /**
     * Removes a scheduled training from the student's training schedule.
     *
     * @param trainingDateTime LocalDateTime corresponding to the training's date and start time.
     */
    public void removeTraining(LocalDateTime trainingDateTime) {
        if (containsTraining(trainingDateTime)) {
            trainingSchedules.remove(trainingDateTime);
        }
    }

    /**
     * Removes all trainings scheduled for the student.
     */
    public void removeAllTraining() {
        trainingSchedules.clear();
    }

    public Id getId() {
        return id;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Day getMondayDismissal() {
        return mondayDismissal;
    }

    public Day getTuesdayDismissal() {
        return tuesdayDismissal;
    }

    public Day getWednesdayDismissal() {
        return wednesdayDismissal;
    }

    public Day getThursdayDismissal() {
        return thursdayDismissal;
    }

    public Day getFridayDismissal() {
        return fridayDismissal;
    }

    public AcademicYear getAcademicYear() {
        return academicYear;
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns an immutable training schedule set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<LocalDateTime> getTrainingSchedule() {
        return Collections.unmodifiableSet(trainingSchedules);
    }

    /**
     * Returns true if both students of the same name have at least one other identity field that is the same.
     * This defines a weaker notion of equality between two students.
     */
    public boolean isSameStudent(Student otherStudent) {
        if (otherStudent == this) {
            return true;
        }

        return otherStudent != null
                && otherStudent.getName().equals(getName())
                && (otherStudent.getPhone().equals(getPhone()) || otherStudent.getEmail().equals(getEmail()));
    }

    /**
     * Returns true if both students have the same identity and data fields.
     * This defines a stronger notion of equality between two students.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Student)) {
            return false;
        }

        Student otherStudent = (Student) other;
        return otherStudent.getName().equals(getName())
                && otherStudent.getPhone().equals(getPhone())
                && otherStudent.getEmail().equals(getEmail())
                && otherStudent.getMondayDismissal().equals(getMondayDismissal())
                && otherStudent.getTuesdayDismissal().equals(getTuesdayDismissal())
                && otherStudent.getWednesdayDismissal().equals(getWednesdayDismissal())
                && otherStudent.getThursdayDismissal().equals(getThursdayDismissal())
                && otherStudent.getFridayDismissal().equals(getFridayDismissal())
                && otherStudent.getAcademicYear().equals(getAcademicYear())
                && otherStudent.getTags().equals(getTags())
                && otherStudent.getTrainingSchedule().equals(getTrainingSchedule());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, academicYear, mondayDismissal, tuesdayDismissal, wednesdayDismissal,
                thursdayDismissal, fridayDismissal, tags, id, trainingSchedules);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" Id: ")
                .append(getId())
                .append(" Phone: ")
                .append(getPhone())
                .append(" Email: ")
                .append(getEmail())
                .append(" Dismissal Times: ")
                .append(getMondayDismissal())
                .append(" ")
                .append(getTuesdayDismissal())
                .append(" ")
                .append(getWednesdayDismissal())
                .append(" ")
                .append(getThursdayDismissal())
                .append(" ")
                .append(getFridayDismissal())
                .append(" ")
                .append(" Academic  Year: ")
                .append(getAcademicYear())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        builder.append(" Training Schedules");
        getTrainingSchedule().forEach(builder::append);
        return builder.toString();
    }

    /**
     * Returns only the essential information of Students (Name, Id, Phone, Email, Academic Year).
     * @return Name, Phone, Email, Academic Year
     */
    public String studentEssentialPrinter() {
        final StringBuilder builder = new StringBuilder();
        builder.append("\n" + getName())
                .append("\nId: ")
                .append(getId())
                .append("\nPhone: ")
                .append(getPhone())
                .append("\nEmail: ")
                .append(getEmail())
                .append("\nAcademic  Year: ")
                .append(getAcademicYear());
        return builder.toString();
    }

    /**
     * Returns whether a student is able to attend a training at a particular DateTime
     */
    public boolean isAvailableAtDateTime(LocalDateTime dateTime) {
        DayOfWeek day = dateTime.getDayOfWeek();

        LocalTime queryTime = LocalTime.from(dateTime);
        LocalTime studentTime;

        switch (day) {
        case MONDAY:
            studentTime = mondayDismissal.dismissalTime;
            return studentTime.equals(queryTime) || studentTime.isBefore(queryTime);
        case TUESDAY:
            studentTime = tuesdayDismissal.dismissalTime;
            return studentTime.equals(queryTime) || studentTime.isBefore(queryTime);
        case WEDNESDAY:
            studentTime = wednesdayDismissal.dismissalTime;
            return studentTime.equals(queryTime) || studentTime.isBefore(queryTime);
        case THURSDAY:
            studentTime = thursdayDismissal.dismissalTime;
            return studentTime.equals(queryTime) || studentTime.isBefore(queryTime);
        case FRIDAY:
            studentTime = fridayDismissal.dismissalTime;
            return studentTime.equals(queryTime) || studentTime.isBefore(queryTime);
        default:
            //Students do not have lessons on Saturday and Sunday, thus able to attend training.
            return true;
        }

    }

    public boolean isAvailableForAllTrainingsScheduled() {
        if (trainingSchedules.isEmpty()) {
            return true;
        }

        boolean isAvailable = true;
        for (LocalDateTime trainingSession: trainingSchedules) {
            if (!isAvailableAtDateTime(trainingSession)) {
                isAvailable = false;
            }
        }

        return isAvailable;
    }
}
