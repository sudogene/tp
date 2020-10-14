package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TRAINING;
import static seedu.address.testutil.TypicalTraining.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Training;

public class DeleteTrainingCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndex_success() {
        Training trainingToDelete = model.getFilteredTrainingList().get(INDEX_FIRST_TRAINING.getZeroBased());
        DeleteTrainingCommand deleteTrainingCommand = new DeleteTrainingCommand(INDEX_FIRST_TRAINING);

        String expectedMessage = String.format(DeleteTrainingCommand.MESSAGE_SUCCESS, trainingToDelete);

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deleteTraining(trainingToDelete);

        assertCommandSuccess(deleteTrainingCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTrainingList().size() + 1);
        DeleteTrainingCommand deleteTrainingCommand = new DeleteTrainingCommand(outOfBoundIndex);

        assertCommandFailure(deleteTrainingCommand, model, Messages.MESSAGE_INVALID_TRAINING_DISPLAYED_INDEX);
    }
}
