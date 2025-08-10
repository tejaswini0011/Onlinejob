<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
    <h2>User Registration</h2>
    <form action="RegisterServlet" method="post">
        Name: <input type="text" name="name" required><br><br>
        Email: <input type="email" name="email" required><br><br>
        Password: <input type="password" name="password" required><br><br>
        Role: 
        <select name="role" required>
            <option value="jobseeker">Job Seeker</option>
            <option value="employer">Employer</option>
        </select><br><br>
        <input type="submit" value="Register">
    </form>
    <p style="color:red;"><%= request.getParameter("error") != null ? request.getParameter("error") : "" %></p>
    <p>Already registered? <a href="index.jsp">Login here</a></p>
</body>
</html>
