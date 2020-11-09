package seedu.canoe.model.student;

import static seedu.canoe.commons.util.CollectionUtil.requireAllNonNull;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;

import seedu.canoe.model.student.time.Day;
import seedu.canoe.model.tag.Tag;
import seedu.canoe.model.training.Training;

/**
 * Represents a Student in the canoe coach book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Student {

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

    class TreeSetComparator implements Comparator<Attendance> {
        public int compare(Attendance attendance1, Attendance attendance2) {
            return attendance1.compareTo(attendance2);
        }
    }

    //Collection of scheduled training dates tagged to the particular student
    private final TreeSet<Attendance> trainingAttendances = new TreeSet<>(new TreeSetComparator());

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
     * Constructs the {@code Student} with a given id and training schedules.
     * Every field must be present and not null.
     */
    public Student(Name name, Phone phone, Email email, AcademicYear academicYear, Set<Tag> tags, Day mondayDismissal,
                   Day tuesdayDismissal, Day wednesdayDismissal, Day thursdayDismissal,
                   Day fridayDismissal, List<Attendance> trainingAttendances, Id id) {
        requireAllNonNull(name, phone, email, tags, academicYear, mondayDismissal, tuesdayDismissal, wednesdayDismissal,
                thursdayDismissal, fridayDismissal, trainingAttendances, id);
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
        this.trainingAttendances.addAll(trainingAttendances);
        this.id = id;
    }

    /**
     * Clones a student.
     */
    public Student cloneStudent() {
        return new Student(getName(), getPhone(), getEmail(), getAcademicYear(), getTags(),
                getMondayDismissal(), getTuesdayDismissal(), getWednesdayDismissal(), getThursdayDismissal(),
                getFridayDismissal(), new ArrayList<>(getTrainingAttendances()), getId());
    }

    /**
     * Creates a student with a new valid Id if it was constructed with placeholder Id.
     * If the student already has a valid Id, the same student is returned.
     */
    public Student createStudentWithValidId() {
        if (getId().equals(Id.getPlaceHolderId())) {
            return new Student(getName(), getPhone(), getEmail(), getAcademicYear(), getTags(),
                    getMondayDismissal(), getTuesdayDismissal(), getWednesdayDismissal(), getThursdayDismissal(),
                    getFridayDismissal(), new ArrayList<>(getTrainingAttendances()), Id.newId());
        }
        return this;
    }

    /**
     * Adds a training session that the student is attending to the student's schedule.
     * Attendances are automatically sorted by their respective date and times.
     *
     * @param trainingAttendance Attendance representing the training that the student is attending.
     * Duplicates are not allowed and will not be added.
     */
    public void addAttendance(Attendance trainingAttendance) {
        trainingAttendances.add(trainingAttendance);
    }

    /**
     * Adds a list of training sessions that the student is attending to the student's schedule.
     * Attendances are automatically sorted by their respective date and times.
     *
     * @param attendingTrainings List of Trainings that the student is going to be attending.
     * Duplicates are not allowed and will not be added.
     */
    public void addAllAttendances(List<Attendance> attendingTrainings) {
        trainingAttendances.addAll(attendingTrainings);
    }

    /**
     * Checks if student is to attend a training scheduled
     *
     * @param attendance Attendance corresponding to the training that the student is supposed to attend.
     * Specified Attendance must exist in the student's training schedule.
     * @return true If Attendance is scheduled for the Student.
     */
    public boolean containsAttendance(Attendance attendance) {
        return trainingAttendances.contains(attendance);
    }

    /**
     * Removes an Attendance from the Student
     *
     * @param attendance Attendance to remove
     */
    public void removeAttendance(Attendance attendance) {
        if (containsAttendance(attendance)) {
            trainingAttendances.remove(attendance);
        }
    }

    /**
     * Removes all Attendances for the student.
     */
    public void removeAllAttendances() {
        trainingAttendances.clear();
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
     * Returns an immutable Attendance set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Attendance> getTrainingAttendances() {
        return Collections.unmodifiableSet(trainingAttendances);
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
                && otherStudent.getAcademicYear().equals(getAcademicYear())
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
                && otherStudent.getTrainingAttendances().equals(getTrainingAttendances());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, academicYear, mondayDismissal, tuesdayDismissal, wednesdayDismissal,
                thursdayDismissal, fridayDismissal, tags, id, trainingAttendances);
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
     * Returns whether a student has an Attendance at a particular DateTime already
     */
    public boolean hasAttendanceAtDateTime(LocalDateTime dateTime) {
        //Has a training scheduled on the same date already
        for (Attendance attendance: trainingAttendances) {
            if (LocalDate.from(attendance.getTrainingTime()).isEqual(LocalDate.from(dateTime))) {
                return true;
            }
        }
        return false;
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

    /**
     * Returns whether a student is able to attend all of his/her Attendances.
     */
    public boolean isAvailableForAllAttendances() {
        if (trainingAttendances.isEmpty()) {
            return true;
        }

        boolean isAvailable = true;
        for (Attendance attendance: trainingAttendances) {
            if (attendance.getTrainingTime().isAfter(LocalDateTime.now())
                    && !isAvailableAtDateTime(attendance.getTrainingTime())) {
                isAvailable = false;
            }
        }

        return isAvailable;
    }

    /**
     * Mark a student's attendance based on training dateTime
     *
     * @param training Training to be marked attendance.
     */
    public void markAttendanceFromTraining(Training training) {
        Attendance markedAttendance = new Attendance(training.getDateTime());
        markedAttendance.marks();
        Optional<Attendance> attendanceToRemove =
                trainingAttendances.stream()
                        .filter(attendance -> attendance.getTrainingTime()
                                .isEqual(training.getDateTime())).findFirst();

        assert(attendanceToRemove.get() != null);

        trainingAttendances.remove(attendanceToRemove.get());
        trainingAttendances.add(markedAttendance);
    }

    /**
     * Mark a student's attendance.
     *
     * @param originalAttendance Attendance to be marked.
     */
    public void markAttendance(Attendance originalAttendance, Attendance markedAttendance) {
        assert(containsAttendance(originalAttendance));

        trainingAttendances.remove(originalAttendance);
        trainingAttendances.add(markedAttendance);
    }

    /**
     * Unmark a student's attendance.
     *
     * @param originalAttendance Attendance to be unmarked.
     */
    public void unmarkAttendance(Attendance originalAttendance, Attendance unmarkedAttendance) {
        assert(containsAttendance(originalAttendance));

        trainingAttendances.remove(originalAttendance);
        trainingAttendances.add(unmarkedAttendance);
    }

    /**
     * Checks student's attendance record and returns whether the student
     * has a bad attendance record.
     *
     * @return whether student has a bad attendance record.
     */
    public boolean hasBadAttendanceRecord() {
        int numOfAbsences = 0;
        int threshold = 3;

        if (trainingAttendances.isEmpty()) {
            return false;
        }

        assert !trainingAttendances.isEmpty();
        for (Attendance attendance : trainingAttendances) {
            if (attendance.getTrainingTime().isAfter(LocalDateTime.now())) {
                continue;
            }
            if (!attendance.isMarked()) {
                numOfAbsences += 1;
            }
        }
        return numOfAbsences > threshold;
    }
}
