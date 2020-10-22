package seedu.canoe.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.canoe.commons.core.Messages.MESSAGE_STUDENTS_NOT_FOUND;

import java.time.LocalTime;
import java.util.List;
import java.util.logging.Logger;

import seedu.canoe.commons.core.LogsCenter;
import seedu.canoe.model.Model;
import seedu.canoe.model.student.AnyMatchPredicateList;
import seedu.canoe.model.student.CommonTimeFinder;

/**
 * Finds the latest dismissal time of all the students stated in the keywords.
 * Keyword matching is case insensitive.
 */
public class CommonTimeCommand extends Command {


    public static final Logger LOGGER = LogsCenter.getLogger(CommonTimeCommand.class);

    public static final String COMMAND_WORD = "common-time";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Finds a common time amongst all selected students "
            + "for each day and displays them as a list.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    public static final String MESSAGE_NO_QUERY = "At least one valid field is required to find a common time.";
    private final AnyMatchPredicateList predicates;
    private List<LocalTime> commonDismissalTimes;

    public CommonTimeCommand(AnyMatchPredicateList predicates) {
        this.predicates = predicates;
    }

    @Override
    public CommandResult execute(Model model) {
        LOGGER.info("=============================[ Executing CommonTimeCommand ]===========================");
        requireNonNull(model);
        model.updateFilteredStudentList(predicates);

        if (model.getFilteredStudentList().isEmpty()) {
            LOGGER.warning("Keywords match zero students.");
            return new CommandResult(MESSAGE_STUDENTS_NOT_FOUND);
        }

        commonDismissalTimes = new CommonTimeFinder(model.getFilteredStudentList()).getCommonDismissalTimes();
        return new CommandResult(commonDismissalTimesToString(commonDismissalTimes));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CommonTimeCommand // instanceof handles nulls
                && predicates.equals(((CommonTimeCommand) other).predicates)); // state check
    }

    /**
     * Converts a list of LocalTime objects into string.
     * Returns a string version of the list.
     *
     * @param commonDismissalTimes list of LocalTime objects
     * @return String version of the given list.
     */
    public String commonDismissalTimesToString (List<LocalTime> commonDismissalTimes) {
        String strCommonDismissalTimes = "";
        for (int i = 0; i < 5; i++) {
            switch (i) {

            case 0:
                strCommonDismissalTimes += "Monday: " + commonDismissalTimes.get(i).toString() + "\n";
                break;

            case 1:
                strCommonDismissalTimes += "Tuesday: " + commonDismissalTimes.get(i).toString() + "\n";
                break;

            case 2:
                strCommonDismissalTimes += "Wednesday: " + commonDismissalTimes.get(i).toString() + "\n";
                break;

            case 3:
                strCommonDismissalTimes += "Thursday: " + commonDismissalTimes.get(i).toString() + "\n";
                break;

            case 4:
                strCommonDismissalTimes += "Friday: " + commonDismissalTimes.get(i).toString();
                break;

            default:
                strCommonDismissalTimes += MESSAGE_NO_QUERY;
                break;
            }
        }
        return strCommonDismissalTimes;
    }
}
