package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.student.Email;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Student;
import seedu.address.model.student.time.Friday;
import seedu.address.model.student.time.Monday;
import seedu.address.model.student.time.Thursday;
import seedu.address.model.student.time.Tuesday;
import seedu.address.model.student.time.Wednesday;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

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


    private Name name;
    private Phone phone;
    private Email email;
    private Set<Tag> tags;
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
        tags = new HashSet<>();
        mondayDismissal = new Monday(DEFAULT_MONDAY);
        tuesdayDismissal = new Tuesday(DEFAULT_TUESDAY);
        wednesdayDismissal = new Wednesday(DEFAULT_WEDNESDAY);
        thursdayDismissal = new Thursday(DEFAULT_THURSDAY);
        fridayDismissal = new Friday(DEFAULT_FRIDAY);
    }

    /**
     * Initializes the StudentBuilder with the data of {@code studentToCopy}.
     */
    public StudentBuilder(Student studentToCopy) {
        name = studentToCopy.getName();
        phone = studentToCopy.getPhone();
        email = studentToCopy.getEmail();
        tags = new HashSet<>(studentToCopy.getTags());
        mondayDismissal = (Monday) studentToCopy.getMondayDismissal();
        tuesdayDismissal = (Tuesday) studentToCopy.getTuesdayDismissal();
        wednesdayDismissal = (Wednesday) studentToCopy.getWednesdayDismissal();
        thursdayDismissal = (Thursday) studentToCopy.getThursdayDismissal();
        fridayDismissal = (Friday) studentToCopy.getFridayDismissal();
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
     * Builds a student
     * @return Student
     */
    public Student build() {
        return new Student(name, phone, email, tags,
            mondayDismissal, tuesdayDismissal, wednesdayDismissal, thursdayDismissal, fridayDismissal);
    }

}
