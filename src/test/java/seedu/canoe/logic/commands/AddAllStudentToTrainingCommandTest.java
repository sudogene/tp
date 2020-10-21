package seedu.canoe.logic.commands;

import org.junit.jupiter.api.Test;
import seedu.canoe.model.Model;
import seedu.canoe.model.ModelManager;
import seedu.canoe.model.UserPrefs;
import seedu.canoe.testutil.TypicalStudentsInTypicalTrainings;

import static seedu.canoe.testutil.Assert.assertThrows;

public class AddAllStudentToTrainingCommandTest {

    private Model model = new ModelManager(TypicalStudentsInTypicalTrainings.getTypicalAddressBook(), new UserPrefs());

    public Model getModel() {
        return model;
    }

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new AddAllStudentToTrainingCommand(null));
    }

    @Test
    public void isAbleToAddStudent() {
        Training training = model.getFilteredTrainingList().get()
    }

}
