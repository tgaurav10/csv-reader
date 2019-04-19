package com.hertz.uber.server.controller;

import com.hertz.uber.server.connector.CsvConnector;
import com.hertz.uber.server.model.dao.UberRideDAO;
import com.hertz.uber.server.model.dto.UberRideDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Paths;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping(value = "/uber-data")
public class UberDataController {

    @Autowired
    UberRideDAO dao;

    @Autowired
    CsvConnector connector;

    @GetMapping(path = "/job", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity get() {
        try {
            String currDir = Paths.get("").toAbsolutePath().toString();
            connector.readCsvFile(currDir + "/uber-application-server/src/main/resources/data/My Uber Drives - 2016.csv");
            List<UberRideDTO> results = dao.findAll();
            return new ResponseEntity<>(results, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error" , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/job/{uuid}", consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE}, produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity edit(@PathVariable("uuid") String uuid, @RequestBody UberRideDTO request) {
        UberRideDTO result = dao.editUberRide(uuid, request);
        return new ResponseEntity<>(result, HttpStatus.OK);

    }

    @DeleteMapping(path = "/job/{uuid}", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
    public ResponseEntity delete(@PathVariable("uuid") String uuid) {
        dao.deleteUberRide(uuid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
