package seedu.canoe.model.student;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.canoe.logic.commands.CommandTestUtil.VALID_ACADEMICYEAR_BOB;
import static seedu.canoe.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.canoe.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.canoe.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.canoe.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.canoe.testutil.Assert.assertThrows;
import static seedu.canoe.testutil.LocalDateTimeUtil.DATE_TIME_NOW_PLUS_ONE_DAY;
import static seedu.canoe.testutil.TypicalStudents.ALICE;
import static seedu.canoe.testutil.TypicalStudents.BOB;

import org.junit.jupiter.api.Test;

import seedu.canoe.testutil.StudentBuilder;

public class StudentTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Student student = new StudentBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> student.getTags().remove(0));
    }

    @Test
    public void asObservableList_modifyTrainingList_throwsUnsupportedOperationException() {
        Student student = new StudentBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> student.getTrainingAttendances().remove(0));
    }

    @Test
    public void isSameStudent() {
        // same object -> returns true
        assertTrue(ALICE.isSameStudent(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameStudent(null));

        // different phone and email -> returns false
        Student editedAlice = new StudentBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.isSameStudent(editedAlice));

        // different name, same academic year -> returns false
        editedAlice = new StudentBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameStudent(editedAlice));

        // same name, same academic year, same phone, different attributes -> returns true
        editedAlice = new StudentBuilder(ALICE).withEmail(VALID_EMAIL_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameStudent(editedAlice));

        // same name, same academic year, same email, different attributes -> returns true
        editedAlice = new StudentBuilder(ALICE).withPhone(VALID_PHONE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameStudent(editedAlice));

        // same name, same academic year, same phone, same email, different attributes -> returns true
        editedAlice = new StudentBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameStudent(editedAlice));

        // same name, different academic year -> returns false
        editedAlice = new StudentBuilder(ALICE).withAcademicYear(VALID_ACADEMICYEAR_BOB).build();
        assertFalse(ALICE.isSameStudent(editedAlice));

        // different phone only -> returns true
        editedAlice = new StudentBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertTrue(ALICE.isSameStudent(editedAlice));

        // different email only -> returns true
        editedAlice = new StudentBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertTrue(ALICE.isSameStudent(editedAlice));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Student aliceCopy = new StudentBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different student -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Student editedAlice = new StudentBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different academic year -> returns false
        editedAlice = new StudentBuilder(ALICE).withAcademicYear(VALID_ACADEMICYEAR_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new StudentBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new StudentBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different trainingAttendances -> returns false
        editedAlice = new StudentBuilder(ALICE).withTrainingAttendances(
            new Attendance(DATE_TIME_NOW_PLUS_ONE_DAY)).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new StudentBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void cloneStudent() {
        // weaker equality
        assertTrue(ALICE.isSameStudent(ALICE.cloneStudent()));

        // stronger equality
        assertEquals(ALICE, ALICE.cloneStudent());

        // equals to student builder
        assertEquals(ALICE.cloneStudent(), new StudentBuilder(ALICE).build());

        // different student -> not equals
        assertNotEquals(BOB, ALICE.cloneStudent());
        assertNotEquals(ALICE, BOB.cloneStudent());
    }

}
