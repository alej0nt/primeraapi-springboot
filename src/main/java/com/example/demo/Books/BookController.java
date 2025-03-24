package com.example.demo.Books;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("api/libros")
public class BookController {

    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    //TODOS LOS GETS

    @GetMapping
    public ResponseEntity<List<Book>> findByFilters(@RequestParam(required = false) String titulo,  @RequestParam(required = false) String autor,
                                                    @RequestParam(defaultValue = "0") int anioDePublicacion) {
        List<Book> books = bookService.findAllByFilters(titulo, autor, anioDePublicacion);
        //Retornar status 200
        return new  ResponseEntity<>(books, HttpStatus.OK);
    }

    @GetMapping ("/auth")
    public ResponseEntity<List<Book>> findAll(@RequestHeader("Authorization") String authToken){
        List<Book> books = bookService.findAllByAuthToken(authToken);
        if (books != null){
            return new  ResponseEntity<>(books, HttpStatus.OK);
        }
        return new  ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    //POST

    @PostMapping
    public ResponseEntity<Book> save(@RequestHeader("Authorization") String authToken, @RequestHeader("X-User-Role") String userRole
            , @RequestBody Book book) {
        Book saveBook = bookService.save(userRole, authToken, book);
        if (saveBook != null){
            return new ResponseEntity<>(saveBook, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    //PUT

    @PutMapping ("/{isbn}")
    public ResponseEntity<Book> update (@RequestBody  Book book, @PathVariable String isbn){
        Book bookToUpdate = bookService.findByIsbn(isbn);
        if (bookToUpdate != null){
            Book updatedBook = bookService.update(book);
            return new ResponseEntity<>(updatedBook, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //DELETE

    @DeleteMapping ("/{isbn}")

    public ResponseEntity<Void> delete(@PathVariable String isbn){
        Book bookToDelete = bookService.findByIsbn(isbn);
        if (bookToDelete != null){
            bookService.delete(isbn);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
