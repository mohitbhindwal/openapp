package com.mohit.openapp;

import java.sql.Date;

public class User {
	
	private String userid;
	private String username;
	private String password;
	private String address;
	private String city;
	private String contactno;
	private long profileid;
	private String gender;
	private Date dob;
	

	public User(){
    	
    }
	

	
	@Override
	public String toString() {
		return "User [userid=" + userid + ", username=" + username + ", password=" + password + ", address=" + address
				+ ", city=" + city + ", contactno=" + contactno + ", profileid=" + profileid + ", gender=" + gender
				+ ", dob=" + dob + "]";
	}



	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getId() {
		return userid;
	}
 
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getContactno() {
		return contactno;
	}
	public void setContactno(String contactno) {
		this.contactno = contactno;
	}
	public long getProfileid() {
		return profileid;
	}
	public void setProfileid(long profileid) {
		this.profileid = profileid;
	}

    public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}



	
}
