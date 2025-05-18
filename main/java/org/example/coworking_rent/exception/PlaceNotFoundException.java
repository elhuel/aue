package org.example.coworking_rent.exception;

public class PlaceNotFoundException extends RuntimeException {
    
    public PlaceNotFoundException(Integer placeNumber) {
        super("Место с номером " + placeNumber + " не найдено");
    }

    public PlaceNotFoundException(String message) {
        super(message);
    }
}