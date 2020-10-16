package seedu.canoe.model.student.exceptions;

/**
 * Signals that the operation will result in duplicate Trainings (Trainings are considered duplicates if they have the
 * same identity).
 */
public class DuplicateTrainingException extends RuntimeException {
    public DuplicateTrainingException() {
        super("Operation would result in duplicate Trainings");
    }
}
