package com.mohit.openapp;

 
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class MyServlet
 */
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	     * @see HttpServlet#HttpServlet()
	     */
	    public DownloadServlet() {
	        super();
	        // TODO Auto-generated constructor stub
	    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException { 
		System.out.println("Reguest received");
		   response.setContentType("text/html;charset=UTF-8");

		    // Create path components to save the file
		    final String path = "D:/test/";// request.getParameter("destination");
		    final Part filePart = request.getPart("myFile");
		    final String fileName = getFileName(filePart);

		    OutputStream out = null;
		    InputStream filecontent = null;
		    final PrintWriter writer = response.getWriter();

		    try {
		        out = new FileOutputStream(new File(path + File.separator
		                + fileName));
		        filecontent = request.getInputStream();

		        int read = 0;
		        final byte[] bytes = new byte[1024];

		        while ((read = filecontent.read(bytes)) != -1) {
		            out.write(bytes, 0, read);
		        }
				System.out.println("New file " + fileName + " created at " + path);
		      System.out.println("File{0}being uploaded to "+  new Object[]{fileName, path});
		    } catch (FileNotFoundException fne) {
				System.out.println("You either did not specify a file to upload or are "
		                + "trying to upload a file to a protected or nonexistent "
		                + "location.");
				System.out.println("<br/> ERROR: " + fne.getMessage());

		        System.out.println( "Problems during file upload. Error: "+           new Object[]{fne.getMessage()});
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
	
	private String getFileName(final Part part) {
	    final String partHeader = part.getHeader("content-disposition");
	   System.out.println("Part Header ="+ partHeader);
	    for (String content : part.getHeader("content-disposition").split(";")) {
	        if (content.trim().startsWith("filename")) {
	            return content.substring(
	                    content.indexOf('=') + 1).trim().replace("\"", "");
	        }
	    }
	    return null;
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
