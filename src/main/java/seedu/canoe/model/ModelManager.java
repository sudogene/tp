package seedu.canoe.model;

import static java.util.Objects.requireNonNull;
import static seedu.canoe.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.canoe.commons.core.GuiSettings;
import seedu.canoe.commons.core.LogsCenter;
import seedu.canoe.model.student.Student;
import seedu.canoe.model.training.Training;

/**
 * Represents the in-memory model of the canoe coach data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final CanoeCoach canoeCoach;
    private final UserPrefs userPrefs;
    private final FilteredList<Student> filteredStudents;
    private final FilteredList<Training> filteredTrainings;

    /**
     * Initializes a ModelManager with the given canoeCoach and userPrefs.
     */
    public ModelManager(ReadOnlyCanoeCoach canoeCoach, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(canoeCoach, userPrefs);

        logger.fine("Initializing with canoe book: " + canoeCoach + " and user prefs " + userPrefs);
        this.canoeCoach = new CanoeCoach(canoeCoach);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredStudents = new FilteredList<>(this.canoeCoach.getStudentList());
        filteredTrainings = new FilteredList<>(this.canoeCoach.getTrainingList());
    }

    public ModelManager() {
        this(new CanoeCoach(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getCanoeCoachFilePath() {
        return userPrefs.getCanoeCoachFilePath();
    }

    @Override
    public void setCanoeCoachFilePath(Path canoeCoachFilePath) {
        requireNonNull(canoeCoachFilePath);
        userPrefs.setCanoeCoachFilePath(canoeCoachFilePath);
    }

    //=========== CanoeCoach ================================================================================

    @Override
    public void setCanoeCoach(ReadOnlyCanoeCoach canoeCoach) {
        this.canoeCoach.resetData(canoeCoach);
    }

    @Override
    public ReadOnlyCanoeCoach getCanoeCoach() {
        return canoeCoach;
    }

    @Override
    public boolean hasStudent(Student student) {
        requireNonNull(student);
        return canoeCoach.hasStudent(student);
    }

    @Override
    public void deleteStudent(Student target) {
        canoeCoach.removeStudent(target);
    }

    @Override
    public void addStudent(Student student) {
        canoeCoach.addStudent(student);
        updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
    }

    @Override
    public void setStudentInUniqueStudentList(Student target, Student editedStudent) {
        requireAllNonNull(target, editedStudent);
        canoeCoach.setStudentInUniqueStudentList(target, editedStudent);
    }

    @Override
    public void setTraining(Training target, Training editedTraining) {
        requireAllNonNull(target, editedTraining);
        canoeCoach.setTraining(target, editedTraining);
    }

    @Override
    public void addTraining(Training training) {
        canoeCoach.addTraining(training);
    }

    @Override
    public boolean hasTraining(Training training) {
        return canoeCoach.hasTraining(training);
    }

    @Override
    public void deleteTraining(Training training) {
        canoeCoach.removeTraining(training);
    }

    //=========== Filtered Student List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Student} backed by the internal list of
     * {@code versionedCanoeCoach}
     */
    @Override
    public ObservableList<Student> getFilteredStudentList() {
        return filteredStudents;
    }

    @Override
    public ObservableList<Training> getFilteredTrainingList() {
        return filteredTrainings;
    }

    @Override
    public void updateFilteredStudentList(Predicate<Student> predicate) {
        requireNonNull(predicate);
        filteredStudents.setPredicate(predicate);
    }

    @Override
    public void updateFilteredTrainingList(Predicate<Training> predicate) {
        requireNonNull(predicate);
        filteredTrainings.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return canoeCoach.equals(other.canoeCoach)
                && userPrefs.equals(other.userPrefs)
                && filteredStudents.equals(other.filteredStudents);
    }

}
