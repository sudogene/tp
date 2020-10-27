package seedu.canoe.testutil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.canoe.model.student.AcademicYear;
import seedu.canoe.model.student.Attendance;
import seedu.canoe.model.student.Email;
import seedu.canoe.model.student.Id;
import seedu.canoe.model.student.Name;
import seedu.canoe.model.student.Phone;
import seedu.canoe.model.student.Student;
import seedu.canoe.model.student.time.Friday;
import seedu.canoe.model.student.time.Monday;
import seedu.canoe.model.student.time.Thursday;
import seedu.canoe.model.student.time.Tuesday;
import seedu.canoe.model.student.time.Wednesday;
import seedu.canoe.model.tag.Tag;
import seedu.canoe.model.util.SampleDataUtil;

/**
 * A utility class to help with building Student objects.
 */
public class StudentBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "alice@gmail.com";
    public static final String DEFAULT_MONDAY = "1500";
    public static final String DEFAULT_TUESDAY = "1500";
    public static final String DEFAULT_WEDNESDAY = "1500";
    public static final String DEFAULT_THURSDAY = "1500";
    public static final String DEFAULT_FRIDAY = "1500";
    public static final String DEFAULT_ACADEMICYEAR = "1";
    public static final String DEFAULT_ID = "1";


    private Id id;
    private Name name;
    private Phone phone;
    private Email email;
    private AcademicYear academicYear;
    private Set<Tag> tags;
    private List<Attendance> trainingAttendances;
    private Monday mondayDismissal;
    private Tuesday tuesdayDismissal;
    private Wednesday wednesdayDismissal;
    private Thursday thursdayDismissal;
    private Friday fridayDismissal;


    /**
     * Creates a {@code StudentBuilder} with the default details.
     */
    public StudentBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        academicYear = new AcademicYear(DEFAULT_ACADEMICYEAR);
        tags = new HashSet<>();
        trainingAttendances = new ArrayList<>();
        mondayDismissal = new Monday(DEFAULT_MONDAY);
        tuesdayDismissal = new Tuesday(DEFAULT_TUESDAY);
        wednesdayDismissal = new Wednesday(DEFAULT_WEDNESDAY);
        thursdayDismissal = new Thursday(DEFAULT_THURSDAY);
        fridayDismissal = new Friday(DEFAULT_FRIDAY);
        id = new Id(DEFAULT_ID);
    }

    /**
     * Initializes the StudentBuilder with the data of {@code studentToCopy}.
     */
    public StudentBuilder(Student studentToCopy) {
        name = studentToCopy.getName();
        phone = studentToCopy.getPhone();
        email = studentToCopy.getEmail();
        academicYear = studentToCopy.getAcademicYear();
        tags = new HashSet<>(studentToCopy.getTags());
        trainingAttendances = new ArrayList<>(studentToCopy.getTrainingAttendances());
        mondayDismissal = (Monday) studentToCopy.getMondayDismissal();
        tuesdayDismissal = (Tuesday) studentToCopy.getTuesdayDismissal();
        wednesdayDismissal = (Wednesday) studentToCopy.getWednesdayDismissal();
        thursdayDismissal = (Thursday) studentToCopy.getThursdayDismissal();
        fridayDismissal = (Friday) studentToCopy.getFridayDismissal();
        id = studentToCopy.getId();
    }

    /**
     * Sets the {@code Name} of the {@code Student} that we are building.
     */
    public StudentBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Student} that we are building.
     */
    public StudentBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Parses the {@code trainingAttendances} into a {@code TreeSet<Attend>} and set it to the {@code Student}
     * that we are building.
     */
    public StudentBuilder withTrainingAttendances(Attendance... trainingAttendances) {
        this.trainingAttendances = SampleDataUtil.getTrainingAttendances(trainingAttendances);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Student} that we are building.
     */
    public StudentBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Student} that we are building.
     */
    public StudentBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code AcademicYear} of the {@code Student} that we are building.
     */
    public StudentBuilder withAcademicYear(String academicYear) {
        this.academicYear = new AcademicYear(academicYear);
        return this;
    }

    /**
     * Sets the {@code Monday} of the {@code Student} that we are building.
     */
    public StudentBuilder withMondayDismissal(String mondayDismissal) {
        this.mondayDismissal = new Monday(mondayDismissal);
        return this;
    }

    /**
     * Sets the {@code Tuesday} of the {@code Student} that we are building.
     */

    public StudentBuilder withTuesdayDismissal(String tuesdayDismissal) {
        this.tuesdayDismissal = new Tuesday(tuesdayDismissal);
        return this;
    }

    /**
     * Sets the {@code Wednesday} of the {@code Student} that we are building.
     */
    public StudentBuilder withWednesdayDismissal(String wednesdayDismissal) {
        this.wednesdayDismissal = new Wednesday(wednesdayDismissal);
        return this;
    }

    /**
     * Sets the {@code Thursday} of the {@code Student} that we are building.
     */
    public StudentBuilder withThursdayDismissal(String thursdayDismissal) {
        this.thursdayDismissal = new Thursday(thursdayDismissal);
        return this;
    }

    /**
     * Sets the {@code Friday} of the {@code Student} that we are building.
     */
    public StudentBuilder withFridayDismissal(String fridayDismissal) {
        this.fridayDismissal = new Friday(fridayDismissal);
        return this;
    }

    /**
     * Sets the {@code Id} of the {@code Student} that we are building.
     */
    public StudentBuilder withId(String id) {
        this.id = new Id(id);
        return this;
    }

    /**
     * Builds a student
     * @return Student
     */
    public Student build() {
        Student newStudent = new Student(name, phone, email, academicYear, tags,
            mondayDismissal, tuesdayDismissal, wednesdayDismissal, thursdayDismissal, fridayDismissal, id);
        newStudent.addAllAttendances(trainingAttendances);
        return newStudent;
    }

}
