package org.example.coworking_rent.dto;

import jakarta.validation.constraints.*;
import java.time.Instant;

public record BookingRequest(
    @NotNull
    @Min(1) @Max(20)
    Integer placeNumber,
    
    @FutureOrPresent
    Instant dateTime
) {}