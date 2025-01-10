<%--
  Created by IntelliJ IDEA.
  User: wowan
  Date: 10/26/2024
  Time: 9:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login page</title>
</head>
<body>
<form method="post" action="controller">
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
        ${wrongActionMessage}
    <br/>
        ${nullPageMessage}
    <br/>
    <input type="submit" value="Login">
</form>
</body>
</html>
