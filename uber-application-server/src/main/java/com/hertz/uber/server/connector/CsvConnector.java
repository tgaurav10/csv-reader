package com.hertz.uber.server.connector;

import com.hertz.uber.server.model.dao.UberRideDAO;
import com.hertz.uber.server.model.dto.UberRideDTO;
import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class CsvConnector {

    @Autowired
    UberRideDAO dao;

    public void readCsvFile(String filename) throws IOException {
        List<List<String>> records = new ArrayList<List<String>>();
        try (CSVReader csvReader = new CSVReader(new FileReader(filename))) {
            String[] values = null;
            while ((values = csvReader.readNext()) != null) {
                records.add(Arrays.asList(values));
            }
            for(List<String> record : records) {
                try {
                    UberRideDTO dto = new UberRideDTO(record);
                    dao.saveUberRide(dto);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
