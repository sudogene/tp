package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.BENSON;
import static seedu.address.testutil.TypicalStudents.DANIEL;
import static seedu.address.testutil.TypicalStudents.FIONA;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.AcademicYearMatchesPredicate;
import seedu.address.model.student.NameContainsKeywordsPredicate;
import seedu.address.model.student.PredicateList;

/**
 * Contains integration tests (interaction with the Model) for {@code CommonTimeCommand}.
 */
class CommonTimeCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_zeroKeywords_defaultDismissalTimes() {
        String expectedMessage = "Monday: 15:00\n"
                + "Tuesday: 15:00\n" + "Wednesday: 15:00\n" + "Thursday: 15:00\n" + "Friday: 15:00";
        NameContainsKeywordsPredicate namePredicate = preparePredicate(" ");
        AcademicYearMatchesPredicate academicYearPredicate = new AcademicYearMatchesPredicate(" ");
        PredicateList predicateList = new PredicateList(Arrays.asList(namePredicate, academicYearPredicate));
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
        PredicateList predicateList = new PredicateList(Arrays.asList(namePredicate));
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
        PredicateList predicateList = new PredicateList(Arrays.asList(namePredicate));
        CommonTimeCommand command = new CommonTimeCommand(predicateList);
        expectedModel.updateFilteredStudentList(predicateList);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredStudentList());
    }

    @Test
    public void execute_oneAcademicYearKeyword_matchedAcademicYearLatestDismissalTimes() {
        String expectedMessage = "Monday: 15:00\n"
                + "Tuesday: 16:23\n" + "Wednesday: 15:00\n" + "Thursday: 17:00\n" + "Friday: 15:00";
        AcademicYearMatchesPredicate academicYearPredicate = new AcademicYearMatchesPredicate("1");
        PredicateList predicateList = new PredicateList(Arrays.asList(academicYearPredicate));
        CommonTimeCommand command = new CommonTimeCommand(predicateList);
        expectedModel.updateFilteredStudentList(predicateList);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, FIONA), model.getFilteredStudentList());
    }

    @Test
    public void execute_multipleKeywords_allMatchLatestDismissalTimes() {
        String expectedMessage = "Monday: 15:00\n"
                + "Tuesday: 16:23\n" + "Wednesday: 15:00\n" + "Thursday: 17:00\n" + "Friday: 15:00";
        NameContainsKeywordsPredicate namePredicate = preparePredicate("Alice Kunz");
        AcademicYearMatchesPredicate academicYearPredicate = new AcademicYearMatchesPredicate("1");
        PredicateList predicateList = new PredicateList(Arrays.asList(namePredicate, academicYearPredicate));
        CommonTimeCommand command = new CommonTimeCommand(predicateList);
        expectedModel.updateFilteredStudentList(predicateList);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, FIONA), model.getFilteredStudentList());
    }

    @Test
    public void execute_multipleKeywords_noMatchDefaultDismissalTimes() {
        String expectedMessage = "Monday: 15:00\n"
                + "Tuesday: 15:00\n" + "Wednesday: 15:00\n" + "Thursday: 15:00\n" + "Friday: 15:00";
        NameContainsKeywordsPredicate namePredicate = preparePredicate("Daniel");
        AcademicYearMatchesPredicate academicYearPredicate = new AcademicYearMatchesPredicate("1");
        PredicateList predicateList = new PredicateList(Arrays.asList(namePredicate, academicYearPredicate));
        CommonTimeCommand command = new CommonTimeCommand(predicateList);
        expectedModel.updateFilteredStudentList(predicateList);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredStudentList());
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
        PredicateList predicateList = new PredicateList(Arrays.asList(dummyPredicate));
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
