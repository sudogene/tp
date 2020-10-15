package seedu.canoe.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.canoe.logic.commands.CommandTestUtil.INVALID_ID_ARRAY;
import static seedu.canoe.logic.commands.CommandTestUtil.VALID_ID_ARRAY;
import static seedu.canoe.logic.commands.CommandTestUtil.VALID_ID_ARRAY2;
import static seedu.canoe.logic.commands.CommandTestUtil.VALID_ID_ARRAY3;
import static seedu.canoe.logic.commands.CommandTestUtil.VALID_ID_STRINGS;
import static seedu.canoe.logic.commands.CommandTestUtil.VALID_ID_STRINGS3;
import static seedu.canoe.testutil.Assert.assertThrows;
import static seedu.canoe.testutil.LocalDateTimeUtil.VALID_LOCAL_DATE_TIME_5;
import static seedu.canoe.testutil.LocalDateTimeUtil.VALID_LOCAL_DATE_TIME_6;
import static seedu.canoe.testutil.TypicalIndexes.INDEX_FIRST_TRAINING;
import static seedu.canoe.testutil.TypicalIndexes.INDEX_SECOND_TRAINING;
import static seedu.canoe.testutil.TypicalIndexes.INDEX_THIRD_TRAINING;

import org.junit.jupiter.api.Test;

import seedu.canoe.commons.core.Messages;
import seedu.canoe.logic.commands.exceptions.CommandException;
import seedu.canoe.model.Model;
import seedu.canoe.model.ModelManager;
import seedu.canoe.model.UserPrefs;
import seedu.canoe.testutil.TypicalStudentsInTypicalTrainings;

public class AddStudentToTrainingCommandTest {

    private Model model = new ModelManager(TypicalStudentsInTypicalTrainings.getTypicalAddressBook(), new UserPrefs());

    public Model getModel() {
        return model;
    }

    @Test
    public void constructor_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new AddStudentToTrainingCommand(INDEX_FIRST_TRAINING, null));
    }

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new AddStudentToTrainingCommand(null, VALID_ID_ARRAY));
    }

    @Test
    public void execute_studentAcceptedByModelAndDuplicate_addSuccessfulAndFail() throws Exception {
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
        assertThrows(CommandException.class, Messages.MESSAGE_DUPLICATE_STUDENTS_IN_TRAINING, () ->
                addStudentToTrainingCommand.execute(getModel()));
    }

    @Test
    public void execute_multipleStudentAcceptedByModel_addSuccessful() throws Exception {
        AddStudentToTrainingCommand addStudentToTrainingCommand =
                new AddStudentToTrainingCommand(INDEX_FIRST_TRAINING, VALID_ID_ARRAY3);
        CommandResult commandResult = addStudentToTrainingCommand.execute(getModel());
        assertEquals(String.format(AddStudentToTrainingCommand.MESSAGE_ADD_STUDENT_SUCCESS, VALID_ID_STRINGS3),
                commandResult.getFeedbackToUser());
        //Student 1 should have dateTime added to his field
        assertTrue(getModel().getFilteredStudentList().get(0).containsTraining(VALID_LOCAL_DATE_TIME_5));
        //Student 2 should have dateTime added to his field
        assertTrue(getModel().getFilteredStudentList().get(1).containsTraining(VALID_LOCAL_DATE_TIME_5));
        //Student 3 should have dateTime added to his field
        assertTrue(getModel().getFilteredStudentList().get(2).containsTraining(VALID_LOCAL_DATE_TIME_5));
        //Three students inside of the training container
        assertTrue(getModel().getFilteredTrainingList().get(0).getStudents().size() == 3);
        //Training class should contain student1 too
        assertTrue(getModel().getFilteredTrainingList().get(0).getStudents().contains(getModel()
                .getFilteredStudentList().get(0)));
        //Training class should contain student2 too
        assertTrue(getModel().getFilteredTrainingList().get(0).getStudents().contains(getModel()
                .getFilteredStudentList().get(1)));
        //Training class should contain student3 too
        assertTrue(getModel().getFilteredTrainingList().get(0).getStudents().contains(getModel()
                .getFilteredStudentList().get(2)));
        assertThrows(CommandException.class, Messages.MESSAGE_DUPLICATE_STUDENTS_IN_TRAINING, () ->
                addStudentToTrainingCommand.execute(getModel()));
    }

    @Test
    public void execute_studentInvalidIndex_throwsCommandException() throws Exception {
        AddStudentToTrainingCommand addStudentToTrainingCommand =
                new AddStudentToTrainingCommand(INDEX_SECOND_TRAINING, INVALID_ID_ARRAY);
        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX, () ->
                addStudentToTrainingCommand.execute(getModel()));
        //Student should not have dateTime added to his field
        assertFalse(getModel().getFilteredStudentList().get(0).containsTraining(VALID_LOCAL_DATE_TIME_6));
        //Student Jonas should not be inside of the training container
        assertTrue(getModel().getFilteredTrainingList().get(1).getStudents().size() == 0);
        //Training class should not contain JONAS too
        assertFalse(getModel().getFilteredTrainingList().get(1).getStudents().contains(getModel()
                .getFilteredStudentList().get(0)));
    }

    @Test
    public void execute_trainingInvalidIndex_throwsCommandException() throws Exception {
        AddStudentToTrainingCommand addStudentToTrainingCommand =
                new AddStudentToTrainingCommand(INDEX_THIRD_TRAINING, VALID_ID_ARRAY);
        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_TRAINING_DISPLAYED_INDEX, () ->
                addStudentToTrainingCommand.execute(getModel()));
    }

    @Test
    public void execute_studentDismissalTimeOver_throwsCommandException() throws Exception {
        AddStudentToTrainingCommand addStudentToTrainingCommand =
                new AddStudentToTrainingCommand(INDEX_SECOND_TRAINING, VALID_ID_ARRAY);
        assertThrows(CommandException.class, AddStudentToTrainingCommand.MESSAGE_STUDENT_UNAVAILABLE, () ->
                addStudentToTrainingCommand.execute(getModel()));
        //Student should not have dateTime added to his field
        assertFalse(getModel().getFilteredStudentList().get(0).containsTraining(VALID_LOCAL_DATE_TIME_6));
        //Student Jonas should not be inside of the training container
        assertTrue(getModel().getFilteredTrainingList().get(1).getStudents().size() == 0);
        //Training class should not contain JONAS too
        assertFalse(getModel().getFilteredTrainingList().get(1).getStudents().contains(getModel()
                .getFilteredStudentList().get(0)));
    }

    @Test
    public void equals() {
        AddStudentToTrainingCommand addStudent1Command =
                new AddStudentToTrainingCommand(INDEX_FIRST_TRAINING, VALID_ID_ARRAY);
        AddStudentToTrainingCommand addStudent12Command =
                new AddStudentToTrainingCommand(INDEX_FIRST_TRAINING, VALID_ID_ARRAY2);

        // same object -> returns true
        assertTrue(addStudent1Command.equals(addStudent1Command));

        // same values -> returns true
        AddStudentToTrainingCommand addStudentToTrainingCommandCopy =
                new AddStudentToTrainingCommand(INDEX_FIRST_TRAINING, VALID_ID_ARRAY);
        assertTrue(addStudent1Command.equals(addStudentToTrainingCommandCopy));

        // different types -> returns false
        assertFalse(addStudent1Command.equals(1));

        // null -> returns false
        assertFalse(addStudent1Command.equals(null));

        // different student -> returns false
        assertFalse(addStudent1Command.equals(addStudent12Command));
    }
}
