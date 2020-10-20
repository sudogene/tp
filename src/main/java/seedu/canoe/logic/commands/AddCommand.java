package seedu.canoe.logic.commands;

import static java.util.Objects.requireNonNull;
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

import seedu.canoe.logic.commands.exceptions.CommandException;
import seedu.canoe.model.Model;
import seedu.canoe.model.student.Student;

/**
 * Adds a student to the canoe book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a student to the canoe book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ACADEMIC_YEAR + "ACADEMIC YEAR "
            + "[" + PREFIX_MONDAY_DISMISSAL + "MONDAY DISMISSAL TIME " + PREFIX_TUESDAY_DISMISSAL
            + "TUESDAY DISMISSAL TIME " + PREFIX_WEDNESDAY_DISMISSAL + "WEDNESDAY DISMISSAL TIME "
            + PREFIX_THURSDAY_DISMISSAL + "THURSDAY DISMISSAL TIME " + PREFIX_FRIDAY_DISMISSAL
            + "FRIDAY DISMISSAL TIME] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ACADEMIC_YEAR + "2 "
            + PREFIX_MONDAY_DISMISSAL + "1600 "
            + PREFIX_TUESDAY_DISMISSAL + "1500 "
            + PREFIX_WEDNESDAY_DISMISSAL + "1400 "
            + PREFIX_THURSDAY_DISMISSAL + "1700 "
            + PREFIX_FRIDAY_DISMISSAL + "1800 "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New student added: %1$s";
    public static final String MESSAGE_DUPLICATE_STUDENT = "This student already exists in the canoe book";

    private final Student toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Student}
     */
    public AddCommand(Student student) {
        requireNonNull(student);
        toAdd = student;
    }

    public Student getStudent() {
        return this.toAdd;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasStudent(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_STUDENT);
        }

        Student validToAdd = toAdd.createStudentWithValidId();
        model.addStudent(validToAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, validToAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
