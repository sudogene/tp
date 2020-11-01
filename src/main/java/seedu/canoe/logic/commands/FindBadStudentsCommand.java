package seedu.canoe.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import seedu.canoe.commons.core.LogsCenter;
import seedu.canoe.model.Model;
import seedu.canoe.model.student.Student;

public class FindBadStudentsCommand extends Command {

    public static final Logger LOGGER = LogsCenter.getLogger(FindBadStudentsCommand.class);

    public static final String COMMAND_WORD = "find-bad-students";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds all students who have missed more than 3 training "
            + "sessions and displays them as a list.\n"
            + "Example: " + COMMAND_WORD;

    private static final String NO_BAD_STUDENTS_MESSAGE = "No students with a bad attendance record were found!";

    private ArrayList<Student> badStudents = new ArrayList<Student>();

    @Override
    public CommandResult execute(Model model) {
        LOGGER.info("=============================[ Executing FindBadStudentsCommand ]===========================");
        requireNonNull(model);

        model.updateFilteredStudentList(Model.PREDICATE_SHOW_ALL_STUDENTS);
        List<Student> studentList = model.getFilteredStudentList();

        for (Student student : studentList) {
            if (student.hasBadAttendanceRecord()) {
                badStudents.add(student);
            }
        }

        if (badStudents.isEmpty()) {
            LOGGER.info("No students with a bad attendance record were found!");
            return new CommandResult(NO_BAD_STUDENTS_MESSAGE);
        }
        String result = getStudentNamesAndIdsAsString(badStudents);

        return new CommandResult(result);
    }

    /**
     * Returns a String with the names and IDs of the students.
     *
     * @return String with names and IDs of Students.
     */
    public String getStudentNamesAndIdsAsString(List<Student> students) {
        String result = "";
        for (Student student : students) {
            result += student.getName() + "(";
            result += "Id: " + student.getId() + ") ";
        }
        result = result.trim();
        return result;
    }
}
