
package com.auca.enrollment;

import java.time.LocalDate;

import com.auca.interfaces.Identifiable;
import com.auca.interfaces.Registrable;
import com.auca.student.Student;
import com.auca.course.Course;
import com.auca.exceptions.ValidationException;

public class Enrollment implements Identifiable, Registrable {

    private String enrollmentId;
    private Student student;
    private Course course;
    private LocalDate enrollmentDate;
    private String semester;
    private String enrollmentStatus;

    public Enrollment(String enrollmentId, Student student,
                      Course course, LocalDate enrollmentDate,
                      String semester, String enrollmentStatus) {

        this.enrollmentId = enrollmentId;
        this.student = student;
        this.course = course;
        this.enrollmentDate = enrollmentDate;
        this.semester = semester;
        this.enrollmentStatus = enrollmentStatus;
    }

    @Override
    public String getId() {
        return enrollmentId;
    }

    @Override
    public String getDisplayName() {
        return student.getDisplayName() + " - " + course.getCourseTitle();
    }

    @Override
    public boolean isActive() {
        return enrollmentStatus != null &&
                enrollmentStatus.equalsIgnoreCase("ACTIVE");
    }

    @Override
    public void validate() throws ValidationException {

        if (enrollmentId == null || enrollmentId.trim().isEmpty())
            throw new ValidationException("Enrollment ID is required.");

        if (student == null)
            throw new ValidationException("Student is required.");

        if (course == null)
            throw new ValidationException("Course is required.");

        if (enrollmentDate == null ||
                enrollmentDate.isAfter(LocalDate.now()))
            throw new ValidationException("Invalid enrollment date.");

        if (semester == null ||
                !semester.matches("(?i)SEMESTER[ ]?[1-3]"))
            throw new ValidationException("Semester must be SEMESTER 1, 2 or 3.");

        if (enrollmentStatus == null ||
                !enrollmentStatus.matches("(?i)ACTIVE|INACTIVE|COMPLETED|DROPPED"))
            throw new ValidationException("Invalid enrollment status.");
    }

    @Override
    public void printDetails() {
        System.out.println(this);
    }

    @Override
    public String getRegistrationSummary() {
        return "Enrollment: " + enrollmentId +
                " | Student: " + student.getDisplayName() +
                " | Course: " + course.getCourseTitle() +
                " | Status: " + enrollmentStatus;
    }

    // Getters
    public String getEnrollmentId() { return enrollmentId; }
    public Student getStudent() { return student; }
    public Course getCourse() { return course; }
    public LocalDate getEnrollmentDate() { return enrollmentDate; }
    public String getSemester() { return semester; }
    public String getEnrollmentStatus() { return enrollmentStatus; }

    // Setters
    public void setEnrollmentId(String enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public void setEnrollmentStatus(String enrollmentStatus) {
        this.enrollmentStatus = enrollmentStatus;
    }

    @Override
    public String toString() {
        return "Enrollment{" +
                "enrollmentId='" + enrollmentId + '\'' +
                ", student=" +
                (student != null ? student.getDisplayName() : "None") +
                ", course=" +
                (course != null ? course.getCourseTitle() : "None") +
                ", enrollmentDate=" + enrollmentDate +
                ", semester='" + semester + '\'' +
                ", enrollmentStatus='" + enrollmentStatus + '\'' +
                '}';
    }
}
