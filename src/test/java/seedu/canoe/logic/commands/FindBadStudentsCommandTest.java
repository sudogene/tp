package seedu.canoe.logic.commands;

import static seedu.canoe.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.canoe.testutil.LocalDateTimeUtil.DATE_TIME_NOW_MINUS_ONE_DAY;
import static seedu.canoe.testutil.LocalDateTimeUtil.DATE_TIME_NOW_MINUS_TWO_DAYS;
import static seedu.canoe.testutil.LocalDateTimeUtil.DATE_TIME_NOW_MINUS_THREE_DAYS;
import static seedu.canoe.testutil.LocalDateTimeUtil.DATE_TIME_NOW_MINUS_FOUR_DAYS;
import static seedu.canoe.testutil.LocalDateTimeUtil.DATE_TIME_NOW_PLUS_ONE_DAY;
import static seedu.canoe.testutil.LocalDateTimeUtil.DATE_TIME_NOW_PLUS_TWO_DAYS;
import static seedu.canoe.testutil.TypicalStudentsInTypicalTrainings.JONAS;
import static seedu.canoe.testutil.TypicalStudentsInTypicalTrainings.QINDA;
import static seedu.canoe.testutil.TypicalStudentsInTypicalTrainings.YANKEE;
import static seedu.canoe.testutil.TypicalStudentsInTypicalTrainings.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.canoe.model.Model;
import seedu.canoe.model.ModelManager;
import seedu.canoe.model.UserPrefs;
import seedu.canoe.model.student.Attendance;

import java.util.Arrays;

class FindBadStudentsCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    void execute_onlyFutureTrainings_noBadStudents() {
        Attendance futureTraining1 = new Attendance(DATE_TIME_NOW_PLUS_ONE_DAY);
        Attendance futureTraining2 = new Attendance(DATE_TIME_NOW_PLUS_TWO_DAYS);

        JONAS.addAllAttendances(Arrays.asList(futureTraining1, futureTraining2));
        QINDA.addAllAttendances(Arrays.asList(futureTraining1, futureTraining2));
        YANKEE.addAllAttendances(Arrays.asList(futureTraining1, futureTraining2));

        String expectedMessage = "No students with a bad attendance record were found!";
        FindBadStudentsCommand command = new FindBadStudentsCommand();
        assertCommandSuccess(command, model, expectedMessage, expectedModel);

        JONAS.removeAllAttendances();
        QINDA.removeAllAttendances();
        YANKEE.removeAllAttendances();
    }

    @Test
    void execute_allPastTrainingsAttended_noBadStudents() {
        Attendance pastTraining1 = new Attendance(DATE_TIME_NOW_MINUS_ONE_DAY);
        Attendance pastTraining2 = new Attendance(DATE_TIME_NOW_MINUS_TWO_DAYS);
        pastTraining1.attendsTraining();
        pastTraining2.attendsTraining();
        JONAS.addAllAttendances(Arrays.asList(pastTraining1, pastTraining2));
        QINDA.addAllAttendances(Arrays.asList(pastTraining1, pastTraining2));
        YANKEE.addAllAttendances(Arrays.asList(pastTraining1, pastTraining2));

        String expectedMessage = "No students with a bad attendance record were found!";
        FindBadStudentsCommand command = new FindBadStudentsCommand();
        assertCommandSuccess(command, model, expectedMessage, expectedModel);

        JONAS.removeAllAttendances();
        QINDA.removeAllAttendances();
        YANKEE.removeAllAttendances();
    }

    @Test
    void execute_allPastTrainingsLessThan4Unattended_noBadStudents() {
        Attendance pastTraining1 = new Attendance(DATE_TIME_NOW_MINUS_ONE_DAY);
        Attendance pastTraining2 = new Attendance(DATE_TIME_NOW_MINUS_TWO_DAYS);
        Attendance pastTraining3 = new Attendance(DATE_TIME_NOW_MINUS_THREE_DAYS);
        JONAS.addAllAttendances(Arrays.asList(pastTraining1));
        QINDA.addAllAttendances(Arrays.asList(pastTraining1, pastTraining2, pastTraining3));
        YANKEE.addAllAttendances(Arrays.asList(pastTraining2, pastTraining3));

        String expectedMessage = "No students with a bad attendance record were found!";
        FindBadStudentsCommand command = new FindBadStudentsCommand();
        assertCommandSuccess(command, model, expectedMessage, expectedModel);

        JONAS.removeAllAttendances();
        QINDA.removeAllAttendances();
        YANKEE.removeAllAttendances();
    }

    @Test
    void execute_allPastTrainingsMorethan3Unattended_noBadStudents() {
        Attendance pastTraining1 = new Attendance(DATE_TIME_NOW_MINUS_ONE_DAY);
        Attendance pastTraining2 = new Attendance(DATE_TIME_NOW_MINUS_TWO_DAYS);
        Attendance pastTraining3 = new Attendance(DATE_TIME_NOW_MINUS_THREE_DAYS);
        Attendance pastTraining4 = new Attendance(DATE_TIME_NOW_MINUS_FOUR_DAYS);
        JONAS.addAllAttendances(Arrays.asList(pastTraining1, pastTraining2, pastTraining3, pastTraining4));
        QINDA.addAllAttendances(Arrays.asList(pastTraining1, pastTraining2, pastTraining3));
        YANKEE.addAllAttendances(Arrays.asList(pastTraining1, pastTraining2, pastTraining3));

        String expectedMessage = "Jonas(ID: 001)";
        FindBadStudentsCommand command = new FindBadStudentsCommand();
        assertCommandSuccess(command, model, expectedMessage, expectedModel);

        JONAS.removeAllAttendances();
        QINDA.removeAllAttendances();
        YANKEE.removeAllAttendances();
    }
}
