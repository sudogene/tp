package seedu.canoe.model;

import java.nio.file.Path;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.canoe.commons.core.GuiSettings;
import seedu.canoe.model.student.Student;
import seedu.canoe.model.student.Training;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Student> PREDICATE_SHOW_ALL_STUDENTS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' canoe book file path.
     */
    Path getAddressBookFilePath();

    /**
     * Sets the user prefs' canoe book file path.
     */
    void setAddressBookFilePath(Path addressBookFilePath);

    /**
     * Replaces canoe book data with the data in {@code addressBook}.
     */
    void setAddressBook(ReadOnlyAddressBook addressBook);

    /** Returns the AddressBook */
    ReadOnlyAddressBook getAddressBook();

    /**
     * Returns true if a student with the same identity as {@code student} exists in the canoe book.
     */
    boolean hasStudent(Student student);

    /**
     * Deletes the given student.
     * The student must exist in the canoe book.
     */
    void deleteStudent(Student target);

    /**
     * Adds the given student.
     * {@code student} must not already exist in the canoe book.
     */
    void addStudent(Student student);

    /**
     * Replaces the given student {@code target} with {@code editedStudent}.
     * {@code target} must exist in the canoe book.
     * The student identity of {@code editedStudent} must not be the same as another
     * existing student in the canoe book.
     */
    void setStudentInUniqueStudentList(Student target, Student editedStudent);

    /**
     * Replaces the given training {@code target} with {@code editedTraining}.
     * {@code target} must exist in the canoe book.
     * The student identity of {@code editedTraining} must not be the same as another
     * existing training in the canoe book.
     */
    void setTraining(Training target, Training editedTraining);

    /**
     * Adds the given Training Session.
     * @param training
     */
    void addTraining(Training training);

    /**
     * Returns true if the Training Session already exists in the canoe book.
     * @param training
     */
    boolean hasTraining(Training training);

    /** Returns an unmodifiable view of the filtered student list */
    ObservableList<Student> getFilteredStudentList();

    /** Returns an unmodifiable view of the filtered training list */
    ObservableList<Training> getFilteredTrainingList();

    /**
     * Deletes the specified Training Session.
     * @param training
     */
    void deleteTraining(Training training);

    /**
     * Updates the filter of the filtered student list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredStudentList(Predicate<Student> predicate);

    /**
     * Updates the filter of the filtered training list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTrainingList(Predicate<Training> predicate);
}
