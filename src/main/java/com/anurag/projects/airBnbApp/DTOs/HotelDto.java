package com.anurag.projects.airBnbApp.DTOs;

import com.anurag.projects.airBnbApp.Entities.HotelContactInfo;
import lombok.Data;
@Data
public class HotelDto {
    private Long id;
    private String name;
    private String city;
    private String[] photos;
    private String[] amenities;
    private HotelContactInfo hotelContactInfo;
    private boolean active;
}
