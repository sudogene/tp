package seedu.canoe.logic.commands;

import static seedu.canoe.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.canoe.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.canoe.testutil.TypicalIndexes.INDEX_FIRST_TRAINING;
import static seedu.canoe.testutil.TypicalTraining.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.canoe.commons.core.Messages;
import seedu.canoe.commons.core.index.Index;
import seedu.canoe.model.Model;
import seedu.canoe.model.ModelManager;
import seedu.canoe.model.UserPrefs;
import seedu.canoe.model.training.Training;

public class DeleteTrainingCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndex_success() {
        Training trainingToDelete = model.getFilteredTrainingList().get(INDEX_FIRST_TRAINING.getZeroBased());
        DeleteTrainingCommand deleteTrainingCommand = new DeleteTrainingCommand(INDEX_FIRST_TRAINING);

        String expectedMessage = String.format(DeleteTrainingCommand.MESSAGE_SUCCESS, trainingToDelete);

        ModelManager expectedModel = new ModelManager(model.getCanoeCoach(), new UserPrefs());
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
