package seedu.canoe.ui;

import java.time.format.DateTimeFormatter;
import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.canoe.model.student.Student;

/**
 * An UI component that displays information of a {@code Student}.
 */
public class StudentCard extends UiPart<Region> {

    private static final String FXML = "StudentListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on CanoeCoach level 4</a>
     */

    public final Student student;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label studentId;
    @FXML
    private Label phone;
    @FXML
    private Label email;
    @FXML
    private Label academicYear;
    @FXML
    private Label dismissalTime;
    @FXML
    private FlowPane dismissalTimes;
    @FXML
    private FlowPane tags;
    @FXML
    private Label trainingTag;
    @FXML
    private FlowPane trainingSchedules;

    /**
     * Creates a {@code StudentCode} with the given {@code Student} and index to display.
     */
    public StudentCard(Student student, int displayedIndex) {
        super(FXML);
        this.student = student;
        id.setText(displayedIndex + ". ");
        studentId.setText("ID: " + student.getId());
        name.setText(student.getName().fullName);
        phone.setText(student.getPhone().value);
        email.setText(student.getEmail().value);
        academicYear.setText("Academic Year: " + student.getAcademicYear().value);
        dismissalTime.setText("Dismissal Times: ");
        Label mondayDismissal = new Label();
        Label tuesdayDismissal = new Label();
        Label wednesdayDismissal = new Label();
        Label thursdayDismissal = new Label();
        Label fridayDismissal = new Label();
        mondayDismissal.setText("Monday: " + student.getMondayDismissal().toString());
        tuesdayDismissal.setText("Tuesday: " + student.getTuesdayDismissal().toString());
        wednesdayDismissal.setText("Wednesday: " + student.getWednesdayDismissal().toString());
        thursdayDismissal.setText("Thursday: " + student.getThursdayDismissal().toString());
        fridayDismissal.setText("Friday: " + student.getFridayDismissal().toString());
        dismissalTimes.getChildren().add(mondayDismissal);
        dismissalTimes.getChildren().add(tuesdayDismissal);
        dismissalTimes.getChildren().add(wednesdayDismissal);
        dismissalTimes.getChildren().add(thursdayDismissal);
        dismissalTimes.getChildren().add(fridayDismissal);
        Separator trainingSeparator = new Separator();
        Separator dismissalTimesSeparator = new Separator();
        student.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        trainingTag.setText("Trainings Scheduled: ");
        student.getTrainingSchedule().stream()
                .forEach(trainingSchedule -> trainingSchedules.getChildren()
                        .add(new Label((trainingSchedule.getTrainingTime())
                            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm")))));

    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StudentCard)) {
            return false;
        }

        // state check
        StudentCard card = (StudentCard) other;
        return id.getText().equals(card.id.getText())
                && student.equals(card.student);
    }
}
