package com.example.demo;

import java.util.UUID;
public class Producto {
    private String id;
    private String nombre;
    private String categoria;
    private int stock;

    public Producto(String nombre, String categoria, int stock) {
        this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.categoria = categoria;
        this.stock = stock;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
