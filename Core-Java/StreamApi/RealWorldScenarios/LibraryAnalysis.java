package Streams.RealWorldScenarios;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Book {
    private String title;
    private String author;
    private int year;
    private String genre;

    public Book(String title, String author, int year, String genre) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.genre = genre;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public int getYear() {
        return year;
    }

    public String getGenre() {
        return genre;
    }
}

public class LibraryAnalysis {
    public static void main(String[] args) {
        List<Book> library = Arrays.asList(
            new Book("The Hobbit", "J.R.R. Tolkien", 1937, "Fantasy"),
            new Book("The Fellowship of the Ring", "J.R.R. Tolkien", 1954, "Fantasy"),
            new Book("The Two Towers", "J.R.R. Tolkien", 1954, "Fantasy"),
            new Book("1984", "George Orwell", 1949, "Dystopian"),
            new Book("Animal Farm", "George Orwell", 1945, "Dystopian"),
            new Book("Brave New World", "Aldous Huxley", 1932, "Dystopian"),
            new Book("Clean Code", "Robert C. Martin", 2008, "Technical"),
            new Book("Clean Architecture", "Robert C. Martin", 2017, "Technical"),
            new Book("Refactoring", "Martin Fowler", 1999, "Technical")
        );

        // 1. Most published genre
        Map.Entry<String, Long> mostPublishedGenre = library.stream()
                .collect(Collectors.groupingBy(Book::getGenre, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .orElseThrow();

        // 2. Author with most books
        Map.Entry<String, Long> authorWithMostBooks = library.stream()
                .collect(Collectors.groupingBy(Book::getAuthor, Collectors.counting()))
                .entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .orElseThrow();

        System.out.println("Most Published Genre: " + mostPublishedGenre.getKey() + " (" + mostPublishedGenre.getValue() + " books)");
        System.out.println("Author with Most Books: " + authorWithMostBooks.getKey() + " (" + authorWithMostBooks.getValue() + " books)");
    }
}
