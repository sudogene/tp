package seedu.canoe.testutil;

import static seedu.canoe.logic.commands.CommandTestUtil.VALID_ACADEMICYEAR_AMY;
import static seedu.canoe.logic.commands.CommandTestUtil.VALID_ACADEMICYEAR_BOB;
import static seedu.canoe.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.canoe.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.canoe.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.canoe.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.canoe.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.canoe.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.canoe.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.canoe.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.canoe.model.CanoeCoach;
import seedu.canoe.model.student.Student;

/**
 * A utility class containing a list of {@code Student} objects to be used in tests.
 */
public class TypicalStudents {

    public static final Student ALICE = new StudentBuilder().withName("Alice Pauline")
            .withId("1")
            .withEmail("alice@example.com")
            .withPhone("94351253")
            .withAcademicYear("1")
            .withThursdayDismissal("1700")
            .withTags("friends").build();
    public static final Student BENSON = new StudentBuilder().withName("Benson Meier")
            .withId("2")
            .withEmail("johnd@example.com")
            .withPhone("98765432")
            .withAcademicYear("2")
            .withMondayDismissal("1621")
            .withWednesdayDismissal("1800")
            .withTags("owesMoney", "friends").build();
    public static final Student CARL = new StudentBuilder().withName("Carl Kurz")
            .withPhone("95352563")
            .withId("3")
            .withAcademicYear("3")
            .withTuesdayDismissal("1400")
            .withEmail("heinz@example.com").build();
    public static final Student DANIEL = new StudentBuilder().withName("Daniel Meier")
            .withPhone("87652533")
            .withId("4")
            .withAcademicYear("4")
            .withWednesdayDismissal("1745")
            .withEmail("cornelia@example.com").withTags("friends").build();
    public static final Student ELLE = new StudentBuilder().withName("Elle Meyer")
            .withPhone("9482224")
            .withId("5")
            .withAcademicYear("5")
            .withWednesdayDismissal("1230")
            .withFridayDismissal("1542")
            .withEmail("werner@example.com").build();
    public static final Student FIONA = new StudentBuilder().withName("Fiona Kunz")
            .withPhone("9482427")
            .withId("6")
            .withAcademicYear("1")
            .withTuesdayDismissal("1623")
            .withEmail("lydia@example.com").build();
    public static final Student GEORGE = new StudentBuilder().withName("George Best")
            .withPhone("9482442")
            .withId("7")
            .withAcademicYear("2")
            .withFridayDismissal("1712")
            .withEmail("anna@example.com").build();

    // Manually added
    public static final Student HOON = new StudentBuilder().withName("Hoon Meier")
            .withPhone("8482424")
            .withId("7")
            .withAcademicYear("1")
            .withEmail("stefan@example.com").build();
    public static final Student IDA = new StudentBuilder().withName("Ida Mueller")
            .withPhone("8482131")
            .withId("8")
            .withAcademicYear("2")
            .withEmail("hans@example.com").build();

    // Manually added - Student's details found in {@code CommandTestUtil}
    public static final Student AMY = new StudentBuilder().withName(VALID_NAME_AMY)
            .withPhone(VALID_PHONE_AMY)
            .withId("9")
            .withEmail(VALID_EMAIL_AMY)
            .withAcademicYear(VALID_ACADEMICYEAR_AMY)
            .withTags(VALID_TAG_FRIEND).build();
    public static final Student BOB = new StudentBuilder().withName(VALID_NAME_BOB)
            .withPhone(VALID_PHONE_BOB)
            .withId("10")
            .withEmail(VALID_EMAIL_BOB)
            .withAcademicYear(VALID_ACADEMICYEAR_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalStudents() {} // prevents instantiation

    /**
     * Returns an {@code CanoeCoach} with all the typical students.
     */
    public static CanoeCoach getTypicalCanoeCoach() {
        CanoeCoach ab = new CanoeCoach();
        for (Student student : getTypicalStudents()) {
            student.removeAllAttendances();
            ab.addStudent(student);
        }
        return ab;
    }

    public static List<Student> getTypicalStudents() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
