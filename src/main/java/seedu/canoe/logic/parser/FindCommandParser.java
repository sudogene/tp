package seedu.canoe.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.canoe.logic.parser.CliSyntax.PREFIX_ACADEMIC_YEAR;
import static seedu.canoe.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.canoe.logic.parser.CliSyntax.PREFIX_FRIDAY_DISMISSAL;
import static seedu.canoe.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.canoe.logic.parser.CliSyntax.PREFIX_MONDAY_DISMISSAL;
import static seedu.canoe.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.canoe.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.canoe.logic.parser.CliSyntax.PREFIX_THURSDAY_DISMISSAL;
import static seedu.canoe.logic.parser.CliSyntax.PREFIX_TUESDAY_DISMISSAL;
import static seedu.canoe.logic.parser.CliSyntax.PREFIX_WEDNESDAY_DISMISSAL;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import seedu.canoe.logic.commands.FindCommand;
import seedu.canoe.logic.parser.exceptions.ParseException;
import seedu.canoe.model.student.AcademicYear;
import seedu.canoe.model.student.AcademicYearMatchesPredicate;
import seedu.canoe.model.student.AllMatchPredicateList;
import seedu.canoe.model.student.EmailContainsKeywordPredicate;
import seedu.canoe.model.student.IdMatchesPredicate;
import seedu.canoe.model.student.NameContainsKeywordsPredicate;
import seedu.canoe.model.student.Phone;
import seedu.canoe.model.student.PhoneMatchesPredicate;
import seedu.canoe.model.student.time.Day;
import seedu.canoe.model.student.time.FridayDismissalPredicate;
import seedu.canoe.model.student.time.MondayDismissalPredicate;
import seedu.canoe.model.student.time.ThursdayDismissalPredicate;
import seedu.canoe.model.student.time.TuesdayDismissalPredicate;
import seedu.canoe.model.student.time.WednesdayDismissalPredicate;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     */
    public FindCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ACADEMIC_YEAR,
                        PREFIX_MONDAY_DISMISSAL, PREFIX_TUESDAY_DISMISSAL, PREFIX_WEDNESDAY_DISMISSAL,
                        PREFIX_THURSDAY_DISMISSAL, PREFIX_FRIDAY_DISMISSAL, PREFIX_ID);

        boolean checkEmptyString = false;
        AllMatchPredicateList predicates = new AllMatchPredicateList();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            String text = argMultimap.getValue(PREFIX_NAME).get();
            if (text.isEmpty()) {
                checkEmptyString = true;
            } else {
                predicates.add(new NameContainsKeywordsPredicate(getKeywordsFromString(text)));
            }
        }

        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            String phoneValue = argMultimap.getValue(PREFIX_PHONE).get();
            if (!phoneValue.isEmpty()) {
                checkEmptyString = false;
                Phone phone = ParserUtil.parsePhone(phoneValue);
                predicates.add(new PhoneMatchesPredicate(phone));
            }
        }

        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            String keyword = argMultimap.getValue(PREFIX_EMAIL).get();
            if (!keyword.isEmpty()) {
                checkEmptyString = false;
                predicates.add(new EmailContainsKeywordPredicate(keyword));
            }
        }

        if (argMultimap.getValue(PREFIX_ACADEMIC_YEAR).isPresent()) {
            String academicYearValue = argMultimap.getValue(PREFIX_ACADEMIC_YEAR).get();
            if (!academicYearValue.isEmpty()) {
                checkEmptyString = false;
                AcademicYear year = ParserUtil.parseAcademicYear(academicYearValue);
                predicates.add(new AcademicYearMatchesPredicate(year));
            }
        }

        if (argMultimap.getValue(PREFIX_MONDAY_DISMISSAL).isPresent()) {
            String timeString = argMultimap.getValue(PREFIX_MONDAY_DISMISSAL).get();
            if (!timeString.isEmpty()) {
                checkEmptyString = false;
                predicates.add(new MondayDismissalPredicate(getTimeFromString(timeString)));
            }
        }

        if (argMultimap.getValue(PREFIX_TUESDAY_DISMISSAL).isPresent()) {
            String timeString = argMultimap.getValue(PREFIX_TUESDAY_DISMISSAL).get();
            if (!timeString.isEmpty()) {
                checkEmptyString = false;
                predicates.add(new TuesdayDismissalPredicate(getTimeFromString(timeString)));
            }
        }

        if (argMultimap.getValue(PREFIX_WEDNESDAY_DISMISSAL).isPresent()) {
            String timeString = argMultimap.getValue(PREFIX_WEDNESDAY_DISMISSAL).get();
            if (!timeString.isEmpty()) {
                checkEmptyString = false;
                predicates.add(new WednesdayDismissalPredicate(getTimeFromString(timeString)));
            }
        }

        if (argMultimap.getValue(PREFIX_THURSDAY_DISMISSAL).isPresent()) {
            String timeString = argMultimap.getValue(PREFIX_THURSDAY_DISMISSAL).get();
            if (!timeString.isEmpty()) {
                checkEmptyString = false;
                predicates.add(new ThursdayDismissalPredicate(getTimeFromString(timeString)));
            }
        }

        if (argMultimap.getValue(PREFIX_FRIDAY_DISMISSAL).isPresent()) {
            String timeString = argMultimap.getValue(PREFIX_FRIDAY_DISMISSAL).get();
            if (!timeString.isEmpty()) {
                checkEmptyString = false;
                predicates.add(new FridayDismissalPredicate(getTimeFromString(timeString)));
            }
        }

        if (argMultimap.getValue(PREFIX_ID).isPresent()) {
            String idValue = argMultimap.getValue(PREFIX_ID).get();
            if (!idValue.isEmpty()) {
                checkEmptyString = false;
                String parsedIdValue = ParserUtil.parseIdValue(idValue);
                predicates.add(new IdMatchesPredicate(parsedIdValue));
            }
        }

        if (predicates.isEmpty() || checkEmptyString) {
            throw new ParseException(FindCommand.MESSAGE_NO_QUERY);
        }

        return new FindCommand(predicates);
    }

    private List<String> getKeywordsFromString(String str) {
        return Arrays.asList(str.split(" "));
    }

    private LocalTime getTimeFromString(String str) throws ParseException {
        Day day = ParserUtil.parseDismissal(Day.DayOfWeek.MONDAY, str);
        return day.dismissalTime;
    }
}
