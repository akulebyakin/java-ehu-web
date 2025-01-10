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
<a href="${pageContext.request.contextPath}/users">User List</a>
<br/>
<a href="${pageContext.request.contextPath}/jsp/register_new_coffeeshop.jsp">Register New Coffee Shop</a>
<br/>
<a href="${pageContext.request.contextPath}/controller?command=list_coffeeshops">List Coffee Shops</a>
<br />
<form method="get" action="${pageContext.request.contextPath}/jsp/update_coffeeshop.jsp">
    <label for="coffeeshopId">Enter Coffee Shop ID:</label>
    <input type="text" id="coffeeshopId" name="id" required>
    <button type="submit">Update Coffee Shop</button>
</form>
<br/>
<c:if test="${not empty errorMessage}">
    <div style="color: red;">
            ${errorMessage}
    </div>
</c:if>
</body>
</html>
