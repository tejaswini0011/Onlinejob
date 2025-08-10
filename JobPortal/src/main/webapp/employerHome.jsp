<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ page import="com.jobportal.model.User" %>
<%
    User user = (User) session.getAttribute("user");
%>
<html>
<head>
    <title>Employer Home</title>
</head>
<body>
    <h2>Welcome, <%= user.getName() %> (Employer)</h2>
    <p>Use the Swing application to manage jobs and view applications.</p>
</body>
</html>
