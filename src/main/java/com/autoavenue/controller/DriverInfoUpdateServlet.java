package com.autoavenue.controller;

import com.autoavenue.model.Driver;
import com.autoavenue.service.DriverService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DriverInfoUpdateServlet")
public class DriverInfoUpdateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private DriverService driverService;

    public DriverInfoUpdateServlet() {
        super();
        driverService = new DriverService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Driver loggedInDriver = (Driver) request.getSession().getAttribute("loggedInDriver");

        request.setAttribute("loggedInDriver", loggedInDriver);

        request.getRequestDispatcher("driverInfoUpdate.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
        try {
            String updatedDriverName = request.getParameter("updatedDriverName");
            String updatedDriverContact = request.getParameter("updatedDriverContact");

            Driver loggedInDriver = (Driver) request.getSession().getAttribute("loggedInDriver");

            loggedInDriver.setDriverName(updatedDriverName);
            loggedInDriver.setDriverContact(updatedDriverContact);

            driverService.updateDriver(loggedInDriver);

            request.getSession().setAttribute("loggedInDriver", loggedInDriver);

            response.sendRedirect("driverDashboard.jsp");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}