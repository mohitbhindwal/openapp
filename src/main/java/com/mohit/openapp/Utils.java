package com.mohit.openapp;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

public class Utils {
	
        	
 

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	public static String registerUser(HashMap map ){
		String result = "SUCCESS";
		User user = new User();
		user.setUserId(map.get("userid").toString());
		user.setUsername(map.get("username").toString());
		user.setPassword(map.get("password").toString());
		user.setCity(map.get("city").toString());
		user.setGender(map.get("gender").toString());
		user.setContactno(map.get("contactno").toString());
		user.setAddress(map.get("address").toString());
		String profilePicPath  = map.get("profilepicpath").toString(); 
		String profilePicName  = map.get("profilepicname").toString(); 
		Long profilepicid =  DBUtils.insertProfilePic(profilePicName, profilePicPath);
	    user.setProfileid(profilepicid);
		DBUtils.saveUserInDB(user);
		return result;
	}
}
