package com.anurag.projects.airBnbApp.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.*;

@Data
@AllArgsConstructor
public class HotelInfoDto {
    private HotelDto hotel;
    private List<RoomDto> rooms;

}
