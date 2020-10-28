package seedu.canoe.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.canoe.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import seedu.canoe.model.Model;

/**
 * Lists all students in the canoe book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all students and trainings.";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        //Make sure all students and trainings are displayed on student and training panel
        model.updateFilteredTrainingList(Model.PREDICATE_SHOW_ALL_TRAININGS);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
