package com.jobportal.service;

import com.jobportal.dao.ApplicationDAO;
import com.jobportal.model.Application;

import java.util.List;

public class ApplicationService {
    private ApplicationDAO applicationDAO = new ApplicationDAO();

    public boolean applyForJob(Application application) {
        return applicationDAO.saveApplication(application);
    }

    public List<Application> getUserApplications(int userId) {
        return applicationDAO.getApplicationsByUserId(userId);
    }

    public List<Application> getEmployerApplications(int employerId) {
        return applicationDAO.getApplicationsForEmployer(employerId);
    }
}
