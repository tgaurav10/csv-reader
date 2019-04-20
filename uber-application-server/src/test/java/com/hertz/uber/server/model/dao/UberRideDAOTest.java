package com.hertz.uber.server.model.dao;

import com.hertz.uber.server.constants.Category;
import com.hertz.uber.server.model.dto.UberRideDTO;
import com.hertz.uber.server.model.entity.UberRide;
import com.hertz.uber.server.model.repository.UberRideRepository;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class UberRideDAOTest extends TestCase {

    private static final Date EXPECTED_DATE = new Date();
    private static final Category EXPECTED_CATEGORY = Category.BUSINESS;
    private static final String EXPECTED_STRING = "TEST";
    private static final Double EXPECTED_DOUBLE = 1.8;

    @InjectMocks
    UberRideDAO uberRideDAOMock;

    @Mock
    UberRideRepository mockRepo;

    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_SaveUberRide() {
        //Given
        UberRideDTO dto = Mockito.mock(UberRideDTO.class);
        UberRide mockResponse = new UberRide();
        mockResponse.setStartDateOfRide(EXPECTED_DATE);
        mockResponse.setEndDateOfRide(EXPECTED_DATE);
        mockResponse.setCategory(EXPECTED_CATEGORY);
        mockResponse.setPickUpPoint(EXPECTED_STRING);
        mockResponse.setDestination(EXPECTED_STRING);
        mockResponse.setMiles(EXPECTED_DOUBLE);
        mockResponse.setPurpose(EXPECTED_STRING);
        when(mockRepo.save(Mockito.any())).thenReturn(mockResponse);

        //When
        UberRideDTO testDTO = uberRideDAOMock.saveUberRide(dto);

        //Then
        assertEquals(EXPECTED_DATE, testDTO.getStartDateOfRide());
        assertEquals(EXPECTED_DATE, testDTO.getEndDateOfRide());
        assertEquals(EXPECTED_CATEGORY, testDTO.getCategory());
        assertEquals(EXPECTED_STRING, testDTO.getPickUpPoint());
        assertEquals(EXPECTED_STRING, testDTO.getDestination());
        assertEquals(EXPECTED_STRING, testDTO.getPurpose());
        assertEquals(EXPECTED_DOUBLE, testDTO.getMiles());
    }

    @Test
    public void test_findAll() {
        //Given
        List<UberRide>  mockResponse = new ArrayList<>();
        UberRide ride = new UberRide();
        ride.setPickUpPoint(EXPECTED_STRING);
        ride.setStartDateOfRide(EXPECTED_DATE);
        ride.setEndDateOfRide(EXPECTED_DATE);
        ride.setCategory(EXPECTED_CATEGORY);
        ride.setPickUpPoint(EXPECTED_STRING);
        ride.setDestination(EXPECTED_STRING);
        ride.setMiles(EXPECTED_DOUBLE);
        ride.setPurpose(EXPECTED_STRING);
        mockResponse.add(ride);
        when(mockRepo.findAll()).thenReturn(mockResponse);

        //When
        UberRideDTO testDTO = uberRideDAOMock.findAll().get(0);

        //Then
        assertEquals(EXPECTED_DATE, testDTO.getStartDateOfRide());
        assertEquals(EXPECTED_DATE, testDTO.getEndDateOfRide());
        assertEquals(EXPECTED_CATEGORY, testDTO.getCategory());
        assertEquals(EXPECTED_STRING, testDTO.getPickUpPoint());
        assertEquals(EXPECTED_STRING, testDTO.getDestination());
        assertEquals(EXPECTED_STRING, testDTO.getPurpose());
        assertEquals(EXPECTED_DOUBLE, testDTO.getMiles());

    }

    @Test
    public void test_findByUuid() {
        //Given
        String uuid = "Test";
        UberRide mockResponse = new UberRide();
        mockResponse.setPickUpPoint(EXPECTED_STRING);
        mockResponse.setStartDateOfRide(EXPECTED_DATE);
        mockResponse.setEndDateOfRide(EXPECTED_DATE);
        mockResponse.setCategory(EXPECTED_CATEGORY);
        mockResponse.setPickUpPoint(EXPECTED_STRING);
        mockResponse.setDestination(EXPECTED_STRING);
        mockResponse.setMiles(EXPECTED_DOUBLE);
        mockResponse.setPurpose(EXPECTED_STRING);
        when(mockRepo.findByUuid(uuid)).thenReturn(mockResponse);

        //When
        UberRideDTO testDTO = uberRideDAOMock.findByUuid(uuid);

        //Then
        assertEquals(EXPECTED_DATE, testDTO.getStartDateOfRide());
        assertEquals(EXPECTED_DATE, testDTO.getEndDateOfRide());
        assertEquals(EXPECTED_CATEGORY, testDTO.getCategory());
        assertEquals(EXPECTED_STRING, testDTO.getPickUpPoint());
        assertEquals(EXPECTED_STRING, testDTO.getDestination());
        assertEquals(EXPECTED_STRING, testDTO.getPurpose());
        assertEquals(EXPECTED_DOUBLE, testDTO.getMiles());
    }

    @Test
    public void test_editUberRide() {
        //Given
        String uuid = "Test";
        UberRide mockResponse = new UberRide();
        mockResponse.setPickUpPoint(EXPECTED_STRING);
        mockResponse.setStartDateOfRide(EXPECTED_DATE);
        mockResponse.setEndDateOfRide(EXPECTED_DATE);
        mockResponse.setCategory(EXPECTED_CATEGORY);
        mockResponse.setPickUpPoint(EXPECTED_STRING);
        mockResponse.setDestination(EXPECTED_STRING);
        mockResponse.setMiles(EXPECTED_DOUBLE);
        mockResponse.setPurpose("New Purpose");
        UberRideDTO newDTO = new UberRideDTO(mockResponse);
        when(mockRepo.findByUuid(uuid)).thenReturn(mockResponse);
        when(mockRepo.save(Mockito.any())).thenReturn(mockResponse);

        //When
        UberRideDTO testDTO = uberRideDAOMock.editUberRide(uuid, newDTO);

        //Then
        assertEquals(EXPECTED_DATE, testDTO.getStartDateOfRide());
        assertEquals(EXPECTED_DATE, testDTO.getEndDateOfRide());
        assertEquals(EXPECTED_CATEGORY, testDTO.getCategory());
        assertEquals(EXPECTED_STRING, testDTO.getPickUpPoint());
        assertEquals(EXPECTED_STRING, testDTO.getDestination());
        assertEquals("New Purpose", testDTO.getPurpose());
        assertEquals(EXPECTED_DOUBLE, testDTO.getMiles());
    }

    @Test
    public void deleteUberRide() {
        //Given
        String uuid = "Test";

        //When/Then
        uberRideDAOMock.deleteUberRide(uuid);
    }


}