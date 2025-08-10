package com.jobportal.servlet;

import com.jobportal.model.User;
import com.jobportal.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class RegisterServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private UserService userService = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        User user = new User(name, email, password, role);

        if (userService.registerUser(user)) {
            response.sendRedirect("index.jsp");
        } else {
            response.sendRedirect("register.jsp?error=Registration failed");
        }
    }
}
