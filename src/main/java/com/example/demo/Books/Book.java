package com.example.demo.Books;

import java.util.UUID;
public class Book {

    private String titulo;
    private String isbn;
    private String autor;
    private int anioPublicacion;

    public Book(String titulo, String autor, int anioPublicacion) {
        this.titulo = titulo;
        this.isbn = UUID.randomUUID().toString();
        this.autor = autor;
        this.anioPublicacion = anioPublicacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }
    public int getAnioPublicacion() {
        return anioPublicacion;
    }
    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }
}
