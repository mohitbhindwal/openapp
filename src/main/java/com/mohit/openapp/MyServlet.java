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
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

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
		 System.out.println("Got");
		 File file ;
		   int maxFileSize = 10000 * 1024;
		   int maxMemSize = 10000 * 1024;
		   ServletContext context = request.getServletContext();
		   String filePath =  new File(".").getAbsolutePath();   //context.getInitParameter("file-upload");
		   String contentType = request.getContentType();
		   if ((contentType.indexOf("multipart/form-data") >= 0)) {
		      DiskFileItemFactory factory = new DiskFileItemFactory();
		      // maximum size that will be stored in memory
		      factory.setSizeThreshold(maxMemSize);
		      // Location to save data that is larger than maxMemSize.
		      factory.setRepository(new File("D:\\temp"));

		      // Create a new file upload handler
		      ServletFileUpload upload = new ServletFileUpload(factory);
		      // maximum file size to be uploaded.
		      upload.setSizeMax( maxFileSize );
		      try{ 
		         // Parse the request to get file items.
		         List fileItems = upload.parseRequest(request);

		         // Process the uploaded file items
		         Iterator i = fileItems.iterator();

		    
		         while ( i.hasNext () ) 
		         {
		            FileItem fi = (FileItem)i.next();
		            if ( !fi.isFormField () )	
		            {
		            // Get the uploaded file parameters
		            String fieldName = fi.getFieldName();
		            String fileName = fi.getName();
		            boolean isInMemory = fi.isInMemory();
		            long sizeInBytes = fi.getSize();
		            // Write the file
		            if( fileName.lastIndexOf("\\") >= 0 ){
		            file = new File( filePath + 
		            fileName.substring( fileName.lastIndexOf("\\"))) ;
		            }else{
		            file = new File( filePath + 
		            fileName.substring(fileName.lastIndexOf("\\")+1)) ;
		            }
		            fi.write( file ) ;
		            
		            System.out.println("@@@@@@"+file.getAbsolutePath());
		            
		          //  Integer imageid =   SamajUtils.insertImage(filePath+fileName, fileName, filePath+fileName,"m");
		          //  out.print(imageid);
		          //  out.println("Uploaded Filename: " + filePath + fileName + "<br>");
		            }
		         }
		         
		        
		      }catch(Exception ex) {
		    	  ex.printStackTrace();
		         System.out.println(ex);
		      }
		   }else{
		      new RuntimeException("klklkl");
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
