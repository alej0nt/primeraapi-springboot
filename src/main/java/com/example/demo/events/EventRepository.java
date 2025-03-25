package com.example.demo.events;

import java.util.*;
import java.util.stream.Collectors;
import org.springframework.stereotype.Repository;

@Repository
public class EventRepository {
    private final Map<String, Event> events = new HashMap<>();
    private final Map<String, Registration> registrations = new HashMap<>();
    private final Map<String, String> authTokens = new HashMap<>();

    public Event saveEvent(Event event) {
        events.put(event.getId(), event);
        return event;
    }

    public void saveToken(String token) {
        authTokens.put("token-" + token, token);
    }

    public List<Event> findAllEvents() {
        return new ArrayList<>(events.values());
    }

    public List<Event> findEventsByDate(Date date) {
        return events.values().stream()
                .filter(e -> date == null || e.getDate().equals(date))
                .collect(Collectors.toList());
    }

    public Registration saveRegistration(Registration registration) {
        registrations.put(registration.getId(), registration);
        return registration;
    }

    public void deleteRegistration(String id) {
        registrations.remove(id);
    }

    public List<Registration> findRegistrationsByEvent(String eventId) {
        return registrations.values().stream()
                .filter(r -> r.getEventId().equals(eventId))
                .collect(Collectors.toList());
    }

    public boolean isValidToken(String token) {
        return authTokens.containsKey(token);
    }

}