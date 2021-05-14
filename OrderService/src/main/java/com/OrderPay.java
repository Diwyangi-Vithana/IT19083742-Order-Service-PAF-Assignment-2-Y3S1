package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class OrderPay {
	// A common method to connect to the DB
		private Connection connect() {
			Connection con = null;
			try {
				Class.forName("com.mysql.jdbc.Driver");

				// Provide the correct details: DBServer/DBName, username, password
				con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return con;
		}
	/////////////////////Readpayements
		public String readOrderPay() {
			String output = "";
			try {
				Connection con = connect();
				if (con == null) {
					return "Error while connecting to the database for reading.";
				}
				// Prepare the html table to be displayed
				output = "<table border='1'><tr><th> pay ID </th> <th>order ID</th><th>Product Name<th>Quantity</th>"
						+ "<th> Price</th>" + "<th>Product Description</th>" + "<th>Order Date</th>"
						+ "<th>Payment Method</th>"
						+ "<th>Card Type</th>" + "<th>Card No</th> <th> SSN </th>"
						+ "<th>Card ExpD Date</th> <th>Amount </th>" + "</tr>";

			
				String query = "select * from orderpay p , orders o where  o.orderID = p.orderID";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				// iterate through the rows in the result set
				while (rs.next()) {
					String payID = Integer.toString(rs.getInt("payID"));
					String orderID = Integer.toString(rs.getInt("orderID"));
					String payMethod = rs.getString("payMethod");
					String cardType = rs.getString("cardType");
					String cardNo = rs.getString("cardNo");
					String SSN = rs.getString("SSN");
					String cardExpDate = rs.getString("cardExpDate");
					String amount = rs.getString("amount");
					String productName = rs.getString("productName");
					String quantity = rs.getString("quantity");
					String price = Double.toString(rs.getDouble("price"));
					String desc = rs.getString("prodDesc");
					String orderDate = rs.getString("orderDate");

					// Add into the html table
					output += "<tr><td>" + payID + "</td>";
					output += "<td>" + orderID + "</td>";
					output += "<td>" + productName + "</td>";
					output += "<td>" + quantity + "</td>";
					output += "<td>" + price + "</td>";
					output += "<td>" + desc + "</td>";
					output += "<td>" + orderDate + "</td>";
					output += "<td>" + payMethod + "</td>";
					output += "<td>" + cardType + "</td>";
					output += "<td>" + cardNo + "</td>";
					output += "<td>" + SSN + "</td>";
					output += "<td>" + cardExpDate + "</td>";
					output += "<td>" + amount + "</td></tr>";

				}
				con.close();
				// Complete the html table
				output += "</table>";
			} catch (Exception e) {
				output = "Error while reading the orders.";
				System.err.println(e.getMessage());
			}
			return output;
		}
	// insert Payments
			public String insertPay(String orderID, String payMethod, String cardType, String cardNo, String SSN,
					String cardExpDate, String amount) {
				String output = "";
				try {
					Connection con = connect();
					if (con == null) {
						return "Error while connecting to the database for inserting.";
					}
					// create a prepared statement
					String query = " insert into orderpay(`payID`,`orderID`,`payMethod`,`cardType`,`cardNo`,`SSN`,`cardExpDate`,`amount`)"
							+ " values (?, ?, ?, ?, ?, ?, ?, ?)";
					PreparedStatement preparedStmt = con.prepareStatement(query);
					// binding values
					preparedStmt.setInt(1, 0);
					preparedStmt.setInt(2, Integer.parseInt(orderID));
					preparedStmt.setString(3, payMethod);
					preparedStmt.setString(4, cardType);
					preparedStmt.setInt(5, Integer.parseInt(cardNo));
					preparedStmt.setInt(6, Integer.parseInt(SSN));
					preparedStmt.setString(7, cardExpDate);
					preparedStmt.setDouble(8, Double.parseDouble(amount));
					// execute the statement

					preparedStmt.execute();
					con.close();
					String newOrderPay = readOrderPay();
					output = "{\"status\":\"success\", \"data\": \"" + newOrderPay + "\"}";
				} catch (Exception e) {
					output = "{\"status\":\"error\", \"data\":\"Error while inserting the Order.\"}";
					System.err.println(e.getMessage());
				}
				return output;
			}
}
