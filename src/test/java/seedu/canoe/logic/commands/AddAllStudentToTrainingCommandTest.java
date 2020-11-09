package seedu.canoe.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.canoe.testutil.Assert.assertThrows;
import static seedu.canoe.testutil.LocalDateTimeUtil.VALID_LOCAL_DATE_TIME_2;
import static seedu.canoe.testutil.LocalDateTimeUtil.VALID_LOCAL_DATE_TIME_3;
import static seedu.canoe.testutil.TypicalIndexes.INDEX_FIFTH_TRAINING;
import static seedu.canoe.testutil.TypicalIndexes.INDEX_FIRST_TRAINING;
import static seedu.canoe.testutil.TypicalIndexes.INDEX_SECOND_TRAINING;
import static seedu.canoe.testutil.TypicalStudents.BOB;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.canoe.commons.core.Messages;
import seedu.canoe.logic.commands.exceptions.CommandException;
import seedu.canoe.model.Model;
import seedu.canoe.model.ModelManager;
import seedu.canoe.model.UserPrefs;
import seedu.canoe.model.student.AllMatchPredicateList;
import seedu.canoe.model.student.Attendance;
import seedu.canoe.model.student.Id;
import seedu.canoe.model.student.Student;
import seedu.canoe.model.training.Training;
import seedu.canoe.testutil.StudentBuilder;
import seedu.canoe.testutil.TrainingBuilder;
import seedu.canoe.testutil.TypicalStudents;
import seedu.canoe.testutil.TypicalTraining;

public class AddAllStudentToTrainingCommandTest {

    private Model model = new ModelManager(TypicalStudents.getTypicalCanoeCoach(), new UserPrefs());

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
        getModel().addTraining(TypicalTraining.VALID_PAST_TRAINING);
        Training training = model.getFilteredTrainingList().get(0);
        Student student = new StudentBuilder(BOB).build();
        assertTrue(AddAllStudentToTrainingCommand.isAbleToAddStudent(student, training));
        getModel().deleteTraining(TypicalTraining.VALID_PAST_TRAINING);
    }

    @Test
    public void executeTrainingInvalidIndexThrowsCommandException() throws Exception {
        AddAllStudentToTrainingCommand addAllStudentToTrainingCommand =
                new AddAllStudentToTrainingCommand(INDEX_FIFTH_TRAINING);
        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_TRAINING_DISPLAYED_INDEX, () ->
                addAllStudentToTrainingCommand.execute(getModel()));
    }

    @Test
    public void executeEmptyStudentListThrowsCommandException() throws Exception {
        AddAllStudentToTrainingCommand addAllStudentToTrainingCommand =
                new AddAllStudentToTrainingCommand(INDEX_FIRST_TRAINING);
        getModel().addTraining(TypicalTraining.VALID_TRAINING);
        getModel().updateFilteredStudentList(new AllMatchPredicateList());
        assertThrows(CommandException.class, addAllStudentToTrainingCommand.MESSAGE_NO_STUDENTS, () ->
                addAllStudentToTrainingCommand.execute(getModel()));
    }

    @Test
    public void executeStudentAddToPastTrainingAddFail() throws Exception {
        AddAllStudentToTrainingCommand addAllStudentToTrainingCommand =
                new AddAllStudentToTrainingCommand(INDEX_SECOND_TRAINING);
        getModel().addTraining(TypicalTraining.VALID_PAST_TRAINING);
        getModel().addTraining(TypicalTraining.VALID_PAST_TRAINING2);
        getModel().updateFilteredStudentList(Model.PREDICATE_SHOW_ALL_STUDENTS);
        assertThrows(CommandException.class, AddAllStudentToTrainingCommand.MESSAGE_TRAINING_CANNOT_ADD, () ->
                addAllStudentToTrainingCommand.execute(getModel()));
    }

    @Test
    public void executeStudentsAcceptedByModelAddSuccessful() throws Exception {
        AddAllStudentToTrainingCommand addAllStudentToTrainingCommand =
                new AddAllStudentToTrainingCommand(INDEX_SECOND_TRAINING);
        getModel().addTraining(new TrainingBuilder().withDateTime(VALID_LOCAL_DATE_TIME_3).build());
        getModel().addTraining(new TrainingBuilder().withDateTime(VALID_LOCAL_DATE_TIME_2).build());
        CommandResult commandResult = addAllStudentToTrainingCommand.execute(getModel());
        assertEquals(String.format(AddAllStudentToTrainingCommand.MESSAGE_ADD_STUDENT_SUCCESS,
                "1, 2, 3, 4, 5, 6, 7"),
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
        assertTrue(getModel().getFilteredTrainingList().get(1).getStudents().size() == 7);
        //Training class should contain student1 too
        assertTrue(getModel().getFilteredTrainingList().get(1).getStudents().contains(getModel()
                .getFilteredStudentList().get(0)));
        //Training class should contain student2 too
        assertTrue(getModel().getFilteredTrainingList().get(1).getStudents().contains(getModel()
                .getFilteredStudentList().get(1)));
        //Training class should contain student3 too
        assertTrue(getModel().getFilteredTrainingList().get(1).getStudents().contains(getModel()
                .getFilteredStudentList().get(2)));
        resetModel();
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
        assertFalse(addAllStudent1Command == null);

        // different training -> returns false
        assertFalse(addAllStudent1Command.equals(addAllStudent2Command));
    }

    private void resetModel() {
        getModel().updateFilteredStudentList(Model.PREDICATE_SHOW_ALL_STUDENTS);
        List<Student> studentList = getModel().getFilteredStudentList();
        List<String> idList = studentList.stream()
                .map(Student::getId)
                .map(Id::getValue)
                .collect(Collectors.toList());
        new DeleteStudentFromTrainingCommand(INDEX_SECOND_TRAINING, idList);
    }
}
