package seedu.canoe.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.canoe.testutil.TypicalStudents.BENSON;
import static seedu.canoe.testutil.TypicalStudents.CARL;
import static seedu.canoe.testutil.TypicalStudents.ELLE;
import static seedu.canoe.testutil.TypicalStudents.GEORGE;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

class CommonTimeFinderTest {

    @Test
    void getCommonDismissalTimes_emptyList_defaultDismissalTimes() {
        List<LocalTime> expectedList = Arrays.asList(
                LocalTime.of(15, 0),
                LocalTime.of(15, 0),
                LocalTime.of(15, 0),
                LocalTime.of(15, 0),
                LocalTime.of(15, 0));
        CommonTimeFinder commonTimeFinder = new CommonTimeFinder(Collections.emptyList());
        assertEquals(commonTimeFinder.getCommonDismissalTimes(), expectedList);
    }

    @Test
    void getCommonDismissalTimes_multipleStudentsList_latestDismissalTimes() {
        List<LocalTime> expectedList = Arrays.asList(
                LocalTime.of(16, 21),
                LocalTime.of(15, 0),
                LocalTime.of(18, 0),
                LocalTime.of(15, 0),
                LocalTime.of(17, 12));
        List<Student> studentList = Arrays.asList(BENSON, CARL, ELLE, GEORGE);
        CommonTimeFinder commonTimeFinder = new CommonTimeFinder(studentList);
        assertEquals(commonTimeFinder.getCommonDismissalTimes(), expectedList);
    }
}
