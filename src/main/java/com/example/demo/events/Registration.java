package com.example.demo.events;

import java.util.Date;

public class Registration {
    private String id;
    private String eventId;
    private String name;
    private String email;
    private Date registrationDate;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getEventId() { return eventId; }
    public void setEventId(String eventId) { this.eventId = eventId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public Date getRegistrationDate() { return registrationDate; }
    public void setRegistrationDate(Date date) { this.registrationDate = date; }
}