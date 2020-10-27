package seedu.canoe.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.canoe.commons.core.GuiSettings;
import seedu.canoe.logic.commands.CommandResult;
import seedu.canoe.logic.commands.exceptions.CommandException;
import seedu.canoe.logic.parser.exceptions.ParseException;
import seedu.canoe.model.ReadOnlyCanoeCoach;
import seedu.canoe.model.student.Student;
import seedu.canoe.model.training.Training;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the CanoeCoach.
     *
     * @see seedu.canoe.model.Model#getCanoeCoach()
     */
    ReadOnlyCanoeCoach getCanoeCoach();

    /** Returns an unmodifiable view of the filtered list of students */
    ObservableList<Student> getFilteredStudentList();

    /** Returns an unmodifiable view of the filtered list of trainings */
    ObservableList<Training> getFilteredTrainingList();

    /**
     * Returns the user prefs' canoe book file path.
     */
    Path getCanoeCoachFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
