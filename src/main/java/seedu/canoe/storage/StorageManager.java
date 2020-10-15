package seedu.canoe.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.canoe.commons.core.LogsCenter;
import seedu.canoe.commons.exceptions.DataConversionException;
import seedu.canoe.model.ReadOnlyCanoeCoach;
import seedu.canoe.model.ReadOnlyUserPrefs;
import seedu.canoe.model.UserPrefs;

/**
 * Manages storage of CanoeCoach data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private CanoeCoachStorage canoeCoachStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code CanoeCoachStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(CanoeCoachStorage canoeCoachStorage, UserPrefsStorage userPrefsStorage) {
        super();
        this.canoeCoachStorage = canoeCoachStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ CanoeCoach methods ==============================

    @Override
    public Path getCanoeCoachFilePath() {
        return canoeCoachStorage.getCanoeCoachFilePath();
    }

    @Override
    public Optional<ReadOnlyCanoeCoach> readCanoeCoach() throws DataConversionException, IOException {
        return readCanoeCoach(canoeCoachStorage.getCanoeCoachFilePath());
    }

    @Override
    public Optional<ReadOnlyCanoeCoach> readCanoeCoach(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return canoeCoachStorage.readCanoeCoach(filePath);
    }

    @Override
    public void saveCanoeCoach(ReadOnlyCanoeCoach canoeCoach) throws IOException {
        saveCanoeCoach(canoeCoach, canoeCoachStorage.getCanoeCoachFilePath());
    }

    @Override
    public void saveCanoeCoach(ReadOnlyCanoeCoach canoeCoach, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        canoeCoachStorage.saveCanoeCoach(canoeCoach, filePath);
    }

}
