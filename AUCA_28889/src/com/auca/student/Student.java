
package com.auca.student;

import java.time.LocalDate;

import com.auca.abstracts.AcademicMember;
import com.auca.department.Department;
import com.auca.exceptions.ValidationException;

public class Student extends AcademicMember {

    // Six mandatory Student attributes
    private String studentNumber;
    private String programName;
    private LocalDate admissionDate;
    private int studyYear;
    private String guardianPhone;
    private boolean scholarshipStatus;

    // Constructor
    public Student(
            String personId, String firstName, String lastName,
            String email, String phoneNumber, LocalDate dateOfBirth,
            String memberNumber, Department department,
            LocalDate joinDate, String academicStatus,
            String institutionalEmail, String advisorName,
            String studentNumber, String programName,
            LocalDate admissionDate, int studyYear,
            String guardianPhone, boolean scholarshipStatus) {

        super(personId, firstName, lastName, email, phoneNumber,
              dateOfBirth, memberNumber, department, joinDate,
              academicStatus, institutionalEmail, advisorName);

        this.studentNumber = studentNumber;
        this.programName = programName;
        this.admissionDate = admissionDate;
        this.studyYear = studyYear;
        this.guardianPhone = guardianPhone;
        this.scholarshipStatus = scholarshipStatus;
    }

    // Required abstract method implementations
    @Override
    public String getRoleDescription() {
        return "Student";
    }

    @Override
    public String getAcademicCategory() {
        return "Undergraduate Student";
    }

    // Identifiable
    @Override
    public String getId() {
        return getPersonId();
    }

    @Override
    public String getDisplayName() {
        return getFirstName() + " " + getLastName();
    }

    // Validation
    @Override
    public void validate() throws ValidationException {

        if (studentNumber == null || studentNumber.trim().isEmpty())
            throw new ValidationException("Student number is required.");

        if (programName == null || programName.trim().length() < 2
                || programName.trim().length() > 120)
            throw new ValidationException("Invalid program name.");

        if (admissionDate == null || admissionDate.isAfter(LocalDate.now()))
            throw new ValidationException("Invalid admission date.");

        if (studyYear < 1 || studyYear > 8)
            throw new ValidationException("Study year must be between 1 and 8.");

        if (guardianPhone == null ||
                !guardianPhone.matches("^\\+?[0-9]{10,15}$"))
            throw new ValidationException("Invalid guardian phone.");

        if (getDepartment() == null)
            throw new ValidationException("Student must have a department.");
    }

    @Override
    public void printDetails() {
        System.out.println(this);
    }

    @Override
    public String getRegistrationSummary() {
        return "Student: " + getDisplayName() +
                " | Student Number: " + studentNumber +
                " | Program: " + programName;
    }

    // Getters
    public String getStudentNumber() { return studentNumber; }
    public String getProgramName() { return programName; }
    public LocalDate getAdmissionDate() { return admissionDate; }
    public int getStudyYear() { return studyYear; }
    public String getGuardianPhone() { return guardianPhone; }
    public boolean isScholarshipStatus() { return scholarshipStatus; }

    // Setters
    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public void setAdmissionDate(LocalDate admissionDate) {
        this.admissionDate = admissionDate;
    }

    public void setStudyYear(int studyYear) {
        this.studyYear = studyYear;
    }

    public void setGuardianPhone(String guardianPhone) {
        this.guardianPhone = guardianPhone;
    }

    public void setScholarshipStatus(boolean scholarshipStatus) {
        this.scholarshipStatus = scholarshipStatus;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nStudent{" +
                "studentNumber='" + studentNumber + '\'' +
                ", programName='" + programName + '\'' +
                ", admissionDate=" + admissionDate +
                ", studyYear=" + studyYear +
                ", guardianPhone='" + guardianPhone + '\'' +
                ", scholarshipStatus=" + scholarshipStatus +
                '}';
    }
}
