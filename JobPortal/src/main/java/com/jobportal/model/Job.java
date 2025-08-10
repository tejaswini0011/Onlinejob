package com.jobportal.model;

public class Job {
    private int jobId;
    private String title;
    private String description;
    private String location;
    private int employerId;

    public Job() {}

    public Job(String title, String description, String location, int employerId) {
        this.title = title;
        this.description = description;
        this.location = location;
        this.employerId = employerId;
    }

    // Getters and setters
    public int getJobId() { return jobId; }
    public void setJobId(int jobId) { this.jobId = jobId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public int getEmployerId() { return employerId; }
    public void setEmployerId(int employerId) { this.employerId = employerId; }
}
