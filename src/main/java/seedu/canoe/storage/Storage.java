package seedu.canoe.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.canoe.commons.exceptions.DataConversionException;
import seedu.canoe.model.ReadOnlyCanoeCoach;
import seedu.canoe.model.ReadOnlyUserPrefs;
import seedu.canoe.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends CanoeCoachStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getCanoeCoachFilePath();

    @Override
    Optional<ReadOnlyCanoeCoach> readCanoeCoach() throws DataConversionException, IOException;

    @Override
    void saveCanoeCoach(ReadOnlyCanoeCoach canoeCoach) throws IOException;

}
