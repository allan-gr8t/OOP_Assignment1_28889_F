
package com.auca.course;

import com.auca.interfaces.Identifiable;
import com.auca.interfaces.Registrable;
import com.auca.department.Department;
import com.auca.teacher.Teacher;
import com.auca.exceptions.ValidationException;

public class Course implements Identifiable, Registrable {

    private String courseId;
    private String courseCode;
    private String courseTitle;
    private int creditHours;
    private Department department;
    private Teacher assignedTeacher;

    public Course(String courseId, String courseCode,
                  String courseTitle, int creditHours,
                  Department department, Teacher assignedTeacher) {

        this.courseId = courseId;
        this.courseCode = courseCode;
        this.courseTitle = courseTitle;
        this.creditHours = creditHours;
        this.department = department;
        this.assignedTeacher = assignedTeacher;
    }

    @Override
    public String getId() {
        return courseId;
    }

    @Override
    public String getDisplayName() {
        return courseTitle;
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public void validate() throws ValidationException {

        if (courseId == null || courseId.trim().isEmpty())
            throw new ValidationException("Course ID is required.");

        if (courseCode == null || courseCode.trim().isEmpty())
            throw new ValidationException("Course code is required.");

        if (courseTitle == null || courseTitle.trim().length() < 2)
            throw new ValidationException("Invalid course title.");

        if (creditHours < 1 || creditHours > 6)
            throw new ValidationException("Credit hours must be between 1 and 6.");

        if (department == null)
            throw new ValidationException("Course requires a department.");

        if (assignedTeacher == null)
            throw new ValidationException("Course requires an assigned teacher.");

        if (!assignedTeacher.getDepartment().getDepartmentId()
                .equals(department.getDepartmentId()))
            throw new ValidationException("Teacher must belong to the course department.");
    }

    @Override
    public void printDetails() {
        System.out.println(this);
    }

    @Override
    public String getRegistrationSummary() {
        return "Course: " + courseTitle +
                " | Code: " + courseCode +
                " | Credits: " + creditHours;
    }

    // Getters
    public String getCourseId() { return courseId; }
    public String getCourseCode() { return courseCode; }
    public String getCourseTitle() { return courseTitle; }
    public int getCreditHours() { return creditHours; }
    public Department getDepartment() { return department; }
    public Teacher getAssignedTeacher() { return assignedTeacher; }

    // Setters
    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setAssignedTeacher(Teacher assignedTeacher) {
        this.assignedTeacher = assignedTeacher;
    }

    @Override
    public String toString() {
        return "Course{" +
                "courseId='" + courseId + '\'' +
                ", courseCode='" + courseCode + '\'' +
                ", courseTitle='" + courseTitle + '\'' +
                ", creditHours=" + creditHours +
                ", department=" +
                (department != null ? department.getDepartmentName() : "None") +
                ", assignedTeacher=" +
                (assignedTeacher != null ? assignedTeacher.getDisplayName() : "None") +
                '}';
    }
}
