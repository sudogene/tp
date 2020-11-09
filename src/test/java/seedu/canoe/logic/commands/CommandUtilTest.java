package seedu.canoe.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.canoe.testutil.Assert.assertThrows;
import static seedu.canoe.testutil.TypicalStudents.ALICE;
import static seedu.canoe.testutil.TypicalStudents.BOB;
import static seedu.canoe.testutil.TypicalStudents.getTypicalAddressBook;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.canoe.logic.commands.exceptions.CommandException;
import seedu.canoe.model.Model;
import seedu.canoe.model.ModelManager;
import seedu.canoe.model.UserPrefs;
import seedu.canoe.model.student.Student;

public class CommandUtilTest {

    private static final String ID_ALICE = "1";
    private static final String ID_NOT_EXISTS = "50";
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void getStudentFromIdNullThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> CommandUtil.getStudentFromId(null, ID_ALICE));
        assertThrows(NullPointerException.class, () -> CommandUtil.getStudentFromId(model, null));
        assertThrows(NullPointerException.class, () -> CommandUtil.getStudentFromId(null, null));
    }

    @Test
    public void getStudentFromIdExistsSuccess() throws CommandException {
        assertEquals(ALICE, CommandUtil.getStudentFromId(model, ID_ALICE));
    }

    @Test
    public void getStudentFromIdNotExistsThrowsCommandException() {
        assertThrows(CommandException.class, () -> CommandUtil.getStudentFromId(model, ID_NOT_EXISTS));
    }

    @Test
    public void getStudentsMessageNullThrowsNullPointerException() {
        assertThrows(NullPointerException.class, () -> CommandUtil.getStudentsMessage(null));
    }

    @Test
    public void getStudentsMessage() {
        List<Student> emptyList = new ArrayList<>();
        List<Student> populatedList = List.of(ALICE, BOB);
        String expectedMessage = "1, 10";

        // Optional string is empty when given empty student list
        assertTrue(CommandUtil.getStudentsMessage(emptyList).isEmpty());

        // Optional string is present when given populated student list
        assertTrue(CommandUtil.getStudentsMessage(populatedList).isPresent());

        // Expected message for populated list
        assertEquals(expectedMessage, CommandUtil.getStudentsMessage(populatedList).get());
    }
}
