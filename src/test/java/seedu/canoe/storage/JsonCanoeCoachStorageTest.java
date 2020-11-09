package seedu.canoe.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.canoe.testutil.Assert.assertThrows;
import static seedu.canoe.testutil.TypicalStudents.ALICE;
import static seedu.canoe.testutil.TypicalStudents.HOON;
import static seedu.canoe.testutil.TypicalStudents.IDA;
import static seedu.canoe.testutil.TypicalStudents.getTypicalCanoeCoach;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.canoe.commons.exceptions.DataConversionException;
import seedu.canoe.model.CanoeCoach;
import seedu.canoe.model.ReadOnlyCanoeCoach;

public class JsonCanoeCoachStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonCanoeCoachStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readCanoeCoach_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readCanoeCoach(null));
    }

    private java.util.Optional<ReadOnlyCanoeCoach> readCanoeCoach(String filePath) throws Exception {
        return new JsonCanoeCoachStorage(Paths.get(filePath)).readCanoeCoach(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readCanoeCoach("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readCanoeCoach("notJsonFormatCanoeCoach.json"));
    }

    @Test
    public void readCanoeCoach_invalidStudentCanoeCoach_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readCanoeCoach("invalidStudentCanoeCoach.json"));
    }

    @Test
    public void readCanoeCoach_invalidAndValidStudentCanoeCoach_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readCanoeCoach("invalidAndValidStudentCanoeCoach.json"));
    }

    @Test
    public void readAndSaveCanoeCoach_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempCanoeCoach.json");
        CanoeCoach original = getTypicalCanoeCoach();
        JsonCanoeCoachStorage jsonCanoeCoachStorage = new JsonCanoeCoachStorage(filePath);

        // Save in new file and read back
        jsonCanoeCoachStorage.saveCanoeCoach(original, filePath);
        ReadOnlyCanoeCoach readBack = jsonCanoeCoachStorage.readCanoeCoach(filePath).get();
        assertEquals(original, new CanoeCoach(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addStudent(HOON);
        original.removeStudent(ALICE);
        jsonCanoeCoachStorage.saveCanoeCoach(original, filePath);
        readBack = jsonCanoeCoachStorage.readCanoeCoach(filePath).get();
        assertEquals(original, new CanoeCoach(readBack));

        // Save and read without specifying file path
        original.addStudent(IDA);
        jsonCanoeCoachStorage.saveCanoeCoach(original); // file path not specified
        readBack = jsonCanoeCoachStorage.readCanoeCoach().get(); // file path not specified
        assertEquals(original, new CanoeCoach(readBack));

    }

    @Test
    public void saveCanoeCoach_nullCanoeCoach_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveCanoeCoach(null, "SomeFile.json"));
    }

    /**
     * Saves {@code CanoeCoach} at the specified {@code filePath}.
     */
    private void saveCanoeCoach(ReadOnlyCanoeCoach canoeCoach, String filePath) {
        try {
            new JsonCanoeCoachStorage(Paths.get(filePath))
                    .saveCanoeCoach(canoeCoach, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveCanoeCoach_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveCanoeCoach(new CanoeCoach(), null));
    }
}
