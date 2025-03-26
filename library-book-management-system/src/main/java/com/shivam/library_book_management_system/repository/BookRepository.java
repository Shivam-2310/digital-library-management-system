package com.shivam.library_book_management_system.repository;

import com.shivam.library_book_management_system.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByBookId(String bookId);
    Optional<Book> findByTitle(String title);
}
