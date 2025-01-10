<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register New Coffee Shop</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/controller">
    <input type="hidden" name="command" value="registerCoffeeshop">
    Name: <br/>
    <label>
        <input type="text" name="name" placeholder="Name" required>
    </label>
    <br/>
    Address: <br/>
    <label>
        <input type="text" name="address" placeholder="Address" required>
    </label>
    <br/>
    Phone: <br/>
    <label>
        <input type="text" name="phone" placeholder="Phone">
    </label>
    <br/>
    Email: <br/>
    <label>
        <input type="email" name="email" placeholder="Email">
    </label>
    <br/>
    Website: <br/>
    <label>
        <input type="text" name="website" placeholder="Website">
    </label>
    <br/>
    Description: <br/>
    <label>
        <textarea name="description" placeholder="Description"></textarea>
    </label>
    <br/>
    Image: <br/>
    <label>
        <input type="text" name="image" placeholder="Image URL">
    </label>
    <br/>
    Rating (0-100): <br/>
    <label>
        <input type="number" name="rating" min="0" max="100" placeholder="Rating">
    </label>
    <br/>
    ${registrationMessage}
    <br/>
    <input type="submit" value="Register Coffee Shop">
</form>
<br />
<a href="${pageContext.request.contextPath}/jsp/main.jsp">Return to Main Page</a>
</body>
</html>