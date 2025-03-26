package com.shivam.library_book_management_system.service;

import com.shivam.library_book_management_system.exception.BookNotFoundException;
import com.shivam.library_book_management_system.model.AvailabilityStatus;
import com.shivam.library_book_management_system.model.Book;
import com.shivam.library_book_management_system.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    private Book sampleBook;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        sampleBook = new Book(1L, "B1001", "Spring Boot Essentials", "John Doe", "Technology", AvailabilityStatus.AVAILABLE);
    }

    @Test
    void addBook_ShouldReturnSavedBook() {
        when(bookRepository.save(sampleBook)).thenReturn(sampleBook);
        Book savedBook = bookService.addBook(sampleBook);

        assertNotNull(savedBook);
        assertEquals("B1001", savedBook.getBookId());
        verify(bookRepository, times(1)).save(sampleBook);
    }

    @Test
    void getAllBooks_ShouldReturnBookList() {
        when(bookRepository.findAll()).thenReturn(List.of(sampleBook));

        List<Book> books = bookService.getAllBooks();

        assertFalse(books.isEmpty());
        assertEquals(1, books.size());
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void getBookById_ExistingBook_ShouldReturnBook() {
        when(bookRepository.findByBookId("B1001")).thenReturn(Optional.of(sampleBook));

        Book foundBook = bookService.getBookById("B1001");

        assertNotNull(foundBook);
        assertEquals("Spring Boot Essentials", foundBook.getTitle());
        verify(bookRepository, times(1)).findByBookId("B1001");
    }

    @Test
    void getBookById_NonExistingBook_ShouldThrowException() {
        when(bookRepository.findByBookId("B9999")).thenReturn(Optional.empty());

        assertThrows(BookNotFoundException.class, () -> bookService.getBookById("B9999"));
        verify(bookRepository, times(1)).findByBookId("B9999");
    }

    @Test
    void updateBook_ExistingBook_ShouldUpdateAndReturnBook() {
        when(bookRepository.findByBookId("B1001")).thenReturn(Optional.of(sampleBook));
        when(bookRepository.save(any(Book.class))).thenReturn(sampleBook);

        Book updatedBook = new Book(1L, "B1001", "Updated Title", "Updated Author", "Updated Genre", AvailabilityStatus.CHECKED_OUT);
        Book result = bookService.updateBook("B1001", updatedBook);

        assertEquals("Updated Title", result.getTitle());
        assertEquals("Updated Author", result.getAuthor());
        assertEquals(AvailabilityStatus.CHECKED_OUT, result.getAvailabilityStatus());
        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    void deleteBook_ExistingBook_ShouldDeleteBook() {
        when(bookRepository.findByBookId("B1001")).thenReturn(Optional.of(sampleBook));
        doNothing().when(bookRepository).delete(sampleBook);

        bookService.deleteBook("B1001");

        verify(bookRepository, times(1)).delete(sampleBook);
    }

    @Test
    void deleteBook_NonExistingBook_ShouldThrowException() {
        when(bookRepository.findByBookId("B9999")).thenReturn(Optional.empty());

        assertThrows(BookNotFoundException.class, () -> bookService.deleteBook("B9999"));
        verify(bookRepository, times(1)).findByBookId("B9999");
    }
}
