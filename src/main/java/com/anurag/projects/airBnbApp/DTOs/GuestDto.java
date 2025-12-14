package com.anurag.projects.airBnbApp.DTOs;

import com.anurag.projects.airBnbApp.Entities.User;
import com.anurag.projects.airBnbApp.Enums.Gender;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class GuestDto {

    private Long id;
    private User user;
    private String name;
    private Gender gender;
    private Integer age;
}
