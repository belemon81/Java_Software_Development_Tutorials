package a1_2001040208;

import common.Genre;
import common.PatronType;

import java.util.Calendar;
import java.util.Date;

public class LibraryManProg {
    private static Date[] checkoutDate = new Date[]{
            new Date(2023 - 1900, Calendar.MARCH, 25),
            new Date(2023 - 1900, Calendar.MAY, 8),
            new Date(2023 - 1900, Calendar.JUNE, 1),
            new Date(2023 - 1900, Calendar.JUNE, 25),
            new Date(2023 - 1900, Calendar.AUGUST, 10)
    };

    private static Date[] dueDate = new Date[]{
            new Date(2023 - 1900, Calendar.APRIL, 25),
            new Date(2023 - 1900, Calendar.MAY, 10),
            new Date(2023 - 1900, Calendar.JUNE, 25),
            new Date(2023 - 1900, Calendar.JULY, 25),
            new Date(2023 - 1900, Calendar.SEPTEMBER, 20)
    };

    public static void main(String[] args) {
        LibraryManager libraryManager = new LibraryManager();

        // TODO: Initialize at least 10 books in the library collection.
        Book[] books = new Book[]{
                new Book("To Kill a Mockingbird", "Harper Lee", Genre.FICTION, 1960, 15),
                new Book("Sapiens: A Brief History of Humankind", "Yuval Noah Harari", Genre.NON_FICTION, 2011, 18),
                new Book("Gone Girl", "Gillian Flynn", Genre.MYSTERY, 2012, 10),
                new Book("Pride and Prejudice", "Jane Austen", Genre.ROMANCE, 1813, 14),
                new Book("Dune", "Frank Herbert", Genre.SCIENCE_FICTION, 1965, 16),
                new Book("The Girl on the Train", "Paula Hawkins", Genre.THRILLER, 2015, 11),
                new Book("Steve Jobs", "Walter Isaacson", Genre.BIOGRAPHY, 2011, 15),
                new Book("A People's History of the United States", "Howard Zinn", Genre.HISTORY, 1980, 17),
                new Book("The Power of Habit", "Charles Duhigg", Genre.SELF_HELP, 2012, 9),
                new Book("The Shining", "Stephen King", Genre.HORROR, 1977, 12),
                new Book("The Lord of the Rings", "J.R.R. Tolkien", Genre.ADVENTURE, 1954, 20),
                new Book("Harry Potter and the Philosopher's Stone", "J.K. Rowling", Genre.FANTASY, 1997, 19)
        };
        for (Book book : books) {
            libraryManager.addBook(book);
        }

        // TODO: Initialize at least 3 patrons involving both regular and premium patrons.
        Patron[] patrons = new Patron[]{
                new Patron("John Smith", new Date(90, Calendar.JULY, 15), "john@gmail.com", "9876543210", PatronType.PREMIUM),
                new Patron("Alice Johnson", new Date(92, Calendar.MARCH, 8), "alice@gmail.com", "1234567890", PatronType.REGULAR),
                new Patron("David Miller", new Date(88, Calendar.DECEMBER, 5), "david@gmail.com", "3456789012", PatronType.PREMIUM),
                new Patron("Emily Davis", new Date(94, Calendar.JANUARY, 17), "emily@gmail.com", "6789012345", PatronType.REGULAR),
                new Patron("Michael Wilson", new Date(87, Calendar.AUGUST, 30), "michael@gmail.com", "2345678901", PatronType.PREMIUM)
        };

        // TODO: Initialize and use to create 5 book transactions

        // transaction is created when patron checks out
        // so creating it directly is not available
        libraryManager.checkoutBook(patrons[4], books[6], checkoutDate[0], dueDate[0]);
        libraryManager.checkoutBook(patrons[1], books[4], checkoutDate[1], dueDate[1]);
        libraryManager.checkoutBook(patrons[2], books[1], checkoutDate[2], dueDate[2]);
        libraryManager.checkoutBook(patrons[3], books[2], checkoutDate[3], dueDate[3]);
        libraryManager.checkoutBook(patrons[0], books[8], checkoutDate[4], dueDate[4]);

        // TODO: Print currently checked-out books
        System.out.println("--------------------------LIST OF CURRENTLY CHECKED-OUT BOOKS-----------------------------------\n");

        int count = 0;
        for (LibraryTransaction libraryTransaction : libraryManager.getTransactions()) {
            if (libraryTransaction.getReturnDate() == null) {
                System.out.println(++count + ".");
                System.out.println(libraryTransaction.getDescription() + "\n");
            }
        }

        // TODO: Patron returns the book (1)
        System.out.println("---------------------------------PATRONS RETURNS THE BOOKS--------------------------------------\n");

        libraryManager.returnBook(libraryManager.getTransactions().get(0), new Date(2023 - 1900, Calendar.APRIL, 12));
        libraryManager.returnBook(libraryManager.getTransactions().get(1), new Date(2023 - 1900, Calendar.JUNE, 10));
        libraryManager.returnBook(libraryManager.getTransactions().get(2), new Date(2023 - 1900, Calendar.JULY, 1));


        // TODO: Print list of the overdue books that are not returned yet
        System.out.println("\n---------------------------------LIST OF THE OVERDUE BOOKS--------------------------------------\n");

        count = 0;
        for (LibraryTransaction libraryTransaction : libraryManager.getOverdueBooks()) {
            System.out.println(++count + ".");
            System.out.println(libraryTransaction.getDescription() + "\n");
        }

        // TODO: Patron returns the book (2)
        System.out.println("---------------------------------PATRONS RETURNS THE BOOKS--------------------------------------\n");

        libraryManager.returnBook(libraryManager.getTransactions().get(4), new Date(2023 - 1900, Calendar.SEPTEMBER, 27));

        // TODO: Sort transactions by patron ID
        System.out.println("\n---------------------------------------AFTER SORTING------------------------------------------\n");
        libraryManager.sort();

        count = 0;
        for (LibraryTransaction libraryTransaction : libraryManager.getTransactions()) {
            System.out.println(++count + ".");
            System.out.println(libraryTransaction.getDescription() + "\n");
        }
    }
}
