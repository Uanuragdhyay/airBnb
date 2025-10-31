package com.anurag.projects.airBnbApp.Services;

import com.anurag.projects.airBnbApp.DTOs.HotelDto;

public interface HotelService {
    HotelDto createNewHotel(HotelDto hotelDto);
    HotelDto getHotelById(Long id);
    HotelDto updateHotelById(Long id,HotelDto hotelDto);
    void deleteHotelById(Long id);
    void activateHotel(Long hotelId);
}
