package com.anurag.projects.airBnbApp.DTOs;

import com.anurag.projects.airBnbApp.Entities.*;
import com.anurag.projects.airBnbApp.Enums.BookingStatus;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class BookingDto {
    private Long id;
    private Integer roomCount;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Payment payment;
    private BookingStatus bookingStatus;
    private Set<GuestDto> guests;

}



