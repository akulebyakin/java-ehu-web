<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Main</title>
</head>
<body>
Hello ${username}! Your role is ${role}
<br/>
<a href="${pageContext.request.contextPath}/controller?command=logout">Logout</a>
<br/>
<a href="users">User List</a>
<br/>
<a href="${pageContext.request.contextPath}/jsp/register_new_coffeeshop.jsp">Register New Coffee Shop</a>
<br/>
<a href="${pageContext.request.contextPath}/controller?command=listCoffeeshops">List Coffee Shops</a>
<br />
<c:if test="${not empty errorMessage}">
    <div style="color: red;">
            ${errorMessage}
    </div>
</c:if>
</body>
</html>
