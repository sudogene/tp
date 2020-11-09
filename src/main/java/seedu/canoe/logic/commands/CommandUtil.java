package seedu.canoe.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;

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

    /**
     * Gets the Student from the Model using the input Student Id.
     * @throws CommandException if the Student does not exist.
     */
    public static Student getStudentFromId(Model model, String id) throws CommandException {
        requireNonNull(model);
        requireNonNull(id);

        for (Student student : model.getFilteredStudentList()) {
            if (student.getId().getValue().equals(id)) {
                return student;
            }
        }

        throw new CommandException(MESSAGE_STUDENT_DOES_NOT_EXIST);
    }

    /**
     * Generates the optional string message of the list of Students using their Id values.
     * May be empty if the given list is empty.
     */
    public static Optional<String> getStudentsMessage(List<Student> students) {
        requireNonNull(students);

        return students.stream()
                .map(Student::getId)
                .map(Id::toString)
                .reduce((id1, id2) -> id1 + ", " + id2);
    }
}
