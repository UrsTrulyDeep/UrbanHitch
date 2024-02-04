package com.autoavenue.controller;

import com.autoavenue.model.Driver;
import com.autoavenue.service.DriverService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/DriverLoginServlet")
public class DriverLoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private DriverService driverService;

    public DriverLoginServlet() {
        super();
        driverService = new DriverService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Driver driver = driverService.authenticateDriver(email, password);

        if (driver != null) {
            request.getSession().setAttribute("loggedInDriver", driver);

            response.sendRedirect("driverDashboard.jsp");
        } else {
            request.getSession().setAttribute("showErrorPopup", true);

            response.sendRedirect("driverLogin.jsp?error=1");
        }
    }
}