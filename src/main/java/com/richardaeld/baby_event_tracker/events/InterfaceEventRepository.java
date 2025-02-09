package com.richardaeld.baby_event_tracker.events;

import java.util.List;
import java.util.Optional;

public interface InterfaceEventRepository {
    List<Event> findAll();

    Optional<Event> findById(Integer event_id);

    void create(Event event);

    void update(Event event, Integer event_id);

    void delete(Integer event_id);

    int count();


}
