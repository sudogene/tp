package seedu.canoe.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.canoe.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.canoe.testutil.Assert.assertThrows;
import static seedu.canoe.testutil.TypicalStudentsInTypicalTrainings.getTypicalAddressBook;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.canoe.commons.core.Messages;
import seedu.canoe.logic.commands.exceptions.CommandException;
import seedu.canoe.model.Model;
import seedu.canoe.model.ModelManager;
import seedu.canoe.model.UserPrefs;
import seedu.canoe.model.student.DateTimeMatchesPredicate;
import seedu.canoe.model.student.IdMatchesPredicate;
import seedu.canoe.model.training.TrainingMatchesDateTimePredicate;
import seedu.canoe.model.training.TrainingMatchesIdPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindStudentTrainingCommand}.
 */
public class FindStudentTrainingCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        String firstIdValue = "1";
        String secondIdValue = "2";
        LocalDateTime firstDateTime = LocalDateTime.parse("2021-08-26 1800",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        LocalDateTime secondDateTime = LocalDateTime.parse("2022-08-26 1800",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));

        IdMatchesPredicate firstStudentPredicate = new IdMatchesPredicate("1");
        IdMatchesPredicate secondStudentPredicate = new IdMatchesPredicate("2");
        DateTimeMatchesPredicate thirdStudentPredicate = new DateTimeMatchesPredicate(firstDateTime);
        DateTimeMatchesPredicate fourthStudentPredicate = new DateTimeMatchesPredicate(secondDateTime);
        TrainingMatchesIdPredicate firstTrainingPredicate = new TrainingMatchesIdPredicate(firstIdValue);
        TrainingMatchesIdPredicate secondTrainingPredicate = new TrainingMatchesIdPredicate(secondIdValue);
        TrainingMatchesDateTimePredicate thirdTrainingPredicate = new TrainingMatchesDateTimePredicate(firstDateTime);
        TrainingMatchesDateTimePredicate fourthTrainingPredicate = new TrainingMatchesDateTimePredicate(secondDateTime);

        FindStudentTrainingCommand findStudentTrainingFirstCommand =
                new FindStudentTrainingCommand(firstStudentPredicate, firstTrainingPredicate);
        FindStudentTrainingCommand findStudentTrainingSecondCommand =
                new FindStudentTrainingCommand(secondStudentPredicate, secondTrainingPredicate);
        FindStudentTrainingCommand findStudentTrainingThirdCommand =
                new FindStudentTrainingCommand(firstStudentPredicate, secondTrainingPredicate);
        FindStudentTrainingCommand findStudentTrainingFourthCommand =
                new FindStudentTrainingCommand(thirdStudentPredicate, thirdTrainingPredicate);
        FindStudentTrainingCommand findStudentTrainingFifthCommand =
                new FindStudentTrainingCommand(fourthStudentPredicate, fourthTrainingPredicate);

        // same object -> returns true
        assertTrue(findStudentTrainingSecondCommand.equals(findStudentTrainingSecondCommand));
        assertTrue(findStudentTrainingFourthCommand.equals(findStudentTrainingFourthCommand));

        // same values -> returns true
        FindStudentTrainingCommand findStudentTrainingFirstCommandCopy =
                new FindStudentTrainingCommand(firstStudentPredicate, firstTrainingPredicate);
        assertTrue(findStudentTrainingFirstCommand.equals(findStudentTrainingFirstCommandCopy));
        FindStudentTrainingCommand findStudentTrainingFourthCommandCopy =
                new FindStudentTrainingCommand(thirdStudentPredicate, thirdTrainingPredicate);
        assertTrue(findStudentTrainingFourthCommand.equals(findStudentTrainingFourthCommandCopy));

        // different types -> returns false
        assertFalse(findStudentTrainingFirstCommand.equals(1));
        assertFalse(findStudentTrainingThirdCommand.equals(1));
        assertFalse(findStudentTrainingFourthCommand.equals(1));

        // null -> returns false
        assertNotNull(findStudentTrainingFirstCommand);
        assertNotNull(findStudentTrainingThirdCommand);

        // different student -> returns false
        assertFalse(findStudentTrainingSecondCommand.equals(findStudentTrainingThirdCommand));
        assertFalse(findStudentTrainingThirdCommand.equals(findStudentTrainingFourthCommand));

        //different training -> returns false
        assertFalse(findStudentTrainingThirdCommand.equals(findStudentTrainingFirstCommand));

        //different training and student -> returns false
        assertFalse(findStudentTrainingFirstCommand.equals(findStudentTrainingSecondCommand));
        assertFalse(findStudentTrainingFourthCommand.equals(findStudentTrainingFifthCommand));
    }

    @Test
    public void execute_studentIndexValid_findSuccessful() {
        TrainingMatchesIdPredicate idPredicate = new TrainingMatchesIdPredicate("1");
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
    public void execute_studentIndexValidWithDateTime_findSuccessful() {
        LocalDateTime dateTime = LocalDateTime.parse("2021-08-26 1800",
                DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        TrainingMatchesDateTimePredicate dateTimePredicate = new TrainingMatchesDateTimePredicate(dateTime);
        IdMatchesPredicate studentIdPredicate = new IdMatchesPredicate("1");
        FindStudentTrainingCommand command = new FindStudentTrainingCommand(studentIdPredicate, dateTimePredicate);
        expectedModel.updateFilteredStudentList(studentIdPredicate);
        expectedModel.updateFilteredTrainingList(dateTimePredicate);
        String expectedMessage = String.format(Messages.MESSAGE_STUDENTS_LISTED_OVERVIEW,
                expectedModel.getFilteredStudentList().size()) + "\n"
                + String.format(Messages.MESSAGE_TRAININGS_LISTED_OVERVIEW,
                expectedModel.getFilteredTrainingList().size());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_studentIndexInvalid_studentNotFound() {
        TrainingMatchesIdPredicate idPredicate = new TrainingMatchesIdPredicate("4");
        IdMatchesPredicate studentIdPredicate = new IdMatchesPredicate("5");
        FindStudentTrainingCommand command = new FindStudentTrainingCommand(studentIdPredicate, idPredicate);
        assertThrows(CommandException.class, FindStudentTrainingCommand.MESSAGE_NO_MATCH, () ->
                command.execute(model));
    }

    @Test
    public void execute_studentIndexNull_studentNotFound() {
        TrainingMatchesIdPredicate idPredicate = new TrainingMatchesIdPredicate(null);
        IdMatchesPredicate studentIdPredicate = new IdMatchesPredicate(null);
        FindStudentTrainingCommand command = new FindStudentTrainingCommand(studentIdPredicate, idPredicate);
        assertThrows(CommandException.class, FindStudentTrainingCommand.MESSAGE_NO_MATCH, () ->
                command.execute(model));
    }

    @Test
    public void execute_studentIndexEmpty_studentNotFound() {
        TrainingMatchesIdPredicate idPredicate = new TrainingMatchesIdPredicate("001");
        IdMatchesPredicate studentIdPredicate = new IdMatchesPredicate("");
        FindStudentTrainingCommand command = new FindStudentTrainingCommand(studentIdPredicate, idPredicate);
        assertThrows(CommandException.class, FindStudentTrainingCommand.MESSAGE_NO_MATCH, () ->
                command.execute(model));
    }

}
