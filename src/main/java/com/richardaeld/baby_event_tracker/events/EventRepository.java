package com.richardaeld.baby_event_tracker.events;

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

    // ===========================
    // Event (Parent)
    //============================
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
        var created = jdbcClient.sql("""
INSERT INTO EVENT 
(baby_id, event_type, start_on, end_on) VALUES 
(:baby_id, :event_type::event_type_enum, :start_on, :end_on)
""")
                .param("baby_id",    event.baby_id())
                .param("event_type", event.event_type().name())
                .param("start_on",   event.start_on())
                .param("end_on",     event.end_on())
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
    // ===========================
    // BABY
    //============================
    // ===========================
    // USERS
    //============================
    // ===========================
    // BABY_CARE_GIVER
    //============================
    // ===========================
    // ALLERGY
    //============================

    // ===========================
    // DIAPER
    //============================
    // ===========================
    // FEEDING
    //============================
    public List<Feeding> findAllFeeding() {
        return jdbcClient.sql("""
SELECT 
    e.event_id,
    e.baby_id,
    e.event_type,
    e.start_on,
    e.end_on,
    f.note_id,
    f.feeding_type,
    f.amount,
    n.note
FROM FEEDING f
INNER JOIN EVENT e
    ON f.event_id = e.event_id
LEFT JOIN NOTE n
    ON f.note_id = n.note_id
    """)
                .query(Feeding.class)
                .list();
    }
    public void create (Feeding feeding) {
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
        System.out.println(feeding);
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------");
//        Create parent event
        Event event = new Event(feeding.event_id(), feeding.baby_id(), feeding.event_type(), feeding.start_on(), feeding.end_on());
        create(event);
//        Create Note
        if (feeding.note() != null && !feeding.note().trim().isEmpty()) {
            Note note = new Note(feeding.note_id(), feeding.note());
            create(note);
        }
//        Create Feeding Event
        var created = jdbcClient.sql("""
INSERT INTO FEEDING 
(event_id, note_id, feeding_type, amount) VALUES 
(:event_id, :note_id, :feeding_type::feeding_type_enum, :amount)
        """)
                .param("event_id", feeding.event_id())
                .param("note_id",  feeding.note_id())
                .param("feeding_type", feeding.feeding_type().name())
                .param("amount", feeding.amount())
                .update();
        Assert.state(created == 1, "Failed to create Feeding Event");
    }

    // ===========================
    // GROWTH
    //============================
    // ===========================
    // MEDICATION
    //============================
    // ===========================
    // NIGHT_CHECK
    //============================
    // ===========================
    // NOTE
    //============================
    public void create (Note note) {
        var created = jdbcClient.sql("INSERT INTO NOTE (note_id, note) VALUES (:note_id, :note)")
                .param("note_id", note.note_id())
                .param("note", note.note())
                .update();
        Assert.state(created == 1, "Failed to create Note");

    }
    // ===========================
    // PUMPING
    //============================
    // ===========================
    // TEMPERATURE
    //============================




    // ===========================
    // Tummy Time
    //============================
    public List<TummyTime> findAllTummyTime() {
        return jdbcClient.sql("""
SELECT
    e.event_id,
    e.baby_id,
    e.event_type,
    e.start_on,
    e.end_on,
    tt.note_id,
    n.note
FROM TUMMY_TIME tt
INNER JOIN EVENT e
    ON tt.event_id = e.event_id
LEFT JOIN NOTE n
    ON tt.note_id = n.note_id
    """)
                .query(TummyTime.class)
                .list();
    }

    public void create (TummyTime tummyTime) {
//        Create parent event
        Event event = new Event(tummyTime.event_id(), tummyTime.baby_id(), tummyTime.event_type(), tummyTime.start_on(), tummyTime.end_on());
        create(event);
//        Create Note
        if (tummyTime.note() != null && !tummyTime.note().trim().isEmpty()) {
            Note note = new Note(tummyTime.note_id(), tummyTime.note());
            create(note);
        }
        var created = jdbcClient.sql("INSERT INTO TUMMY_TIME (event_id, note_id) VALUES (:event_id, :note_id)")
                .param("event_id", tummyTime.event_id())
                .param("note_id",  tummyTime.note_id())
                .update();
        Assert.state(created == 1, "Failed to create Tummy Time Event ");
    }







}
