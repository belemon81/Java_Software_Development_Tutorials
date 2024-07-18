package a1_2001040208;

import common.Genre;

import java.util.Objects;


// A book in the library management system
public class Book {
    private final String ISBN;
    private final String title;
    private final String author;
    private final Genre genre;
    private final int publicationYear;
    private int numCopiesAvailable;

    public Book(String title, String author, Genre genre, int publicationYear, int numCopiesAvailable) {
        if (!validateTitle(title)) {
            throw new IllegalArgumentException("Book.init: Invalid title!");
        } else if (!validateAuthor(author)) {
            throw new IllegalArgumentException("Book.init: Invalid author!");
        } else if (!validatePublicationYear(publicationYear)) {
            throw new IllegalArgumentException("Book.init: Invalid publication year!");
        } else if (!validateNumCopiesAvailable(numCopiesAvailable)) {
            throw new IllegalArgumentException("Book.init: Invalid number of available copies!");
        } else {
            this.title = title;
            this.author = author;
            this.publicationYear = publicationYear;
            this.numCopiesAvailable = numCopiesAvailable;

            // null means 00
            this.genre = genre;
            this.ISBN = generateISBN();
        }
    }

    public String getISBN() {
        return ISBN;
    }

    private boolean validateTitle(String title) {
        return title != null && title.length() > 0;
    }

    private boolean validateAuthor(String author) {
        // not contain less than 2 letters, for later implementation
        if (author == null || author.length() < 2) return false;
        // perhaps the author name should not contain numbers...?
        for (char c : author.toCharArray()) {
            if (Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }

    private boolean validatePublicationYear(int publicationYear) {
        return publicationYear >= 0;
    }

    private boolean validateNumCopiesAvailable(int numCopiesAvailable) {
        return numCopiesAvailable >= 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return publicationYear == book.publicationYear && numCopiesAvailable == book.numCopiesAvailable && Objects.equals(ISBN, book.ISBN) && Objects.equals(title, book.title) && Objects.equals(author, book.author) && genre == book.genre;
    }

    // TODO: generateISBN()
    //      Generate a unique ISBN for each book.
    //      Format: authorCode-genreCode-publicationYear
    //      e.g. JD-02-2023
    private String generateISBN() {
        String authorCode, genreCode;

        // author not contains " ", then take the first 2 letters
        if (!author.contains(" ")) {
            authorCode = author.substring(0, 2).toUpperCase();
        } else {
            // else take the first letters of each word (for the first 2 words)
            authorCode = ("" + author.charAt(0) + author.charAt(author.indexOf(" ") + 1)).toUpperCase();
        }

        if (genre == null) {
            genreCode = "00";
        } else if (genre.ordinal() + 1 < 10) {
            // genre.ordinal() starts from 0, index of genre starts from 1!
            genreCode = "0" + (genre.ordinal() + 1);
        } else {
            genreCode = "" + (genre.ordinal() + 1);
        }

        return authorCode + "-" + genreCode + "-" + publicationYear;
    }

    // TODO: getNumCopiesAvailable()
    //      Keep track of the number of copies available.
    public int getNumCopiesAvailable() {
        return numCopiesAvailable;
    }

    // late/extra TODO: update setter
    public void setNumCopiesAvailable(int numCopiesAvailable) {
        if (numCopiesAvailable >= 0) {
            this.numCopiesAvailable = numCopiesAvailable;
        } else {
            System.err.println("Invalid number of copies available!");
        }
    }

//    public static void main(String[] args) {
//        Book book = new Book("Hello World", "John Doe", Genre.NON_FICTION, 2023, 20);
//        System.out.println(book.ISBN);
//    }
}
