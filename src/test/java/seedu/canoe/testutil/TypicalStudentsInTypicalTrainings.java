package seedu.canoe.testutil;

import static seedu.canoe.testutil.LocalDateTimeUtil.DATE_TIME_NOW_PLUS_ONE_DAY;
import static seedu.canoe.testutil.LocalDateTimeUtil.PAST_LOCAL_DATE_TIME;
import static seedu.canoe.testutil.LocalDateTimeUtil.VALID_LOCAL_DATE_TIME_2;
import static seedu.canoe.testutil.LocalDateTimeUtil.VALID_LOCAL_DATE_TIME_3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.canoe.model.CanoeCoach;
import seedu.canoe.model.student.Student;
import seedu.canoe.model.training.Training;

/**
 * A utility class containing a list of {@code Student} objects to be used in {@code Training} tests.
 */
public class TypicalStudentsInTypicalTrainings {
    //DO NOT REUSE STATIC VARIABLES HERE AS THEIR PROPERTIES WOULD BE MODIFIED BY THE TESTS

    //Training on Current Date + 1 (Makes sure training is always valid)
    public static final Training FUTURE_TRAINING =
            new TrainingBuilder().withDateTime(DATE_TIME_NOW_PLUS_ONE_DAY).build();

    //Training with very late time to make sure students can always attend
    public static final Training LATE_TRAINING = new TrainingBuilder().withDateTime(VALID_LOCAL_DATE_TIME_2).build();

    //Training on 2021-10-23 1500 (Friday)
    public static final Training PAST_TRAINING = new TrainingBuilder().withDateTime(PAST_LOCAL_DATE_TIME).build();

    //Training on Monday 1000 (Reasonably set in the future to check dismissal time clashes)
    public static final Training FUTURE_TRAINING_DISMISSAL_TIME_CLASH =
            new TrainingBuilder().withDateTime(VALID_LOCAL_DATE_TIME_3).build();

    public static final Student JONAS = new StudentBuilder().withName("Jonas")
            .withId("1")
            .withEmail("jonas@example.com")
            .withPhone("92848294")
            .withAcademicYear("2")
            .withTags("friends").build();

    public static final Student QINDA = new StudentBuilder().withName("Qinda Meh")
            .withId("2")
            .withEmail("qd@example.com").withPhone("96204948")
            .withAcademicYear("2")
            .withMondayDismissal("1200")
            .withWednesdayDismissal("1300")
            .withTags("owesMoney", "friends").build();

    public static final Student YANKEE = new StudentBuilder().withName("Yankee Lim").withPhone("85352563")
            .withId("3")
            .withAcademicYear("3")
            .withTuesdayDismissal("1400")
            .withEmail("yank@example.com").build();

    public static final Student JOCK = new StudentBuilder().withName("Jock Kee").withPhone("87346395")
            .withId("4")
            .withAcademicYear("3")
            .withTuesdayDismissal("1600")
            .withWednesdayDismissal("0100")
            .withEmail("jock@example.com").build();

    static {
        FUTURE_TRAINING.addStudent(JONAS);
    }

    private TypicalStudentsInTypicalTrainings() {} // prevents instantiation

    /**
     * Returns an {@code CanoeCoach} with all the typical students and trainings.
     */
    public static CanoeCoach getTypicalAddressBook() {
        CanoeCoach ab2 = new CanoeCoach();
        for (Student student : getTypicalStudents()) {
            ab2.addStudent(student);
        }
        for (Training training : getTypicalTraining()) {
            ab2.addTraining(training);
        }
        return ab2;
    }

    public static List<Student> getTypicalStudents() {
        return new ArrayList<>(Arrays.asList(JONAS, QINDA, YANKEE, JOCK));
    }

    public static List<Training> getTypicalTraining() {
        return new ArrayList<>(Arrays.asList(FUTURE_TRAINING, PAST_TRAINING,
                FUTURE_TRAINING_DISMISSAL_TIME_CLASH, LATE_TRAINING));
    }
}
