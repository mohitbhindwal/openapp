package com.mohit.openapp;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 System.out.println("Request received "+ getClass().getName());
		 File file ;
		   int maxFileSize = 10000 * 1024;
		   int maxMemSize = 10000 * 1024;
		   ServletContext context = request.getServletContext();
		   String filePath = context.getInitParameter("file-upload");
		   String contentType = request.getContentType();
		   System.out.println("server filePath dir = "+filePath);
		   
		   if(!new File(filePath).exists())
			   new File(filePath).mkdirs();
		   
		   
		   if ((contentType.indexOf("multipart/form-data") >= 0)) {
		      DiskFileItemFactory factory = new DiskFileItemFactory();
		      // maximum size that will be stored in memory
		      factory.setSizeThreshold(maxMemSize);
		      // Location to save data that is larger than maxMemSize.
		      factory.setRepository(new File("/home/jboss/tmp/"));
		      // Create a new file upload handler
		      ServletFileUpload upload = new ServletFileUpload(factory);
		      // maximum file size to be uploaded.
		      upload.setSizeMax( maxFileSize );
		      try{ 
		         List fileItems = upload.parseRequest(request);
		         Iterator i = fileItems.iterator();
		         while ( i.hasNext () ) 
		         {
		            FileItem fi = (FileItem)i.next();
		            if ( !fi.isFormField () )	
		            {
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
		            System.out.println("File path is "+file.getAbsolutePath());
		            HashMap map = new HashMap();
		            map.put("userid",request.getParameter("userid"));
		            map.put("username",request.getParameter("username"));
		            map.put("password",request.getParameter("password"));
		            map.put("address",request.getParameter("address"));
		            map.put("city",request.getParameter("city"));
		            map.put("contactno",request.getParameter("contactno"));
		            map.put("gender",request.getParameter("gender"));
		            map.put("dob",request.getParameter("dob"));
		            map.put("profilepicname",file.getName());
		            map.put("profilepicpath",file.getAbsolutePath());
                    System.out.println(map);		            
                    Utils.registerUser(map);
		            }
		         }
		         System.out.println("done");
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
					"jdbc:postgresql://samajappdb:5432/samajappdb","samajappdb",
					"samajappdb");
			response.getWriter().append("con ");
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
