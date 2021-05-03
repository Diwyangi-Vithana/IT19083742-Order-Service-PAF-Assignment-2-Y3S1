<%@page import="com.Order"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Order Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/items.js"></script>
</head>
<body>
<div class="container">
		<div class="row">
			<div class="col-6">
				<h1>Order Management </h1>
				<form id="formOrder" name="formOrder" method = "post" action = "Orders.jsp">
					Product Name: 
					<input id="productName" name="productName" type="text"class="form-control form-control-sm"> <br> 
					Quantity:
					<input id="quantity" name="quantity" type="text"class="form-control form-control-sm"> <br> 
					Price: 
					<input id="price" name="price" type="text"class="form-control form-control-sm"> <br>
					Product Description: 
					<input id="prodDesc" name="prodDesc" type="text"class="form-control form-control-sm"> <br> 
					Ordered Date: 
					<input id="orderDate" name="orderDate" type="date"class="form-control form-control-sm"> <br> 
					
					<input id="btnSave" name="btnSave" type="button" value="Save"class="btn btn-primary">
					
					<input type="hidden"id="hidItemIDSave" name="hidItemIDSave" value="">
					 
				</form>
				
				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<div id="divItemsGrid">
					<%
					Order orderObj = new Order();
					out.print(orderObj.readOrders());
					%>
				</div>
			</div>
		</div>
	</div>
</body>
</html>