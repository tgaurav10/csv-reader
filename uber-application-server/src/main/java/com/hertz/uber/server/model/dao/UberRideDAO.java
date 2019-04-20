package com.hertz.uber.server.model.dao;

import com.hertz.uber.server.model.dto.UberRideDTO;
import com.hertz.uber.server.model.entity.UberRide;
import com.hertz.uber.server.model.repository.UberRideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class UberRideDAO {

    @Autowired
    UberRideRepository repository;

    public UberRideDTO saveUberRide(UberRideDTO dto) {
        UberRide entity = new UberRide();
        populateEntityFromDTO(dto, entity);
        return new UberRideDTO(repository.save(entity));
    }

    public List<UberRideDTO> findAll() {
        Iterable<UberRide> results = repository.findAll();
        List<UberRideDTO> dtos = new ArrayList<>();
        for (UberRide result : results) {
            dtos.add(new UberRideDTO(result));
        }
        return dtos;
    }

    public UberRideDTO findByUuid(String uuid) {
        UberRide ride = repository.findByUuid(uuid);
        if(ride==null) {
            return null;
        }
        return new UberRideDTO(ride);
    }

    @Transactional
    public UberRideDTO editUberRide(String uuid, UberRideDTO dto) {
        repository.deleteByUuid(uuid);
        UberRide entity = new UberRide();
        populateEntityFromDTO(dto, entity);
        return new UberRideDTO(repository.save(entity));
    }

    @Transactional
    public void deleteUberRide(String uuid) {
        repository.deleteByUuid(uuid);
    }

    private void populateEntityFromDTO(UberRideDTO dto, UberRide entity) {
        entity.setUuid(dto.getUuid());
        entity.setStartDateOfRide(dto.getStartDateOfRide());
        entity.setEndDateOfRide(dto.getEndDateOfRide());
        entity.setCategory(dto.getCategory());
        entity.setPickUpPoint(dto.getPickUpPoint());
        entity.setDestination(dto.getDestination());
        entity.setMiles(dto.getMiles());
        entity.setPurpose(dto.getPurpose());
    }

}
