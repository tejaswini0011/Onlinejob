package com.jobportal.service;

import com.jobportal.dao.JobDAO;
import com.jobportal.model.Job;

import java.util.List;

public class JobService {
    private JobDAO jobDAO = new JobDAO();

    public boolean postJob(Job job) {
        return jobDAO.saveJob(job);
    }

    public List<Job> getAllJobs() {
        return jobDAO.getAllJobs();
    }

    public Job getJobById(int jobId) {
        return jobDAO.getJobById(jobId);
    }
}
