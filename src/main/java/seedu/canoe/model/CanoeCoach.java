package seedu.canoe.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.canoe.model.student.Student;
import seedu.canoe.model.student.UniqueStudentList;
import seedu.canoe.model.training.Training;
import seedu.canoe.model.training.UniqueTrainingList;


/**
 * Wraps all data at the canoe coach book level
 * Duplicates are not allowed (by .isSameStudent comparison)
 */
public class CanoeCoach implements ReadOnlyCanoeCoach {

    private final UniqueStudentList students;
    private final UniqueTrainingList trainings;

    /*
     * The 'unusual' code block below is a non-static initialization block, sometimes used to avoid duplication
     * between constructors. See https://docs.oracle.com/javase/tutorial/java/javaOO/initial.html
     *
     * Note that non-static init blocks are not recommended to use. There are other ways to avoid duplication
     *   among constructors.
     */
    {
        students = new UniqueStudentList();
        trainings = new UniqueTrainingList();
    }

    public CanoeCoach() {
    }

    /**
     * Creates an CanoeCoach using the Students in the {@code toBeCopied}
     */
    public CanoeCoach(ReadOnlyCanoeCoach toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the student list with {@code students}.
     * {@code students} must not contain duplicate students.
     */
    public void setStudents(List<Student> students) {
        this.students.setStudents(students);
    }

    /**
     * Replaces the contents of the training list with {@code trainings}.
     * {@code trainings} must not contain duplicate trainings.
     */
    public void setTrainings(List<Training> trainings) {
        this.trainings.setTrainings(trainings);
    }

    /**
     * Replaces the given training {@code target} in the list with {@code editedTraining}.
     * {@code target} must exist in the canoe book.
     * The training identity of {@code editedTraining} must not be the same as another existing
     * training in the canoe book.
     */
    public void setTraining(Training target, Training editedTraining) {
        requireNonNull(editedTraining);
        trainings.setTraining(target, editedTraining);
    }

    /**
     * Resets the existing data of this {@code CanoeCoach} with {@code newData}.
     */
    public void resetData(ReadOnlyCanoeCoach newData) {
        requireNonNull(newData);

        setStudents(newData.getStudentList());
        setTrainings(newData.getTrainingList());
    }

    //// student-level operations and training-level operations

    /**
     * Returns true if a student with the same identity as {@code student} exists in the canoe book.
     */
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return students.contains(student);
    }

    /**
     * Returns true if a training with the same identity as {@code training} exists in the canoe book.
     */
    public boolean hasTraining(Training training) {
        requireNonNull(training);
        return trainings.contains(training);
    }

    /**
     * Adds a student to the canoe book.
     * The student must not already exist in the canoe book.
     */
    public void addStudent(Student p) {
        students.add(p);
    }

    /**
     * Adds a training to the canoe book.
     * The training must not already exist in the canoe book.
     */
    public void addTraining(Training p) {
        trainings.add(p);
    }

    /**
     * Replaces the given student {@code target} in the list with {@code editedStudent}.
     * {@code target} must exist in the canoe book.
     * The student identity of {@code editedStudent} must not be the same as another existing
     * student in the canoe book.
     */
    public void setStudentInUniqueStudentList(Student target, Student editedStudent) {
        requireNonNull(editedStudent);
        students.setStudent(target, editedStudent);
    }

    /**
     * Removes {@code key} from this {@code CanoeCoach}.
     * {@code key} must exist in the canoe book.
     */
    public void removeStudent(Student key) {
        students.remove(key);
    }

    /**
     * Removes the specified Training from the Address Book.
     * @param training
     */
    public void removeTraining(Training training) {
        trainings.remove(training);
    }

    //// util methods

    @Override
    public String toString() {
        return students.asUnmodifiableObservableList().size() + " students";
        // TODO: refine later
    }

    @Override
    public ObservableList<Student> getStudentList() {
        return students.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Training> getTrainingList() {
        return trainings.asUnmodifiableObservableList();
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CanoeCoach // instanceof handles nulls
                && students.equals(((CanoeCoach) other).students));
    }

    @Override
    public int hashCode() {
        return students.hashCode();
    }
}
