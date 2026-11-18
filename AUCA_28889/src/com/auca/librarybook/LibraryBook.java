
package com.auca.librarybook;

import com.auca.interfaces.Identifiable;
import com.auca.interfaces.Registrable;
import com.auca.exceptions.ValidationException;
import com.auca.exceptions.BookUnavailableException;

public class LibraryBook implements Identifiable, Registrable {

    private String bookId;
    private String isbn;
    private String title;
    private String author;
    private int totalCopies;
    private int availableCopies;

    public LibraryBook(String bookId, String isbn,
                       String title, String author,
                       int totalCopies, int availableCopies) {

        this.bookId = bookId;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.totalCopies = totalCopies;
        this.availableCopies = availableCopies;
    }

    @Override
    public String getId() {
        return bookId;
    }

    @Override
    public String getDisplayName() {
        return title;
    }

    @Override
    public boolean isActive() {
        return availableCopies > 0;
    }

    @Override
    public void validate() throws ValidationException {

        if (bookId == null || bookId.trim().isEmpty())
            throw new ValidationException("Book ID is required.");

        if (isbn == null || isbn.trim().isEmpty())
            throw new ValidationException("ISBN is required.");

        if (title == null || title.trim().length() < 2)
            throw new ValidationException("Invalid book title.");

        if (author == null || author.trim().length() < 2)
            throw new ValidationException("Invalid author name.");

        if (totalCopies < 0 || availableCopies < 0)
            throw new ValidationException("Book copies cannot be negative.");

        if (availableCopies > totalCopies)
            throw new ValidationException("Available copies cannot exceed total copies.");
    }

    public void borrowBook() throws BookUnavailableException {

        if (availableCopies <= 0)
            throw new BookUnavailableException("No available copies of this book.");

        availableCopies--;
    }

    public void returnBook() throws ValidationException {

        if (availableCopies >= totalCopies)
            throw new ValidationException("Cannot return book: inventory is already full.");

        availableCopies++;
    }

    @Override
    public void printDetails() {
        System.out.println(this);
    }

    @Override
    public String getRegistrationSummary() {
        return title + " | ISBN: " + isbn +
                " | Available: " + availableCopies + "/" + totalCopies;
    }

    // Getters
    public String getBookId() { return bookId; }
    public String getIsbn() { return isbn; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getTotalCopies() { return totalCopies; }
    public int getAvailableCopies() { return availableCopies; }

    // Setters
    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    @Override
    public String toString() {
        return "LibraryBook{" +
                "bookId='" + bookId + '\'' +
                ", isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", totalCopies=" + totalCopies +
                ", availableCopies=" + availableCopies +
                '}';
    }
}
