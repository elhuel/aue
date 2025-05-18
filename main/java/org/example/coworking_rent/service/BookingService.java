package org.example.coworking_rent.service;

import org.example.coworking_rent.client.AuthClient;
import org.example.coworking_rent.dto.AuthUserResponse;
import org.example.coworking_rent.dto.BookingRequest;
import org.example.coworking_rent.dto.BookingResponse;
import org.example.coworking_rent.exception.BookingConflictException;
import org.example.coworking_rent.exception.BookingNotFoundException;
import org.example.coworking_rent.exception.PlaceNotFoundException;
import org.example.coworking_rent.model.Booking;
import org.example.coworking_rent.model.Place;
import org.example.coworking_rent.repository.BookingRepository;
import org.example.coworking_rent.repository.PlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
@RequiredArgsConstructor
public class BookingService {
    
    private final BookingRepository bookingRepository;
    private final PlaceRepository placeRepository;
    private final AuthClient authClient;

    @Transactional
    public BookingResponse createBooking(BookingRequest request, String token) {
        AuthUserResponse authResponse = authClient.validateToken(token);
        Long userId = authResponse.getUserId();
        
        Place place = placeRepository.findByNumber(request.placeNumber())
                .orElseThrow(() -> new PlaceNotFoundException(request.placeNumber()));
        
    
        Instant startTime = request.dateTime();
        Instant endTime = startTime.plus(1, ChronoUnit.HOURS);

        if (bookingRepository.existsConflict(place.getId(), startTime, endTime)) {
            throw new BookingConflictException("Time slot already booked");
        }

        Booking booking = new Booking();
        booking.setUserId(userId);
        booking.setPlace(place);
        booking.setStartTime(startTime);
        booking.setEndTime(endTime);

        return BookingResponse.fromEntity(bookingRepository.save(booking));
    }

    @Transactional
    public void cancelBooking(Long id, String token) {
        Long userId = authClient.validateToken(token).getUserId();
        Booking booking = bookingRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() -> new BookingNotFoundException(id));
        
        bookingRepository.delete(booking);
    }
}