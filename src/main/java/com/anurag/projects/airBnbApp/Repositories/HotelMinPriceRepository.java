package com.anurag.projects.airBnbApp.Repositories;

import com.anurag.projects.airBnbApp.DTOs.HotelPriceDto;
import com.anurag.projects.airBnbApp.Entities.Hotel;
import com.anurag.projects.airBnbApp.Entities.HotelMinPrice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface HotelMinPriceRepository extends JpaRepository<HotelMinPrice , Long> {
    @Query("""
               select new com.anurag.projects.airBnbApp.DTOs.HotelPriceDto(i.hotel,AVG(i.price))
               from HotelMinPrice i
               where i.hotel.city = :city
               and i.date between :startDate AND :endDate
               and i.hotel.active = true
               group by i.hotel
         """)
    Page<HotelPriceDto>  findHotelsWithAvailableInventory(
            @Param("city") String city,
            @Param("startDate")LocalDate startDate,
            @Param("endDate")LocalDate endDate,
            @Param("roomsCount") Integer roomsCount,
            @Param("dateCount") Long dateCount,
            Pageable pageable
    );

    Optional<HotelMinPrice> findByHotelAndDate(Hotel hotel, LocalDate date);


}
