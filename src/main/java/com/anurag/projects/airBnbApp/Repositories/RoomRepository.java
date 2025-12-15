package com.anurag.projects.airBnbApp.Repositories;

import com.anurag.projects.airBnbApp.Entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    Optional<Room> findByIdAndHotelId(Long roomId, Long HotelId);
}
