
-- Create 3 events from Event record
--public record Event(
--        @NotEmpty
--        @Positive
--        Integer baby_id,
--        @NotEmpty
--        @Positive
--        Integer event_id,
--        @NotEmpty
--        EventType event_type,
--        LocalDateTime start_on,
--        LocalDateTime end_on
--)

INSERT INTO EVENT (baby_id, event_id, event_type, start_on, end_on) VALUES (1, 1, 'BATH', '2021-01-01 08:00:00', '2021-01-01 08:30:00');
INSERT INTO EVENT (baby_id, event_id, event_type, start_on, end_on) VALUES (1, 2, 'DIAPER', '2021-01-01 08:30:00', '2021-01-01 08:45:00');
INSERT INTO EVENT (baby_id, event_id, event_type, start_on, end_on) VALUES (1, 3, 'FEEDING', '2021-01-01 08:45:00', '2021-01-01 09:00:00');
