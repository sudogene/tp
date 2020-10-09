package seedu.address.testutil;

import seedu.address.model.student.Training;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TypicalTraining {
    public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public static final Training VALID_TRAINING = new Training(LocalDateTime.parse("2020-11-20 1900", formatter));
}
