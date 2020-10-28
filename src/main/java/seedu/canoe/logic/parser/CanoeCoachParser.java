package seedu.canoe.logic.parser;

import static seedu.canoe.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.canoe.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.canoe.logic.commands.AddAllStudentToTrainingCommand;
import seedu.canoe.logic.commands.AddCommand;
import seedu.canoe.logic.commands.AddStudentToTrainingCommand;
import seedu.canoe.logic.commands.ClearCommand;
import seedu.canoe.logic.commands.Command;
import seedu.canoe.logic.commands.CommonTimeCommand;
import seedu.canoe.logic.commands.DeleteCommand;
import seedu.canoe.logic.commands.DeleteStudentFromTrainingCommand;
import seedu.canoe.logic.commands.DeleteTrainingCommand;
import seedu.canoe.logic.commands.EditCommand;
import seedu.canoe.logic.commands.ExitCommand;
import seedu.canoe.logic.commands.FindBadStudentsCommand;
import seedu.canoe.logic.commands.FindCommand;
import seedu.canoe.logic.commands.FindStudentTrainingCommand;
import seedu.canoe.logic.commands.HelpCommand;
import seedu.canoe.logic.commands.ListCommand;
import seedu.canoe.logic.commands.MarkAttendanceCommand;
import seedu.canoe.logic.commands.TrainingCommand;
import seedu.canoe.logic.commands.UnmarkAttendanceCommand;
import seedu.canoe.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class CanoeCoachParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case FindStudentTrainingCommand.COMMAND_WORD:
            return new FindStudentTrainingCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case TrainingCommand.COMMAND_WORD:
            return new TrainingCommandParser().parse(arguments);

        case DeleteTrainingCommand.COMMAND_WORD:
            return new DeleteTrainingCommandParser().parse(arguments);

        case CommonTimeCommand.COMMAND_WORD:
            return new CommonTimeCommandParser().parse(arguments);

        case AddStudentToTrainingCommand.COMMAND_WORD:
            return new AddStudentCommandParser().parse(arguments);

        case DeleteStudentFromTrainingCommand.COMMAND_WORD:
            return new DeleteStudentFromTrainingCommandParser().parse(arguments);

        case MarkAttendanceCommand.COMMAND_WORD:
            return new MarkAttendanceCommandParser().parse(arguments);

        case UnmarkAttendanceCommand.COMMAND_WORD:
            return new UnmarkAttendanceCommandParser().parse(arguments);

        case AddAllStudentToTrainingCommand.COMMAND_WORD:
            return new AddAllStudentCommandParser().parse(arguments);

        case FindBadStudentsCommand.COMMAND_WORD:
            return new FindBadStudentsCommand();

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
