package com.mohit.openapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ClientServlet
 */
public class ClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		 
		 String moto = request.getParameter("moto");
		
		 if(moto == "registeruser"){
			 registerUser(request, response);
		 }
		
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private static String registerUser(HttpServletRequest request, HttpServletResponse response){
		String result = "SUCCESS";
		try {
		PrintWriter out;
		out = response.getWriter();
		
		Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            out.write("##########");
            out.write("paramName"+paramName);
             String[] paramValues = request.getParameterValues(paramName);
            for (int i = 0; i < paramValues.length; i++) {
                String paramValue = paramValues[i];
                out.write("paramValue = " + paramValue);
               
            }
        }
        
        User user = new User();
        user.setUserid(request.getParameter("userid"));
        user.setUsername(request.getParameter("username"));
        user.setGender(request.getParameter("gender"));
        user.setCity(request.getParameter("city"));
        user.setAddress(request.getParameter("address"));
        user.setPassword(request.getParameter("password"));
        user.setProfileid(System.currentTimeMillis());
        
        System.out.println(user);
        DBUtils.saveUserInDB(user);   
        out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

}
