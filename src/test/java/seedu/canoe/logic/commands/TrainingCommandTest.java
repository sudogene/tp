package seedu.canoe.logic.commands;

import static seedu.canoe.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.canoe.testutil.Assert.assertThrows;
import static seedu.canoe.testutil.TypicalTraining.VALID_TRAINING;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

import seedu.canoe.logic.commands.exceptions.CommandException;
import seedu.canoe.model.Model;
import seedu.canoe.model.ModelManager;



public class TrainingCommandTest {

    private Model model = new ModelManager();
    private Model expectedModel = new ModelManager();
    private LocalDateTime validDateTime = VALID_TRAINING.getDateTime();

    //tests with a valid Training object.
    @Test
    public void executeTrainingAcceptedByModelAddSuccessful() throws Exception {
        String expectedCommandResult = new TrainingCommand(validDateTime).execute(expectedModel).getFeedbackToUser();
        assertCommandSuccess(new TrainingCommand(VALID_TRAINING.getDateTime()),
                model, expectedCommandResult, expectedModel);
    }

    //tests with a invalid Training object set in the past
    @Test
    public void execute_pastTraining_throwsCommandException() throws Exception {
        LocalDateTime pastDateTime = LocalDateTime.parse("2020-08-26 1800",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        TrainingCommand pastTrainingCommand = new TrainingCommand(pastDateTime);
        assertThrows(CommandException.class, TrainingCommand.MESSAGE_PAST_TRAINING, () ->
            pastTrainingCommand.execute(expectedModel));
    }

    //tests with a invalid Training object with invalid month.
    @Test
    public void executeTrainingRejectedByModelAddFailure() throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        assertThrows(DateTimeParseException.class, () -> new TrainingCommand(LocalDateTime
                .parse("2000-20-11 1900", formatter)));
    }

    //tests with a invalid Training object with wrong format.
    @Test
    public void executeTrainingRejectedByModelAddFailure2() throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
        assertThrows(DateTimeParseException.class, () -> new TrainingCommand(LocalDateTime
                .parse("2000-20-11", formatter)));
    }
}
