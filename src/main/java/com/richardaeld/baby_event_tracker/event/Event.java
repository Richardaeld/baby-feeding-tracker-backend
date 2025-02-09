package com.richardaeld.baby_event_tracker.event;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record Event(
        @NotEmpty
        @Positive
        Integer baby_id,
        @NotEmpty
        @Positive
        Integer event_id,
        @NotEmpty
        EventType event_type,
        LocalDateTime start_on,
        LocalDateTime end_on
) {
        public Event {
                if (!end_on.isAfter(start_on)) {
                        throw new IllegalArgumentException("End time must be after start time");
                }
        }
}
