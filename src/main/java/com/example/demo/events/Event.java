package com.example.demo.events;

import java.util.Date;
import java.util.UUID;

public class Event {
    private String id;
    private String name;
    private Date date;

    public Event() {
        this.id = UUID.randomUUID().toString();
    }

    public Event(String name, Date date) {
        this();
        this.name = name;
        this.date = date;
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }
}