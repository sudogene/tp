package seedu.canoe.ui;

import java.time.format.DateTimeFormatter;
import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.canoe.model.student.Training;

/**
 * An UI component that displays information of a {@code Student}.
 */
public class TrainingCard extends UiPart<Region> {

    private static final String FXML = "TrainingListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Training training;

    @FXML
    private HBox cardPane2;
    @FXML
    private Label id;
    @FXML
    private Label name;
    @FXML
    private FlowPane students;

    /**
     * Creates a {@code StudentCode} with the given {@code Student} and index to display.
     */
    public TrainingCard(Training training, int displayedIndex) {
        super(FXML);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

        this.training = training;
        id.setText(displayedIndex + ". ");
        Separator trainingCardSeparator = new Separator();
        name.setText("Training Session at: " + training.getDateTime().format(formatter));

        training.getStudents().stream()
                .sorted(Comparator.comparing(student -> student.getName().toString()))
                .forEach(student -> students.getChildren().add(new Label(student.studentEssentialPrinter())));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TrainingCard)) {
            return false;
        }

        // state check
        TrainingCard card = (TrainingCard) other;
        return id.getText().equals(card.id.getText())
                && training.equals(card.training);
    }
}
