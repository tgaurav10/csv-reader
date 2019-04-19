package com.hertz.uber.server.model.entity;

import com.hertz.uber.server.constants.Category;
import com.hertz.uber.server.constants.DateConstants;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
public class UberRide {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String uuid;

    @DateTimeFormat(pattern = DateConstants.HR_MIN_TIME_PATTERN)
    private Date startDateOfRide;

    @DateTimeFormat(pattern = DateConstants.HR_MIN_TIME_PATTERN)
    private Date endDateOfRide;

    private Category category;

    private String pickUpPoint;

    private String destination;

    private Double miles;

    private String purpose;

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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
