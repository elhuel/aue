package org.example.coworking_rent.controller;

import org.example.coworking_rent.dto.BookingRequest;
import org.example.coworking_rent.dto.BookingResponse;
import org.example.coworking_rent.service.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
@Tag(name = "Booking Management", description = "APIs for managing coworking space bookings")
public class BookingController {
    
    private final BookingService bookingService;

    @PostMapping
    @Operation(summary = "Create new booking")
    public ResponseEntity<BookingResponse> createBooking(
            @RequestBody BookingRequest request,
            @RequestHeader("Authorization") String token) {
        return ResponseEntity.created(URI.create("/bookings"))
                .body(bookingService.createBooking(request, token));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Cancel booking")
    public ResponseEntity<Void> cancelBooking(
            @PathVariable Long id,
            @RequestHeader("Authorization") String token) {
        bookingService.cancelBooking(id, token);
        return ResponseEntity.noContent().build();
    }
}