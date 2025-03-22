package com.example.demo.Users;

import java.util.UUID;
public class Usuario {
   private String id;
   private String nombre;
   private String email;
   private String role;
   private int edad;
   public Usuario() {
       this.id = UUID.randomUUID().toString();
   }
   public Usuario(String nombre, String email, String role, int edad) {
       this.id = UUID.randomUUID().toString();
       this.nombre = nombre;
       this.email = email;
       this.role = role;
       this.edad = edad;
   }
   // Getters y Setters
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
   public String getEmail() {
       return email;
   }
   public void setEmail(String email) {
       this.email = email;
   }
   public String getRole() {
       return role;
   }
   public void setRole(String role) {
       this.role = role;
   }
   public int getEdad() {
       return edad;
   }
   public void setEdad(int edad) {
       this.edad = edad;
   }
}
