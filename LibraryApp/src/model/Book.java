package model;

import java.util.List;

public class Book {
    private final String isbn;
    private final String title;
    private final List<String> authors;
    private final int year;
    private final String category;
    private int copies;

    public Book(String isbn, String title , List<String> authors,int year, String category,int copies){
        if(isbn==null || isbn.isEmpty()) throw new IllegalArgumentException("ISBN cannot be null or empty");
        if(title==null || title.isEmpty()) throw new IllegalArgumentException("Title cannot be null or empty");
        this.isbn=isbn;
        this.title=title;
        this.authors=authors;
        this.year=year;
        this.category=category;
        this.setCopies(copies);
    }


    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getAuthor() {
        return authors;
    }

    public int getYear() {
        return year;
    }

    public String getCategory() {
        return category;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    @Override
    public String toString() {
        return "[" + isbn + "] " + title + " by " + authors +
                " (" + year + ") | " + category + " | Copies: " + copies;
    }
}
