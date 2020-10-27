package seedu.canoe.storage;

import static seedu.canoe.commons.core.Messages.MESSAGE_INVALID_DATE_TIME;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.canoe.commons.exceptions.IllegalValueException;
import seedu.canoe.model.student.Student;
import seedu.canoe.model.training.Training;


/**
 * Jackson-friendly version of {@link Training}.
 */
class JsonAdaptedTraining {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Training's %s field is missing!";

    private final LocalDateTime dateTime;
    private final List<JsonAdaptedStudent> students = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedTraining} with the given training details.
     */
    @JsonCreator
    public JsonAdaptedTraining(@JsonProperty("dateTime") String dateTime,
                              @JsonProperty("students") List<JsonAdaptedStudent> students) {
        try {
            this.dateTime = LocalDateTime.parse(dateTime);
            if (students != null) {
                this.students.addAll(students);
            }
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(MESSAGE_INVALID_DATE_TIME);
        }
    }

    /**
     * Converts a given {@code Training} into this class for Jackson use.
     */
    public JsonAdaptedTraining(Training source) {
        dateTime = source.getDateTime();

        students.addAll(source.getStudents().stream()
                .map(JsonAdaptedStudent::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted training object into the model's {@code Training} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted training.
     */
    public Training toModelType() throws IllegalValueException {
        final List<Student> studentList = new ArrayList<>();
        for (JsonAdaptedStudent student : students) {
            studentList.add(student.toModelType());
        }

        if (dateTime == null) {
            throw new IllegalValueException(String
                    .format(MISSING_FIELD_MESSAGE_FORMAT, LocalDateTime.class.getSimpleName()));
        }

        final LocalDateTime modelDateTime = dateTime;
        Set<Student> studentSet = new HashSet<>(studentList);

        return new Training(modelDateTime, studentSet);
    }
}
