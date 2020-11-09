package seedu.canoe.logic.commands;

import static seedu.canoe.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.canoe.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.canoe.testutil.TypicalStudents.getTypicalCanoeCoach;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.canoe.model.Model;
import seedu.canoe.model.ModelManager;
import seedu.canoe.model.UserPrefs;
import seedu.canoe.model.student.Student;
import seedu.canoe.testutil.StudentBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalCanoeCoach(), new UserPrefs());
    }

    @Test
    public void execute_newStudent_success() {
        Student validStudent = new StudentBuilder().build();

        Model expectedModel = new ModelManager(model.getCanoeCoach(), new UserPrefs());
        expectedModel.addStudent(validStudent);

        assertCommandSuccess(new AddCommand(validStudent), model,
                String.format(AddCommand.MESSAGE_SUCCESS, validStudent), expectedModel);
    }

    @Test
    public void execute_duplicateStudent_throwsCommandException() {
        Student studentInList = model.getCanoeCoach().getStudentList().get(0);
        assertCommandFailure(new AddCommand(studentInList), model, AddCommand.MESSAGE_DUPLICATE_STUDENT);
    }

}
