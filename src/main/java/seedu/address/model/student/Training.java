package seedu.address.model.student;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import seedu.address.model.student.exceptions.DuplicateStudentException;


public class Training {

    public static final String MESSAGE_CONSTRAINTS = "Must be a valid date and time.";
    private final LocalDateTime dateTime;
    private final Set<Student> students = new HashSet<Student>();

    public Training(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Training(LocalDateTime dateTime, Set<Student> students) {
        this.dateTime = dateTime;
        this.students.addAll(students);
    }

    public LocalDateTime getDateTime() {
        return this.dateTime;
    }

    public Set<Student> getStudents() {
        return this.students;
    }

    /**
     * Adds the specified student to the Training Session.
     * @param student
     */
    public void addStudent(Student student) {
        if (students.contains(student)) {
            throw new DuplicateStudentException();
        } else {
            this.students.add(student);
        }
    }

    /**
     * Removes the specified student from the Training Session.
     * @param student
     */
    public void removeStudent(Student student) {
        if (students.contains(student)) {
            throw new DuplicateStudentException();
        } else {
            this.students.add(student);
        }
    }

    /**
     * Clears all students from the TrainingSession.
     */
    public void clearStudents() {
        this.students.clear();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Training)) {
            return false;
        }

        Training otherTraining = (Training) other;
        return otherTraining.getDateTime().equals(getDateTime())
                && otherTraining.getStudents().equals(getStudents());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + dateTime.hashCode();
        result = prime * result + students.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return dateTime.toString().substring(0, 10) + " " + dateTime.toString().substring(11);
    }
}
