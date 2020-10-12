package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ACADEMIC_YEAR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FRIDAY_DISMISSAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MONDAY_DISMISSAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_THURSDAY_DISMISSAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUESDAY_DISMISSAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEDNESDAY_DISMISSAL;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.student.AcademicYearMatchesPredicate;
import seedu.address.model.student.EmailContainsKeywordPredicate;
import seedu.address.model.student.IdMatchesPredicate;
import seedu.address.model.student.NameContainsKeywordsPredicate;
import seedu.address.model.student.PhoneMatchesPredicate;
import seedu.address.model.student.PredicateList;
import seedu.address.model.student.time.Day;
import seedu.address.model.student.time.FridayDismissalPredicate;
import seedu.address.model.student.time.MondayDismissalPredicate;
import seedu.address.model.student.time.ThursdayDismissalPredicate;
import seedu.address.model.student.time.TuesdayDismissalPredicate;
import seedu.address.model.student.time.WednesdayDismissalPredicate;

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

        PredicateList predicates = new PredicateList();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            String text = argMultimap.getValue(PREFIX_NAME).get();
            predicates.add(new NameContainsKeywordsPredicate(getKeywordsFromString(text)));
        }

        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            String phoneValue = argMultimap.getValue(PREFIX_PHONE).get();
            predicates.add(new PhoneMatchesPredicate(phoneValue));
        }

        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            String keyword = argMultimap.getValue(PREFIX_EMAIL).get();
            predicates.add(new EmailContainsKeywordPredicate(keyword));
        }

        if (argMultimap.getValue(PREFIX_ACADEMIC_YEAR).isPresent()) {
            String academicYearValue = argMultimap.getValue(PREFIX_ACADEMIC_YEAR).get();
            predicates.add(new AcademicYearMatchesPredicate(academicYearValue));
        }

        if (argMultimap.getValue(PREFIX_MONDAY_DISMISSAL).isPresent()) {
            String timeString = argMultimap.getValue(PREFIX_MONDAY_DISMISSAL).get();
            predicates.add(new MondayDismissalPredicate(getTimeFromString(timeString)));
        }

        if (argMultimap.getValue(PREFIX_TUESDAY_DISMISSAL).isPresent()) {
            String timeString = argMultimap.getValue(PREFIX_TUESDAY_DISMISSAL).get();
            predicates.add(new TuesdayDismissalPredicate(getTimeFromString(timeString)));
        }

        if (argMultimap.getValue(PREFIX_WEDNESDAY_DISMISSAL).isPresent()) {
            String timeString = argMultimap.getValue(PREFIX_WEDNESDAY_DISMISSAL).get();
            predicates.add(new WednesdayDismissalPredicate(getTimeFromString(timeString)));
        }

        if (argMultimap.getValue(PREFIX_THURSDAY_DISMISSAL).isPresent()) {
            String timeString = argMultimap.getValue(PREFIX_THURSDAY_DISMISSAL).get();
            predicates.add(new ThursdayDismissalPredicate(getTimeFromString(timeString)));
        }

        if (argMultimap.getValue(PREFIX_FRIDAY_DISMISSAL).isPresent()) {
            String timeString = argMultimap.getValue(PREFIX_FRIDAY_DISMISSAL).get();
            predicates.add(new FridayDismissalPredicate(getTimeFromString(timeString)));
        }

        if (argMultimap.getValue(PREFIX_ID).isPresent()) {
            String idValue = argMultimap.getValue(PREFIX_ID).get();
            System.out.println("idValue = " + idValue);
            predicates.add(new IdMatchesPredicate(idValue));
        }

        if (predicates.isEmpty()) {
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
