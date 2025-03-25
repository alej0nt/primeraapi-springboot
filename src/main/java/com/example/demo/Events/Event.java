package com.example.demo.Events;

import java.util.ArrayList;
import java.util.UUID;
public class Event {

    private String nombreEvento;
    private String fechaEvento;
    private String idEvento;
    private ArrayList<Persona> asistentes;

    public Event(String nombreEvento, String fechaEvento) {
        this.nombreEvento = nombreEvento;
        this.fechaEvento = fechaEvento;
        this.idEvento = UUID.randomUUID().toString();
        this.asistentes = new ArrayList<>();
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public String getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(String fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public String getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(String idEvento) {
        this.idEvento = idEvento;
    }

    public ArrayList<Persona> getAsistentes() {
        return asistentes;
    }

    public void setAsistentes(ArrayList<Persona> asistentes) {
        this.asistentes = asistentes;
    }

    //Add asistente
    public void addAsistente(Persona asistente){
        this.asistentes.add(asistente);
    }
    public void removeAsistente (int posicion){
        this.asistentes.remove(posicion);
    }

}
