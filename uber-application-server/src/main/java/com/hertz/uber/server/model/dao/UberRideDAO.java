package com.hertz.uber.server.model.dao;

import com.hertz.uber.server.model.dto.UberRideDTO;
import com.hertz.uber.server.model.entity.UberRide;
import com.hertz.uber.server.model.repository.UberRideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UberRideDAO {

    @Autowired
    UberRideRepository repository;

    public UberRideDTO saveUberRide(UberRideDTO dto) {
        UberRide entity = new UberRide();
        entity.setStartDateOfRide(dto.getStartDateOfRide());
        entity.setEndDateOfRide(dto.getEndDateOfRide());
        entity.setCategory(dto.getCategory());
        entity.setPickUpPoint(dto.getPickUpPoint());
        entity.setDestination(dto.getDestination());
        entity.setMiles(dto.getMiles());
        entity.setPurpose(dto.getPurpose());
        repository.save(entity);
        return dto;
    }

}
