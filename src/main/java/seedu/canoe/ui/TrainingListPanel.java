package seedu.canoe.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.canoe.commons.core.LogsCenter;
import seedu.canoe.model.training.Training;

/**
 * Panel containing the list of trainings.
 */
public class TrainingListPanel extends UiPart<Region> {
    private static final String FXML = "TrainingListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(TrainingListPanel.class);

    @FXML
    private ListView<Training> trainingListView;

    /**
     * Creates a {@code TrainingListPanel} with the given {@code ObservableList}.
     */
    public TrainingListPanel(ObservableList<Training> trainingList) {
        super(FXML);
        trainingListView.setItems(trainingList);
        trainingListView.setCellFactory(listView -> new TrainingListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Training} using a {@code TrainingCard}.
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
