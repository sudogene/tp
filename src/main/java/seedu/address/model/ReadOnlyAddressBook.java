package seedu.address.model;

import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.model.student.Student;
import seedu.address.model.student.Training;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyAddressBook {

    /**
     * Returns an unmodifiable view of the students list.
     * This list will not contain any duplicate students.
     */
    ObservableList<Student> getStudentList();

    Set<Training> getTrainings();

}
