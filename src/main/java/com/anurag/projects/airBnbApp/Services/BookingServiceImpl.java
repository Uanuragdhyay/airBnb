package com.anurag.projects.airBnbApp.Services;

import com.anurag.projects.airBnbApp.DTOs.BookingDto;
import com.anurag.projects.airBnbApp.DTOs.BookingRequest;
import com.anurag.projects.airBnbApp.DTOs.GuestDto;
import com.anurag.projects.airBnbApp.Entities.*;
import com.anurag.projects.airBnbApp.Enums.BookingStatus;
import com.anurag.projects.airBnbApp.Exceptions.ResourceNotFoundException;
import com.anurag.projects.airBnbApp.Repositories.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingServiceImpl implements  BookingService {

    private final BookingRepository bookingRepository;
    private final GuestRepository guestRepository;
    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;
    private final InventoryRepository inventoryRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public BookingDto initializeBooking(BookingRequest bookingRequest) {
        log.info("----------initialising the booking for hotel : {} , room : {}, date{}-{}  --------",bookingRequest.getHotelId(), bookingRequest.getRoomId(), bookingRequest.getCheckInDate(), bookingRequest.getCheckOutDate());
        Hotel hotel = hotelRepository.findById(bookingRequest.getHotelId())
                .orElseThrow(()-> new ResourceNotFoundException("hotel with id : "+bookingRequest.getHotelId()+" is not present")) ;
        Room room = roomRepository.findById(bookingRequest.getRoomId())
                .orElseThrow(()-> new ResourceNotFoundException("room with id : "+bookingRequest.getRoomId()+" is not present")) ;

        long daysCount = ChronoUnit.DAYS.between(bookingRequest.getCheckInDate(), bookingRequest.getCheckOutDate())+1;

        List<Inventory> inventoryList = inventoryRepository.findAndLockAvailableInventory(
                room.getId(),
                bookingRequest.getCheckInDate(),
                bookingRequest.getCheckOutDate(),
                bookingRequest.getRoomsCount()
                );
        if(inventoryList.size() != daysCount){
            throw new IllegalStateException("Room is not available for this date range");
        }

        //------- Reserving the rooms for the time slab in which one person has initiated the booking ------//
        for(Inventory inventory: inventoryList){
            inventory.setReservedCount(inventory.getReservedCount()+ bookingRequest.getRoomsCount());
        }
        inventoryRepository.saveAll(inventoryList);

        //-------- Creating a booking ---------//


        Booking booking = Booking.builder()
                .bookingStatus(BookingStatus.RESERVED)
                .hotel(hotel)
                .room(room)
                .checkInDate(bookingRequest.getCheckInDate())
                .checkOutDate(bookingRequest.getCheckOutDate())
                .user(getCurrentuser())
                .roomCount(bookingRequest.getRoomsCount())
                .amount(BigDecimal.TEN)
                .build();

        bookingRepository.save(booking);
        return modelMapper.map(booking, BookingDto.class);
    }

    @Override
    public BookingDto addGuests(Long bookingId, List<GuestDto> guestDtoList) {
        Booking booking = bookingRepository.findById(bookingId).
                orElseThrow(()-> new ResourceNotFoundException(" booking with id : "+bookingId+ " is not found") );

        if(isBookingExpired(booking)){
            throw new IllegalStateException("Booking has Already Expired");
        }

        if(booking.getBookingStatus() != BookingStatus.RESERVED){
            throw new IllegalStateException("Booking is not under reserved State, can not add guests");
        }

        for(GuestDto guestDto : guestDtoList){
            Guest guest = modelMapper.map(guestDto, Guest.class);
            guest.setUser(getCurrentuser());
            guestRepository.save(guest);
            booking.getGuests().add(guest);
        }
        booking.setBookingStatus(BookingStatus.GUESTS_ADDED);
        bookingRepository.save(booking);
        return modelMapper.map(booking, BookingDto.class);
    }

    public boolean isBookingExpired(Booking booking){
        return booking.getCreatedAt().plusMinutes(10).isBefore(LocalDateTime.now());
    }

    public User getCurrentuser(){
        User user = new User();
        user.setId(1L);
        return user;
    }
}
