package seedu.canoe.logic.commands;

import static seedu.canoe.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.canoe.testutil.LocalDateTimeUtil.DATE_TIME_NOW_MINUS_FOUR_DAYS;
import static seedu.canoe.testutil.LocalDateTimeUtil.DATE_TIME_NOW_MINUS_ONE_DAY;
import static seedu.canoe.testutil.LocalDateTimeUtil.DATE_TIME_NOW_MINUS_THREE_DAYS;
import static seedu.canoe.testutil.LocalDateTimeUtil.DATE_TIME_NOW_MINUS_TWO_DAYS;
import static seedu.canoe.testutil.LocalDateTimeUtil.DATE_TIME_NOW_PLUS_ONE_DAY;
import static seedu.canoe.testutil.LocalDateTimeUtil.DATE_TIME_NOW_PLUS_TWO_DAYS;
import static seedu.canoe.testutil.TypicalStudents.ALICE;
import static seedu.canoe.testutil.TypicalStudents.CARL;
import static seedu.canoe.testutil.TypicalStudents.FIONA;
import static seedu.canoe.testutil.TypicalStudents.getTypicalCanoeCoach;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.canoe.model.Model;
import seedu.canoe.model.ModelManager;
import seedu.canoe.model.UserPrefs;
import seedu.canoe.model.student.Attendance;

class FindBadStudentsCommandTest {

    private Model model = new ModelManager(getTypicalCanoeCoach(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalCanoeCoach(), new UserPrefs());

    @Test
    void execute_onlyFutureTrainings_noBadStudents() {
        Attendance futureTraining1 = new Attendance(DATE_TIME_NOW_PLUS_ONE_DAY);
        Attendance futureTraining2 = new Attendance(DATE_TIME_NOW_PLUS_TWO_DAYS);

        ALICE.addAllAttendances(Arrays.asList(futureTraining1, futureTraining2));
        CARL.addAllAttendances(Arrays.asList(futureTraining1, futureTraining2));
        FIONA.addAllAttendances(Arrays.asList(futureTraining1, futureTraining2));

        String expectedMessage = "No students with a bad attendance record were found!";
        FindBadStudentsCommand command = new FindBadStudentsCommand();
        assertCommandSuccess(command, model, expectedMessage, expectedModel);

        ALICE.removeAllAttendances();
        CARL.removeAllAttendances();
        FIONA.removeAllAttendances();
    }

    @Test
    void execute_allPastTrainingsAttended_noBadStudents() {
        Attendance pastTraining1 = new Attendance(DATE_TIME_NOW_MINUS_ONE_DAY);
        Attendance pastTraining2 = new Attendance(DATE_TIME_NOW_MINUS_TWO_DAYS);
        pastTraining1.marks();
        pastTraining2.marks();
        ALICE.addAllAttendances(Arrays.asList(pastTraining1, pastTraining2));
        CARL.addAllAttendances(Arrays.asList(pastTraining1, pastTraining2));
        FIONA.addAllAttendances(Arrays.asList(pastTraining1, pastTraining2));

        String expectedMessage = "No students with a bad attendance record were found!";
        FindBadStudentsCommand command = new FindBadStudentsCommand();
        assertCommandSuccess(command, model, expectedMessage, expectedModel);

        ALICE.removeAllAttendances();
        CARL.removeAllAttendances();
        FIONA.removeAllAttendances();
    }

    @Test
    void execute_allPastTrainingsLessThan4Unattended_noBadStudents() {
        Attendance pastTraining1 = new Attendance(DATE_TIME_NOW_MINUS_ONE_DAY);
        Attendance pastTraining2 = new Attendance(DATE_TIME_NOW_MINUS_TWO_DAYS);
        Attendance pastTraining3 = new Attendance(DATE_TIME_NOW_MINUS_THREE_DAYS);
        ALICE.addAllAttendances(Arrays.asList(pastTraining1));
        CARL.addAllAttendances(Arrays.asList(pastTraining1, pastTraining2, pastTraining3));
        FIONA.addAllAttendances(Arrays.asList(pastTraining2, pastTraining3));

        String expectedMessage = "No students with a bad attendance record were found!";
        FindBadStudentsCommand command = new FindBadStudentsCommand();
        assertCommandSuccess(command, model, expectedMessage, expectedModel);

        ALICE.removeAllAttendances();
        CARL.removeAllAttendances();
        FIONA.removeAllAttendances();
    }

    @Test
    void execute_allPastTrainingsMorethan3Unattended_noBadStudents() {
        Attendance pastTraining1 = new Attendance(DATE_TIME_NOW_MINUS_ONE_DAY);
        Attendance pastTraining2 = new Attendance(DATE_TIME_NOW_MINUS_TWO_DAYS);
        Attendance pastTraining3 = new Attendance(DATE_TIME_NOW_MINUS_THREE_DAYS);
        Attendance pastTraining4 = new Attendance(DATE_TIME_NOW_MINUS_FOUR_DAYS);
        ALICE.addAllAttendances(Arrays.asList(pastTraining1, pastTraining2, pastTraining3, pastTraining4));
        CARL.addAllAttendances(Arrays.asList(pastTraining1, pastTraining2, pastTraining3));
        FIONA.addAllAttendances(Arrays.asList(pastTraining1, pastTraining2, pastTraining3));

        String expectedMessage = "Alice Pauline(Id: 1)";
        FindBadStudentsCommand command = new FindBadStudentsCommand();
        assertCommandSuccess(command, model, expectedMessage, expectedModel);

        ALICE.removeAllAttendances();
        CARL.removeAllAttendances();
        FIONA.removeAllAttendances();
    }
}
