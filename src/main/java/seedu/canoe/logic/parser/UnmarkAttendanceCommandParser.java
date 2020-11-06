package seedu.canoe.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.canoe.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.canoe.logic.parser.CliSyntax.PREFIX_ID;

import java.util.logging.Logger;

import seedu.canoe.commons.core.LogsCenter;
import seedu.canoe.commons.core.index.Index;
import seedu.canoe.logic.commands.UnmarkAttendanceCommand;
import seedu.canoe.logic.parser.exceptions.ParseException;
import seedu.canoe.model.student.AnyMatchPredicateList;
import seedu.canoe.model.student.Id;
import seedu.canoe.model.student.IdMatchesPredicate;

public class UnmarkAttendanceCommandParser implements Parser<UnmarkAttendanceCommand> {

    private static final Logger LOGGER = LogsCenter.getLogger(UnmarkAttendanceCommandParser.class);

    /**
     * Parses the given {@code String} of arguments in the context of the MarkAttendanceCommand
     * and returns a MarkAttendanceCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UnmarkAttendanceCommand parse(String args) throws ParseException {
        LOGGER.info("=============================[ Parsing CommonTimeCommand ]===========================");
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ID);

        boolean isEmptyString = false;

        Index trainingIndex;

        try {
            trainingIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    UnmarkAttendanceCommand.MESSAGE_USAGE), pe);
        }

        AnyMatchPredicateList predicates = new AnyMatchPredicateList();
        if (argMultimap.getValue(PREFIX_ID).isPresent()) {
            String ids = argMultimap.getValue(PREFIX_ID).get();
            if (ids.equals("")) {
                LOGGER.warning("No id found after id prefix!");
                isEmptyString = true;
            } else {
                String[] studentIds = ids.split(",");
                if (!ParserUtil.isUniqueList(studentIds)) {
                    throw new ParseException(ParserUtil.MESSAGE_REPEATED_ID);
                }
                for (String id : studentIds) {
                    if (!Id.isValidId(id)) {
                        throw new ParseException(Id.MESSAGE_CONSTRAINTS);
                    }
                    predicates.add(new IdMatchesPredicate(id));
                }
            }
        }

        if (predicates.isEmpty() || isEmptyString) {
            LOGGER.warning("No prefixes found in the command input!" + args);
            throw new ParseException(UnmarkAttendanceCommand.MESSAGE_NO_STUDENTS_SPECIFIED);
        }

        return new UnmarkAttendanceCommand(trainingIndex, predicates);
    }
}
