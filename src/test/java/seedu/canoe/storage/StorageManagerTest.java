package seedu.canoe.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.canoe.testutil.TypicalStudents.getTypicalCanoeCoach;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.canoe.commons.core.GuiSettings;
import seedu.canoe.model.CanoeCoach;
import seedu.canoe.model.ReadOnlyCanoeCoach;
import seedu.canoe.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonCanoeCoachStorage addressBookStorage = new JsonCanoeCoachStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(addressBookStorage, userPrefsStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void prefsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonUserPrefsStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonUserPrefsStorageTest} class.
         */
        UserPrefs original = new UserPrefs();
        original.setGuiSettings(new GuiSettings(300, 600, 4, 6));
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    @Test
    public void addressBookReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonCanoeCoachStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonCanoeCoachStorageTest} class.
         */
        CanoeCoach original = getTypicalCanoeCoach();
        storageManager.saveCanoeCoach(original);
        ReadOnlyCanoeCoach retrieved = storageManager.readCanoeCoach().get();
        assertEquals(original, new CanoeCoach(retrieved));
    }

    @Test
    public void getAddressBookFilePath() {
        assertNotNull(storageManager.getCanoeCoachFilePath());
    }

}
