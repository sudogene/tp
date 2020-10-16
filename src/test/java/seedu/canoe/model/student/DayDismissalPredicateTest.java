package seedu.canoe.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.canoe.model.student.time.Day;
import seedu.canoe.model.student.time.FridayDismissalPredicate;
import seedu.canoe.model.student.time.MondayDismissalPredicate;
import seedu.canoe.model.student.time.ThursdayDismissalPredicate;
import seedu.canoe.model.student.time.TuesdayDismissalPredicate;
import seedu.canoe.model.student.time.WednesdayDismissalPredicate;
import seedu.canoe.testutil.StudentBuilder;

public class DayDismissalPredicateTest {
    private String timePattern = "HHmm";

    @Test
    public void equalsMonday() {
        String firstTime = "1240";
        String secondTime = "1500";

        MondayDismissalPredicate firstPredicate = new MondayDismissalPredicate(
                LocalTime.parse(firstTime, DateTimeFormatter.ofPattern(timePattern)));
        MondayDismissalPredicate secondPredicate = new MondayDismissalPredicate(
                LocalTime.parse(secondTime, DateTimeFormatter.ofPattern(timePattern)));

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        MondayDismissalPredicate firstPredicateCopy = new MondayDismissalPredicate(
                LocalTime.parse(firstTime, DateTimeFormatter.ofPattern(timePattern)));
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different phone number -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void equalsTuesday() {
        String firstTime = "1240";
        String secondTime = "1500";

        TuesdayDismissalPredicate firstPredicate = new TuesdayDismissalPredicate(
                LocalTime.parse(firstTime, DateTimeFormatter.ofPattern(timePattern)));
        TuesdayDismissalPredicate secondPredicate = new TuesdayDismissalPredicate(
                LocalTime.parse(secondTime, DateTimeFormatter.ofPattern(timePattern)));

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        TuesdayDismissalPredicate firstPredicateCopy = new TuesdayDismissalPredicate(
                LocalTime.parse(firstTime, DateTimeFormatter.ofPattern(timePattern)));
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different phone number -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void equalsWednesday() {
        String firstTime = "1240";
        String secondTime = "1500";

        WednesdayDismissalPredicate firstPredicate = new WednesdayDismissalPredicate(
                LocalTime.parse(firstTime, DateTimeFormatter.ofPattern(timePattern)));
        WednesdayDismissalPredicate secondPredicate = new WednesdayDismissalPredicate(
                LocalTime.parse(secondTime, DateTimeFormatter.ofPattern(timePattern)));

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        WednesdayDismissalPredicate firstPredicateCopy = new WednesdayDismissalPredicate(
                LocalTime.parse(firstTime, DateTimeFormatter.ofPattern(timePattern)));
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different phone number -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void equalsThursday() {
        String firstTime = "1240";
        String secondTime = "1500";

        ThursdayDismissalPredicate firstPredicate = new ThursdayDismissalPredicate(
                LocalTime.parse(firstTime, DateTimeFormatter.ofPattern(timePattern)));
        ThursdayDismissalPredicate secondPredicate = new ThursdayDismissalPredicate(
                LocalTime.parse(secondTime, DateTimeFormatter.ofPattern(timePattern)));

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ThursdayDismissalPredicate firstPredicateCopy = new ThursdayDismissalPredicate(
                LocalTime.parse(firstTime, DateTimeFormatter.ofPattern(timePattern)));
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different phone number -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void equalsFriday() {
        String firstTime = "1240";
        String secondTime = "1500";

        FridayDismissalPredicate firstPredicate = new FridayDismissalPredicate(
                LocalTime.parse(firstTime, DateTimeFormatter.ofPattern(timePattern)));
        FridayDismissalPredicate secondPredicate = new FridayDismissalPredicate(
                LocalTime.parse(secondTime, DateTimeFormatter.ofPattern(timePattern)));

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        FridayDismissalPredicate firstPredicateCopy = new FridayDismissalPredicate(
                LocalTime.parse(firstTime, DateTimeFormatter.ofPattern(timePattern)));
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different phone number -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_equalDismissalTime_returnsTrue() {
        String subjectTime = "1500";
        String queryTime = "1500";

        // Monday
        Day monday = new Day(queryTime);
        assertTrue(new MondayDismissalPredicate(monday.dismissalTime).test(
                new StudentBuilder().withMondayDismissal(subjectTime).build()
        ));

        // Tuesday
        Day tuesday = new Day(queryTime);
        assertTrue(new TuesdayDismissalPredicate(tuesday.dismissalTime).test(
                new StudentBuilder().withTuesdayDismissal(subjectTime).build()
        ));

        // Wednesday
        Day wednesday = new Day(queryTime);
        assertTrue(new WednesdayDismissalPredicate(wednesday.dismissalTime).test(
                new StudentBuilder().withWednesdayDismissal(subjectTime).build()
        ));

        // Thursday
        Day thursday = new Day(queryTime);
        assertTrue(new ThursdayDismissalPredicate(thursday.dismissalTime).test(
                new StudentBuilder().withThursdayDismissal(subjectTime).build()
        ));

        // Friday
        Day friday = new Day(queryTime);
        assertTrue(new FridayDismissalPredicate(friday.dismissalTime).test(
                new StudentBuilder().withFridayDismissal(subjectTime).build()
        ));
    }

    @Test
    public void test_queryAfterSubjectDismissalTime_returnsTrue() {
        String subjectTime = "1500";
        String queryTime = "1501";

        // Monday
        Day monday = new Day(queryTime);
        assertTrue(new MondayDismissalPredicate(monday.dismissalTime).test(
                new StudentBuilder().withMondayDismissal(subjectTime).build()
        ));

        // Tuesday
        Day tuesday = new Day(queryTime);
        assertTrue(new TuesdayDismissalPredicate(tuesday.dismissalTime).test(
                new StudentBuilder().withTuesdayDismissal(subjectTime).build()
        ));

        // Wednesday
        Day wednesday = new Day(queryTime);
        assertTrue(new WednesdayDismissalPredicate(wednesday.dismissalTime).test(
                new StudentBuilder().withWednesdayDismissal(subjectTime).build()
        ));

        // Thursday
        Day thursday = new Day(queryTime);
        assertTrue(new ThursdayDismissalPredicate(thursday.dismissalTime).test(
                new StudentBuilder().withThursdayDismissal(subjectTime).build()
        ));

        // Friday
        Day friday = new Day(queryTime);
        assertTrue(new FridayDismissalPredicate(friday.dismissalTime).test(
                new StudentBuilder().withFridayDismissal(subjectTime).build()
        ));
    }

    @Test
    public void test_queryBeforeSubjectDismissalTime_returnsFalse() {
        String subjectTime = "1500";
        String queryTime = "1459";

        // Monday
        Day monday = new Day(queryTime);
        assertFalse(new MondayDismissalPredicate(monday.dismissalTime).test(
                new StudentBuilder().withMondayDismissal(subjectTime).build()
        ));

        // Tuesday
        Day tuesday = new Day(queryTime);
        assertFalse(new TuesdayDismissalPredicate(tuesday.dismissalTime).test(
                new StudentBuilder().withTuesdayDismissal(subjectTime).build()
        ));

        // Wednesday
        Day wednesday = new Day(queryTime);
        assertFalse(new WednesdayDismissalPredicate(wednesday.dismissalTime).test(
                new StudentBuilder().withWednesdayDismissal(subjectTime).build()
        ));

        // Thursday
        Day thursday = new Day(queryTime);
        assertFalse(new ThursdayDismissalPredicate(thursday.dismissalTime).test(
                new StudentBuilder().withThursdayDismissal(subjectTime).build()
        ));

        // Friday
        Day friday = new Day(queryTime);
        assertFalse(new FridayDismissalPredicate(friday.dismissalTime).test(
                new StudentBuilder().withFridayDismissal(subjectTime).build()
        ));
    }
}
