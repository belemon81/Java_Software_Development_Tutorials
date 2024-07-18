package a1_2001040208;

import common.DateUtils;
import common.PatronType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// Responsible for managing library book transactions
public class LibraryManager {
    private final List<Book> books;
    private final List<LibraryTransaction> transactions;

    public LibraryManager() {
        this.books = new ArrayList<>();
        this.transactions = new ArrayList<>();
    }

    public List<LibraryTransaction> getTransactions() {
        return transactions;
    }

    // TODO: addBook()
    //      Add a book to the library
    public void addBook(Book book) {
        books.add(book);
    }

    // TODO: List<LibraryTransaction> getCheckedOutBooks(Patron patron)
    //      Retrieve a list of checked-out books for a specific patron.
    public List<LibraryTransaction> getCheckedOutBooks(Patron patron) {
        List<LibraryTransaction> checkedOutTransactions = new ArrayList<>();
        for (LibraryTransaction transaction : transactions) {
            if (transaction.getPatron().equals(patron)) {
                // late/important TODO: add condition to be checked-out books (return date is available)
                if (transaction.getReturnDate() != null) {
                    checkedOutTransactions.add(transaction);
                }
            }
        }
        return checkedOutTransactions;
    }

    // TODO: checkoutBook(Patron patron, Book book, Date checkoutDate, Date dueDate)
    //      Allows a patron to check out a book.
    //          Check if the patron has exceeded their checkout limit based on their patron type.
    //          If not,
    //              Create a new LibraryTransaction
    //              Add it to the list of transactions
    //              Update the number of available copies for the book
    public void checkoutBook(Patron patron, Book book, Date checkoutDate, Date dueDate) {
        List<LibraryTransaction> checkOutedTransaction = getCheckedOutBooks(patron);
        // If the patron has not exceeded their checkout limit
        if ((patron.getPatronType() == PatronType.REGULAR && checkOutedTransaction.size() < 3)
                || (patron.getPatronType() == PatronType.PREMIUM && checkOutedTransaction.size() < 5)) {
            // If number of copies > 0
            if (book.getNumCopiesAvailable() > 0) {
                // Create and add a new LibraryTransaction
                transactions.add(new LibraryTransaction(patron, book, checkoutDate, dueDate, null));
                // Update the number of copies
                book.setNumCopiesAvailable(book.getNumCopiesAvailable() - 1);
            }
        }
    }

    // TODO: returnBook(LibraryTransaction transaction, Date returnDate)
    //      Allow a patron to return a book, calculates fines (if any), and updates the number of available copies.
    //          Set the return date in the transaction
    //          Calculate fines
    //          Update the number of available copies for the book
    //          Print a success message
    public void returnBook(LibraryTransaction transaction, Date returnDate) {
        transaction.setReturnDate(returnDate);
        transaction.setFine(transaction.calculateFine());
        transaction.getBook().setNumCopiesAvailable(transaction.getBook().getNumCopiesAvailable() + 1);
        System.out.println("Patron #" + transaction.getPatron().getPatronID()
                + " has successfully returned the book #" + transaction.getBook().getISBN()
                + " on " + LibraryTransaction.getFormattedDate(returnDate) + ".");
    }

    // TODO: List<LibraryTransaction> getOverdueBooks()
    //      Returns a list of library transactions representing
    //          1. overdue books
    //          2. that are not returned yet
    public List<LibraryTransaction> getOverdueBooks() {
        List<LibraryTransaction> overdueTransactions = new ArrayList<>();
        for (LibraryTransaction transaction : transactions) {
            if ((new DateUtils().getCurrentDate()).compareTo(transaction.getDueDate()) > 0
                    && transaction.getReturnDate() == null) {
                overdueTransactions.add(transaction);
            }
        }
        return overdueTransactions;
    }

    // TODO: sort()
    //      Sort the list of transactions by patron ID.
    public void sort() {
        transactions.sort((t1, t2) -> {
            String patronID1 = t1.getPatron().getPatronID();
            String patronID2 = t2.getPatron().getPatronID();
            return patronID1.compareTo(patronID2);
        });
    }

}
