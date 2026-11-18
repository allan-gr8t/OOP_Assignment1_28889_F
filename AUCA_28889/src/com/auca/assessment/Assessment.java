
package com.auca.assessment;

import java.time.LocalDate;

import com.auca.interfaces.Identifiable;
import com.auca.interfaces.Registrable;
import com.auca.enrollment.Enrollment;
import com.auca.exceptions.ValidationException;

public class Assessment implements Identifiable, Registrable {

    private String assessmentId;
    private Enrollment enrollment;
    private String assessmentType;
    private double maximumScore;
    private double obtainedScore;
    private LocalDate assessmentDate;

    public Assessment(String assessmentId, Enrollment enrollment,
                      String assessmentType, double maximumScore,
                      double obtainedScore, LocalDate assessmentDate) {

        this.assessmentId = assessmentId;
        this.enrollment = enrollment;
        this.assessmentType = assessmentType;
        this.maximumScore = maximumScore;
        this.obtainedScore = obtainedScore;
        this.assessmentDate = assessmentDate;
    }

    @Override
    public String getId() {
        return assessmentId;
    }

    @Override
    public String getDisplayName() {
        return assessmentType + " - " + assessmentId;
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public void validate() throws ValidationException {

        if (assessmentId == null || assessmentId.trim().isEmpty())
            throw new ValidationException("Assessment ID is required.");

        if (enrollment == null)
            throw new ValidationException("Enrollment is required.");

        if (!enrollment.isActive())
            throw new ValidationException("Only active enrollments can be assessed.");

        if (assessmentType == null ||
                !assessmentType.matches("(?i)QUIZ|TEST|EXAM|ASSIGNMENT|PROJECT"))
            throw new ValidationException("Invalid assessment type.");

        if (maximumScore <= 0)
            throw new ValidationException("Maximum score must be positive.");

        if (obtainedScore < 0 || obtainedScore > maximumScore)
            throw new ValidationException("Obtained score must be between 0 and maximum score.");

        if (assessmentDate == null ||
                assessmentDate.isAfter(LocalDate.now()))
            throw new ValidationException("Invalid assessment date.");
    }

    @Override
    public void printDetails() {
        System.out.println(this);
    }

    @Override
    public String getRegistrationSummary() {
        return "Assessment: " + assessmentType +
                " | Student: " + enrollment.getStudent().getDisplayName() +
                " | Score: " + obtainedScore + "/" + maximumScore;
    }

    // Getters
    public String getAssessmentId() { return assessmentId; }
    public Enrollment getEnrollment() { return enrollment; }
    public String getAssessmentType() { return assessmentType; }
    public double getMaximumScore() { return maximumScore; }
    public double getObtainedScore() { return obtainedScore; }
    public LocalDate getAssessmentDate() { return assessmentDate; }

    // Setters
    public void setAssessmentId(String assessmentId) {
        this.assessmentId = assessmentId;
    }

    public void setEnrollment(Enrollment enrollment) {
        this.enrollment = enrollment;
    }

    public void setAssessmentType(String assessmentType) {
        this.assessmentType = assessmentType;
    }

    public void setMaximumScore(double maximumScore) {
        this.maximumScore = maximumScore;
    }

    public void setObtainedScore(double obtainedScore) {
        this.obtainedScore = obtainedScore;
    }

    public void setAssessmentDate(LocalDate assessmentDate) {
        this.assessmentDate = assessmentDate;
    }

    @Override
    public String toString() {
        return "Assessment{" +
                "assessmentId='" + assessmentId + '\'' +
                ", enrollment=" +
                (enrollment != null ? enrollment.getEnrollmentId() : "None") +
                ", assessmentType='" + assessmentType + '\'' +
                ", maximumScore=" + maximumScore +
                ", obtainedScore=" + obtainedScore +
                ", assessmentDate=" + assessmentDate +
                '}';
    }
}
