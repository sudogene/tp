package seedu.canoe.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.canoe.commons.exceptions.IllegalValueException;
import seedu.canoe.model.CanoeCoach;
import seedu.canoe.model.ReadOnlyCanoeCoach;
import seedu.canoe.model.student.Student;
import seedu.canoe.model.training.Training;

/**
 * An Immutable CanoeCoach that is serializable to JSON format.
 */
@JsonRootName(value = "canoecoach")
class JsonSerializableCanoeCoach {

    public static final String MESSAGE_DUPLICATE_STUDENT = "Students list contains duplicate student(s).";
    public static final String MESSAGE_DUPLICATE_TRAINING = "Training list contains duplicate Training Session(s).";

    private final List<JsonAdaptedStudent> students = new ArrayList<>();
    private final List<JsonAdaptedTraining> trainings = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableCanoeCoach} with the given students.
     */
    @JsonCreator
    public JsonSerializableCanoeCoach(@JsonProperty("students") List<JsonAdaptedStudent> students,
                                      @JsonProperty("trainings") List<JsonAdaptedTraining> trainings) {
        this.students.addAll(students);
        this.trainings.addAll(trainings);
    }

    /**
     * Converts a given {@code ReadOnlyCanoeCoach} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableCanoeCoach}.
     */
    public JsonSerializableCanoeCoach(ReadOnlyCanoeCoach source) {
        students.addAll(source.getStudentList().stream().map(JsonAdaptedStudent::new).collect(Collectors.toList()));
        trainings.addAll(source.getTrainingList().stream().map(JsonAdaptedTraining::new).collect(Collectors.toList()));
    }

    /**
     * Converts this canoe coach book into the model's {@code CanoeCoach} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public CanoeCoach toModelType() throws IllegalValueException {
        CanoeCoach canoeCoach = new CanoeCoach();
        for (JsonAdaptedStudent jsonAdaptedStudent : students) {
            Student student = jsonAdaptedStudent.toModelType();
            if (canoeCoach.hasStudent(student)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_STUDENT);
            }
            canoeCoach.addStudent(student);
        }
        for (JsonAdaptedTraining jsonAdaptedTraining : trainings) {
            Training training = jsonAdaptedTraining.toModelType();
            if (canoeCoach.hasTraining(training)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TRAINING);
            }
            canoeCoach.addTraining(training);
        }

        return canoeCoach;
    }

}
