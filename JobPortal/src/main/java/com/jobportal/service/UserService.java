package com.jobportal.service;

import com.jobportal.dao.UserDAO;
import com.jobportal.model.User;

public class UserService {
    private UserDAO userDAO = new UserDAO();

    public boolean registerUser(User user) {
        return userDAO.registerUser(user);
    }

    public User loginUser(String email, String password) {
        return userDAO.login(email, password);
    }
}
