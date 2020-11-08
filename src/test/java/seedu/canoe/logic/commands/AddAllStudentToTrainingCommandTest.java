package seedu.canoe.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.canoe.logic.commands.CommandTestUtil.VALID_ID_LIST;
import static seedu.canoe.logic.commands.CommandTestUtil.VALID_ID_LIST_2;
import static seedu.canoe.logic.commands.CommandTestUtil.VALID_ID_LIST_6;
import static seedu.canoe.logic.commands.CommandTestUtil.VALID_ID_STRINGS_6;
import static seedu.canoe.testutil.Assert.assertThrows;
import static seedu.canoe.testutil.LocalDateTimeUtil.VALID_LOCAL_DATE_TIME_2;
import static seedu.canoe.testutil.TypicalIndexes.INDEX_FIFTH_TRAINING;
import static seedu.canoe.testutil.TypicalIndexes.INDEX_FIRST_TRAINING;
import static seedu.canoe.testutil.TypicalIndexes.INDEX_FOURTH_TRAINING;
import static seedu.canoe.testutil.TypicalIndexes.INDEX_SECOND_TRAINING;
import static seedu.canoe.testutil.TypicalStudents.BOB;

import org.junit.jupiter.api.Test;

import seedu.canoe.commons.core.Messages;
import seedu.canoe.logic.commands.exceptions.CommandException;
import seedu.canoe.model.Model;
import seedu.canoe.model.ModelManager;
import seedu.canoe.model.UserPrefs;
import seedu.canoe.model.student.AllMatchPredicateList;
import seedu.canoe.model.student.Attendance;
import seedu.canoe.model.student.Student;
import seedu.canoe.model.training.Training;
import seedu.canoe.testutil.StudentBuilder;
import seedu.canoe.testutil.TypicalStudentsInTypicalTrainings;

public class AddAllStudentToTrainingCommandTest {

    private Model model = new ModelManager(TypicalStudentsInTypicalTrainings.getTypicalAddressBook(), new UserPrefs());

    public Model getModel() {
        return model;
    }

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new AddAllStudentToTrainingCommand(null));
    }

    @Test
    public void isAbleToAddStudent() {
        Training training = model.getFilteredTrainingList().get(3);
        Student student = new StudentBuilder(BOB).build();
        assertTrue(AddAllStudentToTrainingCommand.isAbleToAddStudent(student, training));
    }

    @Test
    public void execute_trainingInvalidIndex_throwsCommandException() throws Exception {
        AddAllStudentToTrainingCommand addAllStudentToTrainingCommand =
                new AddAllStudentToTrainingCommand(INDEX_FIFTH_TRAINING);
        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_TRAINING_DISPLAYED_INDEX, () ->
                addAllStudentToTrainingCommand.execute(getModel()));
    }

    @Test
    public void execute_emptyStudentList_throwsCommandException() throws Exception {
        AddAllStudentToTrainingCommand addAllStudentToTrainingCommand =
                new AddAllStudentToTrainingCommand(INDEX_FIRST_TRAINING);
        getModel().updateFilteredStudentList(new AllMatchPredicateList());
        assertThrows(CommandException.class, addAllStudentToTrainingCommand.MESSAGE_NO_STUDENTS, () ->
                addAllStudentToTrainingCommand.execute(getModel()));
    }

    @Test
    public void execute_studentAddToPastTraining_addFail() throws Exception {
        AddAllStudentToTrainingCommand addAllStudentToTrainingCommand =
                new AddAllStudentToTrainingCommand(INDEX_SECOND_TRAINING);
        getModel().updateFilteredStudentList(Model.PREDICATE_SHOW_ALL_STUDENTS);
        assertThrows(CommandException.class, AddAllStudentToTrainingCommand.MESSAGE_TRAINING_CANNOT_ADD, () ->
                addAllStudentToTrainingCommand.execute(getModel()));
    }

    @Test
    public void execute_studentsAcceptedByModel_addSuccessful() throws Exception {
        AddAllStudentToTrainingCommand addAllStudentToTrainingCommand =
                new AddAllStudentToTrainingCommand(INDEX_FOURTH_TRAINING);
        CommandResult commandResult = addAllStudentToTrainingCommand.execute(getModel());
        assertEquals(String.format(AddAllStudentToTrainingCommand.MESSAGE_ADD_STUDENT_SUCCESS,
                "1, 2, 3, 4"),
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

        //Four students inside of the training container
        assertTrue(getModel().getFilteredTrainingList().get(3).getStudents().size() == 4);
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
    public void equals() {
        AddAllStudentToTrainingCommand addAllStudent1Command =
                new AddAllStudentToTrainingCommand(INDEX_FIRST_TRAINING);
        AddAllStudentToTrainingCommand addAllStudent2Command =
                new AddAllStudentToTrainingCommand(INDEX_SECOND_TRAINING);

        // same object -> returns true
        assertTrue(addAllStudent1Command.equals(addAllStudent1Command));

        // same values -> returns true
        AddAllStudentToTrainingCommand addAllStudentToTrainingCommandCopy =
                new AddAllStudentToTrainingCommand(INDEX_FIRST_TRAINING);
        assertTrue(addAllStudent1Command.equals(addAllStudentToTrainingCommandCopy));

        // different types -> returns false
        assertFalse(addAllStudent1Command.equals(1));

        // null -> returns false
        assertFalse(addAllStudent1Command.equals(null));

        // different training -> returns false
        assertFalse(addAllStudent1Command.equals(addAllStudent2Command));
    }

}
