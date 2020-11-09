package seedu.canoe.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.canoe.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.canoe.testutil.Assert.assertThrows;
import static seedu.canoe.testutil.TypicalStudents.ALICE;
import static seedu.canoe.testutil.TypicalStudents.getTypicalCanoeCoach;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.canoe.model.student.Student;
import seedu.canoe.model.student.exceptions.DuplicateStudentException;
import seedu.canoe.model.training.Training;
import seedu.canoe.testutil.StudentBuilder;

public class CanoeCoachTest {

    private final CanoeCoach canoeCoach = new CanoeCoach();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), canoeCoach.getStudentList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> canoeCoach.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        CanoeCoach newData = getTypicalCanoeCoach();
        canoeCoach.resetData(newData);
        assertEquals(newData, canoeCoach);
    }

    @Test
    public void resetData_withDuplicateStudents_throwsDuplicateStudentException() {
        // Two students with the same identity fields
        Student editedAlice = new StudentBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Student> newStudents = Arrays.asList(ALICE, editedAlice);
        CanoeCoachStub newData = new CanoeCoachStub(newStudents);

        assertThrows(DuplicateStudentException.class, () -> canoeCoach.resetData(newData));
    }

    @Test
    public void hasStudent_nullStudent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> canoeCoach.hasStudent(null));
    }

    @Test
    public void hasStudent_studentNotInAddressBook_returnsFalse() {
        assertFalse(canoeCoach.hasStudent(ALICE));
    }

    @Test
    public void hasStudent_studentInAddressBook_returnsTrue() {
        canoeCoach.addStudent(ALICE);
        assertTrue(canoeCoach.hasStudent(ALICE));
    }

    @Test
    public void hasStudent_studentWithSameIdentityFieldsInAddressBook_returnsTrue() {
        canoeCoach.addStudent(ALICE);
        Student editedAlice = new StudentBuilder(ALICE).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(canoeCoach.hasStudent(editedAlice));
    }

    @Test
    public void getStudentList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> canoeCoach.getStudentList().remove(0));
    }

    /**
     * A stub ReadOnlyCanoeCoach whose students list can violate interface constraints.
     */
    private static class CanoeCoachStub implements ReadOnlyCanoeCoach {
        private final ObservableList<Student> students = FXCollections.observableArrayList();
        private final ObservableList<Training> trainings = FXCollections.observableArrayList();

        CanoeCoachStub(Collection<Student> students) {
            this.students.setAll(students);
        }

        @Override
        public ObservableList<Student> getStudentList() {
            return students;
        }

        @Override
        public ObservableList<Training> getTrainingList() {
            return trainings;
        }
    }

}
