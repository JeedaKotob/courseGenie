package com.course_genie.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;


    @Autowired
    public BookController(BookService bookService, BookMapper bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    // Create a new book
    @PostMapping
    public ResponseEntity<BookDTO> createBook(@RequestBody BookDTO bookDTO) {
        BookDTO createdBook = bookService.createBook(bookDTO);
        return ResponseEntity.ok(createdBook);
    }

    // Get all books
    @GetMapping
    public ResponseEntity<List<BookDTO>> getAllBooks() {
        List<BookDTO> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    // Get a book by ID
    @GetMapping("/{id}")
    public ResponseEntity<BookDTO> getBookById(@PathVariable long id) {
        BookDTO book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }

    // Update a book
    @PutMapping("/{id}")
    public ResponseEntity<BookDTO> updateBook(@PathVariable long id, @RequestBody BookDTO bookDTO) {
        BookDTO updatedBook = bookService.updateBook(id, bookDTO);
        return ResponseEntity.ok(updatedBook);
    }

    // Delete a book
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Import a book by ISBN and associate it with a course.
     * Example: POST /api/books/import?isbn=9780134685991&courseId=123
     */
    @PostMapping("/import")
    public ResponseEntity<BookDTO> importBook(@RequestParam String isbn) {
        BookDTO bookDTO = bookService.importBookByIsbn(isbn);
        return ResponseEntity.ok(bookDTO);
    }
    @PutMapping("/{bookId}/updateMapping")
    public ResponseEntity<BookDTO> updateMapping(
            @PathVariable Long bookId,
            @RequestParam Long sectionId,
            @RequestParam boolean mapped,
            @RequestParam boolean requiredReading,
            @RequestParam boolean suggestedReading) {
        Book updatedBook = bookService.updateMapping(bookId, sectionId, mapped, requiredReading, suggestedReading);
        return ResponseEntity.ok(bookMapper.toDTO(updatedBook));
    }
}