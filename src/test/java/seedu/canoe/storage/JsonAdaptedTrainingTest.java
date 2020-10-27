package seedu.canoe.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.canoe.testutil.Assert.assertThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import org.junit.jupiter.api.Test;

import seedu.canoe.model.training.Training;

public class JsonAdaptedTrainingTest {
    private static final LocalDateTime VALID_DATETIME = LocalDateTime.parse("2020-10-20T19:00:00");
    private static final Training VALID_TRAINING = new Training(VALID_DATETIME);

    @Test
    public void toModelType_validTrainingDetails_returnsTraining() throws Exception {
        JsonAdaptedTraining training = new JsonAdaptedTraining(VALID_TRAINING);
        assertEquals(VALID_TRAINING, training.toModelType());
    }

    @Test
    public void toModelType_invalidDateTime_throwsDateTimeParseException() {
        assertThrows(DateTimeParseException.class, () ->
                new JsonAdaptedTraining(new Training(LocalDateTime.parse("2020-20-20T19:00:00"))));
    }

    @Test
    public void toModelType_nullDateTime_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new JsonAdaptedTraining(null).toModelType());
    }
}
