package com.anurag.projects.airBnbApp.Services;

import com.anurag.projects.airBnbApp.DTOs.RoomDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomService {
    RoomDto createNewRoom(RoomDto roomDto, Long hotelId);
    List<RoomDto> getAllRoomsInHotel(Long hotelId);
    RoomDto getRoomById(Long roomId);
    void deleteRoomById(Long roomId);

}
