
package com.auca.teacher;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.auca.abstracts.AcademicMember;
import com.auca.department.Department;
import com.auca.exceptions.ValidationException;

public class Teacher extends AcademicMember {

    // Six mandatory Teacher attributes
    private String employeeNumber;
    private String specialization;
    private LocalDate hireDate;
    private BigDecimal salary;
    private String officeNumber;
    private String employmentType;

    // Constructor
    public Teacher(
            String personId, String firstName, String lastName,
            String email, String phoneNumber, LocalDate dateOfBirth,
            String memberNumber, Department department,
            LocalDate joinDate, String academicStatus,
            String institutionalEmail, String advisorName,
            String employeeNumber, String specialization,
            LocalDate hireDate, BigDecimal salary,
            String officeNumber, String employmentType) {

        super(personId, firstName, lastName, email, phoneNumber,
              dateOfBirth, memberNumber, department, joinDate,
              academicStatus, institutionalEmail, advisorName);

        this.employeeNumber = employeeNumber;
        this.specialization = specialization;
        this.hireDate = hireDate;
        this.salary = salary;
        this.officeNumber = officeNumber;
        this.employmentType = employmentType;
    }

    @Override
    public String getRoleDescription() {
        return "Teacher";
    }

    @Override
    public String getAcademicCategory() {
        return "Academic Staff";
    }

    @Override
    public String getId() {
        return getPersonId();
    }

    @Override
    public String getDisplayName() {
        return getFirstName() + " " + getLastName();
    }

    @Override
    public void validate() throws ValidationException {

        if (employeeNumber == null || employeeNumber.trim().isEmpty())
            throw new ValidationException("Employee number is required.");

        if (specialization == null || specialization.trim().length() < 2)
            throw new ValidationException("Invalid specialization.");

        if (hireDate == null || hireDate.isAfter(LocalDate.now()))
            throw new ValidationException("Invalid hire date.");

        if (salary == null || salary.compareTo(BigDecimal.ZERO) <= 0)
            throw new ValidationException("Salary must be positive.");

        if (officeNumber == null || officeNumber.trim().isEmpty())
            throw new ValidationException("Office number is required.");

        if (employmentType == null ||
                !employmentType.matches("(?i)FULL_TIME|PART_TIME|CONTRACT"))
            throw new ValidationException("Invalid employment type.");

        if (getDepartment() == null)
            throw new ValidationException("Teacher must belong to a department.");
    }

    @Override
    public void printDetails() {
        System.out.println(this);
    }

    @Override
    public String getRegistrationSummary() {
        return "Teacher: " + getDisplayName() +
                " | Employee Number: " + employeeNumber +
                " | Specialization: " + specialization;
    }

    // Getters
    public String getEmployeeNumber() { return employeeNumber; }
    public String getSpecialization() { return specialization; }
    public LocalDate getHireDate() { return hireDate; }
    public BigDecimal getSalary() { return salary; }
    public String getOfficeNumber() { return officeNumber; }
    public String getEmploymentType() { return employmentType; }

    // Setters
    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public void setHireDate(LocalDate hireDate) {
        this.hireDate = hireDate;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    public void setOfficeNumber(String officeNumber) {
        this.officeNumber = officeNumber;
    }

    public void setEmploymentType(String employmentType) {
        this.employmentType = employmentType;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nTeacher{" +
                "employeeNumber='" + employeeNumber + '\'' +
                ", specialization='" + specialization + '\'' +
                ", hireDate=" + hireDate +
                ", salary=" + salary +
                ", officeNumber='" + officeNumber + '\'' +
                ", employmentType='" + employmentType + '\'' +
                '}';
    }
}
