package org.example.coworking_rent.repository;

import org.example.coworking_rent.model.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PlaceRepository extends JpaRepository<Place, Long> {
    Optional<Place> findByNumber(Integer number);
}