package seedu.canoe.testutil;

import static seedu.canoe.logic.parser.CliSyntax.PREFIX_ACADEMIC_YEAR;
import static seedu.canoe.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.canoe.logic.parser.CliSyntax.PREFIX_FRIDAY_DISMISSAL;
import static seedu.canoe.logic.parser.CliSyntax.PREFIX_MONDAY_DISMISSAL;
import static seedu.canoe.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.canoe.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.canoe.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.canoe.logic.parser.CliSyntax.PREFIX_THURSDAY_DISMISSAL;
import static seedu.canoe.logic.parser.CliSyntax.PREFIX_TUESDAY_DISMISSAL;
import static seedu.canoe.logic.parser.CliSyntax.PREFIX_WEDNESDAY_DISMISSAL;

import java.util.Set;

import seedu.canoe.logic.commands.AddCommand;
import seedu.canoe.logic.commands.EditCommand;
import seedu.canoe.model.student.Student;
import seedu.canoe.model.tag.Tag;

/**
 * A utility class for Student.
 */
public class StudentUtil {

    /**
     * Returns an add command string for adding the {@code student}.
     */
    public static String getAddCommand(Student student) {
        return AddCommand.COMMAND_WORD + " " + getStudentDetails(student);
    }

    /**
     * Returns the part of command string for the given {@code student}'s details.
     */
    public static String getStudentDetails(Student student) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + student.getName().fullName + " ");
        sb.append(PREFIX_PHONE + student.getPhone().value + " ");
        sb.append(PREFIX_EMAIL + student.getEmail().value + " ");
        sb.append(PREFIX_ACADEMIC_YEAR + student.getAcademicYear().value + " ");
        sb.append(PREFIX_MONDAY_DISMISSAL + student.getMondayDismissal().toString() + " ");
        sb.append(PREFIX_TUESDAY_DISMISSAL + student.getTuesdayDismissal().toString() + " ");
        sb.append(PREFIX_WEDNESDAY_DISMISSAL + student.getWednesdayDismissal().toString() + " ");
        sb.append(PREFIX_THURSDAY_DISMISSAL + student.getThursdayDismissal().toString() + " ");
        sb.append(PREFIX_FRIDAY_DISMISSAL + student.getFridayDismissal().toString() + " ");
        student.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditStudentDescriptor}'s details.
     */
    public static String getEditStudentDescriptorDetails(EditCommand.EditStudentDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
        descriptor.getAcademicYear().ifPresent(academicYear -> sb.append(PREFIX_ACADEMIC_YEAR)
                .append(academicYear.value).append(" "));
        descriptor.getMondayDismissal().ifPresent(monday -> sb.append(PREFIX_MONDAY_DISMISSAL)
            .append(monday.toString()).append(" "));
        descriptor.getTuesdayDismissal().ifPresent(tuesday -> sb.append(PREFIX_TUESDAY_DISMISSAL)
            .append(tuesday.toString()).append(" "));
        descriptor.getWednesdayDismissal().ifPresent(wednesday -> sb.append(PREFIX_WEDNESDAY_DISMISSAL)
            .append(wednesday.toString()).append(" "));
        descriptor.getThursdayDismissal().ifPresent(thursday -> sb.append(PREFIX_THURSDAY_DISMISSAL)
            .append(thursday.toString()).append(" "));
        descriptor.getFridayDismissal().ifPresent(friday -> sb.append(PREFIX_FRIDAY_DISMISSAL)
            .append(friday.toString()).append(" "));

        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
