
package com.auca.administrator;

import java.time.LocalDate;

import com.auca.abstracts.Person;
import com.auca.exceptions.ValidationException;

public class Administrator extends Person {

    // Six mandatory Administrator attributes
    private String staffNumber;
    private String jobTitle;
    private String assignedOffice;
    private LocalDate employmentDate;
    private int accessLevel;
    private boolean active;

    // Constructor
    public Administrator(
            String personId, String firstName, String lastName,
            String email, String phoneNumber, LocalDate dateOfBirth,
            String staffNumber, String jobTitle,
            String assignedOffice, LocalDate employmentDate,
            int accessLevel, boolean active) {

        super(personId, firstName, lastName, email,
              phoneNumber, dateOfBirth);

        this.staffNumber = staffNumber;
        this.jobTitle = jobTitle;
        this.assignedOffice = assignedOffice;
        this.employmentDate = employmentDate;
        this.accessLevel = accessLevel;
        this.active = active;
    }

    @Override
    public String getRoleDescription() {
        return "Administrator";
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
    public boolean isActive() {
        return active;
    }

    @Override
    public void validate() throws ValidationException {

        if (staffNumber == null || staffNumber.trim().isEmpty())
            throw new ValidationException("Staff number is required.");

        if (jobTitle == null || jobTitle.trim().length() < 2)
            throw new ValidationException("Invalid job title.");

        if (assignedOffice == null || assignedOffice.trim().isEmpty())
            throw new ValidationException("Assigned office is required.");

        if (employmentDate == null ||
                employmentDate.isAfter(LocalDate.now()))
            throw new ValidationException("Invalid employment date.");

        if (accessLevel < 1 || accessLevel > 5)
            throw new ValidationException("Access level must be between 1 and 5.");
    }

    @Override
    public void printDetails() {
        System.out.println(this);
    }

    @Override
    public String getRegistrationSummary() {
        return "Administrator: " + getDisplayName() +
                " | Staff Number: " + staffNumber +
                " | Job Title: " + jobTitle;
    }

    // Getters
    public String getStaffNumber() { return staffNumber; }
    public String getJobTitle() { return jobTitle; }
    public String getAssignedOffice() { return assignedOffice; }
    public LocalDate getEmploymentDate() { return employmentDate; }
    public int getAccessLevel() { return accessLevel; }
    public boolean isActiveStatus() { return active; }

    // Setters
    public void setStaffNumber(String staffNumber) {
        this.staffNumber = staffNumber;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public void setAssignedOffice(String assignedOffice) {
        this.assignedOffice = assignedOffice;
    }

    public void setEmploymentDate(LocalDate employmentDate) {
        this.employmentDate = employmentDate;
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nAdministrator{" +
                "staffNumber='" + staffNumber + '\'' +
                ", jobTitle='" + jobTitle + '\'' +
                ", assignedOffice='" + assignedOffice + '\'' +
                ", employmentDate=" + employmentDate +
                ", accessLevel=" + accessLevel +
                ", active=" + active +
                '}';
    }
}
