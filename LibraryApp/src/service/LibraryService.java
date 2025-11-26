package service;

import model.Book;
import catalog.LibraryCatalog;

import java.util.List;

public class LibraryService {
    private final LibraryCatalog catalog;

    public LibraryService(LibraryCatalog catalog){
        this.catalog=catalog;
    }

    public boolean addNewBook(String isbn, String title, String author,int year,String category,int copies){
        if(catalog.exists(isbn)) return false;
        Book book=new Book(isbn,title,author,year,category,copies);
        return catalog.addBook(book);
    }

    public boolean removeBook(String isbn){
        return catalog.removeBook(isbn);
    }
    public Book findByISBN(String isbn){
        return catalog.getBookByISBN(isbn);
    }
    public List<Book> getAllBooks(){
        return catalog.getAllBooks();
    }
}
