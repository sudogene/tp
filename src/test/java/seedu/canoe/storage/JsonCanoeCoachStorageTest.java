package seedu.canoe.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.canoe.testutil.Assert.assertThrows;
import static seedu.canoe.testutil.TypicalStudents.ALICE;
import static seedu.canoe.testutil.TypicalStudents.HOON;
import static seedu.canoe.testutil.TypicalStudents.IDA;
import static seedu.canoe.testutil.TypicalStudents.getTypicalAddressBook;

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
    public void readAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readAddressBook(null));
    }

    private java.util.Optional<ReadOnlyCanoeCoach> readAddressBook(String filePath) throws Exception {
        return new JsonCanoeCoachStorage(Paths.get(filePath)).readCanoeCoach(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readAddressBook("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () -> readAddressBook("notJsonFormatCanoeCoach.json"));
    }

    @Test
    public void readAddressBook_invalidStudentAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAddressBook("invalidStudentCanoeCoach.json"));
    }

    @Test
    public void readAddressBook_invalidAndValidStudentAddressBook_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readAddressBook("invalidAndValidStudentCanoeCoach.json"));
    }

    @Test
    public void readAndSaveAddressBook_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempAddressBook.json");
        CanoeCoach original = getTypicalAddressBook();
        JsonCanoeCoachStorage jsonAddressBookStorage = new JsonCanoeCoachStorage(filePath);

        // Save in new file and read back
        jsonAddressBookStorage.saveCanoeCoach(original, filePath);
        ReadOnlyCanoeCoach readBack = jsonAddressBookStorage.readCanoeCoach(filePath).get();
        assertEquals(original, new CanoeCoach(readBack));

        // Modify data, overwrite exiting file, and read back
        original.addStudent(HOON);
        original.removeStudent(ALICE);
        jsonAddressBookStorage.saveCanoeCoach(original, filePath);
        readBack = jsonAddressBookStorage.readCanoeCoach(filePath).get();
        assertEquals(original, new CanoeCoach(readBack));

        // Save and read without specifying file path
        original.addStudent(IDA);
        jsonAddressBookStorage.saveCanoeCoach(original); // file path not specified
        readBack = jsonAddressBookStorage.readCanoeCoach().get(); // file path not specified
        assertEquals(original, new CanoeCoach(readBack));

    }

    @Test
    public void saveAddressBook_nullAddressBook_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(null, "SomeFile.json"));
    }

    /**
     * Saves {@code addressBook} at the specified {@code filePath}.
     */
    private void saveAddressBook(ReadOnlyCanoeCoach addressBook, String filePath) {
        try {
            new JsonCanoeCoachStorage(Paths.get(filePath))
                    .saveCanoeCoach(addressBook, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveAddressBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveAddressBook(new CanoeCoach(), null));
    }
}
