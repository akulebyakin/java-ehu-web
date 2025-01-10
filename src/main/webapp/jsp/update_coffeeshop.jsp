<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.example.javaehuweb.model.CoffeeShop" %>
<%@ page import="com.example.javaehuweb.service.impl.CoffeeShopServiceImpl" %>
<%@ page import="com.example.javaehuweb.service.CoffeeShopService" %>
<%@ page import="com.example.javaehuweb.service.impl.CoffeeShopServiceImpl" %>
<%
  String id = request.getParameter("id");
  CoffeeShopService coffeeShopService = new CoffeeShopServiceImpl();
  CoffeeShop coffeeShop = coffeeShopService.getCoffeeShopById(Integer.parseInt(id)).get();
%>
<html>
<head>
  <title>Update Coffee Shop</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/controller">
  <input type="hidden" name="command" value="update_coffeeshop">
  <input type="hidden" name="id" value="<%= coffeeShop.getId() %>">
  Name: <br/>
  <label>
    <input type="text" name="name" value="<%= coffeeShop.getName() %>" placeholder="Name" required>
  </label>
  <br/>
  Address: <br/>
  <label>
    <input type="text" name="address" value="<%= coffeeShop.getAddress() %>" placeholder="Address" required>
  </label>
  <br/>
  Phone: <br/>
  <label>
    <input type="text" name="phone" value="<%= coffeeShop.getPhone() %>" placeholder="Phone">
  </label>
  <br/>
  Email: <br/>
  <label>
    <input type="email" name="email" value="<%= coffeeShop.getEmail() %>" placeholder="Email">
  </label>
  <br/>
  Website: <br/>
  <label>
    <input type="text" name="website" value="<%= coffeeShop.getWebsite() %>" placeholder="Website">
  </label>
  <br/>
  Description: <br/>
  <label>
    <textarea name="description" placeholder="Description"><%= coffeeShop.getDescription() %></textarea>
  </label>
  <br/>
  Image: <br/>
  <label>
    <input type="text" name="image" value="<%= coffeeShop.getImage() %>" placeholder="Image URL">
  </label>
  <br/>
  Rating (0-100): <br/>
  <label>
    <input type="number" name="rating" value="<%= coffeeShop.getRating() %>" min="0" max="100" placeholder="Rating">
  </label>
  <br/>
  ${updateMessage}
  <br/>
  <input type="submit" value="Update Coffee Shop">
</form>
<br />
<a href="${pageContext.request.contextPath}/jsp/main.jsp">Return to Main Page</a>
</body>
</html>