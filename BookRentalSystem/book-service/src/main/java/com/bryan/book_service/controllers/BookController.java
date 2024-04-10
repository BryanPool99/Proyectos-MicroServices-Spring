package com.bryan.book_service.controllers;

import com.bryan.book_service.model.dto.request.BookRequest;
import com.bryan.book_service.model.dto.request.GenderRequest;
import com.bryan.book_service.model.dto.response.BookResponse;
import com.bryan.book_service.model.dto.response.GenderResponse;
import com.bryan.book_service.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;
    //listar todos los libros
    @GetMapping("/findAll")
    public ResponseEntity<?> getAllBooks() {
        List<BookResponse> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }
    //buscar por titulo
    @GetMapping("/search/title")
    public ResponseEntity<List<BookResponse>> findBooksByTitle(@RequestParam String title) {
        List<BookResponse> books = bookService.findBooksByTitle(title);
        return ResponseEntity.ok(books);
    }
    //buscar por author
    @GetMapping("/search/author")
    public ResponseEntity<List<BookResponse>> findBooksByAuthor(@RequestParam String author) {
        List<BookResponse> books = bookService.findBooksByAuthor(author);
        return ResponseEntity.ok(books);
    }
    //buscar por genero
    @GetMapping("/search/gender")
    public ResponseEntity<List<BookResponse>> findBooksByGender(@RequestParam Long genderId) {
        List<BookResponse> books = bookService.findBooksByGenderId(genderId);
        return ResponseEntity.ok(books);
    }

    //agregar nuevo libro
    @PostMapping("/save")
    public ResponseEntity  addBook(@RequestBody BookRequest bookRequest) {
        bookService.addBook(bookRequest);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    //actualizar un libro por Id
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Long id, @RequestBody BookRequest bookRequest){
        bookService.updateBook(id,bookRequest);
        return ResponseEntity.ok("Actualizacion exitosa");
    }
    //eliminar un libro por id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBookById(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.ok().build();
    }
}
