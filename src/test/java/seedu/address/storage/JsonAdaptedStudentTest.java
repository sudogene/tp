package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedStudent.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
<<<<<<< HEAD:src/test/java/seedu/address/storage/JsonAdaptedPersonTest.java
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
=======
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
>>>>>>> 4b5a4f09efcf02a9230b83b6a099aa3ab7eb04e2:src/test/java/seedu/address/storage/JsonAdaptedStudentTest.java

public class JsonAdaptedStudentTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";
    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validStudentDetails_returnsStudent() throws Exception {
        JsonAdaptedStudent student = new JsonAdaptedStudent(BENSON);
        assertEquals(BENSON, student.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
<<<<<<< HEAD:src/test/java/seedu/address/storage/JsonAdaptedPersonTest.java
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_TAGS);
=======
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_TAGS);
>>>>>>> 4b5a4f09efcf02a9230b83b6a099aa3ab7eb04e2:src/test/java/seedu/address/storage/JsonAdaptedStudentTest.java
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
<<<<<<< HEAD:src/test/java/seedu/address/storage/JsonAdaptedPersonTest.java
        JsonAdaptedPerson person = new JsonAdaptedPerson(null, VALID_PHONE, VALID_EMAIL, VALID_TAGS);
=======
        JsonAdaptedStudent student = new JsonAdaptedStudent(null, VALID_PHONE, VALID_EMAIL, VALID_TAGS);
>>>>>>> 4b5a4f09efcf02a9230b83b6a099aa3ab7eb04e2:src/test/java/seedu/address/storage/JsonAdaptedStudentTest.java
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
<<<<<<< HEAD:src/test/java/seedu/address/storage/JsonAdaptedPersonTest.java
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_TAGS);
=======
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_TAGS);
>>>>>>> 4b5a4f09efcf02a9230b83b6a099aa3ab7eb04e2:src/test/java/seedu/address/storage/JsonAdaptedStudentTest.java
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
<<<<<<< HEAD:src/test/java/seedu/address/storage/JsonAdaptedPersonTest.java
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, null, VALID_EMAIL, VALID_TAGS);
=======
        JsonAdaptedStudent student = new JsonAdaptedStudent(VALID_NAME, null, VALID_EMAIL, VALID_TAGS);
>>>>>>> 4b5a4f09efcf02a9230b83b6a099aa3ab7eb04e2:src/test/java/seedu/address/storage/JsonAdaptedStudentTest.java
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
<<<<<<< HEAD:src/test/java/seedu/address/storage/JsonAdaptedPersonTest.java
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_TAGS);
=======
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_TAGS);
>>>>>>> 4b5a4f09efcf02a9230b83b6a099aa3ab7eb04e2:src/test/java/seedu/address/storage/JsonAdaptedStudentTest.java
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
<<<<<<< HEAD:src/test/java/seedu/address/storage/JsonAdaptedPersonTest.java
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
=======
        JsonAdaptedStudent student = new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
>>>>>>> 4b5a4f09efcf02a9230b83b6a099aa3ab7eb04e2:src/test/java/seedu/address/storage/JsonAdaptedStudentTest.java
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
<<<<<<< HEAD:src/test/java/seedu/address/storage/JsonAdaptedPersonTest.java
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME, VALID_PHONE, VALID_EMAIL, invalidTags);
        assertThrows(IllegalValueException.class, person::toModelType);
=======
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_EMAIL, invalidTags);
        assertThrows(IllegalValueException.class, student::toModelType);
>>>>>>> 4b5a4f09efcf02a9230b83b6a099aa3ab7eb04e2:src/test/java/seedu/address/storage/JsonAdaptedStudentTest.java
    }

}
