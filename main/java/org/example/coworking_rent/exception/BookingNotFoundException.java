package org.example.coworking_rent.exception;

public class BookingNotFoundException extends RuntimeException {
    public BookingNotFoundException(Long bookingId) {
        super("Бронирование с ID " + bookingId + " не найдено");
    }
}