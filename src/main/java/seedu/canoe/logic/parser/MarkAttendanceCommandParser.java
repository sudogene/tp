package seedu.canoe.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.canoe.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.canoe.logic.parser.CliSyntax.PREFIX_ID;

import java.util.logging.Logger;

import seedu.canoe.commons.core.LogsCenter;
import seedu.canoe.commons.core.index.Index;
import seedu.canoe.logic.commands.MarkAttendanceCommand;
import seedu.canoe.logic.parser.exceptions.ParseException;
import seedu.canoe.model.student.AnyMatchPredicateList;
import seedu.canoe.model.student.IdMatchesPredicate;

public class MarkAttendanceCommandParser implements Parser<MarkAttendanceCommand>{

    private static final Logger logger = LogsCenter.getLogger(MarkAttendanceCommandParser.class);

    public MarkAttendanceCommand parse(String args) throws ParseException {
        logger.info("=============================[ Parsing CommonTimeCommand ]===========================");
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_ID);

        boolean isEmptyString = false;

        Index trainingIndex;

        try {
            trainingIndex = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    MarkAttendanceCommand.MESSAGE_USAGE), pe);
        }

        AnyMatchPredicateList predicates = new AnyMatchPredicateList();
        if (argMultimap.getValue(PREFIX_ID).isPresent()) {
            String ids = argMultimap.getValue(PREFIX_ID).get();
            if (ids.equals("")) {
                logger.warning("No id found after id prefix!");
                isEmptyString = true;
            } else {
                String[] studentIds = ids.split(",");
                for (String id : studentIds) {
                    predicates.add(new IdMatchesPredicate(id));
                }
            }
        }

        if (predicates.isEmpty() || isEmptyString) {
            logger.warning("No prefixes found in the command input!" + args);
            throw new ParseException(MarkAttendanceCommand.MESSAGE_NO_STUDENTS_SPECIFIED);
        }

        return new MarkAttendanceCommand(trainingIndex, predicates);
    }
}
