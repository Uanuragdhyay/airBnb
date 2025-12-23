package com.anurag.projects.airBnbApp.Controllers;

import com.anurag.projects.airBnbApp.DTOs.HotelDto;
import com.anurag.projects.airBnbApp.DTOs.HotelInfoDto;
import com.anurag.projects.airBnbApp.DTOs.HotelPriceDto;
import com.anurag.projects.airBnbApp.DTOs.HotelSearchRequestDto;
import com.anurag.projects.airBnbApp.Repositories.InventoryRepository;
import com.anurag.projects.airBnbApp.Services.HotelService;
import com.anurag.projects.airBnbApp.Services.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
@RequiredArgsConstructor
public class HotelBrowseController {

    private final InventoryService inventoryService;
    private final HotelService hotelService;

    @GetMapping("/search")
    public ResponseEntity<Page<HotelPriceDto>> searchHotels(@RequestBody HotelSearchRequestDto hotelSearchRequest){
        var page = inventoryService.searchHotels(hotelSearchRequest);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{hotelId}/info")
    public ResponseEntity<HotelInfoDto> getHotelInfo(@PathVariable Long hotelId){
        return  ResponseEntity.ok(hotelService.getHotelInfoByHotelId(hotelId));
    }

}
