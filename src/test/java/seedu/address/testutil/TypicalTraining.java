package seedu.address.testutil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import seedu.address.model.student.Training;

public class TypicalTraining {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public static final Training VALID_TRAINING = new Training(LocalDateTime.parse("2020-11-20 1900", FORMATTER));
}
