package com.bryan.book_service.repositories;

import com.bryan.book_service.model.entities.Book;
import com.bryan.book_service.model.entities.Gender;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book,Long> {
    List<Book> findByTitleContainingIgnoreCase(String title);

    List<Book> findByAuthorContainingIgnoreCase(String author);

    List<Book> findByGender(Gender gender);

    List<Book> findByIsAvailable(Boolean isAvailable);
}
