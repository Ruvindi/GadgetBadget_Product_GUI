package model;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/ProductAPI")
public class ProductAPI extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    Product product_ob = new Product();
	
    public ProductAPI() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String outputString = product_ob.insertProduct(
				request.getParameter("productCode"), 
				request.getParameter("projectName"),
				request.getParameter("projectDesc"), 
				request.getParameter("price")
				);
				
		
		
		response.getWriter().write(outputString);
	}

	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Map paras = getParasMap(request);
		
		String output = product_ob.updateProduct(
				paras.get("productID").toString(), 
				paras.get("productCode").toString(), 
				paras.get("projectName").toString(), 
				paras.get("projectDesc").toString(), 
				paras.get("price").toString()
				
				); 
			
				response.getWriter().write(output); 
		
		
		
	}

	
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Map paras = getParasMap(request);
		
		 String output = product_ob.deleteProduct(paras.get("productID").toString()); 
		 response.getWriter().write(output);
	}
	

	private static Map getParasMap(HttpServletRequest request) 
	{ 
	 Map<String, String> map = new HashMap<String, String>(); 
	 
	try
	 { 
	 Scanner scanner = new Scanner(request.getInputStream(), "UTF-8"); 
	 String queryString = scanner.hasNext() ? 
			 
	 scanner.useDelimiter("\\A").next() : ""; 
	 scanner.close();
	 
	 String[] params = queryString.split("&"); 
	 for (String param : params) 
	 { 
		 String[] p = param.split("=");
		 map.put(p[0], p[1]); 
		 } 
		 } 
		catch (Exception e) 
		 { 
			
		 } 
		return map; 
		}


}
