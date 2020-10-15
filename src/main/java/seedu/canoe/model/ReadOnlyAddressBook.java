package seedu.canoe.model;

import javafx.collections.ObservableList;
import seedu.canoe.model.student.Student;
import seedu.canoe.model.student.Training;

/**
 * Unmodifiable view of an canoe book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the students list.
     * This list will not contain any duplicate students.
     */
    ObservableList<Student> getStudentList();

    ObservableList<Training> getTrainingList();
}
