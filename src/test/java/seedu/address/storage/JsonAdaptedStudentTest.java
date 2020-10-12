package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedStudent.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.BENSON;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.student.AcademicYear;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.time.Day;

public class JsonAdaptedStudentTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_ACADEMICYEAR = "1f";
    private static final String INVALID_TAG = "#friend";
    private static final String INVALID_MONDAY = "Banana";
    private static final String INVALID_TUESDAY = "Banana";
    private static final String INVALID_WEDNESDAY = "Banana";
    private static final String INVALID_THURSDAY = "Banana";
    private static final String INVALID_FRIDAY = "Banana";
    private static final String VALID_ACADEMICYEAR = BENSON.getAcademicYear().toString();
    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_MONDAY = BENSON.getMondayDismissal().toString();
    private static final String VALID_TUESDAY = BENSON.getTuesdayDismissal().toString();
    private static final String VALID_WEDNESDAY = BENSON.getWednesdayDismissal().toString();
    private static final String VALID_THURSDAY = BENSON.getThursdayDismissal().toString();
    private static final String VALID_FRIDAY = BENSON.getFridayDismissal().toString();
    private static final String VALID_ID = "42";
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());
    private static final List<LocalDateTime> VALID_TRAINING_SCHEDULE = BENSON.getTrainingSchedule().stream()
            .collect(Collectors.toList());


    @Test
    public void toModelType_validStudentDetails_returnsStudent() throws Exception {
        JsonAdaptedStudent student = new JsonAdaptedStudent(BENSON);
        assertEquals(BENSON, student.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ACADEMICYEAR, VALID_TAGS,
                    VALID_MONDAY, VALID_TUESDAY, VALID_WEDNESDAY, VALID_THURSDAY, VALID_FRIDAY,
                        VALID_TRAINING_SCHEDULE, VALID_ID);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(null, VALID_PHONE, VALID_EMAIL, VALID_ACADEMICYEAR,
            VALID_TAGS, VALID_MONDAY, VALID_TUESDAY, VALID_WEDNESDAY, VALID_THURSDAY, VALID_FRIDAY,
                VALID_TRAINING_SCHEDULE, VALID_ID);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ACADEMICYEAR, VALID_TAGS,
                    VALID_MONDAY, VALID_TUESDAY, VALID_WEDNESDAY, VALID_THURSDAY, VALID_FRIDAY,
                        VALID_TRAINING_SCHEDULE, VALID_ID);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(VALID_NAME, null, VALID_EMAIL, VALID_ACADEMICYEAR,
            VALID_TAGS, VALID_MONDAY, VALID_TUESDAY, VALID_WEDNESDAY, VALID_THURSDAY, VALID_FRIDAY,
                VALID_TRAINING_SCHEDULE, VALID_ID);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ACADEMICYEAR, VALID_TAGS,
                    VALID_MONDAY, VALID_TUESDAY, VALID_WEDNESDAY, VALID_THURSDAY, VALID_FRIDAY,
                        VALID_TRAINING_SCHEDULE, VALID_ID);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedStudent student = new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, null, VALID_ACADEMICYEAR,
            VALID_TAGS, VALID_MONDAY, VALID_TUESDAY, VALID_WEDNESDAY, VALID_THURSDAY, VALID_FRIDAY,
                VALID_TRAINING_SCHEDULE, VALID_ID);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidAcademicYear_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ACADEMICYEAR, VALID_TAGS,
                    VALID_MONDAY, VALID_TUESDAY, VALID_WEDNESDAY, VALID_THURSDAY, VALID_FRIDAY,
                        VALID_TRAINING_SCHEDULE, VALID_ID);
        String expectedMessage = AcademicYear.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullAcademicYear_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_EMAIL, null, VALID_TAGS,
                    VALID_MONDAY, VALID_TUESDAY, VALID_WEDNESDAY, VALID_THURSDAY, VALID_FRIDAY,
                        VALID_TRAINING_SCHEDULE, VALID_ID);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, AcademicYear.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullMonday_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ACADEMICYEAR, VALID_TAGS,
                        null, VALID_TUESDAY, VALID_WEDNESDAY, VALID_THURSDAY, VALID_FRIDAY,
                        VALID_TRAINING_SCHEDULE, VALID_ID);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Day.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidMonday_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ACADEMICYEAR, VALID_TAGS,
                        INVALID_MONDAY, VALID_TUESDAY, VALID_WEDNESDAY, VALID_THURSDAY, VALID_FRIDAY,
                        VALID_TRAINING_SCHEDULE, VALID_ID);
        String expectedMessage = Day.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullTuesday_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ACADEMICYEAR, VALID_TAGS,
                        VALID_MONDAY, null, VALID_WEDNESDAY, VALID_THURSDAY, VALID_FRIDAY,
                        VALID_TRAINING_SCHEDULE, VALID_ID);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Day.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidTuesday_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ACADEMICYEAR, VALID_TAGS,
                        VALID_MONDAY, INVALID_TUESDAY, VALID_WEDNESDAY, VALID_THURSDAY, VALID_FRIDAY,
                        VALID_TRAINING_SCHEDULE, VALID_ID);
        String expectedMessage = Day.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullWednesday_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ACADEMICYEAR, VALID_TAGS,
                        VALID_MONDAY, VALID_TUESDAY, null, VALID_THURSDAY, VALID_FRIDAY,
                        VALID_TRAINING_SCHEDULE, VALID_ID);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Day.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidWednesday_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ACADEMICYEAR, VALID_TAGS,
                        VALID_MONDAY, VALID_TUESDAY, INVALID_WEDNESDAY, VALID_THURSDAY, VALID_FRIDAY,
                        VALID_TRAINING_SCHEDULE, VALID_ID);
        String expectedMessage = Day.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullThursday_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ACADEMICYEAR, VALID_TAGS,
                        VALID_MONDAY, VALID_TUESDAY, VALID_WEDNESDAY, null, VALID_FRIDAY,
                        VALID_TRAINING_SCHEDULE, VALID_ID);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Day.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidThursday_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ACADEMICYEAR, VALID_TAGS,
                        VALID_MONDAY, VALID_TUESDAY, VALID_WEDNESDAY, INVALID_THURSDAY, VALID_FRIDAY,
                        VALID_TRAINING_SCHEDULE, VALID_ID);
        String expectedMessage = Day.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_nullFriday_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ACADEMICYEAR, VALID_TAGS,
                        VALID_MONDAY, VALID_TUESDAY, VALID_WEDNESDAY, VALID_THURSDAY, null,
                        VALID_TRAINING_SCHEDULE, VALID_ID);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Day.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidFriday_throwsIllegalValueException() {
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ACADEMICYEAR, VALID_TAGS,
                        VALID_MONDAY, VALID_TUESDAY, VALID_WEDNESDAY, VALID_THURSDAY, INVALID_FRIDAY,
                        VALID_TRAINING_SCHEDULE, VALID_ID);
        String expectedMessage = Day.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, student::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedStudent student =
                new JsonAdaptedStudent(VALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ACADEMICYEAR, invalidTags,
                    VALID_MONDAY, VALID_TUESDAY, VALID_WEDNESDAY, VALID_THURSDAY, VALID_FRIDAY,
                        VALID_TRAINING_SCHEDULE, VALID_ID);
        assertThrows(IllegalValueException.class, student::toModelType);
    }

}
