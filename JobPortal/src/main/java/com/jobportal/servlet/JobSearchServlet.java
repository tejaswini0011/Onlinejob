package com.jobportal.servlet;

import com.jobportal.model.Job;
import com.jobportal.service.JobService;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class JobSearchServlet extends HttpServlet {
    private JobService jobService = new JobService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Job> jobList = jobService.getAllJobs();
        request.setAttribute("jobs", jobList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("jobSearch.jsp");
        dispatcher.forward(request, response);
    }
}
