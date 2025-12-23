package com.anurag.projects.airBnbApp.Services;

import com.anurag.projects.airBnbApp.DTOs.HotelDto;
import com.anurag.projects.airBnbApp.DTOs.HotelPriceDto;
import com.anurag.projects.airBnbApp.DTOs.HotelSearchRequestDto;
import com.anurag.projects.airBnbApp.Entities.Room;
import org.springframework.data.domain.Page;

public interface InventoryService {
    void initializeRoomForAYear(Room room);

    void deleteAllInventories(Room room);

    Page<HotelPriceDto> searchHotels(HotelSearchRequestDto hotelSearchRequest);
}
