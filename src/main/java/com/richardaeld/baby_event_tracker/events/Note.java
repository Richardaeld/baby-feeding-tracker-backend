package com.richardaeld.baby_event_tracker.events;

public record Note (
    Integer note_id,
    String note
) {
    public Note {
        if (note.isBlank()) {
            throw new IllegalArgumentException("Note cannot be blank");
        }
    }
}

