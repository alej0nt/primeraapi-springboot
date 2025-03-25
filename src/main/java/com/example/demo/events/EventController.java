package com.example.demo.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/events")
public class EventController {
    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public ResponseEntity<List<Event>> getEvents(@RequestParam(required = false) String date) {
        Date filterDate = date != null ? new Date(date) : null;
        List<Event> events = eventService.getEvents(filterDate);
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @PostMapping("/{id}/register")
    public ResponseEntity<Registration> register(
            @PathVariable String id,
            @RequestBody Map<String, String> body,
            @RequestHeader("X-Tracking-Id") String trackingId) {

        String name = body.get("name");
        String email = body.get("email");

        Registration registration = eventService.register(id, name, email, trackingId);
        return new ResponseEntity<>(registration, HttpStatus.CREATED);
    }

    @DeleteMapping("/{eventId}/registration/{registrationId}")
    public ResponseEntity<Void> cancelRegistration(
            @PathVariable String eventId,
            @PathVariable String registrationId,
            @RequestHeader("Authorization") String token) {

        eventService.cancelRegistration(eventId, registrationId, token);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}