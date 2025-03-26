package com.shivam.library_book_management_system.service;

import com.shivam.library_book_management_system.exception.BookNotFoundException;
import com.shivam.library_book_management_system.model.Book;
import com.shivam.library_book_management_system.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book addBook(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(String bookId) {
        return bookRepository.findByBookId(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book with ID " + bookId + " not found"));
    }

    public Book getBookByTitle(String title) {
        return bookRepository.findByTitle(title)
                .orElseThrow(() -> new BookNotFoundException("Book with title '" + title + "' not found"));
    }

    public Book updateBook(String bookId, Book updatedBook) {
        Book book = getBookById(bookId);
        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        book.setGenre(updatedBook.getGenre());
        book.setAvailabilityStatus(updatedBook.getAvailabilityStatus());
        return bookRepository.save(book);
    }

    public void deleteBook(String bookId) {
        Book book = getBookById(bookId);
        bookRepository.delete(book);
    }
}

