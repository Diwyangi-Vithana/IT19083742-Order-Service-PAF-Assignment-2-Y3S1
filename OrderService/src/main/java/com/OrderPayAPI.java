package com;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/OrderPayAPI")
public class OrderPayAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	OrderPay OrderPayObj = new OrderPay() ;   

    public OrderPayAPI() {
      
    }

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
String output = OrderPayObj.insertPay( 
				
				request.getParameter("orderID"),
				request.getParameter("payMethod"),
				request.getParameter("cardType"),
				request.getParameter("cardNo"),
				request.getParameter("SSN"),
				request.getParameter("cardExpDate"),
				request.getParameter("amount"));
		
		response.getWriter().write(output);
	}



}
