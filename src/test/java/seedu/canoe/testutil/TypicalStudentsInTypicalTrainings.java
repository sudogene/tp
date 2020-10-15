package seedu.canoe.testutil;

import static seedu.canoe.testutil.LocalDateTimeUtil.VALID_LOCAL_DATE_TIME_5;
import static seedu.canoe.testutil.LocalDateTimeUtil.VALID_LOCAL_DATE_TIME_6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.canoe.model.AddressBook;
import seedu.canoe.model.student.Student;
import seedu.canoe.model.student.Training;

/**
 * A utility class containing a list of {@code Student} objects to be used in {@code Training} tests.
 */
public class TypicalStudentsInTypicalTrainings {
    //DO NOT REUSE STATIC VARIABLES HERE AS THEIR PROPERTIES WOULD BE MODIFIED BY THE TESTS
    public static final Training VALID_TRAINING_4 = new TrainingBuilder().withDateTime(VALID_LOCAL_DATE_TIME_5).build();
    public static final Training VALID_TRAINING_5 = new TrainingBuilder().withDateTime(VALID_LOCAL_DATE_TIME_6).build();

    //Has no training, thursday dismissal 1700
    public static final Student JONAS = new StudentBuilder().withName("Jonas")
            .withId("1")
            .withEmail("jonas@example.com")
            .withPhone("92848294")
            .withAcademicYear("2")
            .withThursdayDismissal("1700")
            .withTags("friends").build();
    //Has no training, monday dismissal 1200, wednesday dismissal 1500
    public static final Student QINDA = new StudentBuilder().withName("Qinda Meh")
            .withId("2")
            .withEmail("qd@example.com").withPhone("96204948")
            .withAcademicYear("2")
            .withMondayDismissal("1200")
            .withWednesdayDismissal("1500")
            .withTags("owesMoney", "friends").build();
    //Has no trainings scheduled, tuesday dismissal 1400
    public static final Student YANKEE = new StudentBuilder().withName("Yankee Lim").withPhone("85352563")
            .withId("3")
            .withAcademicYear("3")
            .withTuesdayDismissal("1400")
            .withEmail("yank@example.com").build();

    private TypicalStudentsInTypicalTrainings() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical students and trainings.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab2 = new AddressBook();
        for (Student student : getTypicalStudents()) {
            ab2.addStudent(student);
        }
        for (Training training : getTypicalTraining()) {
            ab2.addTraining(training);
        }
        return ab2;
    }

    public static List<Student> getTypicalStudents() {
        return new ArrayList<>(Arrays.asList(JONAS, QINDA, YANKEE));
    }

    public static List<Training> getTypicalTraining() {
        return new ArrayList<>(Arrays.asList(VALID_TRAINING_4, VALID_TRAINING_5));
    }
}
