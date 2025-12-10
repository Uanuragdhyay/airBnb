package com.anurag.projects.airBnbApp.Services;

import com.anurag.projects.airBnbApp.DTOs.HotelDto;
import com.anurag.projects.airBnbApp.DTOs.HotelSearchRequestDto;
import com.anurag.projects.airBnbApp.Entities.Hotel;
import com.anurag.projects.airBnbApp.Entities.Inventory;
import com.anurag.projects.airBnbApp.Entities.Room;
import com.anurag.projects.airBnbApp.Repositories.InventoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.time.temporal.ChronoUnit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryServiceImpl implements InventoryService{


    private final InventoryRepository inventoryRepository;
    @Override
    @Transactional
    public void initializeRoomForAYear(Room room) {
        LocalDate today = LocalDate.now();
        LocalDate endDate = today.plusYears(1);
        for(; !today.isAfter(endDate);  today = today.plusDays(1)){
            Inventory inventory = Inventory.builder()
                    .hotel(room.getHotel())
                    .room(room)
                    .bookedCount(0)
                    .city(room.getHotel().getCity())
                    .date(today)
                    .price(room.getBasePrice())
                    .surgeFactor(BigDecimal.ONE)
                    .totalCount(room.getTotalCount())
                    .closed(false)
                    .build();

            inventoryRepository.save(inventory);
        }
    }

    @Override
    public void deleteAllInventories(Room room) {
        LocalDate today = LocalDate.now();
        inventoryRepository.deleteByRoom(room);
    }

    @Override
    public Page<HotelDto> searchHotels(HotelSearchRequestDto hotelSearchRequest) {
        Pageable pageable = PageRequest.of(hotelSearchRequest.getPage(), hotelSearchRequest.getSize());

        long dateCount = ChronoUnit.DAYS.between(
                hotelSearchRequest.getStartDate(),
                hotelSearchRequest.getEndDate()
        )+1;
        Page<Hotel> hotelPage = inventoryRepository.findHotelsWithAvailableInventory(
                hotelSearchRequest.getCity(),
                hotelSearchRequest.getStartDate(),hotelSearchRequest.getEndDate(),
                hotelSearchRequest.getRoomCount(),dateCount, pageable
        );
        return null;
    }
}
