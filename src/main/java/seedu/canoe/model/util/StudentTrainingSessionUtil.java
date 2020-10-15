package seedu.canoe.model.util;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.canoe.model.Model;
import seedu.canoe.model.student.Student;
import seedu.canoe.model.student.Training;
import seedu.canoe.model.student.TrainingMatchesPredicate;

public class StudentTrainingSessionUtil {
    public static List<LocalDateTime> getConflictsInStudentTrainingSchedule(
            Set<LocalDateTime> trainingSchedules, Student student) {
        List<LocalDateTime> conflicts = trainingSchedules
                .stream()
                .filter(training -> !student.isAvailableAtDateTime(training))
                .collect(Collectors.toList());
        return conflicts;
    }

    public static List<Training> getTrainingListFromDateTimeList(List<LocalDateTime> localDateTimes, Model model) {
        TrainingMatchesPredicate predicate = new TrainingMatchesPredicate(localDateTimes);
        model.updateFilteredTrainingList(predicate);
        return model.getFilteredTrainingList();
    }
}
