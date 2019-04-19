package com.hertz.uber.server.model.dto;

import com.hertz.uber.server.constants.Category;
import com.hertz.uber.server.constants.DateConstants;
import com.hertz.uber.server.model.entity.UberRide;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
public class UberRideDTOTest extends TestCase {

    private static final Date EXPECTED_DATE = new Date();
    private static final Category EXPECTED_CATEGORY = Category.BUSINESS;
    private static final String EXPECTED_STRING = "TEST";
    private static final Double EXPECTED_DOUBLE = 1.8;


    @Test
    public void testConstructor_WithList() throws ParseException {
        //Given
        List<String> list = Arrays.asList("12/29/2016 20:15", "12/29/2016 20:45", "Business", "Karachi", "Karachi", "7.2" ,"Meeting");

        //When
        UberRideDTO dto = new UberRideDTO(list);

        //Then
        SimpleDateFormat sdf = new SimpleDateFormat(DateConstants.HR_MIN_TIME_PATTERN);
        assertEquals(list.get(0), sdf.format(dto.getStartDateOfRide()));
        assertEquals(list.get(1), sdf.format(dto.getEndDateOfRide()));
        assertEquals(list.get(2).toUpperCase(), dto.getCategory().name());
        assertEquals(list.get(3), dto.getPickUpPoint());
        assertEquals(list.get(4), dto.getDestination());
        assertEquals(Double.parseDouble(list.get(5)), dto.getMiles());
        assertEquals(list.get(6), dto.getPurpose());



    }

    @Test
    public void testConstructor_WithEntity() throws ParseException {
        //Given
        UberRide entity = new UberRide();
        entity.setUuid(EXPECTED_STRING);
        entity.setStartDateOfRide(EXPECTED_DATE);
        entity.setEndDateOfRide(EXPECTED_DATE);
        entity.setPickUpPoint(EXPECTED_STRING);
        entity.setDestination(EXPECTED_STRING);
        entity.setCategory(EXPECTED_CATEGORY);
        entity.setPurpose(EXPECTED_STRING);
        entity.setMiles(EXPECTED_DOUBLE);

        //When
        UberRideDTO dto = new UberRideDTO(entity);

        //Then

        assertEquals(EXPECTED_STRING, dto.getUuid());
        assertEquals(EXPECTED_DATE, dto.getStartDateOfRide());
        assertEquals(EXPECTED_DATE, dto.getEndDateOfRide());
        assertEquals(EXPECTED_CATEGORY, dto.getCategory());
        assertEquals(EXPECTED_STRING, dto.getPickUpPoint());
        assertEquals(EXPECTED_STRING, dto.getDestination());
        assertEquals(EXPECTED_DOUBLE, dto.getMiles());
        assertEquals(EXPECTED_STRING, dto.getPurpose());



    }


}
