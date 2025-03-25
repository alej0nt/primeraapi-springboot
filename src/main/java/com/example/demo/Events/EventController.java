package com.example.demo.Events;

import java.util.ArrayList;
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
@RequestMapping("api/eventos")
public class EventController {

    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    //GET

    @GetMapping
    public ResponseEntity<List<Event>> findAllByFilter(@RequestParam(required = false) String nombreDeEvento,
                                                       @RequestParam(required = false) String fechaDeEvento) {
        List<Event> events = eventService.findByFilters(nombreDeEvento, fechaDeEvento);
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    //POST

    @PostMapping
    public ResponseEntity<Event> save(@RequestBody Event event){
        Event myEvent = eventService.save(event);
        return new ResponseEntity<>(myEvent, HttpStatus.CREATED);
    }

    //PUT
    @PutMapping ("/{id}")
    public ResponseEntity<Event> update(@PathVariable String eventId, @RequestBody Event event){
        Event myEvent = eventService.findById(eventId);
        if (myEvent != null){
            event.setIdEvento(eventId);
            Event myUpdatedEvent = eventService.update(event);
            return new ResponseEntity<>(myUpdatedEvent, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //REMOVE

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String eventId){
        Event myEvent = eventService.findById(eventId);
        if (myEvent != null){
            eventService.remove(eventId);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //AGREGAR PARTICIPANTES

    @PostMapping ("/{id}/inscribir")
    public ResponseEntity<Void> inscribir(@PathVariable String eventId, @RequestHeader("X-Tracking-id") String event, @RequestBody Persona persona){
        Event myEvent = eventService.findById(eventId);
        if (myEvent != null){
            eventService.addAsistente(persona, eventId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //ELIMINAR PARTICIPANTES

    @DeleteMapping ("/{eventId}/inscripcion/{personaId}")
    public ResponseEntity<Void> inscripcion(@RequestHeader("Authorization") String authToken, @PathVariable String eventId, @PathVariable String personaId){
        Event myEvent = eventService.findById(eventId);
        if (myEvent != null){
            eventService.removeAsistente(authToken, eventId, personaId);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
