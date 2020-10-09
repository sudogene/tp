package seedu.address.logic.commands;

import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalTraining.VALID_TRAINING;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.student.Training;

import java.time.LocalDateTime;

public class DeleteTrainingCommandTest {
    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();
    LocalDateTime dateTime = VALID_TRAINING.getDateTime();

    @Test
    public void execute_validDeletionSuccessTest() throws Exception {
        model.getAddressBook().getTrainings().add(new Training(dateTime));
        expectedModel.getAddressBook().getTrainings().add(new Training(dateTime));
        CommandResult expectedDeleteTrainingCommand = new DeleteTrainingCommand(dateTime).execute(expectedModel);
        assertCommandSuccess(new DeleteTrainingCommand(dateTime), model, expectedDeleteTrainingCommand, expectedModel);
        model = new ModelManager();
        expectedModel = new ModelManager();
    }

    @Test
    public void execute_invalidDeletionFailureTest() throws Exception {
        model = new ModelManager();
        expectedModel = new ModelManager();
        assertThrows(CommandException.class, () -> new DeleteTrainingCommand(dateTime).execute(expectedModel).getFeedbackToUser());
    }
}
