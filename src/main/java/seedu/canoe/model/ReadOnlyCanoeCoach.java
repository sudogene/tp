package seedu.canoe.model;

import javafx.collections.ObservableList;
import seedu.canoe.model.student.Student;
import seedu.canoe.model.training.Training;

/**
 * Unmodifiable view of a canoe coach book
 */
public interface ReadOnlyCanoeCoach {

    /**
     * Returns an unmodifiable view of the students list.
     * This list will not contain any duplicate students.
     */
    ObservableList<Student> getStudentList();

    ObservableList<Training> getTrainingList();
}
