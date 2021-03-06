
package com;

import java.sql.*;

public class Order {

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

//read order details 
	public String readOrders() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'; ><tr><th>Order ID</th>" + "<th>Product Name</th><th>Quantity</th>"
					+ "<th>Price</th><th>Product Description</th><th>Order Date</th>"
					+ "<th>Update</th><th>Remove</th><th>Buy</th></tr>";

			String query = "select * from orders";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String orderID = Integer.toString(rs.getInt("orderID"));
				String productName = rs.getString("productName");
				String quantity = rs.getString("quantity");
				String price = Double.toString(rs.getDouble("price"));
				String desc = rs.getString("prodDesc");
				String orderDate = rs.getString("orderDate");

				// Add into the html table
				output += "<tr><td><input id='hidOrderIDUpdate' name='hidOrderIDUpdate'type='hidden' value='" + orderID
						+ "'>" + orderID + "</td>";
				output += "<td>" + productName + "</td>";
				output += "<td>" + quantity + "</td>";
				output += "<td>" + price + "</td>";
				output += "<td>" + desc + "</td>";
				output += "<td>" + orderDate + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update'class='btnUpdate btn btn-secondary'></td>"
						+ "<td>"
						+ "<input name='btnRemove' type='button' value='Remove'class='btnRemove btn btn-danger' data-orderid = '"
						+ orderID + "'></td>"
						+ "<td><input name='btnBuy' type='button' value='Buy'class='btn btn-danger' data-Orderid = '"
						+ orderID + "'>" + "</td></tr>";
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

	// add order to whishlist
	public String insertOrder(String productName, String quantity, String price, String desc, String orderDate) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into orders(`orderID`,`productName`,`quantity`,`price`,`prodDesc`,`orderDate`)"
					+ " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, productName);
			preparedStmt.setString(3, quantity);
			preparedStmt.setDouble(4, Double.parseDouble(price));
			preparedStmt.setString(5, desc);
			preparedStmt.setString(6, orderDate);
			// execute the statement

			preparedStmt.execute();
			con.close();
			String newOrders = readOrders();
			output = "{\"status\":\"success\", \"data\": \"" + newOrders + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while inserting the Order.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	// update order details
	public String updateOrder(String orderID, String productName, String quantity, String price, String prodDesc,
			String orderDate)

	{
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for updating.";
			}
			// create a prepared statement
			String query = "UPDATE orders SET productName=?,quantity=?,price=?,prodDesc=?, orderDate=? WHERE orderID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values

			preparedStmt.setString(1, productName);
			preparedStmt.setString(3, quantity);
			preparedStmt.setDouble(2, Double.parseDouble(price));
			preparedStmt.setString(4, prodDesc);
			preparedStmt.setString(5, orderDate);
			preparedStmt.setInt(6, Integer.parseInt(orderID));

			// execute the statement
			preparedStmt.execute();
			con.close();
			String newOrders = readOrders();
			output = "{\"status\":\"success\", \"data\": \"" + newOrders + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while updating the item.\"}";
			System.err.println(e.getMessage());
		}
		return output;

	}

	// delete order
	public String deleteOrder(String orderID) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from orders where orderID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(orderID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newOrders = readOrders();
			output = "{\"status\":\"success\", \"data\": \"" + newOrders + "\"}";
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\":\"Error while updating the item.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}

	

}