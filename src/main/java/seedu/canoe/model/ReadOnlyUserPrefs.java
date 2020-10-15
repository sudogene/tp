package seedu.canoe.model;

import java.nio.file.Path;

import seedu.canoe.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getCanoeCoachFilePath();

}
