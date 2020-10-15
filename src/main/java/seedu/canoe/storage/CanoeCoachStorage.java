package seedu.canoe.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.canoe.commons.exceptions.DataConversionException;
import seedu.canoe.model.CanoeCoach;
import seedu.canoe.model.ReadOnlyCanoeCoach;

/**
 * Represents a storage for {@link CanoeCoach}.
 */
public interface CanoeCoachStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getCanoeCoachFilePath();

    /**
     * Returns CanoeCoach data as a {@link ReadOnlyCanoeCoach}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyCanoeCoach> readCanoeCoach() throws DataConversionException, IOException;

    /**
     * @see #getCanoeCoachFilePath()
     */
    Optional<ReadOnlyCanoeCoach> readCanoeCoach(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyCanoeCoach} to the storage.
     *
     * @param canoeCoach cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveCanoeCoach(ReadOnlyCanoeCoach canoeCoach) throws IOException;

    /**
     * @see #saveCanoeCoach(ReadOnlyCanoeCoach)
     */
    void saveCanoeCoach(ReadOnlyCanoeCoach canoeCoach, Path filePath) throws IOException;

}
