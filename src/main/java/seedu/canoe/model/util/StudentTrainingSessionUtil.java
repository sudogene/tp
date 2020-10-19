package seedu.canoe.model.util;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.canoe.model.Model;
import seedu.canoe.model.student.Attend;
import seedu.canoe.model.student.Student;
import seedu.canoe.model.student.Training;
import seedu.canoe.model.student.TrainingMatchesPredicate;

public class StudentTrainingSessionUtil {
    public static List<Attend> getConflictsInStudentTrainingSchedule(
            Set<Attend> trainingSchedules, Student student) {
        List<Attend> conflicts = trainingSchedules
                .stream()
                .filter(attend -> !student.isAvailableAtDateTime(attend.getTrainingTime()))
                .collect(Collectors.toList());
        return conflicts;
    }

    public static List<Training> getTrainingListFromAttendList(List<Attend> trainingsAttending, Model model) {
        TrainingMatchesPredicate predicate = new TrainingMatchesPredicate(trainingsAttending);
        model.updateFilteredTrainingList(predicate);
        return model.getFilteredTrainingList();
    }
}
