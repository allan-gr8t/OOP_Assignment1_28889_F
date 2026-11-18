
package com.auca.bookloan;

import java.time.LocalDate;

import com.auca.interfaces.Identifiable;
import com.auca.interfaces.Registrable;
import com.auca.student.Student;
import com.auca.librarybook.LibraryBook;
import com.auca.exceptions.ValidationException;

public class BookLoan implements Identifiable, Registrable {

    private String loanId;
    private Student student;
    private LibraryBook book;
    private LocalDate borrowedDate;
    private LocalDate dueDate;
    private LocalDate returnedDate;

    public BookLoan(String loanId, Student student,
                    LibraryBook book, LocalDate borrowedDate,
                    LocalDate dueDate, LocalDate returnedDate) {

        this.loanId = loanId;
        this.student = student;
        this.book = book;
        this.borrowedDate = borrowedDate;
        this.dueDate = dueDate;
        this.returnedDate = returnedDate;
    }

    @Override
    public String getId() {
        return loanId;
    }

    @Override
    public String getDisplayName() {
        return student.getDisplayName() + " - " + book.getTitle();
    }

    @Override
    public boolean isActive() {
        return returnedDate == null;
    }

    @Override
    public void validate() throws ValidationException {

        if (loanId == null || loanId.trim().isEmpty())
            throw new ValidationException("Loan ID is required.");

        if (student == null)
            throw new ValidationException("Student is required.");

        if (book == null)
            throw new ValidationException("Library book is required.");

        if (borrowedDate == null ||
                borrowedDate.isAfter(LocalDate.now()))
            throw new ValidationException("Invalid borrowing date.");

        if (dueDate == null || !dueDate.isAfter(borrowedDate))
            throw new ValidationException("Due date must be after borrowing date.");

        if (returnedDate != null &&
                returnedDate.isBefore(borrowedDate))
            throw new ValidationException("Return date cannot be before borrowing date.");
    }

    // Record a book return
    public void markAsReturned(LocalDate date) throws ValidationException {

        if (returnedDate != null)
            throw new ValidationException("This book has already been returned.");

        if (date == null || date.isBefore(borrowedDate) ||
                date.isAfter(LocalDate.now()))
            throw new ValidationException("Invalid return date.");

        returnedDate = date;
    }

    @Override
    public void printDetails() {
        System.out.println(this);
    }

    @Override
    public String getRegistrationSummary() {
        return "Loan: " + loanId +
                " | Student: " + student.getDisplayName() +
                " | Book: " + book.getTitle() +
                " | Returned: " + (returnedDate != null);
    }

    // Getters
    public String getLoanId() { return loanId; }
    public Student getStudent() { return student; }
    public LibraryBook getBook() { return book; }
    public LocalDate getBorrowedDate() { return borrowedDate; }
    public LocalDate getDueDate() { return dueDate; }
    public LocalDate getReturnedDate() { return returnedDate; }

    // Setters
    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setBook(LibraryBook book) {
        this.book = book;
    }

    public void setBorrowedDate(LocalDate borrowedDate) {
        this.borrowedDate = borrowedDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setReturnedDate(LocalDate returnedDate) {
        this.returnedDate = returnedDate;
    }

    @Override
    public String toString() {
        return "BookLoan{" +
                "loanId='" + loanId + '\'' +
                ", student=" +
                (student != null ? student.getDisplayName() : "None") +
                ", book=" +
                (book != null ? book.getTitle() : "None") +
                ", borrowedDate=" + borrowedDate +
                ", dueDate=" + dueDate +
                ", returnedDate=" + returnedDate +
                '}';
    }
}
