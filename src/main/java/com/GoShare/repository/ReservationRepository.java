package com.GoShare.repository;

import com.GoShare.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByOwner(String owner);
    List<Reservation> findByLoaner(String loaner);
}
