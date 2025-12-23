package com.anurag.projects.airBnbApp.DTOs;

import com.anurag.projects.airBnbApp.Entities.Hotel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class HotelPriceDto {
    private Hotel hotel;
    private Double price;
}
