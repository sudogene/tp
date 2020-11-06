package seedu.canoe.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.canoe.logic.commands.CommandTestUtil.INVALID_ID_LIST;
import static seedu.canoe.logic.commands.CommandTestUtil.VALID_ID_LIST;
import static seedu.canoe.logic.commands.CommandTestUtil.VALID_ID_LIST_2;
import static seedu.canoe.logic.commands.CommandTestUtil.VALID_ID_STRINGS;
import static seedu.canoe.testutil.Assert.assertThrows;
import static seedu.canoe.testutil.LocalDateTimeUtil.DATE_TIME_NOW_PLUS_ONE_DAY;
import static seedu.canoe.testutil.TypicalIndexes.INDEX_FIFTH_TRAINING;
import static seedu.canoe.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;
import static seedu.canoe.testutil.TypicalIndexes.INDEX_FIRST_TRAINING;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.canoe.commons.core.Messages;
import seedu.canoe.logic.commands.exceptions.CommandException;
import seedu.canoe.model.Model;
import seedu.canoe.model.ModelManager;
import seedu.canoe.model.UserPrefs;
import seedu.canoe.model.student.Attendance;
import seedu.canoe.model.student.Id;
import seedu.canoe.testutil.TypicalStudentsInTypicalTrainings;

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
                new DeleteStudentFromTrainingCommand(null, VALID_ID_LIST));
    }

    @Test
    public void execute_studentAcceptedByModel_removeSuccessful() throws Exception {
        DeleteStudentFromTrainingCommand deleteStudentFromTrainingCommand =
                new DeleteStudentFromTrainingCommand(INDEX_FIRST_TRAINING, VALID_ID_LIST);
        CommandResult commandResult2 = deleteStudentFromTrainingCommand.execute(getModel());
        assertEquals(String.format(DeleteStudentFromTrainingCommand.MESSAGE_DELETE_STUDENT_SUCCESS, VALID_ID_STRINGS)
                + " from Training Session 1",
                commandResult2.getFeedbackToUser());
        //Student JONAS should not have dateTime in his field anymore
        assertFalse(getModel().getFilteredStudentList().get(0)
                .containsAttendance(new Attendance(DATE_TIME_NOW_PLUS_ONE_DAY)));
        //Nobody inside of the training container
        assertTrue(getModel().getFilteredTrainingList().get(0).getStudents().size() == 0);
        //Training class should not contain JONAS too
        assertFalse(getModel().getFilteredTrainingList().get(0).getStudents().contains(getModel()
                .getFilteredStudentList().get(0)));

    }

    @Test
    public void hasUniqueStudentsToDelete() {
        List<Id> uniqueList = List.of(new Id("1"), new Id("2"), new Id("3"));
        Id idRepeated = new Id("2");
        List<Id> nonUniqueList = List.of(new Id("1"), idRepeated, idRepeated);

        assertTrue(DeleteStudentFromTrainingCommand.hasUniqueStudentsToDelete(uniqueList));
        assertFalse(DeleteStudentFromTrainingCommand.hasUniqueStudentsToDelete(nonUniqueList));
    }

    @Test
    public void execute_studentNotInTraining_removeFail() {
        DeleteStudentFromTrainingCommand deleteStudentFromTrainingCommand =
                new DeleteStudentFromTrainingCommand(INDEX_FIRST_TRAINING, VALID_ID_LIST_2);
        assertThrows(CommandException.class, DeleteStudentFromTrainingCommand.MESSAGE_INVALID_STUDENT, () ->
                deleteStudentFromTrainingCommand.execute(getModel()));
    }

    @Test
    public void execute_multipleStudentAcceptedByModel_deleteSuccessful() throws Exception {
        DeleteStudentFromTrainingCommand deleteStudentFromTrainingCommand =
                new DeleteStudentFromTrainingCommand(INDEX_FIRST_TRAINING, VALID_ID_LIST);
        CommandResult commandResult2 = deleteStudentFromTrainingCommand.execute(getModel());
        assertEquals(String.format(DeleteStudentFromTrainingCommand
                        .MESSAGE_DELETE_STUDENT_SUCCESS, VALID_ID_STRINGS) + " from Training Session 1",
                commandResult2.getFeedbackToUser());
        //Student 1 should not have dateTime added to his field
        assertFalse(getModel().getFilteredStudentList().get(0)
                .containsAttendance(new Attendance(DATE_TIME_NOW_PLUS_ONE_DAY)));
        //Student 2 should not have dateTime added to his field
        assertFalse(getModel().getFilteredStudentList().get(1)
                .containsAttendance(new Attendance(DATE_TIME_NOW_PLUS_ONE_DAY)));
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
    }

    @Test
    public void execute_studentInvalidIndex_throwsCommandException() throws Exception {
        DeleteStudentFromTrainingCommand deleteStudentFromTrainingCommand =
                new DeleteStudentFromTrainingCommand(INDEX_FIRST_TRAINING, INVALID_ID_LIST);
        assertThrows(CommandException.class, CommandUtil.MESSAGE_STUDENT_DOES_NOT_EXIST, () ->
                deleteStudentFromTrainingCommand.execute(getModel()));
        //Student should still have dateTime in his field
        assertTrue(getModel().getFilteredStudentList().get(0)
                .containsAttendance(new Attendance(DATE_TIME_NOW_PLUS_ONE_DAY)));
        //Student Jonas should be inside of the training container
        assertFalse(getModel().getFilteredTrainingList().get(0).getStudents().size() == 0);
        //Training class should contain JONAS too
        assertTrue(getModel().getFilteredTrainingList().get(0).getStudents().contains(getModel()
                .getFilteredStudentList().get(0)));
    }

    @Test
    public void execute_trainingInvalidIndex_throwsCommandException() throws Exception {
        DeleteStudentFromTrainingCommand deleteStudentFromTrainingCommand =
                new DeleteStudentFromTrainingCommand(INDEX_FIFTH_TRAINING, VALID_ID_LIST);
        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_TRAINING_DISPLAYED_INDEX, () ->
                deleteStudentFromTrainingCommand.execute(getModel()));
    }

    @Test
    public void equals() {
        DeleteStudentFromTrainingCommand deleteStudent1Command =
                new DeleteStudentFromTrainingCommand(INDEX_FIRST_STUDENT, VALID_ID_LIST);
        DeleteStudentFromTrainingCommand deleteStudent12Command =
                new DeleteStudentFromTrainingCommand(INDEX_FIRST_STUDENT, VALID_ID_LIST_2);

        // same object -> returns true
        assertTrue(deleteStudent1Command.equals(deleteStudent1Command));

        // same values -> returns true
        DeleteStudentFromTrainingCommand deleteStudentFromTrainingCommandCopy =
                new DeleteStudentFromTrainingCommand(INDEX_FIRST_STUDENT, VALID_ID_LIST);
        assertTrue(deleteStudent1Command.equals(deleteStudentFromTrainingCommandCopy));

        // different types -> returns false
        assertFalse(deleteStudent1Command.equals(1));

        // null -> returns false
        assertFalse(deleteStudent1Command.equals(null));

        // different student -> returns false
        assertFalse(deleteStudent1Command.equals(deleteStudent12Command));
    }
}
