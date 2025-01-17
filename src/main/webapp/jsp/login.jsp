<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login page</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="login">
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
        ${errorLoginPasswordMessage}
    <br/>
        ${successfulRegistrationMessage}
    <br/>
        ${nullPageMessage}
    <br/>
    <input type="submit" value="Login">
    <br />
    <a href="${pageContext.request.contextPath}/jsp/registration.jsp">Registration</a>
</form>
</body>
</html>
