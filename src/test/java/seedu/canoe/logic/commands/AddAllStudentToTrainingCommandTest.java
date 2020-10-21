package seedu.canoe.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.canoe.testutil.Assert.assertThrows;
import static seedu.canoe.testutil.TypicalStudents.ALICE;

import org.junit.jupiter.api.Test;

import seedu.canoe.model.Model;
import seedu.canoe.model.ModelManager;
import seedu.canoe.model.UserPrefs;
import seedu.canoe.model.student.Student;
import seedu.canoe.model.student.Training;
import seedu.canoe.testutil.StudentBuilder;
import seedu.canoe.testutil.TypicalStudentsInTypicalTrainings;

public class AddAllStudentToTrainingCommandTest {

    private Model model = new ModelManager(TypicalStudentsInTypicalTrainings.getTypicalAddressBook(), new UserPrefs());

    public Model getModel() {
        return model;
    }

    @Test
    public void constructor_nullIndex_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new AddAllStudentToTrainingCommand(null));
    }

    @Test
    public void isAbleToAddStudent() {
        Training training = model.getFilteredTrainingList().get(0);
        Student student = new StudentBuilder(ALICE).build();
        assertTrue(AddAllStudentToTrainingCommand.isAbleToAddStudent(student, training));
    }

}
