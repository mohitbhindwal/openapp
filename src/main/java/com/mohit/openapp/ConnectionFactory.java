package com.mohit.openapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.commons.io.IOUtils;
import org.postgresql.ds.PGPoolingDataSource;

public class ConnectionFactory {

	private static ConnectionFactory INSTANCE = new ConnectionFactory();
	private static PGPoolingDataSource source;

	private ConnectionFactory() {

		source = new PGPoolingDataSource();
		source.setDataSourceName("samajappdb");
		source.setServerName("samajappdb:5432"); 
		source.setDatabaseName("samajappdb");  
		source.setUser("samajappdb");  
		source.setPassword("samajappdb");  
		source.setMaxConnections(25);
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	static public Connection getConnection() {
		Connection c = null;
		try {
			c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/bhawsarsamaj", "postgres", "postgres");
			c = source.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
			source.close();
			source = new PGPoolingDataSource();
			source.setDataSourceName("A Data Source");
			source.setServerName("127.7.240.130:5432"); // localhost:5444
			source.setDatabaseName("bhawsarsamaj"); // bhawsarsamaj
			source.setUser("adminbubmaeh"); // postgres
			source.setPassword("Tf3KcHb6XpGc"); // postgres
			source.setMaxConnections(25);
			try {
				Class.forName("org.postgresql.Driver");
			} catch (ClassNotFoundException ce) {
				ce.printStackTrace();
			}
		}

		return c;
	}

	public static void destroyPool() {
		if (source != null) {
			source.close();
		}
	}

	public static void main(String[] args) throws Exception, UnsupportedEncodingException {
		System.out.println();
		Connection connection = ConnectionFactory.getConnection();
		Statement statement = connection.createStatement();
		

		  PreparedStatement ps2 = connection.prepareStatement("INSERT INTO img(imgoid,imgname,imgpath) VALUES (?, ?, ?)");
		    ps2.setBytes(1, IOUtils.toByteArray(new FileInputStream(new File("D:/Desert.jpg"))));
		    ps2.setString(2, "asd2");
	    	ps2.setString(3, "test2");
	    	ps2.execute();
	    	ps2.close();
		PreparedStatement ps = connection.prepareStatement("SELECT imgoid FROM img WHERE id = ?");
	    ps.setInt(1, 2);
	    byte[] byteImg = null;
		ResultSet rs = ps.executeQuery();
		while (rs.next()) {
			   byteImg = rs.getBytes(1);
		}
			 IOUtils.write(byteImg ,   new FileOutputStream(new File("D:/yahoo2.jpg")));
            ps2.close();
	
	    connection.close();
	}

}
