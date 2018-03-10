package com.mohit.openapp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MyServlet
 */
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String postgresqlDatabase = System.getenv("POSTGRESQL_DATABASE");
		String postgresqlpwd = System.getenv("POSTGRESQL_PASSWORD");
		String postgresqluser = System.getenv("POSTGRESQL_USER");
		
		
		response.getWriter().append("qweqweqe"+postgresqlDatabase);
		response.getWriter().append("qweqweqe"+postgresqlpwd);
		response.getWriter().append("qweqweqe"+postgresqluser);
		
		response.getWriter().append("HI Mohit ").append(request.getContextPath());
 
		
		System.out.println("-------- PostgreSQL "
				+ "JDBC Connection Testing ------------");
		response.getWriter().append("HI Mohit ZZZZZZZZZZZZ");
		try {

			Class.forName("org.postgresql.Driver");
			response.getWriter().append("HI Mohit 222222222222");
		} catch (ClassNotFoundException e) {

			System.out.println("Where is your PostgreSQL JDBC Driver? "
					+ "Include in your library path!");
			e.printStackTrace();
			return;

		}

		System.out.println("PostgreSQL JDBC Driver Registered!");

		Connection connection = null;

		try {

			connection = DriverManager.getConnection(
					"jdbc:postgresql://samajappdb:5432/"+postgresqlDatabase,postgresqluser,
					postgresqlpwd);
			response.getWriter().append("HI Mohit 3333333333333333333333333");
		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;

		}

		if (connection != null) {
			System.out.println("You made it, take control your database now!");
			response.getWriter().append("HI Mohit 666666666666666");
		} else {
			System.out.println("Failed to make connection!");
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
