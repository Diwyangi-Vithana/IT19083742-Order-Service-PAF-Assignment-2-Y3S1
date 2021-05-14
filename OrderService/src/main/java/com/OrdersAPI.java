package com;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/OrdersAPI")
public class OrdersAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Order OrderObj = new Order() ;

    public OrdersAPI() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String output = OrderObj.insertOrder( 
				
				request.getParameter("productName"),
				request.getParameter("quantity"),
				request.getParameter("price"),
				request.getParameter("prodDesc"),
				request.getParameter("orderDate"));
		
		response.getWriter().write(output);
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request);
		
		String output = OrderObj.updateOrder(
				paras.get("orderID").toString(),
				paras.get("productName").toString(),
				paras.get("quantity").toString(),
				paras.get("price").toString(),
				paras.get("prodDesc").toString(),
				paras.get("orderDate").toString());
		response.getWriter().write(output);
	}
	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map paras = getParasMap(request);
		String output = OrderObj.deleteOrder(
				paras.get("orderID").toString());
		response.getWriter().write(output);

	}
	// Convert request parameters to a Map
			private static Map getParasMap(HttpServletRequest request) {
				Map<String, String> map = new HashMap<String, String>();
				try {
					Scanner scanner = new Scanner(request.getInputStream(), "UTF-8");
					String queryString = scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
					scanner.close();
					String[] params = queryString.split("&");
					for (String param : params) {

						String[] p = param.split("=");
						map.put(p[0], p[1]);
					}
				} catch (Exception e) {
				}
				return map;
			}
}
