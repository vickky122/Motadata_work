package catalog;

import model.Book;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LibraryCatalog {
    private final Map<String, Book> booksByISBN=new HashMap<>();

    public boolean addBook(Book book){
        if(booksByISBN.containsKey(book.getIsbn())) return false;
        booksByISBN.put(book.getIsbn(),book);
        return true;
    }

    public boolean removeBook(String isbn){
        return booksByISBN.remove(isbn) != null;
    }

    public Book getBookByISBN(String isbn){
        return booksByISBN.get(isbn);
    }
    public List<Book> getAllBooks(){
        return new ArrayList<>(booksByISBN.values());
    }
    public boolean exists(String isbn){
        return booksByISBN.containsKey(isbn);
    }
}
