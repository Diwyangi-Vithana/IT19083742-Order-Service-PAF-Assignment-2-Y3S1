
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
			output = "<table border='1'; ><tr><th>Order ID</th>"+"<th>Product Name</th><th>Quantity</th>"
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
				output += "<tr><td><input id='hidItemIDUpdate' name='hidItemIDUpdate'type='hidden' value='" + orderID + "'>" + orderID + "</td>";
				output += "<td>" + productName + "</td>";
				output += "<td>" + quantity + "</td>";
				output += "<td>" + price + "</td>";
				output += "<td>" + desc + "</td>";
				output += "<td>" + orderDate + "</td>";
				// buttons
				output += "<td><input name='btnUpdate' type='button' value='Update'class='btnUpdate btn btn-secondary'></td>"
						+ "<td>"
						+ "<input name='btnRemove' type='button' value='Remove'class='btnRemove btn btn-danger'data-itemid = '" + orderID + "'></td>"
						+ "<td><input name='btnBuy' type='submit' value='Buy'class='btn btn-danger' data-itemid = '" + orderID + "'>"
						+ "</td></tr>";
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

	
}
