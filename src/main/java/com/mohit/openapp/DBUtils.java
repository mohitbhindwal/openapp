package com.mohit.openapp;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.commons.io.IOUtils;

public class DBUtils {

	public static long insertProfilePic(String fileName, String filePath) {
		long id = System.currentTimeMillis();
		Connection connection = ConnectionFactory.getConnection();
		try {
			PreparedStatement ps = connection
					.prepareStatement("INSERT INTO images(id,imgname,imgbytes) VALUES (?, ?, ?)");
			ps.setLong(1, id);
			ps.setString(2, fileName);
			ps.setBytes(3, IOUtils.toByteArray(new FileInputStream(new File(filePath))));
			ps.execute();
			ps.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
			id = 0L;
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
				id = 0L;
			}
		}
		return id;
	}

	public static boolean saveUserInDB(User user) {
		boolean result = true;
		Connection connection = ConnectionFactory.getConnection();
		try {

			String sql = "insert into users(userid,username,password,dob,address,contactno,"
					+ "city,gender,profileid values(?,?,?,?,?,?,?,?,?) ";

			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, user.getUserid());
			ps.setString(2, user.getUsername());
			ps.setString(3, user.getPassword());
			ps.setDate(4, user.getDob());
			ps.setString(5, user.getAddress());
			ps.setString(6, user.getContactno());
			ps.setString(7, user.getGender());
			ps.setLong(8, user.getProfileid());
	 		ps.execute();
			ps.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
			result = false;
		} finally {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
				result = false;
			}
		}
		return result;
	}
}
