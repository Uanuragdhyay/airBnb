package com.anurag.projects.airBnbApp.Controllers;

import com.anurag.projects.airBnbApp.DTOs.BookingDto;
import com.anurag.projects.airBnbApp.DTOs.BookingRequest;
import com.anurag.projects.airBnbApp.DTOs.GuestDto;
import com.anurag.projects.airBnbApp.Services.BookingService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
@RequiredArgsConstructor
public class HotelBookingController {
    private final BookingService bookingService;

    @PostMapping("/init")
    public ResponseEntity<BookingDto> initializeBooking(@RequestBody BookingRequest bookingRequest){
        return ResponseEntity.ok(bookingService.initializeBooking(bookingRequest));
    }

    @PostMapping("/{bookingId}/addGuests")
    public ResponseEntity<BookingDto> addGuests(@RequestBody List<GuestDto> guestDtoList, @PathVariable Long bookingId){
        return ResponseEntity.ok(bookingService.addGuests(bookingId, guestDtoList));
    }

}
