package seedu.canoe.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.canoe.commons.core.index.Index;
import seedu.canoe.commons.util.StringUtil;
import seedu.canoe.logic.parser.exceptions.ParseException;
import seedu.canoe.model.student.AcademicYear;
import seedu.canoe.model.student.Email;
import seedu.canoe.model.student.Id;
import seedu.canoe.model.student.Name;
import seedu.canoe.model.student.Phone;
import seedu.canoe.model.student.time.Day;
import seedu.canoe.model.student.time.Friday;
import seedu.canoe.model.student.time.Monday;
import seedu.canoe.model.student.time.Thursday;
import seedu.canoe.model.student.time.Tuesday;
import seedu.canoe.model.student.time.Wednesday;
import seedu.canoe.model.tag.Tag;
import seedu.canoe.model.training.Training;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes and doing checks.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer!";
    public static final String MESSAGE_REPEATED_ID = "Id cannot be repeated!";
    public static final String MESSAGE_NO_ID_PROVIDED = "At least one valid Id must be provided!";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String dismissalTime} into a {@code Day} based on the {@code dayOfWeek}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code dismissalTime} or {@code dayOfWeek} is invalid.
     */
    public static Day parseDismissal(Day.DayOfWeek dayOfWeek, String dismissalTime) throws ParseException {
        requireNonNull(dayOfWeek, dismissalTime);
        String trimmedDismissalTime = dismissalTime.trim();
        if (!Day.isValidDismissalTime(trimmedDismissalTime)) {
            throw new ParseException(Day.MESSAGE_CONSTRAINTS);
        }
        switch (dayOfWeek) {
        case MONDAY:
            return new Monday(trimmedDismissalTime);
        case TUESDAY:
            return new Tuesday(trimmedDismissalTime);
        case WEDNESDAY:
            return new Wednesday(trimmedDismissalTime);
        case THURSDAY:
            return new Thursday(trimmedDismissalTime);
        case FRIDAY:
            return new Friday(trimmedDismissalTime);
        default:
            throw new ParseException("Unexpected value: " + dayOfWeek);
        }
    }

    /**
     * Parses a {@code String academicYear} into an {@code AcademicYear}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code academicYear} is invalid.
     */
    public static AcademicYear parseAcademicYear(String academicYear) throws ParseException {
        requireNonNull(academicYear);
        String trimmedAcademicYear = academicYear.trim();
        if (!AcademicYear.isValidAcademicYear(trimmedAcademicYear)) {
            throw new ParseException(AcademicYear.MESSAGE_CONSTRAINTS);
        }
        return new AcademicYear(trimmedAcademicYear);
    }

    /**
     * Parses a {@code String training} into a {@code Training}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code training} is invalid.
     */
    public static Training parseTraining(String training) throws ParseException {
        requireNonNull(training);
        String trimmedTraining = training.trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd HHmm")
                .withResolverStyle(ResolverStyle.STRICT);
        try {
            LocalDateTime dateTime = LocalDateTime.parse(trimmedTraining, formatter);
            return new Training(dateTime);
        } catch (DateTimeException e) {
            throw new ParseException(Training.MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses {@code String id} value.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code id} is invalid.
     */
    public static String parseIdValue(String id) throws ParseException {
        requireNonNull(id);
        String trimmedId = id.trim();
        if (!Id.isValidId(trimmedId)) {
            throw new ParseException(Id.MESSAGE_CONSTRAINTS);
        }
        return trimmedId;
    }

    /**
     * Parses a {@code String[] id} array into a list of {@code String Id} values.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given array contains invalid {@code id}.
     */
    public static List<String> parseMultipleIds(String[] ids) throws ParseException {
        requireNonNull(ids);

        if (ids.length == 0) {
            throw new ParseException(MESSAGE_NO_ID_PROVIDED);
        }

        if (!isUniqueList(ids)) {
            throw new ParseException(MESSAGE_REPEATED_ID);
        }

        List<String> idList = new ArrayList<>();

        for (String id : ids) {
            String parsedIdValue = parseIdValue(id);
            idList.add(parsedIdValue);
        }

        return idList;
    }

    /**
     * Checks if a given array of strings is unique.
     */
    public static boolean isUniqueList(String[] toCheck) {
        List<String> toCheckList = Arrays.asList(toCheck);
        return new HashSet<>(toCheckList).size() == toCheckList.size();
    }
}
