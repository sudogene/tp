package seedu.canoe.ui;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.stream.Collectors;

import javafx.beans.binding.When;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import seedu.canoe.model.student.Attendance;
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
    private Label pastTrainingTag;
    @FXML
    private FlowPane pastTrainingAttendances;
    @FXML
    private Label upcomingTrainingTag;
    @FXML
    private FlowPane upcomingTrainingAttendances;

    /**
     * Creates a {@code StudentCode} with the given {@code Student} and index to display.
     */
    public StudentCard(Student student, int displayedIndex) {
        super(FXML);
        this.student = student;
        Background unmarkedAttendance = new Background(new BackgroundFill(Color.RED,
                null, null));
        Background markedAttendance = new Background(new BackgroundFill(Color.GREEN,
                null, null));
        id.setText(displayedIndex + ". ");
        studentId.setText("Id: " + student.getId());
        name.setText(student.getName().fullName);
        phone.setText("Phone: " + student.getPhone().value);
        email.setText("Email: " + student.getEmail().value);
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
        pastTrainingTag.setText("Past and Ongoing Trainings : ");
        // Pulls out the trainings which have been past from training start time
        for (Attendance attendance
                : student.getTrainingAttendances().stream().filter(training -> training.getTrainingTime()
                        .isBefore(LocalDateTime.now())).collect(Collectors.toList())) {
            Label pastAttendanceLabel = new Label(attendance.toString());
            pastAttendanceLabel.backgroundProperty().bind(
                    new When(isMarked(attendance.isMarked())).then(markedAttendance)
                            .otherwise(unmarkedAttendance));
            pastTrainingAttendances.getChildren().add(pastAttendanceLabel);
        }
        upcomingTrainingTag.setText("Upcoming Trainings : ");
        // Pulls out the upcoming trainings which have not passed yet
        for (Attendance attendance
                : student.getTrainingAttendances().stream().filter(training -> training.getTrainingTime()
                        .isAfter(LocalDateTime.now())).collect(Collectors.toList())) {
            Label upcomingAttendanceLabel = new Label(attendance.toString());
            upcomingAttendanceLabel.backgroundProperty().bind(
                    new When(isMarked(attendance.isMarked())).then(markedAttendance)
                            .otherwise(unmarkedAttendance));
            upcomingTrainingAttendances.getChildren().add(upcomingAttendanceLabel);
        }
    }
    private BooleanProperty isMarked(boolean attendance) {
        return new SimpleBooleanProperty(attendance);
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
