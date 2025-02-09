package com.richardaeld.baby_event_tracker.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Repository
public class EventRepository {
    private static final Logger log = LoggerFactory.getLogger(EventRepository.class);
    private final JdbcClient jdbcClient;

    public EventRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public List<Event> findAll() {
        return jdbcClient.sql("SELECT * FROM EVENT")
                .query(Event.class)
                .list();
    }

    public Optional<Event> findById(Integer event_id) {
        return jdbcClient.sql("SELECT * FROM EVENT WHERE event_id = :event_id")
                .param("event_id", event_id)
                .query(Event.class)
                .optional();
    }

    public void create (Event event) {
//        System.out.println("Event created: " + event.event_id() + " " + event.baby_id() + " " + event.event_type() + " " + event.start_on() + " " + event.end_on());
        var created = jdbcClient.sql("INSERT INTO EVENT (baby_id, event_type, start_on, end_on) VALUES (:baby_id, :event_type::event_type_enum, :start_on, :end_on)")
                .param("baby_id", event.baby_id())
                .param("event_type", event.event_type().name())
                .param("start_on", event.start_on())
                .param("end_on", event.end_on())
                .update();
        Assert.state(created == 1, "Failed to create event " + event.event_type().name());
    }

    public void update(Event event, Integer event_id) {
        var updated = jdbcClient.sql("UPDATE EVENT SET baby_id = :baby_id, event_id = :event_id, event_type = :event_type::event_type_enum, start_on = :start_on, end_on = :end_on WHERE event_id = :event_id")
                .param("event_id", event.event_id())
                .param("baby_id", event.baby_id())
                .param("event_type", event.event_type().name())
                .param("start_on", event.start_on())
                .param("end_on", event.end_on())
                .update();
        Assert.state(updated == 1, "Failed to update event " + event.event_id());
    }

    public void delete(Integer event_id) {
        var deleted = jdbcClient.sql("DELETE FROM EVENT WHERE event_id = :event_id")
                .param("event_id", event_id)
                .update();
        Assert.state(deleted == 1, "Failed to delete event " + event_id);
    }



}
