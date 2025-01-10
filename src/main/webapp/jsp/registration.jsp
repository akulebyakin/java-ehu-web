<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login page</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="registration">
    Name: <br/>
    <label>
        <input type="text" name="username" placeholder="Name">
    </label>
    <br/>
    Email: <br/>
    <label>
        <input type="email" name="email" placeholder="Email">
    </label>
    <br/>
    Login: <br/>
    <label>
        <input type="text" name="login" placeholder="Login">
    </label>
    <br/>
    Password: <br/>
    <label>
        <input type="password" name="password" placeholder="Password">
    </label>
    <br/>
        ${errorRegistrationMessage}
    <br/>
    <input type="submit" value="Register">
</form>
<br />
<a href="${pageContext.request.contextPath}/jsp/login.jsp">Return back to Login</a>
</body>
</html>
