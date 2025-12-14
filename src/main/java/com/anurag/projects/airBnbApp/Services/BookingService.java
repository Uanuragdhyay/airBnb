package com.anurag.projects.airBnbApp.Services;

import com.anurag.projects.airBnbApp.DTOs.BookingDto;
import com.anurag.projects.airBnbApp.DTOs.BookingRequest;
import com.anurag.projects.airBnbApp.DTOs.GuestDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface BookingService {
     BookingDto initializeBooking(BookingRequest bookingRequest);

     BookingDto addGuests(Long bookingId, List<GuestDto> guestDtoList);
}
