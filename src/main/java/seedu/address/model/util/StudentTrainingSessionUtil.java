package seedu.address.model.util;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.Model;
import seedu.address.model.student.Student;
import seedu.address.model.student.Training;
import seedu.address.model.student.TrainingMatchesPredicate;

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
