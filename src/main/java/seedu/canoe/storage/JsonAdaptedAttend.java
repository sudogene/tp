package seedu.canoe.storage;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.canoe.commons.exceptions.IllegalValueException;
import seedu.canoe.model.student.Attendance;

/**
 * Jackson-friendly version of {@link Attendance}.
 */
class JsonAdaptedAttend {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Attend's %s field is missing!";

    private final LocalDateTime trainingTime;
    private boolean isMarked = false;

    /**
     * Constructs a {@code JsonAdaptedAttend} with the given {@code trainingTime}.
     */
    @JsonCreator
    public JsonAdaptedAttend(@JsonProperty("trainingTime") LocalDateTime trainingTime,
                             @JsonProperty("isMarked") boolean isMarked) {
        this.trainingTime = trainingTime;
        this.isMarked = isMarked;
    }

    /**
     * Converts a given {@code Attend} into this class for Jackson use.
     */
    public JsonAdaptedAttend(Attendance source) {
        trainingTime = source.getTrainingTime();
        isMarked = source.isMarked();
    }

    /**
     * Converts this Jackson-friendly adapted attend object into the model's {@code Attend} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted attend.
     */
    public Attendance toModelType() throws IllegalValueException {

        if (trainingTime == null) {
            throw new IllegalValueException(String
                    .format(MISSING_FIELD_MESSAGE_FORMAT, LocalDateTime.class.getSimpleName()));
        }
        Attendance attendance = new Attendance(trainingTime);
        if (isMarked) {
            attendance.marks();
        }
        return attendance;
    }
}
