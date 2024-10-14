<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>
<a href="users">User List</a>
<form name="send" action="${pageContext.request.contextPath}/hello-servlet" method="get">
    <label>
        <input type="text" name="number" value="" />
    </label>
    <button type="submit" name="submit">Send</button>
</form>
</body>
</html>