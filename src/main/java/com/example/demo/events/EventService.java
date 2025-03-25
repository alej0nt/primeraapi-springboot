package com.example.demo.events;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class EventService {
    private final EventRepository eventRepository;

    @Autowired
    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
        this.initSampleData();
    }

    public void initSampleData() {
        Event event1 = new Event("Tech Conference", new Date(125, 3, 15));
        Event event2 = new Event("Workshop", new Date(125, 4, 1));
        saveEvent(event1);
        saveEvent(event2);

        String token1 = UUID.randomUUID().toString();
        this.saveToken(token1);
    }

    public Event saveEvent(Event event) {
        return this.eventRepository.saveEvent(event);
    }

    public void saveToken(String token) {
        this.eventRepository.saveToken(token);
    }

    public List<Event> getEvents(Date date) {
        if (date != null) {
            return eventRepository.findEventsByDate(date);
        }
        return eventRepository.findAllEvents();
    }

    public Registration register(String eventId, String name, String email, String trackingId) {
        Registration registration = new Registration();
        registration.setId(UUID.randomUUID().toString());
        registration.setEventId(eventId);
        registration.setName(name);
        registration.setEmail(email);
        registration.setRegistrationDate(new Date());

        return eventRepository.saveRegistration(registration);
    }

    public void cancelRegistration(String eventId, String registrationId, String token) {
        if (eventRepository.isValidToken(token)) {
            eventRepository.deleteRegistration(registrationId);
        }
    }
}