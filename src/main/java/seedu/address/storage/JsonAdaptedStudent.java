package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.student.AcademicYear;
import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Student;
import seedu.address.model.student.time.Day;
import seedu.address.model.student.time.Friday;
import seedu.address.model.student.time.Monday;
import seedu.address.model.student.time.Thursday;
import seedu.address.model.student.time.Tuesday;
import seedu.address.model.student.time.Wednesday;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Student}.
 */
class JsonAdaptedStudent {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Student's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String academicYear;
    private final String mondayDismissal;
    private final String tuesdayDismissal;
    private final String wednesdayDismissal;
    private final String thursdayDismissal;
    private final String fridayDismissal;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedStudent} with the given student details.
     */
    @JsonCreator
    public JsonAdaptedStudent(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                              @JsonProperty("email") String email,
                              @JsonProperty("academicYear") String academicYear,
                              @JsonProperty("monday") String mondayDismissal,
                              @JsonProperty("tuesday") String tuesdayDismissal,
                              @JsonProperty("wednesday") String wednesdayDismissal,
                              @JsonProperty("thursday") String thursdayDismissal,
                              @JsonProperty("friday") String fridayDismissal,
                              @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.academicYear = academicYear;
        this.mondayDismissal = mondayDismissal;
        this.tuesdayDismissal = tuesdayDismissal;
        this.wednesdayDismissal = wednesdayDismissal;
        this.thursdayDismissal = thursdayDismissal;
        this.fridayDismissal = fridayDismissal;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Student} into this class for Jackson use.
     */
    public JsonAdaptedStudent(Student source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        academicYear = source.getAcademicYear().value;
        mondayDismissal = source.getMondayDismissal().toString();
        tuesdayDismissal = source.getTuesdayDismissal().toString();
        wednesdayDismissal = source.getWednesdayDismissal().toString();
        thursdayDismissal = source.getThursdayDismissal().toString();
        fridayDismissal = source.getFridayDismissal().toString();

        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted student object into the model's {@code Student} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted student.
     */
    public Student toModelType() throws IllegalValueException {
        final List<Tag> studentTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            studentTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);
      
        if (academicYear == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    AcademicYear.class.getSimpleName()));
        }
        if (!AcademicYear.isValidAcademicYear(academicYear)) {
            throw new IllegalValueException(AcademicYear.MESSAGE_CONSTRAINTS);
        }
        final AcademicYear modelAcademicYear = new AcademicYear(academicYear);

        if (mondayDismissal == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Day.class.getSimpleName()));
        }
        if (!Day.isValidDismissalTime(mondayDismissal)) {
            throw new IllegalValueException(Day.MESSAGE_CONSTRAINTS);
        }
        final Day monday = new Monday(mondayDismissal);

        if (tuesdayDismissal == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Day.class.getSimpleName()));
        }
        if (!Day.isValidDismissalTime(tuesdayDismissal)) {
            throw new IllegalValueException(Day.MESSAGE_CONSTRAINTS);
        }
        final Day tuesday = new Tuesday(tuesdayDismissal);

        if (wednesdayDismissal == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Day.class.getSimpleName()));
        }
        if (!Day.isValidDismissalTime(wednesdayDismissal)) {
            throw new IllegalValueException(Day.MESSAGE_CONSTRAINTS);
        }
        final Day wednesday = new Wednesday(wednesdayDismissal);

        if (thursdayDismissal == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Day.class.getSimpleName()));
        }
        if (!Day.isValidDismissalTime(thursdayDismissal)) {
            throw new IllegalValueException(Day.MESSAGE_CONSTRAINTS);
        }
        final Day thursday = new Thursday(thursdayDismissal);

        if (fridayDismissal == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Day.class.getSimpleName()));
        }
        if (!Day.isValidDismissalTime(fridayDismissal)) {
            throw new IllegalValueException(Day.MESSAGE_CONSTRAINTS);
        }
        final Day friday = new Friday(fridayDismissal);

        final Set<Tag> modelTags = new HashSet<>(studentTags);
        return new Student(modelName, modelPhone, modelEmail, modelAcademicYear, modelTags, monday, tuesday, wednesday, thursday, friday);

    }

}
