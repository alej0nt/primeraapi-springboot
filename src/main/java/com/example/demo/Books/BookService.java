package com.example.demo.Books;

import java.util.List;

import com.example.demo.Products.ProductoRepository;
import com.example.demo.Users.Usuario;
import com.example.demo.Users.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public BookService(BookRepository bookRepository, UsuarioRepository usuarioRepository, ProductoRepository productoRepository) {
        this.bookRepository = bookRepository;
        this.usuarioRepository = usuarioRepository;
        initData();
    }

    private void initData(){
        bookRepository.save(new Book("Cien años de soledad", "Gabriel Garcia Marquez", 1990));
        bookRepository.save(new Book("La divina comedia", "Dante Aligheri", 1660));

    }
    public Book save (String userRole, String authToken, Book book) {
        Usuario usuario = usuarioRepository.findByAuthToken(authToken);
        if (usuario != null && usuario.getRole().equals(userRole)) {
            return  bookRepository.save(book);
        }
        return null;
    }

    public Book findByIsbn(String isbn) {
        return bookRepository.findByIsbn(isbn);
    }
    public List<Book> findAllByAuthToken(String token) {
        Usuario usuario = usuarioRepository.findByAuthToken(token);
        if  (usuario != null) {
            return bookRepository.findAll();
        }
        return null;
    }

    public void delete(String isbn) {
        bookRepository.deleteByIsbn(isbn);
    }

    public Book update(Book book) {
        return bookRepository.update(book);
    }

    public List<Book> findAllByFilters(String titulo, String autor, int anioDepublicacion) {
        return bookRepository.findByFilter(titulo, autor, anioDepublicacion);
    }
}
