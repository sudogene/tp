package seedu.canoe.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.canoe.commons.core.LogsCenter;
import seedu.canoe.commons.exceptions.DataConversionException;
import seedu.canoe.commons.exceptions.IllegalValueException;
import seedu.canoe.commons.util.FileUtil;
import seedu.canoe.commons.util.JsonUtil;
import seedu.canoe.model.ReadOnlyCanoeCoach;

/**
 * A class to access CanoeCoach data stored as a json file on the hard disk.
 */
public class JsonCanoeCoachStorage implements CanoeCoachStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonCanoeCoachStorage.class);

    private Path filePath;

    public JsonCanoeCoachStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getCanoeCoachFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyCanoeCoach> readCanoeCoach() throws DataConversionException {
        return readCanoeCoach(filePath);
    }

    /**
     * Similar to {@link #readCanoeCoach()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyCanoeCoach> readCanoeCoach(Path filePath) throws DataConversionException {
        requireNonNull(filePath);

        Optional<JsonSerializableCanoeCoach> jsonCanoeCoach = JsonUtil.readJsonFile(
                filePath, JsonSerializableCanoeCoach.class);
        if (!jsonCanoeCoach.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonCanoeCoach.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveCanoeCoach(ReadOnlyCanoeCoach canoeCoach) throws IOException {
        saveCanoeCoach(canoeCoach, filePath);
    }

    /**
     * Similar to {@link #saveCanoeCoach(ReadOnlyCanoeCoach)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveCanoeCoach(ReadOnlyCanoeCoach canoeCoach, Path filePath) throws IOException {
        requireNonNull(canoeCoach);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableCanoeCoach(canoeCoach), filePath);
    }

}
