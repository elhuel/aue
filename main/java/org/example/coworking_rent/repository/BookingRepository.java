package org.example.coworking_rent.repository;

import org.example.coworking_rent.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.Instant;
import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    @Query("SELECT CASE WHEN COUNT(b) > 0 THEN true ELSE false END " +
           "FROM Booking b " +
           "WHERE b.place.id = :placeId " +
           "AND b.status = 'ACTIVE' " +
           "AND (b.startTime < :endTime AND b.endTime > :startTime)")
    boolean existsConflict(
        @Param("placeId") Long placeId,
        @Param("startTime") Instant startTime,
        @Param("endTime") Instant endTime
    );

    Optional<Booking> findByIdAndUserId(Long id, Long userId);
}