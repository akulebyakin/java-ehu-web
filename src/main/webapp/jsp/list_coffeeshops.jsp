<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>List of Coffee Shops</title>
</head>
<body>
<h2>Registered Coffee Shops</h2>
<table border="1">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Address</th>
        <th>Phone</th>
        <th>Email</th>
        <th>Website</th>
        <th>Description</th>
        <th>Image</th>
        <th>Rating</th>
    </tr>
    <c:forEach var="coffeeshop" items="${coffeeshops}">
        <tr>
            <td>${coffeeshop.id}</td>
            <td>${coffeeshop.name}</td>
            <td>${coffeeshop.address}</td>
            <td>${coffeeshop.phone}</td>
            <td>${coffeeshop.email}</td>
            <td>${coffeeshop.website}</td>
            <td>${coffeeshop.description}</td>
            <td><img src="${not empty coffeeshop.image}" alt="Coffeeshop Image" width="100"></td>
            <td>${coffeeshop.rating}</td>
        </tr>
    </c:forEach>
</table>
<br />
<a href="${pageContext.request.contextPath}/jsp/main.jsp">Return to Main Page</a>
</body>
</html>