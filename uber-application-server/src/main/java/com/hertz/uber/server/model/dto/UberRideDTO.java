package com.hertz.uber.server.model.dto;

import com.hertz.uber.server.constants.Category;
import com.hertz.uber.server.constants.DateConstants;
import com.hertz.uber.server.model.entity.UberRide;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UberRideDTO {

    private Date startDateOfRide;
    private Date endDateOfRide;
    private Category category;
    private String pickUpPoint;
    private String destination;
    private Double miles;
    private String purpose;
    private SimpleDateFormat sdf = new SimpleDateFormat(DateConstants.HR_MIN_TIME_PATTERN);

    public UberRideDTO(List<String> row) throws ParseException {
        startDateOfRide = sdf.parse(row.get(0));
        endDateOfRide = sdf.parse(row.get(1));
        category = Category.valueOf(row.get(2));
        pickUpPoint = row.get(3);
        destination = row.get(4);
        miles = Double.parseDouble(row.get(5));
        purpose = row.get(6);
    }

    public UberRideDTO(UberRide entity) {
        startDateOfRide = entity.getStartDateOfRide();
        endDateOfRide = entity.getEndDateOfRide();
        category = entity.getCategory();
        pickUpPoint = entity.getPickUpPoint();
        destination = entity.getDestination();
        miles = entity.getMiles();
        purpose = entity.getPurpose();
    }

    public Date getStartDateOfRide() {
        return startDateOfRide;
    }

    public void setStartDateOfRide(Date startDateOfRide) {
        this.startDateOfRide = startDateOfRide;
    }

    public Date getEndDateOfRide() {
        return endDateOfRide;
    }

    public void setEndDateOfRide(Date endDateOfRide) {
        this.endDateOfRide = endDateOfRide;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getPickUpPoint() {
        return pickUpPoint;
    }

    public void setPickUpPoint(String pickUpPoint) {
        this.pickUpPoint = pickUpPoint;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Double getMiles() {
        return miles;
    }

    public void setMiles(Double miles) {
        this.miles = miles;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
}
