package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ID_ARRAY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_ARRAY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_ARRAY2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_ARRAY3;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_STRINGS;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ID_STRINGS3;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.LocalDateTimeUtil.VALID_LOCAL_DATE_TIME_5;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TRAINING;
import static seedu.address.testutil.TypicalIndexes.INDEX_THIRD_TRAINING;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.testutil.TypicalStudentsInTypicalTrainings;

public class DeleteStudentFromTrainingCommandTest {

    private Model model = new ModelManager(TypicalStudentsInTypicalTrainings.getTypicalAddressBook(), new UserPrefs());

    public Model getModel() {
        return model;
    }

    @Test
    public void constructor_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new DeleteStudentFromTrainingCommand(INDEX_FIRST_STUDENT, null));
    }

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new DeleteStudentFromTrainingCommand(null, VALID_ID_ARRAY));
    }

    @Test
    public void execute_studentAcceptedByModelNoStudent_removeSuccessfulAndFail() throws Exception {
        DeleteStudentFromTrainingCommand deleteStudentFromTrainingCommand =
                new DeleteStudentFromTrainingCommand(INDEX_FIRST_TRAINING, VALID_ID_ARRAY);
        assertThrows(CommandException.class, DeleteStudentFromTrainingCommand.MESSAGE_INVALID_STUDENT, () ->
                deleteStudentFromTrainingCommand.execute(getModel()));

        AddStudentToTrainingCommand addStudentToTrainingCommand =
                new AddStudentToTrainingCommand(INDEX_FIRST_TRAINING, VALID_ID_ARRAY);
        CommandResult commandResult = addStudentToTrainingCommand.execute(getModel());
        assertEquals(String.format(AddStudentToTrainingCommand.MESSAGE_ADD_STUDENT_SUCCESS, VALID_ID_STRINGS),
                commandResult.getFeedbackToUser());
        //Student should have dateTime added to his field
        assertTrue(getModel().getFilteredStudentList().get(0).containsTraining(VALID_LOCAL_DATE_TIME_5));
        //One student Jonas inside of the training container
        assertTrue(getModel().getFilteredTrainingList().get(0).getStudents().size() == 1);
        //Training class should contain JONAS too
        assertTrue(getModel().getFilteredTrainingList().get(0).getStudents().contains(getModel()
                .getFilteredStudentList().get(0)));

        CommandResult commandResult2 = deleteStudentFromTrainingCommand.execute(getModel());
        assertEquals(String.format(DeleteStudentFromTrainingCommand.MESSAGE_DELETE_STUDENT_SUCCESS, VALID_ID_STRINGS),
                commandResult2.getFeedbackToUser());
        //Student should not have dateTime in his field anymore
        assertFalse(getModel().getFilteredStudentList().get(0).containsTraining(VALID_LOCAL_DATE_TIME_5));
        //Nobody inside of the training container
        assertTrue(getModel().getFilteredTrainingList().get(0).getStudents().size() == 0);
        //Training class should not contain JONAS too
        assertFalse(getModel().getFilteredTrainingList().get(0).getStudents().contains(getModel()
                .getFilteredStudentList().get(0)));

    }

    @Test
    public void execute_multipleStudentAcceptedByModel_deleteSuccessful() throws Exception {
        AddStudentToTrainingCommand addStudentToTrainingCommand =
                new AddStudentToTrainingCommand(INDEX_FIRST_TRAINING, VALID_ID_ARRAY3);
        CommandResult commandResult = addStudentToTrainingCommand.execute(getModel());
        assertEquals(String.format(AddStudentToTrainingCommand.MESSAGE_ADD_STUDENT_SUCCESS, VALID_ID_STRINGS3),
                commandResult.getFeedbackToUser());

        DeleteStudentFromTrainingCommand deleteStudentFromTrainingCommand =
                new DeleteStudentFromTrainingCommand(INDEX_FIRST_TRAINING, VALID_ID_ARRAY3);
        CommandResult commandResult2 = deleteStudentFromTrainingCommand.execute(getModel());
        assertEquals(String.format(DeleteStudentFromTrainingCommand.MESSAGE_DELETE_STUDENT_SUCCESS, VALID_ID_STRINGS3),
                commandResult2.getFeedbackToUser());

        //Student 1 should not have dateTime added to his field
        assertFalse(getModel().getFilteredStudentList().get(0).containsTraining(VALID_LOCAL_DATE_TIME_5));
        //Student 2 should not have dateTime added to his field
        assertFalse(getModel().getFilteredStudentList().get(1).containsTraining(VALID_LOCAL_DATE_TIME_5));
        //Student 3 should not have dateTime added to his field
        assertFalse(getModel().getFilteredStudentList().get(2).containsTraining(VALID_LOCAL_DATE_TIME_5));
        //0 students inside of the training container
        assertTrue(getModel().getFilteredTrainingList().get(0).getStudents().size() == 0);
        //Training class should not contain student1 too
        assertFalse(getModel().getFilteredTrainingList().get(0).getStudents().contains(getModel()
                .getFilteredStudentList().get(0)));
        //Training class should not contain student2 too
        assertFalse(getModel().getFilteredTrainingList().get(0).getStudents().contains(getModel()
                .getFilteredStudentList().get(1)));
        //Training class should not contain student3 too
        assertFalse(getModel().getFilteredTrainingList().get(0).getStudents().contains(getModel()
                .getFilteredStudentList().get(2)));
        assertThrows(CommandException.class, DeleteStudentFromTrainingCommand.MESSAGE_INVALID_STUDENT, () ->
                deleteStudentFromTrainingCommand.execute(getModel()));
    }

    @Test
    public void execute_studentInvalidIndex_throwsCommandException() throws Exception {
        AddStudentToTrainingCommand addStudentToTrainingCommand =
                new AddStudentToTrainingCommand(INDEX_FIRST_TRAINING, VALID_ID_ARRAY);
        CommandResult commandResult = addStudentToTrainingCommand.execute(getModel());
        DeleteStudentFromTrainingCommand deleteStudentFromTrainingCommand =
                new DeleteStudentFromTrainingCommand(INDEX_FIRST_TRAINING, INVALID_ID_ARRAY);
        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX, () ->
                deleteStudentFromTrainingCommand.execute(getModel()));
        //Student should still have dateTime in his field
        assertTrue(getModel().getFilteredStudentList().get(0).containsTraining(VALID_LOCAL_DATE_TIME_5));
        //Student Jonas should be inside of the training container
        assertFalse(getModel().getFilteredTrainingList().get(0).getStudents().size() == 0);
        //Training class should contain JONAS too
        assertTrue(getModel().getFilteredTrainingList().get(0).getStudents().contains(getModel()
                .getFilteredStudentList().get(0)));
    }

    @Test
    public void execute_trainingInvalidIndex_throwsCommandException() throws Exception {
        DeleteStudentFromTrainingCommand deleteStudentFromTrainingCommand =
                new DeleteStudentFromTrainingCommand(INDEX_THIRD_TRAINING, VALID_ID_ARRAY);
        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_TRAINING_DISPLAYED_INDEX, () ->
                deleteStudentFromTrainingCommand.execute(getModel()));
    }

    @Test
    public void equals() {
        DeleteStudentFromTrainingCommand deleteStudent1Command =
                new DeleteStudentFromTrainingCommand(INDEX_FIRST_STUDENT, VALID_ID_ARRAY);
        DeleteStudentFromTrainingCommand deleteStudent12Command =
                new DeleteStudentFromTrainingCommand(INDEX_FIRST_STUDENT, VALID_ID_ARRAY2);

        // same object -> returns true
        assertTrue(deleteStudent1Command.equals(deleteStudent1Command));

        // same values -> returns true
        DeleteStudentFromTrainingCommand deleteStudentFromTrainingCommandCopy =
                new DeleteStudentFromTrainingCommand(INDEX_FIRST_STUDENT, VALID_ID_ARRAY);
        assertTrue(deleteStudent1Command.equals(deleteStudentFromTrainingCommandCopy));

        // different types -> returns false
        assertFalse(deleteStudent1Command.equals(1));

        // null -> returns false
        assertFalse(deleteStudent1Command.equals(null));

        // different student -> returns false
        assertFalse(deleteStudent1Command.equals(deleteStudent12Command));
    }
}
