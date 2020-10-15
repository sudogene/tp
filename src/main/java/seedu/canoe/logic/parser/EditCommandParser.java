package seedu.canoe.logic.parser;

import static java.util.Objects.requireNonNull;
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

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.canoe.commons.core.index.Index;
import seedu.canoe.logic.commands.EditCommand;
import seedu.canoe.logic.commands.EditCommand.EditStudentDescriptor;
import seedu.canoe.logic.parser.exceptions.ParseException;
import seedu.canoe.model.student.time.Day;
import seedu.canoe.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_MONDAY_DISMISSAL,
                        PREFIX_TUESDAY_DISMISSAL, PREFIX_WEDNESDAY_DISMISSAL, PREFIX_THURSDAY_DISMISSAL,
                        PREFIX_FRIDAY_DISMISSAL, PREFIX_TAG, PREFIX_ACADEMIC_YEAR);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCommand.MESSAGE_USAGE), pe);
        }

        EditStudentDescriptor editStudentDescriptor = new EditStudentDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editStudentDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editStudentDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editStudentDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }

        if (argMultimap.getValue(PREFIX_ACADEMIC_YEAR).isPresent()) {
            editStudentDescriptor.setAcademicYear(ParserUtil.parseAcademicYear(
                argMultimap.getValue(PREFIX_ACADEMIC_YEAR).get()));
        }

        if (argMultimap.getValue(PREFIX_MONDAY_DISMISSAL).isPresent()) {
            editStudentDescriptor.setMondayDismissal(ParserUtil.parseDismissal(Day.DayOfWeek.MONDAY,
                    argMultimap.getValue(PREFIX_MONDAY_DISMISSAL).get()));
        }

        if (argMultimap.getValue(PREFIX_TUESDAY_DISMISSAL).isPresent()) {
            editStudentDescriptor.setTuesdayDismissal(ParserUtil.parseDismissal(Day.DayOfWeek.TUESDAY,
                    argMultimap.getValue(PREFIX_TUESDAY_DISMISSAL).get()));
        }

        if (argMultimap.getValue(PREFIX_WEDNESDAY_DISMISSAL).isPresent()) {
            editStudentDescriptor.setWednesdayDismissal(ParserUtil.parseDismissal(Day.DayOfWeek.WEDNESDAY,
                    argMultimap.getValue(PREFIX_WEDNESDAY_DISMISSAL).get()));
        }

        if (argMultimap.getValue(PREFIX_THURSDAY_DISMISSAL).isPresent()) {
            editStudentDescriptor.setThursdayDismissal(ParserUtil.parseDismissal(Day.DayOfWeek.THURSDAY,
                    argMultimap.getValue(PREFIX_THURSDAY_DISMISSAL).get()));
        }

        if (argMultimap.getValue(PREFIX_FRIDAY_DISMISSAL).isPresent()) {
            editStudentDescriptor.setFridayDismissal(ParserUtil.parseDismissal(Day.DayOfWeek.FRIDAY,
                    argMultimap.getValue(PREFIX_FRIDAY_DISMISSAL).get()));
        }

        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editStudentDescriptor::setTags);

        if (!editStudentDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditCommand.MESSAGE_NOT_EDITED);
        }

        return new EditCommand(index, editStudentDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

}
