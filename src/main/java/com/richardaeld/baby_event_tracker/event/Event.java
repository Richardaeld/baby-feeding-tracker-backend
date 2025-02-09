package com.richardaeld.baby_event_tracker.event;

import java.time.LocalDateTime;

public record Event(
        Integer event_id,
        Integer baby_id,
        EventType event_type,
        LocalDateTime start_on,
        LocalDateTime end_on
) {
        public Event {
                if (!end_on.isAfter(start_on)) {
                        threw new IllegalArgumentException("End time must be after start time");
                }
        }
}
