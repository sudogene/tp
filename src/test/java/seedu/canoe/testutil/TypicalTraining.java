package seedu.canoe.testutil;

import static seedu.canoe.testutil.LocalDateTimeUtil.DATE_TIME_NOW_MINUS_ONE_DAY;
import static seedu.canoe.testutil.LocalDateTimeUtil.DATE_TIME_NOW_MINUS_THREE_DAYS;
import static seedu.canoe.testutil.LocalDateTimeUtil.DATE_TIME_NOW_MINUS_TWO_DAYS;
import static seedu.canoe.testutil.LocalDateTimeUtil.DATE_TIME_NOW_PLUS_FOUR_DAYS;
import static seedu.canoe.testutil.LocalDateTimeUtil.DATE_TIME_NOW_PLUS_ONE_DAY;
import static seedu.canoe.testutil.LocalDateTimeUtil.DATE_TIME_NOW_PLUS_THREE_DAYS;
import static seedu.canoe.testutil.LocalDateTimeUtil.DATE_TIME_NOW_PLUS_TWO_DAYS;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.canoe.model.CanoeCoach;
import seedu.canoe.model.training.Training;

public class TypicalTraining {

    public static final Training VALID_TRAINING = new TrainingBuilder()
            .withDateTime(DATE_TIME_NOW_PLUS_ONE_DAY).build();

    public static final Training VALID_TRAINING1 = new TrainingBuilder()
            .withDateTime(DATE_TIME_NOW_PLUS_TWO_DAYS).build();

    public static final Training VALID_TRAINING2 = new TrainingBuilder()
            .withDateTime(DATE_TIME_NOW_PLUS_THREE_DAYS).build();

    public static final Training VALID_TRAINING3 = new TrainingBuilder()
            .withDateTime(DATE_TIME_NOW_PLUS_FOUR_DAYS).build();

    public static final Training VALID_PAST_TRAINING = new TrainingBuilder()
        .withDateTime(DATE_TIME_NOW_MINUS_ONE_DAY).build();

    public static final Training VALID_PAST_TRAINING1 = new TrainingBuilder()
        .withDateTime(DATE_TIME_NOW_MINUS_TWO_DAYS).build();

    public static final Training VALID_PAST_TRAINING2 = new TrainingBuilder()
        .withDateTime(DATE_TIME_NOW_MINUS_THREE_DAYS).build();


    /**
     * Returns an {@code CanoeCoach} with all the typical trainings.
     */
    public static CanoeCoach getTypicalAddressBook() {
        CanoeCoach ab = new CanoeCoach();
        for (Training training : getTypicalTrainings()) {
            ab.addTraining(training);
        }
        return ab;
    }

    public static List<Training> getTypicalTrainings() {
        return new ArrayList<>(Arrays.asList(VALID_TRAINING, VALID_TRAINING1, VALID_TRAINING2, VALID_TRAINING3));
    }
}
