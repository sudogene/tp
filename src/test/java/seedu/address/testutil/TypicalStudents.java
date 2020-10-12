package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ACADEMICYEAR_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ACADEMICYEAR_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.student.Student;

/**
 * A utility class containing a list of {@code Student} objects to be used in tests.
 */
public class TypicalStudents {

    public static final Student ALICE = new StudentBuilder().withName("Alice Pauline")
            .withEmail("alice@example.com")
            .withPhone("94351253")
            .withAcademicYear("1")
            .withTags("friends")
            .withMondayDismissal("1300")
            .withThursdayDismissal("1700").build();
    public static final Student BENSON = new StudentBuilder().withName("Benson Meier")
            .withEmail("johnd@example.com").withPhone("98765432")
            .withAcademicYear("2")
            .withTags("owesMoney", "friends")
            .withTuesdayDismissal("1000")
            .withWednesdayDismissal("1800").build();
    public static final Student CARL = new StudentBuilder().withName("Carl Kurz").withPhone("95352563")
            .withAcademicYear("3")
            .withEmail("heinz@example.com")
            .withTuesdayDismissal("1400").build();
    public static final Student DANIEL = new StudentBuilder().withName("Daniel Meier").withPhone("87652533")
            .withAcademicYear("4")
            .withEmail("cornelia@example.com")
            .withTags("friends")
            .withMondayDismissal("1621")
            .withWednesdayDismissal("1745").build();
    public static final Student ELLE = new StudentBuilder().withName("Elle Meyer").withPhone("9482224")
            .withAcademicYear("5")
            .withEmail("werner@example.com")
            .withWednesdayDismissal("1230")
            .withFridayDismissal("1542").build();
    public static final Student FIONA = new StudentBuilder().withName("Fiona Kunz").withPhone("9482427")
            .withAcademicYear("1")
            .withEmail("lydia@example.com")
            .withTuesdayDismissal("1623").build();
    public static final Student GEORGE = new StudentBuilder().withName("George Best").withPhone("9482442")
            .withAcademicYear("2")
            .withEmail("anna@example.com")
            .withFridayDismissal("1712").build();

    // Manually added
    public static final Student HOON = new StudentBuilder().withName("Hoon Meier").withPhone("8482424")
            .withAcademicYear("1")
            .withEmail("stefan@example.com").build();
    public static final Student IDA = new StudentBuilder().withName("Ida Mueller").withPhone("8482131")
            .withAcademicYear("2")
            .withEmail("hans@example.com").build();

    // Manually added - Student's details found in {@code CommandTestUtil}
    public static final Student AMY = new StudentBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withEmail(VALID_EMAIL_AMY)
            .withAcademicYear(VALID_ACADEMICYEAR_AMY)
            .withTags(VALID_TAG_FRIEND).build();
    public static final Student BOB = new StudentBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withEmail(VALID_EMAIL_BOB)
            .withAcademicYear(VALID_ACADEMICYEAR_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalStudents() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical students.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Student student : getTypicalStudents()) {
            ab.addStudent(student);
        }
        return ab;
    }

    public static List<Student> getTypicalStudents() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
