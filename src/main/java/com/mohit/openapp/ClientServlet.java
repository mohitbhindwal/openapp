package com.mohit.openapp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

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
		PrintWriter out =  response.getWriter();
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
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
			        out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
