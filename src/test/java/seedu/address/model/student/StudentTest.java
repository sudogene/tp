package seedu.address.model.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.ALICE;
import static seedu.address.testutil.TypicalStudents.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.StudentBuilder;

public class StudentTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Student student = new StudentBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> student.getTags().remove(0));
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

        // different name -> returns false
        editedAlice = new StudentBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameStudent(editedAlice));

        // same name, same phone, different attributes -> returns true
<<<<<<< HEAD:src/test/java/seedu/address/model/person/PersonTest.java
        editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB)
=======
        editedAlice = new StudentBuilder(ALICE).withEmail(VALID_EMAIL_BOB)
>>>>>>> 4b5a4f09efcf02a9230b83b6a099aa3ab7eb04e2:src/test/java/seedu/address/model/student/StudentTest.java
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameStudent(editedAlice));

        // same name, same email, different attributes -> returns true
<<<<<<< HEAD:src/test/java/seedu/address/model/person/PersonTest.java
        editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB)
=======
        editedAlice = new StudentBuilder(ALICE).withPhone(VALID_PHONE_BOB)
>>>>>>> 4b5a4f09efcf02a9230b83b6a099aa3ab7eb04e2:src/test/java/seedu/address/model/student/StudentTest.java
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameStudent(editedAlice));

        // same name, same phone, same email, different attributes -> returns true
<<<<<<< HEAD:src/test/java/seedu/address/model/person/PersonTest.java
        editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSamePerson(editedAlice));
=======
        editedAlice = new StudentBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSameStudent(editedAlice));
>>>>>>> 4b5a4f09efcf02a9230b83b6a099aa3ab7eb04e2:src/test/java/seedu/address/model/student/StudentTest.java
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

        // different phone -> returns false
        editedAlice = new StudentBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
<<<<<<< HEAD:src/test/java/seedu/address/model/person/PersonTest.java
        editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
=======
        editedAlice = new StudentBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
>>>>>>> 4b5a4f09efcf02a9230b83b6a099aa3ab7eb04e2:src/test/java/seedu/address/model/student/StudentTest.java
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new StudentBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
