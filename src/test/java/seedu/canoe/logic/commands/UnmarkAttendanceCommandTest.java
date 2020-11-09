package seedu.canoe.logic.commands;

import static seedu.canoe.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.canoe.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.canoe.model.Model.PREDICATE_SHOW_ALL_STUDENTS;
import static seedu.canoe.testutil.TypicalIndexes.INDEX_FIRST_TRAINING;
import static seedu.canoe.testutil.TypicalIndexes.INDEX_SECOND_TRAINING;
import static seedu.canoe.testutil.TypicalIndexes.INDEX_THIRD_TRAINING;

import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.canoe.commons.core.index.Index;
import seedu.canoe.model.Model;
import seedu.canoe.model.ModelManager;
import seedu.canoe.model.UserPrefs;
import seedu.canoe.model.student.AnyMatchPredicateList;
import seedu.canoe.model.student.Attendance;
import seedu.canoe.model.student.IdMatchesPredicate;
import seedu.canoe.model.student.Student;
import seedu.canoe.model.training.Training;
import seedu.canoe.testutil.TypicalStudents;
import seedu.canoe.testutil.TypicalTraining;

class UnmarkAttendanceCommandTest {
    private Model model = new ModelManager(TypicalStudents.getTypicalCanoeCoach(), new UserPrefs());
    private Model expectedModel = new ModelManager(TypicalStudents.getTypicalCanoeCoach(), new UserPrefs());

    @Test
    void execute_trainingIndexOutOfRange_commandFailure() {
        String expectedMessage = "Search result matches no trainings!";
        Index trainingIndex = INDEX_FIRST_TRAINING;
        IdMatchesPredicate firstIdPredicate = new IdMatchesPredicate("1");
        IdMatchesPredicate secondIdPredicate = new IdMatchesPredicate("6");
        UnmarkAttendanceCommand command = new UnmarkAttendanceCommand(
                trainingIndex, AnyMatchPredicateList.of(firstIdPredicate, secondIdPredicate));
        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    void execute_studentIndexOutOfRange_commandFailure() {
        String expectedMessage = "Search result matches no students!";
        Training firstTraining = TypicalTraining.VALID_PAST_TRAINING;
        Training secondTraining = TypicalTraining.VALID_PAST_TRAINING1;
        model.addTraining(firstTraining);
        model.addTraining(secondTraining);
        Index trainingIndex = INDEX_SECOND_TRAINING;
        IdMatchesPredicate firstIdPredicate = new IdMatchesPredicate("45");
        IdMatchesPredicate secondIdPredicate = new IdMatchesPredicate("67");
        UnmarkAttendanceCommand command = new UnmarkAttendanceCommand(
                trainingIndex, AnyMatchPredicateList.of(firstIdPredicate, secondIdPredicate));
        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    void execute_validParams_commandFailure() {
        String expectedMessage = "These students do not have the specified"
                + " training session scheduled: 1, 4!";
        IdMatchesPredicate firstIdPredicate = new IdMatchesPredicate("1");
        IdMatchesPredicate secondIdPredicate = new IdMatchesPredicate("4");

        Training firstTraining = TypicalTraining.VALID_PAST_TRAINING;
        Training secondTraining = TypicalTraining.VALID_PAST_TRAINING1;
        Training thirdTraining = TypicalTraining.VALID_PAST_TRAINING2;
        model.addTraining(firstTraining);
        model.addTraining(secondTraining);
        model.addTraining(thirdTraining);

        Index trainingIndex = INDEX_THIRD_TRAINING;
        UnmarkAttendanceCommand command = new UnmarkAttendanceCommand(
                trainingIndex, AnyMatchPredicateList.of(firstIdPredicate, secondIdPredicate));
        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    void execute_validParams_commandSuccess() {
        String expectedMessage = "Unmarked these students for their attendance: 2, 5!";
        IdMatchesPredicate firstIdPredicate = new IdMatchesPredicate("2");
        IdMatchesPredicate secondIdPredicate = new IdMatchesPredicate("5");

        Training firstTraining = TypicalTraining.VALID_PAST_TRAINING;
        Training secondTraining = TypicalTraining.VALID_PAST_TRAINING1;
        Training thirdTraining = TypicalTraining.VALID_PAST_TRAINING2;
        model.addTraining(firstTraining);
        model.addTraining(secondTraining);
        model.addTraining(thirdTraining);
        expectedModel.addTraining(firstTraining);
        expectedModel.addTraining(secondTraining);
        expectedModel.addTraining(thirdTraining);

        AnyMatchPredicateList predicateList = AnyMatchPredicateList.of(firstIdPredicate, secondIdPredicate);
        expectedModel.updateFilteredStudentList(predicateList);
        List<Student> studentList = expectedModel.getFilteredStudentList();

        Student firstStudent = TypicalStudents.BENSON;
        Student secondStudent = TypicalStudents.ELLE;
        Attendance attendance = new Attendance(firstTraining.getDateTime());
        attendance.marks();
        firstStudent.addAttendance(attendance);
        secondStudent.addAttendance(attendance);
        expectedModel.setStudentInUniqueStudentList(studentList.get(0), firstStudent);
        expectedModel.setStudentInUniqueStudentList(studentList.get(1), secondStudent);
        expectedModel.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);

        Index trainingIndex = INDEX_FIRST_TRAINING;
        UnmarkAttendanceCommand command = new UnmarkAttendanceCommand(
                trainingIndex, AnyMatchPredicateList.of(firstIdPredicate, secondIdPredicate));
        assertCommandSuccess(command, model, expectedMessage, expectedModel);

        // Reset students in typical address book
        firstStudent.removeAllAttendances();
        secondStudent.removeAllAttendances();
    }
}
