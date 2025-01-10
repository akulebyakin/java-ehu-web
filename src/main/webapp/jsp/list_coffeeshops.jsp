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
<h3>Delete Coffee Shop by ID</h3>
<form action="${pageContext.request.contextPath}/controller" method="post">
    <input type="hidden" name="command" value="delete_coffeeshop">
    <label for="deleteId">Coffee Shop ID:</label>
    <input type="text" id="deleteId" name="id" required>
    <button type="submit">Delete</button>
</form>
<!-- Display success message -->
<c:if test="${not empty successMessage}">
    <div style="color: green;">
            ${successMessage}
    </div>
</c:if>
<!-- Display error message -->
<c:if test="${not empty errorMessage}">
    <div style="color: red;">
    ${errorMessage}
    </div>
</c:if>
<br />
<a href="${pageContext.request.contextPath}/jsp/main.jsp">Return to Main Page</a>
</body>
</html>