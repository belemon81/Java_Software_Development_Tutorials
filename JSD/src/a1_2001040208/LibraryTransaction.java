package a1_2001040208;

import common.DateUtils;

import java.text.DecimalFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class LibraryTransaction {
    // TODO: getDescription()
    //      Include return date (if available), and fine amount (if applicable).
    //      e.g. Transaction Details:
    //               Patron ID: P001
    //               Book ISBN: HL-01-1960
    //               Checkout Date: Sat, Mar 25 2023
    //               Due Date: Tue, Apr 25 2023
    //               Return Date: Sat, May 27 2023
    //               Fine Amount: $96.00
    private static final DecimalFormat DECIMAL_FORMATTER = new DecimalFormat("#.00");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("E, MMM dd yyyy"); // Tue, Apr 25 2023
    private final Patron patron;
    private final Book book;
    private final Date checkoutDate;
    private final Date dueDate;
    private Date returnDate;
    private double fine;

    public LibraryTransaction(Patron patron, Book book, Date checkoutDate, Date dueDate, Date returnDate) {
        if (!validatePatron(patron)) {
            throw new IllegalArgumentException("LibraryTransaction.init: Invalid patron!");
        } else if (!validateBook(book)) {
            throw new IllegalArgumentException("LibraryTransaction.init: Invalid book!");
        } else if (!validateCheckoutDate(checkoutDate)) {
            throw new IllegalArgumentException("LibraryTransaction.init: Invalid check out date!");
        } else if (!validateDueDate(dueDate)) {
            throw new IllegalArgumentException("LibraryTransaction.init: Invalid due date!");
        } else if (!validateDuration(checkoutDate, dueDate, returnDate)) {
            throw new IllegalArgumentException("LibraryTransaction.init: Invalid date duration!");
        } else {
            this.patron = patron;
            this.book = book;
            this.checkoutDate = checkoutDate;
            this.dueDate = dueDate;
            this.returnDate = returnDate;

            this.fine = calculateFine();
        }
    }

    public static String getFormattedDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).format(DATE_FORMATTER);
    }

    public Patron getPatron() {
        return patron;
    }

    public Book getBook() {
        return book;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    // late/important TODO: update setter
    public void setReturnDate(Date returnDate) {
        if (validateDuration(checkoutDate, dueDate, returnDate)) {
            this.returnDate = returnDate;
        } else {
            System.err.println("Invalid return date!");
        }
    }

    // late/extra TODO: update setter
    public void setFine(double fine) {
        if (fine >= 0) {
            this.fine = fine;
        } else {
            System.err.println("Invalid fine!");
        }
    }

    private boolean validatePatron(Patron patron) {
        return patron != null;
    }

    private boolean validateBook(Book book) {
        return book != null;
    }

    private boolean validateDueDate(Date dueDate) {
        return dueDate != null;
    }

    private boolean validateCheckoutDate(Date checkoutDate) {
        return checkoutDate != null;
    }

    private boolean validateDuration(Date checkoutDate, Date dueDate, Date returnDate) {
        // due date should be later than or equal to check out date
        // due date < check out date = false
        if (dueDate.compareTo(checkoutDate) < 0) return false;
        if (returnDate != null) {
            // return date should be later than or equal to check out date
            return returnDate.compareTo(checkoutDate) >= 0;
        } else {
            return true;
        }
    }

    // TODO: calculateFine()
    //      Calculate the fine amount based on the difference between the return date and due date
    //          $1.00 per day for books overdue by 1 to 7 days
    //          $2.00 per day for books overdue by 8 to 14 days
    //          $3.00 per day for books overdue by more than 14 days
    public double calculateFine() {
        // calculate if return date available
        if (returnDate != null) {
            return getFine(returnDate);
        } else {
            // else continue calculating fine to the current day
            Date currentDate = (new DateUtils()).getCurrentDate();
            return getFine(currentDate);
        }
    }

    private double getFine(Date returnDate) {
        // get days between
        long overdue = ChronoUnit.DAYS.between(dueDate.toInstant(), returnDate.toInstant());
        // calculate fine
        if (overdue <= 0) {
            return 0;
        } else if (overdue <= 7) {
            return 1D * overdue;
        } else if (overdue <= 14) {
            return 2D * overdue;
        } else {
            return 3D * overdue;
        }
    }

    public String getDescription() {
        return "Transaction Details:" +
                "\n    Patron ID: " + patron.getPatronID() +
                "\n    Book ISBN: " + book.getISBN() +
                "\n    Checkout Date: " + getFormattedDate(checkoutDate) +
                "\n    Due Date: " + getFormattedDate(dueDate) +
                (returnDate != null ? "\n    Return Date: " + getFormattedDate(returnDate) : "") +
                (fine != 0 ? "\n    Fine Amount: $" + DECIMAL_FORMATTER.format(fine) : "");
    }

//    public static void main(String[] args) {
//        Book book = new Book("Until that day", "Hannah Lisa", Genre.FICTION, 1960, 20);
//        Patron patron = new Patron("Amber Valentine", new Date(98, Calendar.OCTOBER, 22), "amber@gmail.com", "0123456789", PatronType.PREMIUM);
//        LibraryTransaction libraryTransaction = new LibraryTransaction(patron, book, new Date(2023 - 1900, Calendar.MARCH, 25), new Date(2023 - 1900, Calendar.APRIL, 25), new Date(2023 - 1900, Calendar.MAY, 27));
//        System.out.println(libraryTransaction.getDescription());
//    }

}
