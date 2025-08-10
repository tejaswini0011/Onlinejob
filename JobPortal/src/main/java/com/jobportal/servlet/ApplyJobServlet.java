package com.jobportal.servlet;

import com.jobportal.model.Application;
import com.jobportal.model.User;
import com.jobportal.service.ApplicationService;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.Date;

public class ApplyJobServlet extends HttpServlet {
    private ApplicationService applicationService = new ApplicationService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int jobId = Integer.parseInt(request.getParameter("jobId"));
        String resumeLink = request.getParameter("resumeLink");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        Application application = new Application();
        application.setJobId(jobId);
        application.setUserId(user.getUserId());
        application.setApplyDate(new Date());
        application.setResumeLink(resumeLink);

        if (applicationService.applyForJob(application)) {
            response.sendRedirect("jobSearch.jsp?msg=Application submitted");
        } else {
            response.sendRedirect("jobSearch.jsp?error=Application failed");
        }
    }
}
