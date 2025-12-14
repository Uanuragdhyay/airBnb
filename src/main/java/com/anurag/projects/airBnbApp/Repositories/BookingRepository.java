package com.anurag.projects.airBnbApp.Repositories;

import com.anurag.projects.airBnbApp.Entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
