package seedu.canoe.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.canoe.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.canoe.testutil.Assert.assertThrows;
import static seedu.canoe.testutil.TypicalStudentsInTypicalTrainings.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.canoe.commons.core.Messages;
import seedu.canoe.logic.commands.exceptions.CommandException;
import seedu.canoe.model.Model;
import seedu.canoe.model.ModelManager;
import seedu.canoe.model.UserPrefs;
import seedu.canoe.model.student.Id;
import seedu.canoe.model.student.IdMatchesPredicate;
import seedu.canoe.model.student.TrainingMatchesIdPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindStudentTrainingCommand}.
 */
public class FindStudentTrainingCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        Id firstIdValue = new Id("001");
        Id secondIdValue = new Id("002");

        IdMatchesPredicate firstStudentPredicate = new IdMatchesPredicate("001");
        IdMatchesPredicate secondStudentPredicate = new IdMatchesPredicate("002");
        TrainingMatchesIdPredicate firstTrainingPredicate = new TrainingMatchesIdPredicate(firstIdValue);
        TrainingMatchesIdPredicate secondTrainingPredicate = new TrainingMatchesIdPredicate(secondIdValue);

        FindStudentTrainingCommand findStudentTrainingFirstCommand =
                new FindStudentTrainingCommand(firstStudentPredicate, firstTrainingPredicate);
        FindStudentTrainingCommand findStudentTrainingSecondCommand =
                new FindStudentTrainingCommand(secondStudentPredicate, secondTrainingPredicate);
        FindStudentTrainingCommand findStudentTrainingThirdCommand =
                new FindStudentTrainingCommand(firstStudentPredicate, secondTrainingPredicate);

        // same object -> returns true
        assertTrue(findStudentTrainingSecondCommand.equals(findStudentTrainingSecondCommand));

        // same values -> returns true
        FindStudentTrainingCommand findStudentTrainingFirstCommandCopy =
                new FindStudentTrainingCommand(firstStudentPredicate, firstTrainingPredicate);
        assertTrue(findStudentTrainingFirstCommand.equals(findStudentTrainingFirstCommandCopy));

        // different types -> returns false
        assertFalse(findStudentTrainingFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findStudentTrainingFirstCommand.equals(null));

        // different student -> returns false
        assertFalse(findStudentTrainingSecondCommand.equals(findStudentTrainingThirdCommand));

        //different training -> returns false
        assertFalse(findStudentTrainingThirdCommand.equals(findStudentTrainingFirstCommand));

        //different training and student -> returns false
        assertFalse(findStudentTrainingFirstCommand.equals(findStudentTrainingSecondCommand));
    }

    @Test
    public void execute_studentIndexValid_findSuccessful() {
        TrainingMatchesIdPredicate idPredicate = new TrainingMatchesIdPredicate(new Id("1"));
        IdMatchesPredicate studentIdPredicate = new IdMatchesPredicate("1");
        FindStudentTrainingCommand command = new FindStudentTrainingCommand(studentIdPredicate, idPredicate);
        expectedModel.updateFilteredStudentList(studentIdPredicate);
        expectedModel.updateFilteredTrainingList(idPredicate);
        String expectedMessage = String.format(Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW,
                expectedModel.getFilteredStudentList().size()) + "\n"
                + String.format(Messages.MESSAGE_TRAININGS_LISTED_OVERVIEW,
                expectedModel.getFilteredTrainingList().size());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_studentIndexInvalid_studentNotFound() {
        TrainingMatchesIdPredicate idPredicate = new TrainingMatchesIdPredicate(new Id("4"));
        IdMatchesPredicate studentIdPredicate = new IdMatchesPredicate("4");
        FindStudentTrainingCommand command = new FindStudentTrainingCommand(studentIdPredicate, idPredicate);
        assertThrows(CommandException.class, FindStudentTrainingCommand.MESSAGE_STUDENT_DOES_NOT_EXIST, () ->
                command.execute(model));
    }

    @Test
    public void execute_studentIndexNull_studentNotFound() {
        TrainingMatchesIdPredicate idPredicate = new TrainingMatchesIdPredicate(null);
        IdMatchesPredicate studentIdPredicate = new IdMatchesPredicate(null);
        FindStudentTrainingCommand command = new FindStudentTrainingCommand(studentIdPredicate, idPredicate);
        assertThrows(CommandException.class, FindStudentTrainingCommand.MESSAGE_STUDENT_DOES_NOT_EXIST, () ->
                command.execute(model));
    }

    @Test
    public void execute_studentIndexEmpty_studentNotFound() {
        TrainingMatchesIdPredicate idPredicate = new TrainingMatchesIdPredicate(new Id("001"));
        IdMatchesPredicate studentIdPredicate = new IdMatchesPredicate("");
        FindStudentTrainingCommand command = new FindStudentTrainingCommand(studentIdPredicate, idPredicate);
        assertThrows(CommandException.class, FindStudentTrainingCommand.MESSAGE_STUDENT_DOES_NOT_EXIST, () ->
                command.execute(model));
    }

}
