package com.richardaeld.baby_event_tracker.events;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record Event(
//        Parent
        Integer event_id,
        @NotNull
        @Positive
        Integer baby_id,
        @NotNull
        EventType event_type,
        @NotNull
        LocalDateTime start_on,
        LocalDateTime end_on,
//        Children
        Integer note_id,
        String note,

        Integer temperature,
        Integer amount,
        String dosage,
        String name,
        Integer height,
        Integer weight,
        String color

) {
        public Event {
                if (!end_on.isAfter(start_on)) {
                        throw new IllegalArgumentException("End time must be after start time");
                }
        }
}
