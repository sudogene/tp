package seedu.canoe.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.canoe.logic.commands.CommandTestUtil.INVALID_ID_LIST;
import static seedu.canoe.logic.commands.CommandTestUtil.VALID_ID_LIST;
import static seedu.canoe.logic.commands.CommandTestUtil.VALID_ID_LIST_2;
import static seedu.canoe.logic.commands.CommandTestUtil.VALID_ID_LIST_5;
import static seedu.canoe.logic.commands.CommandTestUtil.VALID_ID_LIST_6;
import static seedu.canoe.logic.commands.CommandTestUtil.VALID_ID_STRINGS_5;
import static seedu.canoe.logic.commands.CommandTestUtil.VALID_ID_STRINGS_6;
import static seedu.canoe.testutil.Assert.assertThrows;
import static seedu.canoe.testutil.LocalDateTimeUtil.DATE_TIME_NOW_PLUS_ONE_DAY;
import static seedu.canoe.testutil.LocalDateTimeUtil.VALID_LOCAL_DATE_TIME_2;
import static seedu.canoe.testutil.LocalDateTimeUtil.VALID_LOCAL_DATE_TIME_3;
import static seedu.canoe.testutil.TypicalIndexes.INDEX_FIFTH_TRAINING;
import static seedu.canoe.testutil.TypicalIndexes.INDEX_FIRST_TRAINING;
import static seedu.canoe.testutil.TypicalIndexes.INDEX_FOURTH_TRAINING;
import static seedu.canoe.testutil.TypicalIndexes.INDEX_SECOND_TRAINING;
import static seedu.canoe.testutil.TypicalIndexes.INDEX_THIRD_TRAINING;

import org.junit.jupiter.api.Test;

