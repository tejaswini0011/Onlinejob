<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h2>User Login</h2>
    <form action="LoginServlet" method="post">
        Email: <input type="email" name="email" required><br><br>
        Password: <input type="password" name="password" required><br><br>
        <input type="submit" value="Login">
    </form>
    <p style="color:red;"><%= request.getParameter("error") != null ? request.getParameter("error") : "" %></p>
    <p>New user? <a href="register.jsp">Register here</a></p>
</body>
</html>
