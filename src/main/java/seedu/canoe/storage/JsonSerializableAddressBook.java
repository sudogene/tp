package seedu.canoe.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.canoe.commons.exceptions.IllegalValueException;
import seedu.canoe.model.AddressBook;
import seedu.canoe.model.ReadOnlyAddressBook;
import seedu.canoe.model.student.Student;
import seedu.canoe.model.student.Training;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_STUDENT = "Students list contains duplicate student(s).";
    public static final String MESSAGE_DUPLICATE_TRAINING = "Training list contains duplicate Training Session(s).";

    private final List<JsonAdaptedStudent> students = new ArrayList<>();
    private final List<JsonAdaptedTraining> trainings = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given students.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("students") List<JsonAdaptedStudent> students,
                                       @JsonProperty("trainings") List<JsonAdaptedTraining> trainings) {
        this.students.addAll(students);
        this.trainings.addAll(trainings);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        students.addAll(source.getStudentList().stream().map(JsonAdaptedStudent::new).collect(Collectors.toList()));
        trainings.addAll(source.getTrainingList().stream().map(JsonAdaptedTraining::new).collect(Collectors.toList()));
    }

    /**
     * Converts this canoe book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedStudent jsonAdaptedStudent : students) {
            Student student = jsonAdaptedStudent.toModelType();
            if (addressBook.hasStudent(student)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_STUDENT);
            }
            addressBook.addStudent(student);
        }
        for (JsonAdaptedTraining jsonAdaptedTraining : trainings) {
            Training training = jsonAdaptedTraining.toModelType();
            if (addressBook.hasTraining(training)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TRAINING);
            }
            addressBook.addTraining(training);
        }

        return addressBook;
    }

}
