package model;

public class Book {
    private final String isbn;
    private final String title;
    private final String author;
    private final int year;
    private final String category;
    private int copies;

    public Book(String isbn, String title , String author,int year, String category,int copies){
        if(isbn==null || isbn.isEmpty()) throw new IllegalArgumentException("ISBN cannot be null or empty");
        if(title==null || title.isEmpty()) throw new IllegalArgumentException("Title cannot be null or empty");
        this.isbn=isbn;
        this.title=title;
        this.author=author;
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

    public String getAuthor() {
        return author;
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
        return "[" + isbn + "] " + title + " by " + author +
                " (" + year + ") | " + category + " | Copies: " + copies;
    }
}
