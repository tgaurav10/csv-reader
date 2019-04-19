package com.hertz.uber.server.controller;

import com.hertz.uber.server.connector.CsvConnector;
import com.hertz.uber.server.constants.Category;
import com.hertz.uber.server.model.dao.UberRideDAO;
import com.hertz.uber.server.model.dto.UberRideDTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(UberDataController.class)
public class UberDataControllerTest {

    private static final String REST_ENDPOINT = "http://localhost:8080/uber-data/job";
    private static final Date EXPECTED_DATE = new Date();
    private static final Category EXPECTED_CATEGORY = Category.BUSINESS;
    private static final String EXPECTED_STRING = "TEST";
    private static final Double EXPECTED_DOUBLE = 1.8;


    /**
     * Spring MVC test support entity.
     */
    @Autowired
    private MockMvc mvc;


    @MockBean
    private UberRideDAO uberRideDAOMock;

    @MockBean
    private CsvConnector connectorMock;


    @Test
    public void test_get() throws Exception {
        //Given
        List<UberRideDTO> mockResponse = new ArrayList<>();
        UberRideDTO ride = new UberRideDTO();
        ride.setPickUpPoint(EXPECTED_STRING);
        ride.setStartDateOfRide(EXPECTED_DATE);
        ride.setEndDateOfRide(EXPECTED_DATE);
        ride.setCategory(EXPECTED_CATEGORY);
        ride.setPickUpPoint(EXPECTED_STRING);
        ride.setDestination(EXPECTED_STRING);
        ride.setMiles(EXPECTED_DOUBLE);
        ride.setPurpose(EXPECTED_STRING);
        mockResponse.add(ride);
        when(uberRideDAOMock.findAll()).thenReturn(mockResponse);

        //When
        //Get to Rest endpoint.
        mvc.perform(get(REST_ENDPOINT))
                //Then
                .andExpect(status().isOk());
    }

    @Test
    public void test_edit() throws Exception {
        //Given
        UberRideDTO ride = new UberRideDTO();
        ride.setPickUpPoint(EXPECTED_STRING);
        ride.setStartDateOfRide(EXPECTED_DATE);
        ride.setEndDateOfRide(EXPECTED_DATE);
        ride.setCategory(EXPECTED_CATEGORY);
        ride.setPickUpPoint(EXPECTED_STRING);
        ride.setDestination(EXPECTED_STRING);
        ride.setMiles(EXPECTED_DOUBLE);
        ride.setPurpose(EXPECTED_STRING);
        when(uberRideDAOMock.editUberRide(Mockito.anyString(), Mockito.any())).thenReturn(ride);
        System.out.println(put(REST_ENDPOINT + "/123")
                .content("{\"startDateOfRide\":\"04/19/2019 01:01\",\"endDateOfRide\":\"04/19/2019 01:01\",\"category\":\"BUSINESS\",\"pickUpPoint\":\"TEST\",\"destination\":\"TEST\",\"purpose\":\"TEST\"}")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .buildRequest(mvc.getDispatcherServlet().getServletContext()).getContentAsString());

        //When
        //Get to Rest endpoint.
        mvc.perform(put(REST_ENDPOINT+"/123")
                .content(ride.toString())
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                //Then
                .andExpect(status().isOk());
    }

    @Test
    public void test_delete() throws Exception {
        //When
        //Get to Rest endpoint.
        mvc.perform(delete(REST_ENDPOINT + "/123"))
                //Then
                .andExpect(status().isOk());
    }
}