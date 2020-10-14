package seedu.address.testutil;

import static seedu.address.testutil.LocalDateTimeUtil.VALID_LOCAL_DATE_TIME;
import static seedu.address.testutil.LocalDateTimeUtil.VALID_LOCAL_DATE_TIME_1;
import static seedu.address.testutil.LocalDateTimeUtil.VALID_LOCAL_DATE_TIME_3;
import static seedu.address.testutil.LocalDateTimeUtil.VALID_LOCAL_DATE_TIME_4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.student.Training;

public class TypicalTraining {

    public static final Training VALID_TRAINING = new TrainingBuilder().withDateTime(VALID_LOCAL_DATE_TIME_1).build();

    public static final Training VALID_TRAINING1 = new TrainingBuilder().withDateTime(VALID_LOCAL_DATE_TIME).build();

    public static final Training VALID_TRAINING2 = new TrainingBuilder().withDateTime(VALID_LOCAL_DATE_TIME_4).build();

    public static final Training VALID_TRAINING3 = new TrainingBuilder().withDateTime(VALID_LOCAL_DATE_TIME_3).build();

    /**
     * Returns an {@code AddressBook} with all the typical trainings.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Training training : getTypicalTrainings()) {
            ab.addTraining(training);
        }
        return ab;
    }

    public static List<Training> getTypicalTrainings() {
        return new ArrayList<>(Arrays.asList(VALID_TRAINING, VALID_TRAINING1, VALID_TRAINING2, VALID_TRAINING3));
    }
}
