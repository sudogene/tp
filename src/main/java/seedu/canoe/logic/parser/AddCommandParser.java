package seedu.canoe.logic.parser;

import static seedu.canoe.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.canoe.logic.parser.CliSyntax.PREFIX_ACADEMIC_YEAR;
import static seedu.canoe.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.canoe.logic.parser.CliSyntax.PREFIX_FRIDAY_DISMISSAL;
import static seedu.canoe.logic.parser.CliSyntax.PREFIX_MONDAY_DISMISSAL;
import static seedu.canoe.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.canoe.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.canoe.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.canoe.logic.parser.CliSyntax.PREFIX_THURSDAY_DISMISSAL;
import static seedu.canoe.logic.parser.CliSyntax.PREFIX_TUESDAY_DISMISSAL;
import static seedu.canoe.logic.parser.CliSyntax.PREFIX_WEDNESDAY_DISMISSAL;
import static seedu.canoe.model.student.time.Friday.DEFAULT_FRIDAY_DISMISSAL;
import static seedu.canoe.model.student.time.Monday.DEFAULT_MONDAY_DISMISSAL;
import static seedu.canoe.model.student.time.Thursday.DEFAULT_THURSDAY_DISMISSAL;
import static seedu.canoe.model.student.time.Tuesday.DEFAULT_TUESDAY_DISMISSAL;
import static seedu.canoe.model.student.time.Wednesday.DEFAULT_WEDNESDAY_DISMISSAL;

import java.util.Set;
import java.util.stream.Stream;

import seedu.canoe.logic.commands.AddCommand;
import seedu.canoe.logic.parser.exceptions.ParseException;
import seedu.canoe.model.student.AcademicYear;
import seedu.canoe.model.student.Email;
import seedu.canoe.model.student.Id;
import seedu.canoe.model.student.Name;
import seedu.canoe.model.student.Phone;
import seedu.canoe.model.student.Student;
import seedu.canoe.model.student.time.Day;
import seedu.canoe.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddCommandParser implements Parser<AddCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL,
                        PREFIX_ACADEMIC_YEAR, PREFIX_MONDAY_DISMISSAL, PREFIX_TUESDAY_DISMISSAL,
                        PREFIX_WEDNESDAY_DISMISSAL, PREFIX_THURSDAY_DISMISSAL, PREFIX_FRIDAY_DISMISSAL, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ACADEMIC_YEAR)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        AcademicYear academicYear = ParserUtil
                .parseAcademicYear(argMultimap.getValue(PREFIX_ACADEMIC_YEAR).get());
        Day mondayDismissal =
                ParserUtil.parseDismissal(Day.DayOfWeek.MONDAY,
                        argMultimap.getValue(PREFIX_MONDAY_DISMISSAL).orElse(DEFAULT_MONDAY_DISMISSAL));
        Day tuesdayDismissal =
                ParserUtil.parseDismissal(Day.DayOfWeek.TUESDAY,
                        argMultimap.getValue(PREFIX_TUESDAY_DISMISSAL).orElse(DEFAULT_TUESDAY_DISMISSAL));
        Day wednesdayDismissal =
                ParserUtil.parseDismissal(Day.DayOfWeek.WEDNESDAY,
                        argMultimap.getValue(PREFIX_WEDNESDAY_DISMISSAL).orElse(DEFAULT_WEDNESDAY_DISMISSAL));
        Day thursdayDismissal =
                ParserUtil.parseDismissal(Day.DayOfWeek.THURSDAY,
                        argMultimap.getValue(PREFIX_THURSDAY_DISMISSAL).orElse(DEFAULT_THURSDAY_DISMISSAL));
        Day fridayDismissal = ParserUtil.parseDismissal(Day.DayOfWeek.FRIDAY,
                argMultimap.getValue(PREFIX_FRIDAY_DISMISSAL).orElse(DEFAULT_FRIDAY_DISMISSAL));

        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Student student = new Student(name, phone, email, academicYear, tagList, mondayDismissal, tuesdayDismissal,
                wednesdayDismissal, thursdayDismissal, fridayDismissal, Id.getPlaceHolderId());

        return new AddCommand(student);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
