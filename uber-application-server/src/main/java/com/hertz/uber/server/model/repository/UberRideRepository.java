package com.hertz.uber.server.model.repository;

import com.hertz.uber.server.model.entity.UberRide;
import org.springframework.data.repository.CrudRepository;

public interface UberRideRepository extends CrudRepository<UberRide, Long> {

    public UberRide findByUuid(String uuid);

    public void deleteByUuid(String uuid);

}
