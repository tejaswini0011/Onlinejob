package com.jobportal.swing;

import com.jobportal.model.Application;
import com.jobportal.model.Job;
import com.jobportal.service.ApplicationService;
import com.jobportal.service.JobService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class EmployerFrame extends JFrame {
    private JTextField titleField;
    private JTextArea descArea;
    private JTextField locationField;
    private JButton postButton, viewApplicationsButton;
    private int employerId;  // Set this after login or hardcode for now

    private JobService jobService = new JobService();
    private ApplicationService applicationService = new ApplicationService();

    public EmployerFrame(int employerId) {
        this.employerId = employerId;

        setTitle("Employer Dashboard");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Top Panel
        JPanel formPanel = new JPanel(new GridLayout(6, 1));
        formPanel.add(new JLabel("Job Title:"));
        titleField = new JTextField();
        formPanel.add(titleField);

        formPanel.add(new JLabel("Description:"));
        descArea = new JTextArea(3, 20);
        formPanel.add(new JScrollPane(descArea));

        formPanel.add(new JLabel("Location:"));
        locationField = new JTextField();
        formPanel.add(locationField);

        add(formPanel, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        postButton = new JButton("Post Job");
        viewApplicationsButton = new JButton("View Applications");

        buttonPanel.add(postButton);
        buttonPanel.add(viewApplicationsButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Action Listeners
        postButton.addActionListener(e -> postJob());
        viewApplicationsButton.addActionListener(e -> showApplications());

        setVisible(true);
    }

    private void postJob() {
        String title = titleField.getText();
        String desc = descArea.getText();
        String loc = locationField.getText();

        if (title.isEmpty() || desc.isEmpty() || loc.isEmpty()) {
            JOptionPane.showMessageDialog(this, "All fields must be filled!");
            return;
        }

        Job job = new Job();
        job.setTitle(title);
        job.setDescription(desc);
        job.setLocation(loc);
        job.setEmployerId(employerId);

        boolean success = jobService.postJob(job);
        if (success) {
            JOptionPane.showMessageDialog(this, "Job Posted Successfully!");
            titleField.setText("");
            descArea.setText("");
            locationField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Failed to post job.");
        }
    }

    private void showApplications() {
        List<Application> apps = applicationService.getEmployerApplications(employerId);
        StringBuilder sb = new StringBuilder();
        for (Application app : apps) {
            sb.append("Application ID: ").append(app.getApplicationId()).append("\n")
              .append("Job ID: ").append(app.getJobId()).append("\n")
              .append("User ID: ").append(app.getUserId()).append("\n")
              .append("Date: ").append(app.getApplyDate()).append("\n")
              .append("Resume: ").append(app.getResumeLink()).append("\n\n");
        }

        JTextArea textArea = new JTextArea(sb.toString());
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setPreferredSize(new Dimension(400, 300));
        JOptionPane.showMessageDialog(this, scrollPane, "Applications", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        // You can hardcode employerId = 1 for testing
        new EmployerFrame(1);
    }
}
