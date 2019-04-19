package com.hertz.uber.server.connector;

import com.hertz.uber.server.model.dao.UberRideDAO;
import com.hertz.uber.server.model.dto.UberRideDTO;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.nio.file.Paths;

import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class CsvConnectorTest {

    @InjectMocks
    CsvConnector csvConnectorMock;

    @Mock
    UberRideDAO daoMock;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void testReadCsvFile() throws Exception {
        //Given
        UberRideDTO dtoMock = Mockito.mock(UberRideDTO.class);
        when(daoMock.saveUberRide(Mockito.any())).thenReturn(dtoMock);
        String currDir = Paths.get("").toAbsolutePath().toString();

        //When/Then
        csvConnectorMock.readCsvFile(currDir + "/src/test/resources/data/My Uber Drives - 2016.csv");
    }
}