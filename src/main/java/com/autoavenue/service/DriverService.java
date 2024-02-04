package com.autoavenue.service;

import com.autoavenue.dao.DriverDAO;
import com.autoavenue.model.Driver;

public class DriverService {

    private DriverDAO driverDAO;

    public DriverService() {
        this.driverDAO = new DriverDAO();
    }

    public boolean registerDriver(Driver driver) {
        driverDAO.saveDriver(driver);
        return true; 
    }

    public boolean isEmailAlreadyRegistered(String email) {
        return driverDAO.isEmailAlreadyRegistered(email);
    }

    public Driver authenticateDriver(String email, String password) {
        return driverDAO.getDriverByEmailAndPassword(email, password);
    }

    public boolean updateDriver(Driver updatedDriver) {
        try {
            Driver existingDriver = driverDAO.getDriverById(updatedDriver.getDriverId());

            if (existingDriver != null) {
                existingDriver.setDriverName(updatedDriver.getDriverName());
                existingDriver.setDriverContact(updatedDriver.getDriverContact());

                driverDAO.updateDriver(existingDriver);

                return true;
            } else {
                // Driver not found
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}