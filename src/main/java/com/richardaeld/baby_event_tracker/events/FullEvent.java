package com.richardaeld.baby_event_tracker.events;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public class FullEvent {

        public LocalDateTime birthday;
        public String first_name;
        public String last_name;
        public String gender;

        public Integer event_id;
        public Integer baby_id;
        public EventType event_type;
        public LocalDateTime start_on;
        public LocalDateTime end_on;
        public Float amount;
        public FeedingType feeding_type;
        public Integer note_id;
        public String note;
        public String color;
        public Float height;
        public Float weight;
        public String name;
        public String dosage;

}