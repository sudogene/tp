package seedu.canoe.testutil;

import seedu.canoe.model.CanoeCoach;
import seedu.canoe.model.student.Student;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 *     {@code CanoeCoach ab = new AddressBookBuilder().withStudent("John", "Doe").build();}
 */
public class AddressBookBuilder {

    private CanoeCoach canoeCoach;

    public AddressBookBuilder() {
        canoeCoach = new CanoeCoach();
    }

    public AddressBookBuilder(CanoeCoach canoeCoach) {
        this.canoeCoach = canoeCoach;
    }

    /**
     * Adds a new {@code Student} to the {@code CanoeCoach} that we are building.
     */
    public AddressBookBuilder withStudent(Student student) {
        canoeCoach.addStudent(student);
        return this;
    }

    public CanoeCoach build() {
        return canoeCoach;
    }
}
