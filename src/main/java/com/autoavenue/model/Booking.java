package com.autoavenue.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookingId")
    private Long bookingId;

    @Column(length = 191)
    private String sourceLocation;

    @Column(length = 191)
    private String destinationLocation;

    private double distance;
    private int estimatedTime;
    private double cost;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    private Date bookingTime;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User bookedByUser;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "driver_id")
    private Driver bookedByDriver;

    public Booking() {
    }

    public Booking(String sourceLocation, String destinationLocation, double distance, int estimatedTime, double cost) {
        this.sourceLocation = sourceLocation;
        this.destinationLocation = destinationLocation;
        this.distance = distance;
        this.estimatedTime = estimatedTime;
        this.cost = cost;
        this.bookingTime = new Date();
    }


    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public String getSourceLocation() {
		return sourceLocation;
	}

	public void setSourceLocation(String sourceLocation) {
		this.sourceLocation = sourceLocation;
	}

	public String getDestinationLocation() {
		return destinationLocation;
	}

	public void setDestinationLocation(String destinationLocation) {
		this.destinationLocation = destinationLocation;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public int getEstimatedTime() {
		return estimatedTime;
	}

	public void setEstimatedTime(int estimatedTime) {
		this.estimatedTime = estimatedTime;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public User getBookedByUser() {
		return bookedByUser;
	}

	public void setBookedByUser(User bookedByUser) {
		this.bookedByUser = bookedByUser;
	}

	public Driver getBookedByDriver() {
		return bookedByDriver;
	}

	public void setBookedByDriver(Driver bookedByDriver) {
		this.bookedByDriver = bookedByDriver;
	}
	
	public Date getBookingTime() {
		return bookingTime;
	}

	public void setBookingTime(Date bookingTime) {
		this.bookingTime = bookingTime;
	}

	@Override
    public String toString() {
        return "Booking [bookingId=" + bookingId + ", sourceLocation=" + sourceLocation + ", destinationLocation="
                + destinationLocation + ", distance=" + distance + ", estimatedTime=" + estimatedTime + ", cost="
                + cost + "]";
    }
}
