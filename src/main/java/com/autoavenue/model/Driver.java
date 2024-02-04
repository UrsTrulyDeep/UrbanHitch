package com.autoavenue.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "drivers")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long driverId;

    private String driverName;
    private String driverContact;
    private String cabNumber;
    private String email;
    private String password;
    private boolean isAvailable;

    @OneToOne(mappedBy = "bookedByDriver")
    private Booking bookedRide;

    private String driverLocation;

    public Driver() {
    }

    public Driver(String driverName, String driverContact, String cabNumber, String email, String password, String driverLocation) {
        this.driverName = driverName;
        this.driverContact = driverContact;
        this.cabNumber = cabNumber;
        this.email = email;
        this.password = password;
        this.driverLocation = driverLocation;
        this.isAvailable = true;
    }

     public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverContact() {
        return driverContact;
    }

    public void setDriverContact(String driverContact) {
        this.driverContact = driverContact;
    }

    public String getCabNumber() {
        return cabNumber;
    }

    public void setCabNumber(String cabNumber) {
        this.cabNumber = cabNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Booking getBookedRide() {
        return bookedRide;
    }

    public void setBookedRide(Booking bookedRide) {
        this.bookedRide = bookedRide;
    }

    public String getDriverLocation() {
        return driverLocation;
    }

    public void setDriverLocation(String driverLocation) {
        this.driverLocation = driverLocation;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }

    @Override
    public String toString() {
        return "Driver [driverId=" + driverId + ", driverName=" + driverName + ", driverContact=" + driverContact
                + ", cabNumber=" + cabNumber + ", email=" + email + ", password=" + password + ", driverLocation=" + driverLocation + "]";
    }
}
