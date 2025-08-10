package com.jobportal.dao;

import com.jobportal.model.Application;
import com.jobportal.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ApplicationDAO {

    // Save a job application to the database
    public boolean saveApplication(Application application) {
        String sql = "INSERT INTO applications (job_id, user_id, apply_date, resume_link) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, application.getJobId());
            stmt.setInt(2, application.getUserId());
            stmt.setDate(3, new java.sql.Date(application.getApplyDate().getTime()));
            stmt.setString(4, application.getResumeLink());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Get all applications submitted by a specific user
    public List<Application> getApplicationsByUserId(int userId) {
        List<Application> applications = new ArrayList<>();
        String sql = "SELECT * FROM applications WHERE user_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Application app = new Application();
                app.setApplicationId(rs.getInt("application_id"));
                app.setJobId(rs.getInt("job_id"));
                app.setUserId(rs.getInt("user_id"));
                app.setApplyDate(rs.getDate("apply_date"));
                app.setResumeLink(rs.getString("resume_link"));
                applications.add(app);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return applications;
    }

    // Get all applications submitted for jobs posted by a specific employer
    public List<Application> getApplicationsForEmployer(int employerId) {
        List<Application> applications = new ArrayList<>();
        String sql = "SELECT a.* FROM applications a JOIN jobs j ON a.job_id = j.job_id WHERE j.employer_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
             
            stmt.setInt(1, employerId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Application app = new Application();
                app.setApplicationId(rs.getInt("application_id"));
                app.setJobId(rs.getInt("job_id"));
                app.setUserId(rs.getInt("user_id"));
                app.setApplyDate(rs.getDate("apply_date"));
                app.setResumeLink(rs.getString("resume_link"));
                applications.add(app);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return applications;
    }
}
