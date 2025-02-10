package com.richardaeld.baby_event_tracker.events;

import java.time.LocalDateTime;

public record TummyTime (
    Integer event_id,
    Integer baby_id,
    EventType event_type,
    LocalDateTime start_on,
    LocalDateTime end_on,
    Integer note_id,
    String note
) {
    public TummyTime {
        if (!end_on.isAfter(start_on)) {
            throw new IllegalArgumentException("End time must be after start time");
        }
    }
}