import seedu.canoe.commons.core.Messages;
import seedu.canoe.logic.commands.exceptions.CommandException;
import seedu.canoe.model.Model;
import seedu.canoe.model.ModelManager;
import seedu.canoe.model.UserPrefs;
import seedu.canoe.model.student.Attendance;
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
                new AddStudentToTrainingCommand(null, VALID_ID_LIST));
    }

    @Test
    public void execute_studentDuplicateInTraining_addFail() throws Exception {
        //JONAS is already in the first training
        AddStudentToTrainingCommand addStudentToTrainingCommand =
                new AddStudentToTrainingCommand(INDEX_FIRST_TRAINING, VALID_ID_LIST);
        assertThrows(CommandException.class, Messages.MESSAGE_DUPLICATE_STUDENTS_IN_TRAINING, () ->
                addStudentToTrainingCommand.execute(getModel()));
    }

    @Test
    public void execute_studentAddToPastTraining_addFail() throws Exception {
        AddStudentToTrainingCommand addStudentToTrainingCommand =
                new AddStudentToTrainingCommand(INDEX_SECOND_TRAINING, VALID_ID_LIST_2);
        assertThrows(CommandException.class, AddStudentToTrainingCommand.MESSAGE_TRAINING_CANNOT_ADD, () ->
                addStudentToTrainingCommand.execute(getModel()));
    }

    @Test
    public void execute_studentAcceptedByModel_addSuccessful() throws Exception {
        AddStudentToTrainingCommand addStudentToTrainingCommand =
                new AddStudentToTrainingCommand(INDEX_FOURTH_TRAINING, VALID_ID_LIST_5);
        CommandResult commandResult = addStudentToTrainingCommand.execute(getModel());
        assertEquals(String.format(AddStudentToTrainingCommand.MESSAGE_ADD_STUDENT_SUCCESS, VALID_ID_STRINGS_5)
                        + " to Training Session 4",
                commandResult.getFeedbackToUser());

        //JOCK should have dateTime added to his field
        assertTrue(getModel().getFilteredStudentList().get(3)
                .containsAttendance(new Attendance(VALID_LOCAL_DATE_TIME_2)));
        //One student JONAS now inside of the training container
        assertTrue(getModel().getFilteredTrainingList().get(3).getStudents().size() == 1);

        //Training class should still contain JONAS too
        assertTrue(getModel().getFilteredTrainingList().get(3).getStudents().contains(getModel()
                .getFilteredStudentList().get(3)));
    }

    @Test
    public void execute_multipleStudentAcceptedByModel_addSuccessful() throws Exception {
        AddStudentToTrainingCommand addStudentToTrainingCommand =
                new AddStudentToTrainingCommand(INDEX_FOURTH_TRAINING, VALID_ID_LIST_6);
        CommandResult commandResult = addStudentToTrainingCommand.execute(getModel());
        assertEquals(String.format(AddStudentToTrainingCommand.MESSAGE_ADD_STUDENT_SUCCESS, VALID_ID_STRINGS_6
                        + " to Training Session 4"),
                commandResult.getFeedbackToUser());
        //Student 1 should already have dateTime in his field
        assertTrue(getModel().getFilteredStudentList().get(0)
                .containsAttendance(new Attendance(VALID_LOCAL_DATE_TIME_2)));
        //Student 2 should have dateTime added to his field
        assertTrue(getModel().getFilteredStudentList().get(1)
                .containsAttendance(new Attendance(VALID_LOCAL_DATE_TIME_2)));
        //Student 3 should have dateTime added to his field
        assertTrue(getModel().getFilteredStudentList().get(2)
                .containsAttendance(new Attendance(VALID_LOCAL_DATE_TIME_2)));

        //Three students inside of the training container
        assertTrue(getModel().getFilteredTrainingList().get(3).getStudents().size() == 3);
        //Training class should contain student1 too
        assertTrue(getModel().getFilteredTrainingList().get(3).getStudents().contains(getModel()
                .getFilteredStudentList().get(0)));
        //Training class should contain student2 too
        assertTrue(getModel().getFilteredTrainingList().get(3).getStudents().contains(getModel()
                .getFilteredStudentList().get(1)));
        //Training class should contain student3 too
        assertTrue(getModel().getFilteredTrainingList().get(3).getStudents().contains(getModel()
                .getFilteredStudentList().get(2)));
    }

    @Test
    public void execute_studentInvalidIndex_throwsCommandException() throws Exception {
        AddStudentToTrainingCommand addStudentToTrainingCommand =
                new AddStudentToTrainingCommand(INDEX_FIRST_TRAINING, INVALID_ID_LIST);
        assertThrows(CommandException.class, CommandUtil.MESSAGE_STUDENT_DOES_NOT_EXIST, () ->
                addStudentToTrainingCommand.execute(getModel()));
        //Student JONAS should still have dateTime in his field
        assertTrue(getModel().getFilteredStudentList().get(0)
                .containsAttendance(new Attendance(DATE_TIME_NOW_PLUS_ONE_DAY)));
        //Training container should still only contain Jonas
        assertTrue(getModel().getFilteredTrainingList().get(0).getStudents().size() == 1);
        //Training class should still contain JONAS too
        assertTrue(getModel().getFilteredTrainingList().get(0).getStudents().contains(getModel()
                .getFilteredStudentList().get(0)));
    }

    @Test
    public void execute_trainingInvalidIndex_throwsCommandException() throws Exception {
        AddStudentToTrainingCommand addStudentToTrainingCommand =
                new AddStudentToTrainingCommand(INDEX_FIFTH_TRAINING, VALID_ID_LIST);
        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_TRAINING_DISPLAYED_INDEX, () ->
                addStudentToTrainingCommand.execute(getModel()));
    }

    @Test
    public void execute_studentDismissalTimeOver_throwsCommandException() throws Exception {
        AddStudentToTrainingCommand addStudentToTrainingCommand =
                new AddStudentToTrainingCommand(INDEX_THIRD_TRAINING, VALID_ID_LIST);
        assertThrows(CommandException.class, AddStudentToTrainingCommand.MESSAGE_STUDENT_UNAVAILABLE, () ->
                addStudentToTrainingCommand.execute(getModel()));
        //Student JONAS should not have dateTime added to his field
        assertFalse(getModel().getFilteredStudentList().get(0).containsAttendance(
            new Attendance(VALID_LOCAL_DATE_TIME_3)));
        //Student JONAS should not be inside of the training container
        assertTrue(getModel().getFilteredTrainingList().get(1).getStudents().size() == 0);
        //Training class should not contain JONAS too
        assertFalse(getModel().getFilteredTrainingList().get(1).getStudents().contains(getModel()
                .getFilteredStudentList().get(0)));
    }

    @Test
    public void equals() {
        AddStudentToTrainingCommand addStudent1Command =
                new AddStudentToTrainingCommand(INDEX_FIRST_TRAINING, VALID_ID_LIST);
        AddStudentToTrainingCommand addStudent12Command =
                new AddStudentToTrainingCommand(INDEX_FIRST_TRAINING, VALID_ID_LIST_2);

        // same object -> returns true
        assertTrue(addStudent1Command.equals(addStudent1Command));

        // same values -> returns true
        AddStudentToTrainingCommand addStudentToTrainingCommandCopy =
                new AddStudentToTrainingCommand(INDEX_FIRST_TRAINING, VALID_ID_LIST);
        assertTrue(addStudent1Command.equals(addStudentToTrainingCommandCopy));

        // different types -> returns false
        assertFalse(addStudent1Command.equals(1));

        // null -> returns false
        assertFalse(addStudent1Command.equals(null));

        // different student -> returns false
        assertFalse(addStudent1Command.equals(addStudent12Command));
    }
}
