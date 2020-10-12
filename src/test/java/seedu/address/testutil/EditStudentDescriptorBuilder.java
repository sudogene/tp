package seedu.address.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.EditCommand;
import seedu.address.model.student.AcademicYear;
import seedu.address.model.student.Email;
import seedu.address.model.student.Id;
import seedu.address.model.student.Name;
import seedu.address.model.student.Phone;
import seedu.address.model.student.Student;
import seedu.address.model.student.time.Friday;
import seedu.address.model.student.time.Monday;
import seedu.address.model.student.time.Thursday;
import seedu.address.model.student.time.Tuesday;
import seedu.address.model.student.time.Wednesday;
import seedu.address.model.tag.Tag;

/**
 * A utility class to help with building EditStudentDescriptor objects.
 */
public class EditStudentDescriptorBuilder {

    private EditCommand.EditStudentDescriptor descriptor;

    public EditStudentDescriptorBuilder() {
        descriptor = new EditCommand.EditStudentDescriptor();
    }

    public EditStudentDescriptorBuilder(EditCommand.EditStudentDescriptor descriptor) {
        this.descriptor = new EditCommand.EditStudentDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditStudentDescriptor} with fields containing {@code student}'s details
     */
    public EditStudentDescriptorBuilder(Student student) {
        descriptor = new EditCommand.EditStudentDescriptor();
        descriptor.setName(student.getName());
        descriptor.setPhone(student.getPhone());
        descriptor.setEmail(student.getEmail());
        descriptor.setTags(student.getTags());
        descriptor.setMondayDismissal(student.getMondayDismissal());
        descriptor.setTuesdayDismissal(student.getTuesdayDismissal());
        descriptor.setWednesdayDismissal(student.getWednesdayDismissal());
        descriptor.setThursdayDismissal(student.getThursdayDismissal());
        descriptor.setFridayDismissal(student.getFridayDismissal());
    }

    /**
     * Sets the {@code Name} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code AcademicYear} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withAcademicYear(String academicYear) {
        descriptor.setAcademicYear(new AcademicYear(academicYear));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditStudentDescriptor}
     * that we are building.
     */
    public EditStudentDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTags(tagSet);
        return this;
    }

    /**
     * Sets the {@code Monday} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withMonday(String monday) {
        descriptor.setMondayDismissal(new Monday(monday));
        return this;
    }

    /**
     * Sets the {@code Tuesday} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withTuesday(String tuesday) {
        descriptor.setTuesdayDismissal(new Tuesday(tuesday));
        return this;
    }

    /**
     * Sets the {@code Wednesday} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withWednesday(String wednesday) {
        descriptor.setWednesdayDismissal(new Wednesday(wednesday));
        return this;
    }

    /**
     * Sets the {@code Thursday} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withThursday(String thursday) {
        descriptor.setThursdayDismissal(new Thursday(thursday));
        return this;
    }

    /**
     * Sets the {@code Friday} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withFriday(String friday) {
        descriptor.setFridayDismissal(new Friday(friday));
        return this;
    }

    /**
     * Sets the {@code Id} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withId(String validId) {
        descriptor.setId(new Id(validId));
        return this;
    }

    public EditCommand.EditStudentDescriptor build() {
        return descriptor;
    }
}
