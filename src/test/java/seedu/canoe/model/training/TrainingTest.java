package seedu.canoe.model.training;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.canoe.testutil.LocalDateTimeUtil.VALID_LOCAL_DATE_TIME_2;
import static seedu.canoe.testutil.TypicalTraining.VALID_TRAINING;
import static seedu.canoe.testutil.TypicalTraining.VALID_TRAINING2;

import org.junit.jupiter.api.Test;

import seedu.canoe.testutil.TrainingBuilder;

public class TrainingTest {

    @Test
    public void isSameTraining() {
        // same object -> returns true
        assertTrue(VALID_TRAINING.isSameTraining(VALID_TRAINING));

        // null -> returns false
        assertFalse(VALID_TRAINING.isSameTraining(null));

        // different datetime -> returns false
        Training editedTraining = new TrainingBuilder(VALID_TRAINING).withDateTime(VALID_LOCAL_DATE_TIME_2).build();
        assertFalse(VALID_TRAINING.isSameTraining(editedTraining));

    }

    @Test
    public void equals() {
        // same values -> returns true
        Training training = new TrainingBuilder(VALID_TRAINING).build();
        assertTrue(VALID_TRAINING.equals(training));

        // same object -> returns true
        assertTrue(VALID_TRAINING.equals(VALID_TRAINING));

        // null -> returns false
        assertFalse(VALID_TRAINING.equals(null));

        // different type -> returns false
        assertFalse(VALID_TRAINING.equals(5));

        // different student -> returns false
        assertFalse(VALID_TRAINING.equals(VALID_TRAINING2));

        // different date time -> returns false
        Training editedTraining = new TrainingBuilder(VALID_TRAINING).withDateTime(VALID_LOCAL_DATE_TIME_2).build();
        assertFalse(VALID_TRAINING.equals(editedTraining));

    }

}
