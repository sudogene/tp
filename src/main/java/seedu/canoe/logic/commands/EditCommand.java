package seedu.canoe.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.canoe.logic.parser.CliSyntax.PREFIX_ACADEMIC_YEAR;
import static seedu.canoe.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.canoe.logic.parser.CliSyntax.PREFIX_FRIDAY_DISMISSAL;
import static seedu.canoe.logic.parser.CliSyntax.PREFIX_MONDAY_DISMISSAL;
import static seedu.canoe.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.canoe.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.canoe.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.canoe.logic.parser.CliSyntax.PREFIX_THURSDAY_DISMISSAL;
import static seedu.canoe.logic.parser.CliSyntax.PREFIX_TUESDAY_DISMISSAL;
import static seedu.canoe.logic.parser.CliSyntax.PREFIX_WEDNESDAY_DISMISSAL;
import static seedu.canoe.model.Model.PREDICATE_SHOW_ALL_STUDENTS;
import static seedu.canoe.model.Model.PREDICATE_SHOW_ALL_TRAININGS;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import seedu.canoe.commons.core.LogsCenter;
import seedu.canoe.commons.core.Messages;
import seedu.canoe.commons.core.index.Index;
import seedu.canoe.commons.util.CollectionUtil;
import seedu.canoe.logic.commands.exceptions.CommandException;
import seedu.canoe.model.Model;
import seedu.canoe.model.student.AcademicYear;
import seedu.canoe.model.student.Attendance;
import seedu.canoe.model.student.Email;
import seedu.canoe.model.student.Id;
import seedu.canoe.model.student.Name;
import seedu.canoe.model.student.Phone;
import seedu.canoe.model.student.Student;
import seedu.canoe.model.student.time.Day;
import seedu.canoe.model.tag.Tag;
import seedu.canoe.model.training.Training;
import seedu.canoe.model.util.StudentTrainingSessionUtil;

/**
 * Edits the details of an existing student in the canoe book.
 */
public class EditCommand extends Command {

