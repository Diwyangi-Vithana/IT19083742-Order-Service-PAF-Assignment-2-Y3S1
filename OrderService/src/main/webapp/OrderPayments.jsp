<%@page import="com.OrderPay"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>OrderPay Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/OrderPay.js"></script>
</head>
<body>
<div class="container">
		<div class="row">
			<div class="col-6">
				<h1>Order Payment Details</h1>
				<form id="formOrderPay" name="formOrderPay" method="post"
					action="OrderPays.jsp">
					Order ID: <input id="OrderPayID" name="OrderPayID"
						type="text" class="form-control form-control-sm"> <br>
					Payment Method: <input id="payMethod" name="payMethod" type="text"
						class="form-control form-control-sm"> <br>
					Card Type: <input id="cardType" name="cardType" type="text"
						class="form-control form-control-sm"> <br> 
					Card No: <input id="cardNo" name="cardNo" type="text"
						class="form-control form-control-sm"> <br>
					SSN: <input id="SSN" name="SSN" type="text"
						class="form-control form-control-sm"> <br>	
					Card Exp date: <input id="cardExpDate" name="cardExpDate" type="date"
						class="form-control form-control-sm"> <br>		
					Amount: <input id="amount" name="amount" type="text"
						class="form-control form-control-sm"> <br>		
						
						 <input id="btnSave" name="btnSave" type="button" value="Save"
						class="btn btn-primary"> <input type="hidden"
						id="hidOrderPayIDSave" name="hidOrderPayIDSave" value="">

				</form>

				<div id="alertSuccess" class="alert alert-success"></div>
				<div id="alertError" class="alert alert-danger"></div>
				<br>
				<div id="divOrderPaysPayGrid">
					<%
					OrderPay OrderPayObj = new OrderPay();
					out.print(OrderPayObj.readOrderPay());
					%>
				</div>
			</div>
		</div>
	</div>
</body>
</html>