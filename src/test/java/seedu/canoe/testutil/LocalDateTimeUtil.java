package seedu.canoe.testutil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * List of local date times for testing purposes.
 */
public class LocalDateTimeUtil {
    //Do not modify any of the local date times to prevent regressions with other tests
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    public static final LocalDateTime VALID_LOCAL_DATE_TIME_2 = LocalDateTime.parse("2060-09-20 2300", FORMATTER);
    public static final LocalDateTime VALID_LOCAL_DATE_TIME_3 = LocalDateTime.parse("2060-09-20 1000", FORMATTER);
    public static final LocalDateTime PAST_LOCAL_DATE_TIME = LocalDateTime.parse("2020-10-23 1500", FORMATTER);
    public static final LocalDateTime DATE_TIME_NOW_PLUS_ONE_DAY = LocalDateTime.now().plusDays(1);
    public static final LocalDateTime DATE_TIME_NOW_PLUS_TWO_DAYS = LocalDateTime.now().plusDays(2);
    public static final LocalDateTime DATE_TIME_NOW_PLUS_THREE_DAYS = LocalDateTime.now().plusDays(3);
    public static final LocalDateTime DATE_TIME_NOW_PLUS_FOUR_DAYS = LocalDateTime.now().plusDays(4);
    public static final LocalDateTime DATE_TIME_NOW_MINUS_ONE_DAY = LocalDateTime.now().minusDays(1);
    public static final LocalDateTime DATE_TIME_NOW_MINUS_TWO_DAYS = LocalDateTime.now().minusDays(2);
    public static final LocalDateTime DATE_TIME_NOW_MINUS_THREE_DAYS = LocalDateTime.now().minusDays(3);
    public static final LocalDateTime DATE_TIME_NOW_MINUS_FOUR_DAYS = LocalDateTime.now().minusDays(4);

}
