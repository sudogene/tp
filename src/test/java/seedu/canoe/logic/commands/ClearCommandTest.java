package seedu.canoe.logic.commands;

import static seedu.canoe.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.canoe.testutil.TypicalStudents.getTypicalCanoeCoach;

import org.junit.jupiter.api.Test;

import seedu.canoe.model.CanoeCoach;
import seedu.canoe.model.Model;
import seedu.canoe.model.ModelManager;
import seedu.canoe.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyAddressBook_success() {
        Model model = new ModelManager(getTypicalCanoeCoach(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalCanoeCoach(), new UserPrefs());
        expectedModel.setCanoeCoach(new CanoeCoach());

        //Checks that new Canoe Coach has no students.
        assert (new CanoeCoach().getStudentList().isEmpty());

        //Checks that new Canoe Coach has no trainings.
        assert (new CanoeCoach().getTrainingList().isEmpty());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
