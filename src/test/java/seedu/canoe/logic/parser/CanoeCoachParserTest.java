package seedu.canoe.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.canoe.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.canoe.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.canoe.testutil.Assert.assertThrows;
import static seedu.canoe.testutil.TypicalIndexes.INDEX_FIRST_STUDENT;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.canoe.logic.commands.AddCommand;
import seedu.canoe.logic.commands.ClearCommand;
import seedu.canoe.logic.commands.DeleteCommand;
import seedu.canoe.logic.commands.EditCommand;
import seedu.canoe.logic.commands.ExitCommand;
import seedu.canoe.logic.commands.FindCommand;
import seedu.canoe.logic.commands.HelpCommand;
import seedu.canoe.logic.commands.ListCommand;
import seedu.canoe.logic.commands.MarkAttendanceCommand;
import seedu.canoe.logic.parser.exceptions.ParseException;
import seedu.canoe.model.student.AllMatchPredicateList;
import seedu.canoe.model.student.AnyMatchPredicateList;
import seedu.canoe.model.student.IdMatchesPredicate;
import seedu.canoe.model.student.NameContainsKeywordsPredicate;
import seedu.canoe.model.student.Phone;
import seedu.canoe.model.student.PhoneMatchesPredicate;
import seedu.canoe.model.student.Student;
import seedu.canoe.testutil.EditStudentDescriptorBuilder;
import seedu.canoe.testutil.StudentBuilder;
import seedu.canoe.testutil.StudentUtil;

public class CanoeCoachParserTest {

    private final CanoeCoachParser parser = new CanoeCoachParser();

    @Test
    public void parseCommand_add() throws Exception {
        Student student = new StudentBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(StudentUtil.getAddCommand(student));
        assertEquals(new AddCommand(student), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_STUDENT.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_STUDENT), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Student student = new StudentBuilder().build();
        EditCommand.EditStudentDescriptor descriptor = new EditStudentDescriptorBuilder(student).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_STUDENT.getOneBased() + " " + StudentUtil.getEditStudentDescriptorDetails(descriptor));
        EditCommand expectedCommand = new EditCommand(INDEX_FIRST_STUDENT, descriptor);
        assertEquals(expectedCommand, command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " n/"
                        + keywords.stream().collect(Collectors.joining(" "))
                        + " p/123456");
        assertEquals(new FindCommand(new AllMatchPredicateList(Arrays.asList(
                new NameContainsKeywordsPredicate(Arrays.asList("foo", "bar", "baz")),
                new PhoneMatchesPredicate(new Phone("123456"))))), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_mark_attendance() throws Exception {
        MarkAttendanceCommand command = (MarkAttendanceCommand) parser.parseCommand(
                MarkAttendanceCommand.COMMAND_WORD + " 3 "
                + "id/1,2,4");
        assertEquals(new MarkAttendanceCommand(ParserUtil.parseIndex("3"),
                AnyMatchPredicateList.of(
                        new IdMatchesPredicate("1"),
                        new IdMatchesPredicate("2"),
                        new IdMatchesPredicate("4"))), command);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
