package seedu.address.testutil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeUtil {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    public static final LocalDateTime VALID_LOCAL_DATE_TIME = LocalDateTime.parse("2020-11-20 1800", FORMATTER);
    public static final LocalDateTime VALID_LOCAL_DATE_TIME_2 = LocalDateTime.parse("2020-11-21 1500", FORMATTER);
}
