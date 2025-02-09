package com.richardaeld.baby_event_tracker.event;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record Event(
        Integer event_id,
        @NotNull
        @Positive
        Integer baby_id,
        @NotNull
        EventType event_type,
        @NotNull
        LocalDateTime start_on,
        LocalDateTime end_on
) {
        public Event {
                if (!end_on.isAfter(start_on)) {
                        throw new IllegalArgumentException("End time must be after start time");
                }
        }
}
