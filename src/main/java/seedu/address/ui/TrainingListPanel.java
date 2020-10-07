package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.student.Student;

/**
 * Panel containing the list of students.
 */
public class TrainingListPanel extends UiPart<Region> {
    private static final String FXML = "TrainingListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TrainingListPanel.class);

    @FXML
    private ListView<Student> trainingListView;

    /**
     * Creates a {@code StudentListPanel} with the given {@code ObservableList}.
     */
    public TrainingListPanel(ObservableList<Student> studentList) {
        super(FXML);
        trainingListView.setItems(studentList);
        trainingListView.setCellFactory(listView -> new StudentListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Student} using a {@code StudentCard}.
     */
    class StudentListViewCell extends ListCell<Student> {
        //Need to replace all the instance of Students to Training.
        @Override
        protected void updateItem(Student student, boolean empty) {
            super.updateItem(student, empty);

            if (empty || student == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new TrainingCard(student, getIndex() + 1).getRoot());
            }
        }
    }

}
