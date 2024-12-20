<%@ page import="dao.*" %>
<%@ page import="model.*" %>
<%@ page import="connection.DbConnection" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
User auth = (User) request.getSession().getAttribute("auth");
if (auth != null) {
    response.sendRedirect("index.jsp");
}
%>

<!DOCTYPE html>
<html>
<head>
    <title>Admin</title>
    <%@ include file="/includes/head.jsp" %>
</head>
<body>
<%@ include file="/includes/navbar.jsp" %>


<div class="container">
    <h1>All Orders</h1>
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>Order ID</th>
                <th>User ID</th>
                <th>Product Name</th>
                <th>Category</th>
                <th>Quantity</th>
                <th>Total Price</th>
                <th>Order Date</th>
            </tr>
        </thead>
        <tbody>
            <%
                OrderDao orderDao = new OrderDao(DbConnection.getConnection());
                List<Order> orders = orderDao.getAllOrders();
                for(Order order : orders) {
            %>
            <tr>
                <td><%= order.getOrderId() %></td>
                <td><%= order.getUid() %></td>
                <td><%= order.getName() %></td>
                <td><%= order.getCategory() %></td>
                <td><%= order.getQunatity() %></td>
                <td>$<%= order.getPrice() %></td>
                <td><%= order.getDate() %></td>
            </tr>
            <% } %>
        </tbody>
    </table>
</div>

<%@ include file="/includes/footer.jsp" %>
</body>
</html>
