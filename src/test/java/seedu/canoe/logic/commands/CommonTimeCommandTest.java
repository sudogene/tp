package seedu.canoe.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.canoe.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.canoe.testutil.TypicalStudents.ALICE;
import static seedu.canoe.testutil.TypicalStudents.BENSON;
import static seedu.canoe.testutil.TypicalStudents.DANIEL;
import static seedu.canoe.testutil.TypicalStudents.FIONA;
import static seedu.canoe.testutil.TypicalStudents.GEORGE;
import static seedu.canoe.testutil.TypicalStudents.getTypicalCanoeCoach;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.canoe.model.Model;
import seedu.canoe.model.ModelManager;
import seedu.canoe.model.UserPrefs;
import seedu.canoe.model.student.AcademicYear;
import seedu.canoe.model.student.AcademicYearMatchesPredicate;
import seedu.canoe.model.student.AnyMatchPredicateList;
import seedu.canoe.model.student.NameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code CommonTimeCommand}.
 */
class CommonTimeCommandTest {
    private Model model = new ModelManager(getTypicalCanoeCoach(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalCanoeCoach(), new UserPrefs());

    @Test
    public void execute_zeroKeywords_defaultDismissalTimes() {
        String expectedMessage = "Search result matches no students!";
        NameContainsKeywordsPredicate namePredicate = preparePredicate(" ");
        AnyMatchPredicateList predicateList = new AnyMatchPredicateList(
                Arrays.asList(namePredicate)
        );
        CommonTimeCommand command = new CommonTimeCommand(predicateList);
        expectedModel.updateFilteredStudentList(predicateList);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredStudentList());
    }

    @Test
    public void execute_oneNameKeyword_oneStudentMatchedDismissalTimes() {
        String expectedMessage = "Monday: 15:00\n"
                + "Tuesday: 15:00\n" + "Wednesday: 15:00\n" + "Thursday: 17:00\n" + "Friday: 15:00";
        NameContainsKeywordsPredicate namePredicate = preparePredicate("Alice");
        AnyMatchPredicateList predicateList = new AnyMatchPredicateList(Arrays.asList(namePredicate));
        CommonTimeCommand command = new CommonTimeCommand(predicateList);
        expectedModel.updateFilteredStudentList(predicateList);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE), model.getFilteredStudentList());
    }

    @Test
    public void execute_multipleNameKeywords_aLlMatchLatestDismissalTimes() {
        String expectedMessage = "Monday: 16:21\n"
                + "Tuesday: 15:00\n" + "Wednesday: 18:00\n" + "Thursday: 17:00\n" + "Friday: 15:00";
        NameContainsKeywordsPredicate namePredicate = preparePredicate("Alice Benson Daniel");
        AnyMatchPredicateList predicateList = new AnyMatchPredicateList(Arrays.asList(namePredicate));
        CommonTimeCommand command = new CommonTimeCommand(predicateList);
        expectedModel.updateFilteredStudentList(predicateList);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredStudentList());
    }

    @Test
    public void execute_oneAcademicYearKeyword_matchedAcademicYearLatestDismissalTimes() {
        String expectedMessage = "Monday: 15:00\n"
                + "Tuesday: 16:23\n" + "Wednesday: 15:00\n" + "Thursday: 17:00\n" + "Friday: 15:00";
        AcademicYearMatchesPredicate academicYearPredicate = new AcademicYearMatchesPredicate(
                new AcademicYear("1"));
        AnyMatchPredicateList predicateList = new AnyMatchPredicateList(Arrays.asList(academicYearPredicate));
        CommonTimeCommand command = new CommonTimeCommand(predicateList);
        expectedModel.updateFilteredStudentList(predicateList);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, FIONA), model.getFilteredStudentList());
    }

    @Test
    public void execute_multipleNameKeywordsSameAcademicYear_allMatchLatestDismissalTimes() {
        String expectedMessage = "Monday: 15:00\n"
                + "Tuesday: 16:23\n" + "Wednesday: 15:00\n" + "Thursday: 17:00\n" + "Friday: 15:00";
        NameContainsKeywordsPredicate namePredicate = preparePredicate("Alice Kunz");
        AcademicYearMatchesPredicate academicYearPredicate = new AcademicYearMatchesPredicate(
                new AcademicYear("1"));
        AnyMatchPredicateList predicateList = new AnyMatchPredicateList(
                Arrays.asList(namePredicate, academicYearPredicate)
        );
        CommonTimeCommand command = new CommonTimeCommand(predicateList);
        expectedModel.updateFilteredStudentList(predicateList);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, FIONA), model.getFilteredStudentList());
    }

    @Test
    public void execute_multipleNameKeywordsDifferentAcademicYear_allMatchLatestDismissalTimes() {
        String expectedMessage = "Monday: 15:00\n"
                + "Tuesday: 16:23\n" + "Wednesday: 17:45\n" + "Thursday: 17:00\n" + "Friday: 17:12";
        NameContainsKeywordsPredicate namePredicate = preparePredicate("Daniel Best");
        AcademicYearMatchesPredicate academicYearPredicate = new AcademicYearMatchesPredicate(
                new AcademicYear("1"));
        AnyMatchPredicateList predicateList = new AnyMatchPredicateList(
                Arrays.asList(namePredicate, academicYearPredicate)
        );
        CommonTimeCommand command = new CommonTimeCommand(predicateList);
        expectedModel.updateFilteredStudentList(predicateList);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, DANIEL, FIONA, GEORGE), model.getFilteredStudentList());
    }

    @Test
    void commonDismissalTimesToString() {
        String expectedMessage = "Monday: 12:34\n"
                + "Tuesday: 20:42\n" + "Wednesday: 16:59\n" + "Thursday: 09:01\n" + "Friday: 11:20";
        LocalTime mondayDismissalTime = LocalTime.of(12, 34);
        LocalTime tuesdayDismissalTime = LocalTime.of(20, 42);
        LocalTime wednesdayDismissalTime = LocalTime.of(16, 59);
        LocalTime thursdayDismissalTime = LocalTime.of(9, 01);
        LocalTime fridayDismissalTime = LocalTime.of(11, 20);
        List<LocalTime> commonDismissalTimes = Arrays.asList(
                mondayDismissalTime,
                tuesdayDismissalTime,
                wednesdayDismissalTime,
                thursdayDismissalTime,
                fridayDismissalTime);
        NameContainsKeywordsPredicate dummyPredicate = preparePredicate(" ");
        AnyMatchPredicateList predicateList = new AnyMatchPredicateList(Arrays.asList(dummyPredicate));
        assertEquals(expectedMessage,
                new CommonTimeCommand(predicateList).commonDismissalTimesToString(commonDismissalTimes));
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
