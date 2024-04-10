package com.bryan.book_service.services;

import com.bryan.book_service.model.dto.request.BookRequest;
import com.bryan.book_service.model.dto.response.BookResponse;
import com.bryan.book_service.model.entities.Book;
import com.bryan.book_service.model.entities.Gender;
import com.bryan.book_service.repositories.BookRepository;
import com.bryan.book_service.repositories.GenderRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    GenderRepository genderRepository;
    //agregar nuevo libro
    public void addBook(BookRequest bookRequest){
        Gender gender = genderRepository.findById(bookRequest.getGenderId())
                .orElseThrow(() -> new EntityNotFoundException("Gender not found"));
        var newBook=Book.builder()
                .title(bookRequest.getTitle())
                .author(bookRequest.getAuthor())
                .gender(gender)
                .synopsis(bookRequest.getSynopsis())
                .isAvailable(bookRequest.getIsAvailable())
                .build();
        bookRepository.save(newBook);
        log.info("Book added: {}", newBook);
    }
    //listar libros
    public List<BookResponse> getAllBooks() {
        var books = bookRepository.findAll();

        return books.stream().map(this::mapToBookResponse).toList();
    }
    //actualizar libro
    public void updateBook(Long id, BookRequest bookRequest) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));

        Gender gender = genderRepository.findById(bookRequest.getGenderId())
                .orElseThrow(() -> new EntityNotFoundException("Gender not found"));

        existingBook.setTitle(bookRequest.getTitle());
        existingBook.setAuthor(bookRequest.getAuthor());
        existingBook.setGender(gender);
        existingBook.setSynopsis(bookRequest.getSynopsis());
        existingBook.setIsAvailable(bookRequest.getIsAvailable());

        bookRepository.save(existingBook);
        log.info("Book updated: {}", existingBook);
    }
    //eliminar libro
    public void deleteBook(Long id) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));

        bookRepository.delete(existingBook);
        log.info("Book deleted with id {}", id);
    }
    //buscar por Titulo
    public List<BookResponse> findBooksByTitle(String title) {
        List<Book> books = bookRepository.findByTitleContainingIgnoreCase(title);
        return books.stream().map(this::mapToBookResponse).toList();
    }
    //buscar por autor
    public List<BookResponse> findBooksByAuthor(String author) {
        List<Book> books = bookRepository.findByAuthorContainingIgnoreCase(author);
        return books.stream().map(this::mapToBookResponse).toList();
    }
    //buscar por genero
    public List<BookResponse> findBooksByGenderId(Long genderId) {
        Gender gender = genderRepository.findById(genderId)
                .orElseThrow(() -> new EntityNotFoundException("Gender not found"));
        List<Book> books = bookRepository.findByGender(gender);
        return books.stream().map(this::mapToBookResponse).toList();
    }
    private BookResponse mapToBookResponse(Book book) {
        String genderName = book.getGender().getName();
        return BookResponse.builder()
                .id(book.getId())
                .title(book.getTitle())
                .author(book.getAuthor())
                .genderName(genderName)
                .synopsis(book.getSynopsis())
                .isAvailable(book.getIsAvailable())
                .build();
    }
}
