package com.example.demo.Books;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {

    //Base de datos simulada
    //Nuestra clave en vez de ser el id va a ser el isbn
    private final Map<String, Book> baseDeDatos =  new HashMap<>();

    //Guardar libro
    public Book save (Book book) {
        //Se guarda el isbn del nuevo libro y se guarda el producto
        baseDeDatos.put(book.getIsbn(),  book);
        return book;
    }

    //Encontrar libro por isbn
    public Book findByIsbn(String isbn) {
        //Encontramos nuestro libro por isbn
        return baseDeDatos.get(isbn);
    }

    //Actualizar
    public Book update(Book book ) {
        //Si la base de datos contiene la clave del objeto que vamos a actualizar se hace esto
      if (baseDeDatos.containsKey(book.getIsbn())) {
          //Se actualiza
          baseDeDatos.put(book.getIsbn(), book);
          return book;
      }
      return null;
    }

    public void deleteByIsbn(String isbn) {
        //Lo eliminamos por nuestra clave (isbn)
        baseDeDatos.remove(isbn);
    }

    public List<Book> findAll() {
        return new ArrayList<>(baseDeDatos.values());
    }

    public List<Book> findByFilter(String titulo, String autor, int anioDepublicacion) {
        return baseDeDatos.values().stream()
                .filter(o -> titulo == null || o.getTitulo().contains(titulo))
                .filter(o -> autor == null || o.getAutor().contains(autor))
                .filter(o -> anioDepublicacion == 0 || o.getAnioPublicacion() == anioDepublicacion)
                .collect(Collectors.toList());
    }
}
