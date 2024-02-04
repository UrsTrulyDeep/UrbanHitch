package com.autoavenue.service;

import com.autoavenue.dao.BookingDAO;
import com.autoavenue.model.Booking;
import com.autoavenue.model.User;

import java.util.List;

public class BookingService {
    private BookingDAO bookingDAO;

    public BookingService() {
        this.bookingDAO = new BookingDAO();
    }

    public void bookCab(Booking newBooking) {
        bookingDAO.saveBooking(newBooking);
    }

    public List<Booking> getAllBookings() {
        return bookingDAO.getAllBookings();
    }

    public List<Booking> getBookingHistoryForUser(User user) {
        return bookingDAO.getBookingHistoryForUser(user);
    }
}
