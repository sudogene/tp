package seedu.canoe.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.canoe.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

import seedu.canoe.commons.exceptions.IllegalValueException;
import seedu.canoe.commons.util.JsonUtil;
import seedu.canoe.model.CanoeCoach;
import seedu.canoe.testutil.TypicalStudents;

public class JsonSerializableCanoeCoachTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonSerializableCanoeCoachTest");
    private static final Path TYPICAL_STUDENTS_FILE = TEST_DATA_FOLDER.resolve("typicalStudentsCanoeCoach.json");
    private static final Path INVALID_STUDENT_FILE = TEST_DATA_FOLDER.resolve("invalidStudentCanoeCoach.json");
    private static final Path DUPLICATE_STUDENT_FILE = TEST_DATA_FOLDER.resolve("duplicateStudentCanoeCoach.json");

    @Test
    public void toModelType_typicalStudentsFile_success() throws Exception {
        JsonSerializableCanoeCoach dataFromFile = JsonUtil.readJsonFile(TYPICAL_STUDENTS_FILE,
                JsonSerializableCanoeCoach.class).get();
        CanoeCoach canoeCoachFromFile = dataFromFile.toModelType();
        CanoeCoach typicalStudentsCanoeCoach = TypicalStudents.getTypicalCanoeCoach();
        assertEquals(canoeCoachFromFile, typicalStudentsCanoeCoach);
    }

    @Test
    public void toModelType_invalidStudentFile_throwsIllegalValueException() throws Exception {
        JsonSerializableCanoeCoach dataFromFile = JsonUtil.readJsonFile(INVALID_STUDENT_FILE,
                JsonSerializableCanoeCoach.class).get();
        assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    }

    @Test
    public void toModelType_duplicateStudents_throwsIllegalValueException() throws Exception {
        JsonSerializableCanoeCoach dataFromFile = JsonUtil.readJsonFile(DUPLICATE_STUDENT_FILE,
                JsonSerializableCanoeCoach.class).get();
        assertThrows(IllegalValueException.class, JsonSerializableCanoeCoach.MESSAGE_DUPLICATE_STUDENT,
                dataFromFile::toModelType);
    }

}
