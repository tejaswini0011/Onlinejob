package com.jobportal.dao;

import com.jobportal.model.Job;
import com.jobportal.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JobDAO {

    public boolean saveJob(Job job) {
        String sql = "INSERT INTO jobs (employer_id, title, description, location) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, job.getEmployerId());
            stmt.setString(2, job.getTitle());
            stmt.setString(3, job.getDescription());
            stmt.setString(4, job.getLocation());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Job> getAllJobs() {
        List<Job> jobs = new ArrayList<>();
        String sql = "SELECT * FROM jobs";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Job job = new Job();
                job.setJobId(rs.getInt("job_id"));
                job.setEmployerId(rs.getInt("employer_id"));
                job.setTitle(rs.getString("title"));
                job.setDescription(rs.getString("description"));
                job.setLocation(rs.getString("location"));
                jobs.add(job);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return jobs;
    }

    public Job getJobById(int jobId) {
        String sql = "SELECT * FROM jobs WHERE job_id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, jobId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Job job = new Job();
                job.setJobId(rs.getInt("job_id"));
                job.setEmployerId(rs.getInt("employer_id"));
                job.setTitle(rs.getString("title"));
                job.setDescription(rs.getString("description"));
                job.setLocation(rs.getString("location"));
                return job;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
