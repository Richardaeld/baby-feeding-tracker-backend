package com.richardaeld.baby_event_tracker.event;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventRepository eventRepository;

    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @GetMapping("")
    List<Event> findAll() {
        return eventRepository.findAll();
    }

    @GetMapping("/{event_id}")
    Event findById(@PathVariable Integer event_id) {
        Optional<Event> event = eventRepository.findById(event_id);
        if (event.isEmpty()) {
            throw new EventNotFoundException();
        }
        return event.get();
    }

    //POST
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    void create (@RequestBody Event event) {
        eventRepository.create(event);
    }

    //PUT
    @PutMapping("/{event_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void update(@RequestBody Event event, @PathVariable Integer event_id) {
        eventRepository.update(event,event_id);
    }

    //DELETE
    @DeleteMapping("/{event_id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable Integer event_id) {
        eventRepository.delete(event_id);
    }

}
