package com.mohit.openapp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

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
		 
	    response.setContentType("text/html;charset=UTF-8");
	     System.out.println("asdasdadasdas" + new File(".").getAbsolutePath());
	    // Create path components to save the file
	    final String path = new File(".").getAbsolutePath();
	    
	    final String fileName =   path + "test.png";//getFileName(request);

	    OutputStream out = null;
	    InputStream filecontent = null;
	    final PrintWriter writer = response.getWriter();
	    
	    
	    writer.println("New file " + fileName + " created at " + path);
	    try {
	        out = new FileOutputStream(new File(path + File.separator
	                + fileName));
	        filecontent = request.getInputStream();

	        int read = 0;
	        final byte[] bytes = new byte[1024];

	        while ((read = filecontent.read(bytes)) != -1) {
	            out.write(bytes, 0, read);
	        }
	        writer.println("New file " + fileName + " created at " + path);
	       System.out.println(  "File{0}being uploaded to {1}" +      new Object[]{fileName, path});
	    } catch (FileNotFoundException fne) {
	        writer.println("You either did not specify a file to upload or are "
	                + "trying to upload a file to a protected or nonexistent "
	                + "location.");
	        writer.println("<br/> ERROR: " + fne.getMessage());

	        System.out.println(  "Problems during file upload. Error: {0}"+
	                new Object[]{fne.getMessage()});
	    } finally {
	        if (out != null) {
	            out.close();
	        }
	        if (filecontent != null) {
	            filecontent.close();
	        }
	        if (writer != null) {
	            writer.close();
	        }
	    }	
	}
	
	private String getFileName(HttpServletRequest request) {
	    final String partHeader = request.getHeader("content-disposition");
	   System.out.println("Part Header ="+ partHeader);
	    for (String content : request.getHeader("content-disposition").split(";")) {
	        if (content.trim().startsWith("filename")) {
	            return content.substring(
	                    content.indexOf('=') + 1).trim().replace("\"", "");
	        }
	    }
	    return null;
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	private void postgres(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{

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

		
		System.out.println("@@@@@@@@"+postgresqlDatabase);
		System.out.println("@@@@@@@@"+postgresqluser);
		System.out.println("@@@@@@@@"+postgresqlpwd);
		Connection connection = null;

		try {

			connection = DriverManager.getConnection(
					"jdbc:postgresql://samajappdb:5432/samajappdb","samajappdb",
					"samajappdb");
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

}
