package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.student.Training;

/**
 * Panel containing the list of students.
 */
public class TrainingListPanel extends UiPart<Region> {
    private static final String FXML = "TrainingListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TrainingListPanel.class);

    @FXML
    private ListView<Training> trainingListView;

    /**
     * Creates a {@code StudentListPanel} with the given {@code ObservableList}.
     */
    public TrainingListPanel(ObservableList<Training> trainingList) {
        super(FXML);
        trainingListView.setItems(trainingList);
        trainingListView.setCellFactory(listView -> new TrainingListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Student} using a {@code StudentCard}.
     */
    class TrainingListViewCell extends ListCell<Training> {
        @Override
        protected void updateItem(Training training, boolean empty) {
            super.updateItem(training, empty);

            if (empty || training == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new TrainingCard(training, getIndex() + 1).getRoot());
            }
        }
    }

}
