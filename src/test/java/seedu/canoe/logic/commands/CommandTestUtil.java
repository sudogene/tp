package seedu.canoe.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
import static seedu.canoe.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.canoe.commons.core.index.Index;
import seedu.canoe.logic.commands.exceptions.CommandException;
import seedu.canoe.model.CanoeCoach;
import seedu.canoe.model.Model;
import seedu.canoe.model.student.NameContainsKeywordsPredicate;
import seedu.canoe.model.student.Student;
import seedu.canoe.testutil.EditStudentDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_EMAIL_AMY = "amy@example.com";
    public static final String VALID_EMAIL_BOB = "bob@example.com";
    public static final String VALID_ACADEMICYEAR_AMY = "1";
    public static final String VALID_ACADEMICYEAR_BOB = "2";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";
    public static final String VALID_MONDAY_AMY = "1500";
    public static final String VALID_TUESDAY_AMY = "1500";
    public static final String VALID_WEDNESDAY_AMY = "1500";
    public static final String VALID_THURSDAY_AMY = "1500";
    public static final String VALID_FRIDAY_AMY = "1500";
    public static final String VALID_MONDAY_BOB = "1500";
    public static final String VALID_TUESDAY_BOB = "1500";
    public static final String VALID_WEDNESDAY_BOB = "1500";
    public static final String VALID_THURSDAY_BOB = "1500";
    public static final String VALID_FRIDAY_BOB = "1500";
    public static final String VALID_ID = "1";
    public static final String VALID_ID_STRINGS = "1";
    public static final String VALID_ID_STRINGS_2 = "2";
    public static final String VALID_ID_STRINGS_3 = "2, 3";
    public static final String VALID_ID_STRINGS_4 = "1, 2";
    public static final String VALID_ID_STRINGS_5 = "4";
    public static final String VALID_ID_STRINGS_6 = "1, 2, 3";
    public static final List<String> VALID_ID_LIST = List.of("1");
    public static final List<String> VALID_ID_LIST_2 = List.of("2");
    public static final List<String> VALID_ID_LIST_3 = List.of("2", "3");
    public static final List<String> VALID_ID_LIST_4 = List.of("1", "2");
    public static final List<String> VALID_ID_LIST_5 = List.of("4");
    public static final List<String> VALID_ID_LIST_6 = List.of("1", "2", "3");
    public static final List<String> INVALID_ID_LIST = List.of("9");
    public static final List<String> INVALID_ID_LIST_REPEATED = List.of("1", "2", "2");

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String EMAIL_DESC_AMY = " " + PREFIX_EMAIL + VALID_EMAIL_AMY;
    public static final String EMAIL_DESC_BOB = " " + PREFIX_EMAIL + VALID_EMAIL_BOB;
    public static final String ACADEMICYEAR_DESC_AMY = " "
            + PREFIX_ACADEMIC_YEAR + VALID_ACADEMICYEAR_AMY;
    public static final String ACADEMICYEAR_DESC_BOB = " "
            + PREFIX_ACADEMIC_YEAR + VALID_ACADEMICYEAR_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;
    public static final String MONDAY_DESC_AMY = " " + PREFIX_MONDAY_DISMISSAL + VALID_MONDAY_AMY;
    public static final String TUESDAY_DESC_AMY = " " + PREFIX_TUESDAY_DISMISSAL + VALID_TUESDAY_AMY;
    public static final String WEDNESDAY_DESC_AMY = " " + PREFIX_WEDNESDAY_DISMISSAL + VALID_WEDNESDAY_AMY;
    public static final String THURSDAY_DESC_AMY = " " + PREFIX_THURSDAY_DISMISSAL + VALID_THURSDAY_AMY;
    public static final String FRIDAY_DESC_AMY = " " + PREFIX_FRIDAY_DISMISSAL + VALID_FRIDAY_AMY;
    public static final String MONDAY_DESC_BOB = " " + PREFIX_MONDAY_DISMISSAL + VALID_MONDAY_BOB;
    public static final String TUESDAY_DESC_BOB = " " + PREFIX_TUESDAY_DISMISSAL + VALID_TUESDAY_BOB;
    public static final String WEDNESDAY_DESC_BOB = " " + PREFIX_WEDNESDAY_DISMISSAL + VALID_WEDNESDAY_BOB;
    public static final String THURSDAY_DESC_BOB = " " + PREFIX_THURSDAY_DISMISSAL + VALID_THURSDAY_BOB;
    public static final String FRIDAY_DESC_BOB = " " + PREFIX_FRIDAY_DISMISSAL + VALID_FRIDAY_BOB;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones
    public static final String INVALID_EMAIL_DESC = " " + PREFIX_EMAIL + "bob!yahoo"; // missing '@' symbol
    public static final String INVALID_ACADEMICYEAR_DESC = " "
            + PREFIX_ACADEMIC_YEAR + "1f"; // 'f' not allowed in academic year
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags


    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditStudentDescriptor DESC_AMY;
    public static final EditCommand.EditStudentDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditStudentDescriptorBuilder().withName(VALID_NAME_AMY)
                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY)
                .withAcademicYear(VALID_ACADEMICYEAR_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditStudentDescriptorBuilder().withName(VALID_NAME_BOB)
                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAcademicYear(VALID_ACADEMICYEAR_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND)
                .withId(VALID_ID).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel,
                                            CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel,
                                            String expectedMessage, Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the canoe book, filtered student list and selected student in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        CanoeCoach expectedCanoeCoach = new CanoeCoach(actualModel.getCanoeCoach());
        List<Student> expectedFilteredList = new ArrayList<>(actualModel.getFilteredStudentList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedCanoeCoach, actualModel.getCanoeCoach());
        assertEquals(expectedFilteredList, actualModel.getFilteredStudentList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the student at the given {@code targetIndex} in the
     * {@code model}'s canoe book.
     */
    public static void showStudentAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredStudentList().size());

        Student student = model.getFilteredStudentList().get(targetIndex.getZeroBased());
        final String[] splitName = student.getName().fullName.split("\\s+");
        model.updateFilteredStudentList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredStudentList().size());
    }

}
