package com.autoavenue.controller;

import com.autoavenue.model.User;
import com.autoavenue.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/UserRegistrationServlet")
public class UserRegistrationServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private UserService userService;

	public UserRegistrationServlet() {
		super();
		userService = new UserService();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userName = request.getParameter("userName");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String userContact = request.getParameter("userContact");

		try {
			if (userService.isEmailAlreadyRegistered(email)) {
				request.setAttribute("registrationError", "Email is already registered. Please use a different email.");
				request.getRequestDispatcher("userRegistration.jsp").forward(request, response);
			} else {
				User user = new User(userName, password, email, userContact);
				userService.registerUser(user);
				response.sendRedirect("registrationSuccess.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("registrationError", "Registration failed. Please try again.");
			request.getRequestDispatcher("userRegistration.jsp").forward(request, response);
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("userRegistration.jsp");
	}
}
