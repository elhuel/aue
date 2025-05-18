package org.example.coworking_rent.dto;

import java.time.Instant;

public record BookingResponse(
    Long id,
    Integer placeNumber,
    Instant startTime,
    Instant endTime,
    String status  
) {
    public static BookingResponse fromEntity(org.example.coworking_rent.model.Booking booking) {
        return new BookingResponse(
            booking.getId(),
            booking.getPlace().getNumber(),
            booking.getStartTime(),
            booking.getEndTime(),
            booking.getStatus().name()  
        );
    }
}