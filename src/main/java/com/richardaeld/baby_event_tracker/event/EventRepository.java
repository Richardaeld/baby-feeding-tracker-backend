package com.richardaeld.baby_event_tracker.event;

import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class EventRepository {
    private List<Event> events = new ArrayList<>();

    List<Event> findAll() {
        return events;
    }

    Optional <Event> findById(Integer event_id) {
        return events.stream()
                .filter(event -> event.event_id() == event_id)
                .findFirst();
    }

    void create (@Valid Event event) {
        events.add(event);
    }
    void update (@ Valid Event event, Integer event_id) {
        Optional<Event> existingEvent = findById(event_id);
        if (existingEvent.isPresent()) {
            events.set(events.indexOf(existingEvent.get()), event);
        }
    }

    void delete (Integer event_id) {
        events.removeIf(event -> event.event_id().equals(event_id));
    }

    @PostConstruct
    private void init() {
        events.add(new Event(1, 1, EventType.BATH, LocalDateTime.now(), LocalDateTime.now().plus(1, ChronoUnit.HOURS)));
        events.add(new Event(2, 1, EventType.FEEDING, LocalDateTime.now(), LocalDateTime.now().plus(1, ChronoUnit.HOURS)));
        events.add(new Event(3, 1, EventType.SLEEPING, LocalDateTime.now(), LocalDateTime.now().plus(1, ChronoUnit.HOURS)));
    }
}
