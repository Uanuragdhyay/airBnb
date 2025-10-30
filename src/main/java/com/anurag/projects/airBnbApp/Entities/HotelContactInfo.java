package com.anurag.projects.airBnbApp.Entities;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
@Embeddable
public class HotelContactInfo {
    private String address;
    private String phoneNo;
    private String email;
    private String location;
}
