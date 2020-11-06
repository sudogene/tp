package seedu.canoe.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.canoe.logic.commands.exceptions.CommandException;
import seedu.canoe.model.Model;
import seedu.canoe.model.student.Id;
import seedu.canoe.model.student.Student;

/**
 * Contains utility methods used for *Command classes.
 */
public class CommandUtil {

    public static final String MESSAGE_STUDENT_DOES_NOT_EXIST = "An Id specified "
            + "did not correspond to an existing Student!";

    public static Student getStudentFromId(Model model, Id id) throws CommandException {
        requireNonNull(model);
        requireNonNull(id);

        for (Student student : model.getFilteredStudentList()) {
            if (student.getId().equals(id)) {
                return student;
            }
        }

        throw new CommandException(MESSAGE_STUDENT_DOES_NOT_EXIST);
    }
}
