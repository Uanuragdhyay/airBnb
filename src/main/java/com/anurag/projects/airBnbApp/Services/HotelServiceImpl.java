package com.anurag.projects.airBnbApp.Services;

import com.anurag.projects.airBnbApp.DTOs.HotelDto;
import com.anurag.projects.airBnbApp.Entities.Hotel;
import com.anurag.projects.airBnbApp.Exceptions.ResourceNotFoundException;
import com.anurag.projects.airBnbApp.Repositories.HotelRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
@Data
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;
    @Override
    public HotelDto createNewHotel(HotelDto hotelDto) {
        log.info("creating a new hotel with name : {}", hotelDto.getName());
        Hotel hotel = modelMapper.map(hotelDto, Hotel.class);
        hotel.setActive(false);
        hotelRepository.save(hotel);
        log.info("created a new hotel with id : {}", hotelDto.getId());
        return modelMapper.map(hotel, HotelDto.class);
    }

    @Override
    public HotelDto getHotelById(Long id) {
        log.info("getting a new hotel with id : {}", id);
        Hotel hotel = hotelRepository
                .findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Hotel not found with Id : "+id));
        return modelMapper.map(hotel,HotelDto.class);
    }

    @Override
    public HotelDto updateHotelById(Long id, HotelDto hotelDto) {
        log.info("updating hotel with id: {}", id);
        Hotel hotel = hotelRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with Id: " + id));

        // Update only top-level fields
        hotel.setName(hotelDto.getName());
        hotel.setCity(hotelDto.getCity());
        hotel.setActive(hotelDto.isActive());
        hotel.setAmenities(hotelDto.getAmenities());
        hotel.setPhotos(hotelDto.getPhotos());

        // ✅ Carefully update nested entity
        if (hotel.getHotelContactInfo() != null && hotelDto.getHotelContactInfo() != null) {
            hotel.getHotelContactInfo().setAddress(hotelDto.getHotelContactInfo().getAddress());
            hotel.getHotelContactInfo().setEmail(hotelDto.getHotelContactInfo().getEmail());
            hotel.getHotelContactInfo().setPhoneNo(hotelDto.getHotelContactInfo().getPhoneNo());
            hotel.getHotelContactInfo().setLocation(hotelDto.getHotelContactInfo().getLocation());
        }

        hotel = hotelRepository.save(hotel);
        return modelMapper.map(hotel, HotelDto.class);
    }

    @Override
    public void deleteHotelById(Long id) {
        boolean exists = hotelRepository.existsById(id);
        if(!exists)throw  new ResourceNotFoundException("Hotel not found with Id : "+id);
        hotelRepository.deleteById(id);
    }

    @Override
    public void activateHotel(Long hotelId) {
        log.info("activating hotel with id: {}", hotelId);
        Hotel hotel = hotelRepository
                .findById(hotelId)
                .orElseThrow(() -> new ResourceNotFoundException("Hotel not found with Id: " + hotelId));

        hotel.setActive(true);
        hotelRepository.save(hotel);
    }
}

