
package com.auca.feepayment;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.auca.interfaces.Identifiable;
import com.auca.interfaces.Registrable;
import com.auca.student.Student;
import com.auca.exceptions.ValidationException;

public class FeePayment implements Identifiable, Registrable {

    private String paymentId;
    private Student student;
    private BigDecimal amount;
    private LocalDate paymentDate;
    private String paymentMethod;
    private String receiptNumber;

    public FeePayment(String paymentId, Student student,
                      BigDecimal amount, LocalDate paymentDate,
                      String paymentMethod, String receiptNumber) {

        this.paymentId = paymentId;
        this.student = student;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.paymentMethod = paymentMethod;
        this.receiptNumber = receiptNumber;
    }

    @Override
    public String getId() {
        return paymentId;
    }

    @Override
    public String getDisplayName() {
        return "Payment " + receiptNumber;
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public void validate() throws ValidationException {

        if (paymentId == null || paymentId.trim().isEmpty())
            throw new ValidationException("Payment ID is required.");

        if (student == null)
            throw new ValidationException("Student is required.");

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0)
            throw new ValidationException("Payment amount must be positive.");

        if (paymentDate == null ||
                paymentDate.isAfter(LocalDate.now()))
            throw new ValidationException("Invalid payment date.");

        if (paymentMethod == null ||
                !paymentMethod.matches("(?i)CASH|MOBILE_MONEY|BANK_TRANSFER|CARD"))
            throw new ValidationException("Invalid payment method.");

        if (receiptNumber == null || receiptNumber.trim().isEmpty())
            throw new ValidationException("Receipt number is required.");
    }

    @Override
    public void printDetails() {
        System.out.println(this);
    }

    @Override
    public String getRegistrationSummary() {
        return "Payment: " + receiptNumber +
                " | Student: " + student.getDisplayName() +
                " | Amount: " + amount;
    }

    // Getters
    public String getPaymentId() { return paymentId; }
    public Student getStudent() { return student; }
    public BigDecimal getAmount() { return amount; }
    public LocalDate getPaymentDate() { return paymentDate; }
    public String getPaymentMethod() { return paymentMethod; }
    public String getReceiptNumber() { return receiptNumber; }

    // Setters
    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setReceiptNumber(String receiptNumber) {
        this.receiptNumber = receiptNumber;
    }

    @Override
    public String toString() {
        return "FeePayment{" +
                "paymentId='" + paymentId + '\'' +
                ", student=" +
                (student != null ? student.getDisplayName() : "None") +
                ", amount=" + amount +
                ", paymentDate=" + paymentDate +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", receiptNumber='" + receiptNumber + '\'' +
                '}';
    }
}
