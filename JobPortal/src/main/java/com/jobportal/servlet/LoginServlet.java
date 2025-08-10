package com.jobportal.servlet;

import com.jobportal.model.User;
import com.jobportal.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    private UserService userService = new UserService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        User user = userService.loginUser(email, password);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            if ("employer".equalsIgnoreCase(user.getRole())) {
                response.sendRedirect("employerHome.jsp");
            } else {
                response.sendRedirect("jobSearch.jsp");
            }
        } else {
            response.sendRedirect("login.jsp?error=Invalid credentials");
        }
    }
}
