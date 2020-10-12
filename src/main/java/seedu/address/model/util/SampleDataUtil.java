package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
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
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Student[] getSampleStudents() {
        return new Student[] {
            new Student(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new AcademicYear("2"), getTagSet("friends"), new Monday("1500"),
                new Tuesday("1500"), new Wednesday("1500"),
                new Thursday("1500"), new Friday("1500"), Id.newId()),
            new Student(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new AcademicYear("3"), getTagSet("colleagues", "friends"), new Monday("1500"),
                new Tuesday("1500"), new Wednesday("1500"),
                new Thursday("1500"), new Friday("1500"), Id.newId()),
            new Student(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new AcademicYear("5"),
                getTagSet("neighbours"), new Monday("1500"), new Tuesday("1500"),
                new Wednesday("1500"), new Thursday("1500"), new Friday("1500"),
                    Id.newId()),
            new Student(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new AcademicYear("1"), getTagSet("family"), new Monday("1500"),
                new Tuesday("1500"), new Wednesday("1500"),
                new Thursday("1500"), new Friday("1500"), Id.newId()),
            new Student(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new AcademicYear("4"), getTagSet("classmates"), new Monday("1500"),
                new Tuesday("1500"), new Wednesday("1500"),
                new Thursday("1500"), new Friday("1500"), Id.newId()),
            new Student(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new AcademicYear("1"), getTagSet("colleagues"), new Monday("1500"),
                new Tuesday("1500"), new Wednesday("1500"),
                new Thursday("1500"), new Friday("1500"), Id.newId())
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Student sampleStudent : getSampleStudents()) {
            sampleAb.addStudent(sampleStudent);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
