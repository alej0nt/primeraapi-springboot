package com.example.demo.Events;

import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class EventRepository {

    private final Map<String, Event> baseDeDatos = new HashMap<>();

    //Agregar un evento
    public Event save (Event e) {
        baseDeDatos.put(e.getIdEvento(), e);
        return e;
    }

    //Eliminar un evento
    public void delete (String idEvento) {
        baseDeDatos.remove(idEvento);
    }

    //Editar un evento
    public Event update (Event e) {
        if (baseDeDatos.containsKey(e.getIdEvento())) {
            baseDeDatos.put(e.getIdEvento(), e);
            return e;
        }
        return null;
    }

    //Todos los metodos que tienen que ver con listar eventos
    public List<Event> findAll () {
        return new ArrayList<>(this.baseDeDatos.values());
    }

    public Event findById (String idEvento) {
        return baseDeDatos.get(idEvento);
    }

    public List<Event> findByFilters (String nombreEvento, String fechaDeEvento) {
        return baseDeDatos.values().stream()
                .filter(e -> nombreEvento == null || e.getNombreEvento().contains(nombreEvento))
                .filter(e -> fechaDeEvento == null || e.getFechaEvento().contains(fechaDeEvento))
                .collect(Collectors.toList());
    }

    //Agregar un asistente al evento

    public void addAsistente(String nombre, String email, String idEvento) {
        Persona persona = new Persona(nombre, email);
        baseDeDatos.get(idEvento).addAsistente(persona);
    }

    //Eliminar un asistente del evento

    public void removeAsistente(String idPersona, String idEvento) {
        int index = encontrarAsistente(idEvento, idPersona);
        if (index != -1){
            baseDeDatos.get(idEvento).removeAsistente(index);
        }
    }

    private int encontrarAsistente (String idEvento, String idPersona) {
        if (baseDeDatos.containsKey(idEvento)) {
            ArrayList<Persona> personas = baseDeDatos.get(idEvento).getAsistentes();
            for (int i = 0; i<personas.size(); i++) {
                if (personas.get(i).getId().equals(idPersona)) {
                    return i;
                }
            }
        }
        return -1;
    }
}
