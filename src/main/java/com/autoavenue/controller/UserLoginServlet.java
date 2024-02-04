package com.autoavenue.controller;

import com.autoavenue.model.User;
import com.autoavenue.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/UserLoginServlet")
public class UserLoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UserService userService;

    public UserLoginServlet() {
        super();
        userService = new UserService();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = userService.authenticateUser(email, password);

        if (user != null) {
            request.getSession().setAttribute("loggedInUser", user);

            response.sendRedirect("userDashboard.jsp");
        } else {
            request.getSession().setAttribute("showErrorPopup", true);

            response.sendRedirect("userLogin.jsp?error=1");
        }
    }
}
