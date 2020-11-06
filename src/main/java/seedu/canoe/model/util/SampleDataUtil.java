package seedu.canoe.model.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.canoe.model.CanoeCoach;
import seedu.canoe.model.ReadOnlyCanoeCoach;
import seedu.canoe.model.student.AcademicYear;
import seedu.canoe.model.student.Attendance;
import seedu.canoe.model.student.Email;
import seedu.canoe.model.student.Id;
import seedu.canoe.model.student.Name;
import seedu.canoe.model.student.Phone;
import seedu.canoe.model.student.Student;
import seedu.canoe.model.student.time.Friday;
import seedu.canoe.model.student.time.Monday;
import seedu.canoe.model.student.time.Thursday;
import seedu.canoe.model.student.time.Tuesday;
import seedu.canoe.model.student.time.Wednesday;
import seedu.canoe.model.tag.Tag;
import seedu.canoe.model.training.Training;

/**
 * Contains utility methods for populating {@code CanoeCoach} with sample data.
 */
public class SampleDataUtil {

    public static Training[] getPastSampleTraining() {
        LocalDateTime pastDateTime = LocalDateTime.parse("2020-08-26 1500",
                DateTimeFormatter.ofPattern("yyyy-MM-dd " + "HHmm"));
        LocalDateTime secondPastDateTime = LocalDateTime.parse("2020-09-15 1500",
                DateTimeFormatter.ofPattern("yyyy-MM-dd " + "HHmm"));
        LocalDateTime thirdPastDateTime = LocalDateTime.parse("2020-06-15 1500",
                DateTimeFormatter.ofPattern("yyyy-MM-dd " + "HHmm"));
        LocalDateTime fourthPastDateTime = LocalDateTime.parse("2020-03-15 1500",
                DateTimeFormatter.ofPattern("yyyy-MM-dd " + "HHmm"));
        Training firstTraining = new Training(pastDateTime);
        Training secondTraining = new Training(secondPastDateTime);
        Training thirdTraining = new Training(thirdPastDateTime);
        Training fourthTraining = new Training(fourthPastDateTime);

        return new Training[] {
            firstTraining, secondTraining, thirdTraining, fourthTraining
        };
    }

    public static Training[] getFutureSampleTraining() {
        LocalDateTime nearFutureDateTime = LocalDateTime.parse("2021-01-26 1500",
                DateTimeFormatter.ofPattern("yyyy-MM-dd " + "HHmm"));
        LocalDateTime secondNearFutureDateTime = LocalDateTime.parse("2021-02-12 1500",
                DateTimeFormatter.ofPattern("yyyy-MM-dd " + "HHmm"));

        Training firstTraining = new Training(nearFutureDateTime);
        Training secondTraining = new Training(secondNearFutureDateTime);

        return new Training[] {
            firstTraining, secondTraining
        };
    }
    public static Student[] getSampleStudents() {
        Student alex = new Student(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new AcademicYear("2"), getTagSet("fast swimmer"), new Monday("1500"),
                new Tuesday("1500"), new Wednesday("1500"),
                new Thursday("1500"), new Friday("1500"), Id.newId());
        Student bernice = new Student(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new AcademicYear("3"), getTagSet("fast runner", "leader"), new Monday("1500"),
                new Tuesday("1500"), new Wednesday("1500"),
                new Thursday("1500"), new Friday("1500"), Id.newId());
        Student charlotte = new Student(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte"
                + "@example.com"), new AcademicYear("5"), getTagSet("fast swimmer"),
                new Monday("1500"), new Tuesday("1500"),
                new Wednesday("1500"), new Thursday("1500"), new Friday("1500"),
                Id.newId());
        Student david = new Student(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                        new AcademicYear("1"), getTagSet("fittest"), new Monday("1500"),
                        new Tuesday("1500"), new Wednesday("1500"),
                        new Thursday("1500"), new Friday("1500"), Id.newId());
        Student irfan = new Student(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                        new AcademicYear("4"), getTagSet("slowest runner"), new Monday("1500"),
                        new Tuesday("1500"), new Wednesday("1500"),
                        new Thursday("1500"), new Friday("1500"), Id.newId());
        Student roy = new Student(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                        new AcademicYear("1"), getTagSet("best canoer"), new Monday("1500"),
                        new Tuesday("1500"), new Wednesday("1500"),
                        new Thursday("1500"), new Friday("1500"), Id.newId());

        return new Student[] {
            alex, bernice, charlotte, david, irfan, roy
        };
    }

    public static ReadOnlyCanoeCoach getCanoeCoachBook() {
        CanoeCoach sampleAb = new CanoeCoach();
        for (Student sampleStudent : getSampleStudents()) {
            sampleAb.addStudent(sampleStudent);
        }

        for (Training sampleTraining : getPastSampleTraining()) {
            sampleAb.addTraining(sampleTraining);
            for (Student sampleStudent : sampleAb.getStudentList()) {
                sampleTraining.addStudent(sampleStudent);
                Student alex = sampleAb.getStudentList().get(0);
                Student bernice = sampleAb.getStudentList().get(1);
                if (!(sampleStudent == alex || sampleStudent == bernice)) {
                    sampleStudent.markAttendanceFromTraining(sampleTraining);
                }
            }
        }

        for (Training sampleTraining : getFutureSampleTraining()) {
            sampleAb.addTraining(sampleTraining);
            for (Student sampleStudent : sampleAb.getStudentList()) {
                sampleTraining.addStudent(sampleStudent);
            }
        }

        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    /**
     * Returns a training schedule list containing the list of LocalDateTimes given.
     */
    public static List<Attendance> getTrainingAttendances(Attendance... attendance) {
        return Arrays.stream(attendance)
                .collect(Collectors.toList());
    }

}
