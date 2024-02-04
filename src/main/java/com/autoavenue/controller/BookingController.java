package com.autoavenue.controller;

import com.autoavenue.model.Booking;
import com.autoavenue.model.Driver;
import com.autoavenue.model.Route;
import com.autoavenue.model.User;
import com.autoavenue.service.BookingService;
import com.autoavenue.service.RouteService;
import com.autoavenue.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

@WebServlet("/BookingController")
public class BookingController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private BookingService bookingService;
    private UserService userService;
    private RouteService routeService;

    public BookingController() {
        bookingService = new BookingService();
        userService = new UserService();
        routeService = new RouteService();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String sourceLocation = request.getParameter("sourceLocation");
        String destinationLocation = request.getParameter("destinationLocation");

        Route route = routeService.getRouteBySourceAndDestination(sourceLocation, destinationLocation);

        if (route != null) {
            double distance = route.getDistance();
            int estimatedTime = route.getEstimatedTime();
            double cost = route.getCost();

            User loggedInUser = (User) request.getSession().getAttribute("loggedInUser");

            Booking newBooking = new Booking(sourceLocation, destinationLocation, distance, estimatedTime, cost);
            newBooking.setBookedByUser(loggedInUser);
            newBooking.setBookingTime(new Date()); 
            
            Driver assignedDriver = userService.getAvailableDriverByLocation(sourceLocation);

            if (assignedDriver != null) {
                assignedDriver.setAvailable(false);
                userService.updateDriver(assignedDriver);
                newBooking.setBookedByDriver(assignedDriver);

                bookingService.bookCab(newBooking);

                request.setAttribute("bookingId", newBooking.getBookingId());
                request.setAttribute("sourceLocation", newBooking.getSourceLocation());
                request.setAttribute("destinationLocation", newBooking.getDestinationLocation());
                request.setAttribute("cabNumber", assignedDriver.getCabNumber());
                request.setAttribute("driverName", assignedDriver.getDriverName());
                request.setAttribute("driverContact", assignedDriver.getDriverContact());
                request.setAttribute("distance", newBooking.getDistance());
                request.setAttribute("estimatedTime", newBooking.getEstimatedTime());
                request.setAttribute("cost", newBooking.getCost());

                request.getRequestDispatcher("bookingSuccess.jsp").forward(request, response);

                userService.updateDriverAvailabilityStatus(assignedDriver.getDriverId(), true);
            } else {
                request.setAttribute("bookingError", "No driver available for the provided source location.");
                request.getRequestDispatcher("bookingFailure.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("bookingError", "No route found for the provided source and destination.");
            request.getRequestDispatcher("bookingFailure.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         response.sendRedirect("cabBooking.jsp");
    }
}
