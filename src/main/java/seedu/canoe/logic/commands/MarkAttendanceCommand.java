package seedu.canoe.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.canoe.commons.core.Messages.MESSAGE_STUDENTS_NOT_FOUND;
import static seedu.canoe.commons.core.Messages.MESSAGE_TRAININGS_NOT_FOUND;
import static seedu.canoe.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import seedu.canoe.commons.core.LogsCenter;
import seedu.canoe.commons.core.index.Index;
import seedu.canoe.logic.commands.exceptions.CommandException;
import seedu.canoe.model.Model;
import seedu.canoe.model.student.AnyMatchPredicateList;
import seedu.canoe.model.student.Attendance;
import seedu.canoe.model.student.Student;
import seedu.canoe.model.training.Training;

public class MarkAttendanceCommand extends Command {

    public static final Logger LOGGER = LogsCenter.getLogger(MarkAttendanceCommand.class);

    public static final String COMMAND_WORD = "mark-attendance";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Gets all students in the training session"
            + " whose Ids corresponds to the specified Ids and marks them as attended the training session.\n"
            + "Parameters: ID [MORE_IDS]...\n"
            + "Example: " + COMMAND_WORD + " 2 id/1,4,19";

    public static final String MESSAGE_NO_STUDENTS_SPECIFIED = "At least one student to be marked must be specified.";
    public static final String MESSAGE_INVALID_STUDENT_MARKED = "These students do not have the specified"
            + " training session scheduled: %1$s!";
    public static final String MESSAGE_MARK_ATTENDANCE_SUCCESS = "Marked these students for their attendance: %1$s!";
    public static final String MESSAGE_TRAINING_NOT_OVER = "The training is yet to be conducted, and attendance cannot "
        + "be marked yet!";

    private final Index trainingIndex;
    private final AnyMatchPredicateList predicates;

    /**
     * Constructs a new MarkAttendanceCommand object.
     *
     * @param trainingIndex index of training.
     * @param predicates list of predicates.
     */
    public MarkAttendanceCommand(Index trainingIndex, AnyMatchPredicateList predicates) {
        this.trainingIndex = trainingIndex;
        this.predicates = predicates;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        LOGGER.info("=============================[ Executing MarkAttendanceCommand ]===========================");
        requireNonNull(model);

        List<Training> lastShownList = model.getFilteredTrainingList();

        if (trainingIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(MESSAGE_TRAININGS_NOT_FOUND);
        }

        Training training = lastShownList.get(trainingIndex.getZeroBased());

        if (training.getDateTime().isAfter(LocalDateTime.now())) {
            throw new CommandException(MESSAGE_TRAINING_NOT_OVER);
        }

        model.updateFilteredStudentList(predicates);

        if (model.getFilteredStudentList().isEmpty()) {
            LOGGER.warning("Ids match zero students.");
            model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
            throw new CommandException(MESSAGE_STUDENTS_NOT_FOUND);
        }

        if (training.getDateTime().isAfter(LocalDateTime.now())) {
            throw new CommandException(MESSAGE_TRAINING_NOT_OVER);
        }

        List<Student> attendedStudents = model.getFilteredStudentList();

        Attendance unmarkedAttendance = new Attendance(training.getDateTime());
        Attendance markedAttendance = new Attendance(training.getDateTime());
        markedAttendance.marks();

        List<Student> studentsNoAttendance = studentsWithNoAttendance(unmarkedAttendance, attendedStudents);
        if (!studentsNoAttendance.isEmpty()) {
            LOGGER.warning("Some students do not contain training session");
            model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
            throw new CommandException(String.format(
                    MESSAGE_INVALID_STUDENT_MARKED, CommandUtil.getStudentsMessage(studentsNoAttendance).get())
            );
        }

        for (Student student : attendedStudents) {
            student.markAttendance(unmarkedAttendance, markedAttendance);
        }
        Optional<String> result = CommandUtil.getStudentsMessage(attendedStudents);

        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(String.format(MESSAGE_MARK_ATTENDANCE_SUCCESS, result.get()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof MarkAttendanceCommand // instanceof handles nulls
                && predicates.equals(((MarkAttendanceCommand) other).predicates)); // state check
    }

    /**
     * Checks the list of students and returns a list of students who do not have the attendance.
     *
     * @param attendance to be checked for.
     * @param studentsToCheck list of students to check.
     * @return list of students who do not have training session scheduled.
     */
    public List<Student> studentsWithNoAttendance(Attendance attendance, List<Student> studentsToCheck) {
        assert attendance != null;
        List<Student> studentsNoAttendance = studentsToCheck.stream()
                .filter(student -> !student.containsAttendance(attendance))
                .collect(Collectors.toList());

        return studentsNoAttendance;
    }
}