    public static final Logger LOGGER = LogsCenter.getLogger(EditCommand.class);

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the student identified "
            + "by the index number used in the displayed student list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ACADEMIC_YEAR + "ACADEMIC YEAR] "
            + "[" + PREFIX_MONDAY_DISMISSAL + "MONDAY DISMISSAL TIME " + PREFIX_TUESDAY_DISMISSAL
            + "TUESDAY DISMISSAL TIME " + PREFIX_WEDNESDAY_DISMISSAL + "WEDNESDAY DISMISSAL TIME "
            + PREFIX_THURSDAY_DISMISSAL + "THURSDAY DISMISSAL TIME " + PREFIX_FRIDAY_DISMISSAL
            + "FRIDAY DISMISSAL TIME] "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com "
            + PREFIX_ACADEMIC_YEAR + "1 "
            + PREFIX_MONDAY_DISMISSAL + "1600 " + PREFIX_TUESDAY_DISMISSAL
            + "1500 " + PREFIX_WEDNESDAY_DISMISSAL + "1400 "
            + PREFIX_THURSDAY_DISMISSAL + "1700 " + PREFIX_FRIDAY_DISMISSAL
            + "1800";

    public static final String MESSAGE_EDIT_STUDENT_SUCCESS = "Edited Student: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_STUDENT = "This student already exists in the canoe book.";

    private final Index index;
    private final EditStudentDescriptor editStudentDescriptor;

    /**
     * @param index of the student in the filtered student list to edit
     * @param editStudentDescriptor details to edit the student with
     */
    public EditCommand(Index index, EditStudentDescriptor editStudentDescriptor) {
        LOGGER.info("=============================[ Executing EditCommand ]===========================");
        requireNonNull(index);
        requireNonNull(editStudentDescriptor);

        this.index = index;
        this.editStudentDescriptor = new EditStudentDescriptor(editStudentDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownStudentList = model.getFilteredStudentList();

        if (index.getZeroBased() >= lastShownStudentList.size()) {
            LOGGER.warning("Index is invalid.");
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToEdit = lastShownStudentList.get(index.getZeroBased());
        Student editedStudent = createEditedStudent(studentToEdit, editStudentDescriptor);

        if (!studentToEdit.isSameStudent(editedStudent) && model.hasStudent(editedStudent)) {
            LOGGER.warning("No fields have been edited");
            throw new CommandException(MESSAGE_DUPLICATE_STUDENT);
        }

        if (!editedStudent.isAvailableForAllAttendances()) {
            List<Attendance> dateTimesUnableToAttendance = StudentTrainingSessionUtil
                    .getConflictsInStudentTrainingAttendances(editedStudent.getTrainingAttendances(), editedStudent);
            List<Training> trainingsUnableToAttend = StudentTrainingSessionUtil
                    .getTrainingListFromTrainingAttendances(dateTimesUnableToAttendance, model);

            for (Training training: trainingsUnableToAttend) {
                Training editedTraining = new Training(training.getDateTime(), training.getStudents());
                editedTraining.removeStudent(studentToEdit);
                editedStudent.removeAttendance(new Attendance(training.getDateTime()));
                model.setTraining(training, editedTraining);
            }
        }

        List<Attendance> studentTrainingDateTimeList = new ArrayList<>(editedStudent.getTrainingAttendances());
        List<Training> studentTrainingList = StudentTrainingSessionUtil
                .getTrainingListFromTrainingAttendances(studentTrainingDateTimeList, model);

        for (Training training: studentTrainingList) {
            Training editedTraining = new Training(training.getDateTime(), training.getStudents());
            editedTraining.removeStudent(studentToEdit);
            editedTraining.addStudent(editedStudent);
            model.setTraining(training, editedTraining);
        }

        model.setStudentInUniqueStudentList(studentToEdit, editedStudent);
        model.updateFilteredTrainingList(PREDICATE_SHOW_ALL_TRAININGS);
        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);
        return new CommandResult(String.format(MESSAGE_EDIT_STUDENT_SUCCESS, editedStudent));
    }

    /**
     * Creates and returns a {@code Student} with the details of {@code studentToEdit}
     * edited with {@code editStudentDescriptor}.
     */
    private static Student createEditedStudent(Student studentToEdit, EditStudentDescriptor editStudentDescriptor) {
        assert studentToEdit != null;

        Name updatedName = editStudentDescriptor.getName().orElse(studentToEdit.getName());
        Phone updatedPhone = editStudentDescriptor.getPhone().orElse(studentToEdit.getPhone());
        Email updatedEmail = editStudentDescriptor.getEmail().orElse(studentToEdit.getEmail());
        AcademicYear updatedAcademicYear = editStudentDescriptor.getAcademicYear()
                .orElse(studentToEdit.getAcademicYear());
        Day mondayDismissal = editStudentDescriptor.getMondayDismissal().orElse(studentToEdit.getMondayDismissal());
        Day tuesdayDismissal = editStudentDescriptor.getTuesdayDismissal().orElse(studentToEdit.getTuesdayDismissal());
        Day wednesdayDismissal =
                editStudentDescriptor.getWednesdayDismissal().orElse(studentToEdit.getWednesdayDismissal());
        Day thursdayDismissal =
                editStudentDescriptor.getThursdayDismissal().orElse(studentToEdit.getThursdayDismissal());
        Day fridayDismissal = editStudentDescriptor.getFridayDismissal().orElse(studentToEdit.getFridayDismissal());
        Set<Tag> updatedTags = editStudentDescriptor.getTags().orElse(studentToEdit.getTags());
        List<Attendance> trainingAttendances = studentToEdit.getTrainingAttendances().stream()
                .collect(Collectors.toList());
        Id id = studentToEdit.getId();

        Student newStudent = new Student(updatedName, updatedPhone, updatedEmail, updatedAcademicYear, updatedTags,
            mondayDismissal, tuesdayDismissal, wednesdayDismissal, thursdayDismissal, fridayDismissal, id);
        newStudent.addAllAttendances(trainingAttendances);
        return newStudent;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editStudentDescriptor.equals(e.editStudentDescriptor);
    }

    /**
     * Stores the details to edit the student with. Each non-empty field value will replace the
     * corresponding field value of the student.
     */
    public static class EditStudentDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private AcademicYear academicYear;
        private Id id;

        //Dismissal Times
        private Day mondayDismissal;
        private Day tuesdayDismissal;
        private Day wednesdayDismissal;
        private Day thursdayDismissal;
        private Day fridayDismissal;

        private Set<Tag> tags;

        public EditStudentDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditStudentDescriptor(EditStudentDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setMondayDismissal(toCopy.mondayDismissal);
            setTuesdayDismissal(toCopy.tuesdayDismissal);
            setWednesdayDismissal(toCopy.wednesdayDismissal);
            setThursdayDismissal(toCopy.thursdayDismissal);
            setFridayDismissal(toCopy.fridayDismissal);
            setAcademicYear(toCopy.academicYear);
            setTags(toCopy.tags);
            setId(toCopy.id);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, academicYear,
                mondayDismissal, tuesdayDismissal, wednesdayDismissal, thursdayDismissal, fridayDismissal, tags);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setMondayDismissal(Day mondayDismissal) {
            this.mondayDismissal = mondayDismissal;
        }

        public Optional<Day> getMondayDismissal() {
            return Optional.ofNullable(mondayDismissal);
        }

        public void setTuesdayDismissal(Day tuesdayDismissal) {
            this.tuesdayDismissal = tuesdayDismissal;
        }

        public Optional<Day> getTuesdayDismissal() {
            return Optional.ofNullable(tuesdayDismissal);
        }

        public void setWednesdayDismissal(Day wednesdayDismissal) {
            this.wednesdayDismissal = wednesdayDismissal;
        }

        public Optional<Day> getWednesdayDismissal() {
            return Optional.ofNullable(wednesdayDismissal);
        }

        public void setThursdayDismissal(Day thursdayDismissal) {
            this.thursdayDismissal = thursdayDismissal;
        }

        public Optional<Day> getThursdayDismissal() {
            return Optional.ofNullable(thursdayDismissal);
        }

        public void setFridayDismissal(Day fridayDismissal) {
            this.fridayDismissal = fridayDismissal;
        }

        public Optional<Day> getFridayDismissal() {
            return Optional.ofNullable(fridayDismissal);
        }

        public void setAcademicYear (AcademicYear academicYear) {
            this.academicYear = academicYear;
        }

        public Optional<AcademicYear> getAcademicYear() {
            return Optional.ofNullable(academicYear);

        }

        public Optional<Id> getId() {
            return Optional.ofNullable(id);
        }

        public void setId(Id id) {
            this.id = id;
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditStudentDescriptor)) {
                return false;
            }

            // state check
            EditStudentDescriptor e = (EditStudentDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getMondayDismissal().equals(e.getMondayDismissal())
                    && getTuesdayDismissal().equals(e.getTuesdayDismissal())
                    && getWednesdayDismissal().equals(e.getWednesdayDismissal())
                    && getThursdayDismissal().equals(e.getThursdayDismissal())
                    && getFridayDismissal().equals(e.getFridayDismissal())
                    && getAcademicYear().equals(e.getAcademicYear())
                    && getTags().equals(e.getTags());
        }
    }
}
