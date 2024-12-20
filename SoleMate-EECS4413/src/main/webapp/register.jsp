<%@page import="model.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
    User auth = (User) request.getSession().getAttribute("auth");
    if (auth != null) {
        response.sendRedirect("index.jsp");
    }
    ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
    if (cart_list != null) {
        request.setAttribute("cart_list", cart_list);
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
    <%@ include file="/includes/head.jsp"%>
</head>
<body>

<%@ include file="/includes/navbar.jsp" %>

<div class="container">
    <div class="card w-50 mx-auto my-5">
        <div class="card-header text-center">User Registration</div>
        <div class="card-body">
            <form action="user-register" method="post">
                <div class="form-group">
                    <label>Name</label> 
                    <input type="text" name="register-name" class="form-control" placeholder="Enter your name">
                </div>
                <div class="form-group">
                    <label>Email address</label> 
                    <input type="email" name="register-email" class="form-control" placeholder="Enter email">
                </div>
                <div class="form-group">
                    <label>Password</label> 
                    <input type="password" name="register-password" class="form-control" placeholder="Password">
                </div>
                <div class="text-center">
                    <button type="submit" class="btn btn-primary">Register</button>
                </div>
            </form>
        </div>
    </div>
</div>

<%@ include file="/includes/footer.jsp" %>
</body>
</html>
