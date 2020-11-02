package seedu.canoe.model.util;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.canoe.model.Model;
import seedu.canoe.model.student.Attendance;
import seedu.canoe.model.student.Student;
import seedu.canoe.model.training.Training;
import seedu.canoe.model.training.TrainingMatchesPredicate;

public class StudentTrainingSessionUtil {
    public static List<Attendance> getConflictsInStudentTrainingAttendances(
        Set<Attendance> trainingSchedules, Student student) {
        List<Attendance> conflicts = trainingSchedules
                .stream()
                .filter(attend -> attend.getTrainingTime().isAfter(LocalDateTime.now())
                        && !student.isAvailableAtDateTime(attend.getTrainingTime()))
                .collect(Collectors.toList());
        return conflicts;
    }

    public static List<Training> getTrainingListFromTrainingAttendances(List<Attendance> trainingAttendances,
                                                                        Model model) {
        TrainingMatchesPredicate predicate = new TrainingMatchesPredicate(trainingAttendances);
        model.updateFilteredTrainingList(predicate);
        return model.getFilteredTrainingList();
    }
}
