package seedu.address.testutil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.student.Student;
import seedu.address.model.student.Training;

public class TypicalTraining {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public static final Training VALID_TRAINING = new Training(LocalDateTime.parse("2020-11-20 1900", FORMATTER));

    public static final Training VALID_TRAINING1 = new Training(LocalDateTime.parse("2020-11-20 1800", FORMATTER));

    public static final Training VALID_TRAINING2 = new Training(LocalDateTime.parse("2020-10-20 1800", FORMATTER));

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
        return new ArrayList<>(Arrays.asList(VALID_TRAINING, VALID_TRAINING1, VALID_TRAINING2));
    }
}
