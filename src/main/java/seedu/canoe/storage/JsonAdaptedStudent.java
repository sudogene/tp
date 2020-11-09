package seedu.canoe.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.canoe.commons.exceptions.IllegalValueException;
import seedu.canoe.model.student.AcademicYear;
import seedu.canoe.model.student.Email;
import seedu.canoe.model.student.Id;
import seedu.canoe.model.student.Name;
import seedu.canoe.model.student.Phone;
import seedu.canoe.model.student.Student;
import seedu.canoe.model.student.time.Day;
import seedu.canoe.model.student.time.Friday;
import seedu.canoe.model.student.time.Monday;
import seedu.canoe.model.student.time.Thursday;
import seedu.canoe.model.student.time.Tuesday;
import seedu.canoe.model.student.time.Wednesday;
import seedu.canoe.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Student}.
 */
class JsonAdaptedStudent {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Student's %s field is missing!";

    private final String id;
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
    private final List<JsonAdaptedAttend> trainingAttendances = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedStudent} with the given student details.
     */
    @JsonCreator
    public JsonAdaptedStudent(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                              @JsonProperty("email") String email,
                              @JsonProperty("academicYear") String academicYear,
                              @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                              @JsonProperty("monday") String mondayDismissal,
                              @JsonProperty("tuesday") String tuesdayDismissal,
                              @JsonProperty("wednesday") String wednesdayDismissal,
                              @JsonProperty("thursday") String thursdayDismissal,
                              @JsonProperty("friday") String fridayDismissal,
                              @JsonProperty("trainingAttendances") List<JsonAdaptedAttend>trainingAttendances,
                              @JsonProperty("id") String id) {
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
        if (trainingAttendances != null) {
            this.trainingAttendances.addAll(trainingAttendances);
        }
        this.id = id;
    }

    /**
     * Converts a given {@code Student} into this class for Jackson use.
     */
    public JsonAdaptedStudent(Student source) {
        id = source.getId().value;
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

        trainingAttendances.addAll(source.getTrainingAttendances().stream()
                .map(JsonAdaptedAttend::new)
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

        if (id == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Id.class.getSimpleName()));
        }
        final Id studentId = new Id(id);

        Student student = new Student(modelName, modelPhone, modelEmail, modelAcademicYear,
            modelTags, monday, tuesday, wednesday, thursday, friday, studentId);

        for (JsonAdaptedAttend attend : trainingAttendances) {
            student.addAttendance(attend.toModelType());
        }

        return student;
    }

}
