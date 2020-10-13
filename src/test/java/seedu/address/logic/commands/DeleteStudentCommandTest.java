package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_ARRAY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_ARRAY2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_STRINGS;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalStudents.ALICE;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Student;
import seedu.address.model.student.Training;
import seedu.address.testutil.TypicalTraining;

public class DeleteStudentCommandTest {

    private Model model = new ModelManager(TypicalTraining.getTypicalAddressBook(), new UserPrefs());

    @Test
    public void constructor_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteStudentCommand(INDEX_FIRST_STUDENT, null));
    }

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeleteStudentCommand(null, VALID_ID_ARRAY));
    }

    @Test
    public void execute_studentAcceptedByModel_deleteSuccessful() throws Exception {
        DeleteStudentCommand deleteStudentCommand = new DeleteStudentCommand(INDEX_FIRST_STUDENT, VALID_ID_ARRAY);
        Training editedTraining = new Training(VALID_DATETIME, new HashSet<Student>());
        editedTraining.addStudent(ALICE);

        String expectedMessage = String
                .format(DeleteStudentCommand.MESSAGE_ADD_STUDENT_SUCCESS, VALID_ID_STRINGS.toString());

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.setTraining(model.getFilteredTrainingList().get(0), editedTraining);

        assertCommandSuccess(deleteStudentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        DeleteStudentCommand deleteStudent1Command = new DeleteStudentCommand(INDEX_FIRST_STUDENT, VALID_ID_ARRAY);
        DeleteStudentCommand deleteStudent12Command = new DeleteStudentCommand(INDEX_FIRST_STUDENT, VALID_ID_ARRAY2);

        // same object -> returns true
        assertTrue(deleteStudent1Command.equals(deleteStudent1Command));

        // same values -> returns true
        DeleteStudentCommand deleteStudentCommandCopy = new DeleteStudentCommand(INDEX_FIRST_STUDENT, VALID_ID_ARRAY);
        assertTrue(deleteStudent1Command.equals(deleteStudentCommandCopy));

        // different types -> returns false
        assertFalse(deleteStudent1Command.equals(1));

        // null -> returns false
        assertFalse(deleteStudent1Command.equals(null));

        // different student -> returns false
        assertFalse(deleteStudent1Command.equals(deleteStudent12Command));
    }
}
