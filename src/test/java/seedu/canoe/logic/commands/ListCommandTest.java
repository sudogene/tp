package seedu.canoe.logic.commands;

import static seedu.canoe.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.canoe.logic.commands.CommandTestUtil.showStudentAtIndex;
import static seedu.canoe.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.canoe.testutil.TypicalStudents.getTypicalCanoeCoach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.canoe.model.Model;
import seedu.canoe.model.ModelManager;
import seedu.canoe.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalCanoeCoach(), new UserPrefs());
        expectedModel = new ModelManager(model.getCanoeCoach(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showStudentAtIndex(model, INDEX_FIRST_STUDENT);
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
