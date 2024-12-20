<%@ page import="connection.DbConnection" %>
<%@page import="connection.DbConnection"%>
<%@page import="dao.ProductDao"%>
<%@page import="model.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
User auth = (User) request.getSession().getAttribute("auth");
if (auth != null) {
    request.setAttribute("person", auth);
}

String sortField = request.getParameter("sortField");
String sortOrder = request.getParameter("sortOrder");

ProductDao pd = new ProductDao(DbConnection.getConnection(application));
List<Product> products;
if (sortField != null && sortOrder != null) {
    products = pd.getSortedProducts(sortField, sortOrder);
} else {
    products = pd.getAllProducts();
}
ArrayList<Cart> cart_list = (ArrayList<Cart>) session.getAttribute("cart-list");
if (cart_list != null) {
	request.setAttribute("cart_list", cart_list);
}
%>
    
    
<!DOCTYPE html>
<html>
<head>
<title>SoleMate</title>
<%@ include file="/includes/head.jsp"%>
</head>
<body>

<%@ include file="/includes/navbar.jsp" %>

	<div class="container">
		<div class="card-header my-3">All Products</div>
			<form action="index.jsp" method="get">
    			<select name="sortField">
        			<option value="price">Price</option>
        			<option value="name">Name</option>
    			</select>
    			<select name="sortOrder">
        			<option value="ASC">Ascending</option>
        			<option value="DESC">Descending</option>
    			</select>
    			<button type="submit">Sort</button>
			</form>
		<div class="row">
			<%
			if (!products.isEmpty()) {
				for (Product p : products) {
			%>
			<div class="col-md-3 my-3">
				<div class="card w-100">
					<img class="card-img-top" src="product-images/<%=p.getImage() %>"
						alt="Card image cap">
					<div class="card-body">
						<h5 class="card-title"><%=p.getName() %></h5>
						<h6 class="price">Price: $<%=p.getPrice() %></h6>
						<h6 class="category">Category: <%=p.getCategory() %></h6>
						<div class="mt-3 d-flex justify-content-between">
							<a class="btn btn-dark" href="add-to-cart?id=<%=p.getId()%>">Add to Cart</a> <a
								class="btn btn-primary" href="order-now?quantity=1&id=<%=p.getId()%>">Buy Now</a>
						</div>
					</div>
				</div>
			</div>
			<%
			}
			} else {
			out.println("There is no proucts");
			}
			%>

		</div>
	</div>


<%@ include file="/includes/footer.jsp" %>
</body>
</html>