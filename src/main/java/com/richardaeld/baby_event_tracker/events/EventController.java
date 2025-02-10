package com.richardaeld.baby_event_tracker.events;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/events")
public class EventController {

    private final EventRepository eventRepository;

    public EventController(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    // ===========================
    // SELECT
    //============================
    @GetMapping("")
    List<Event> findAll() {
        return eventRepository.findAll();
    }

    @GetMapping("/feeding")
    List<Feeding> findAllFeeding() { return eventRepository.findAllFeeding(); }

    @GetMapping("/tummy_time")
    List<TummyTime> findAllTummyTime() { return eventRepository.findAllTummyTime(); };

    // ===========================
    // SELECT 1
    //============================
    @GetMapping("/{event_id}")
    Event findById(@PathVariable Integer event_id) {
        Optional<Event> event = eventRepository.findById(event_id);
        if (event.isEmpty()) {
            throw new EventNotFoundException();
        }
        return event.get();
    }

    // ===========================
    // POST
    //============================
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    void create (@Valid @RequestBody Event event) {
        eventRepository.create(event);
    }

    @PostMapping("/feeding")
    @ResponseStatus(HttpStatus.CREATED)
    void create (@Valid @RequestBody Feeding feeding) { eventRepository.create(feeding); }

    @PostMapping("/tummy_time")
    @ResponseStatus(HttpStatus.CREATED)
    void createTummyTime(@Valid @RequestBody TummyTime tummyTime) {eventRepository.create(tummyTime);}



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
