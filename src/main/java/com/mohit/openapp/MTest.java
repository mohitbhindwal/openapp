package com.mohit.openapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.message.BasicNameValuePair;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class MTest {

	static String SERVER_PATH = "http://openapp-bhawsarapp.1d35.starter-us-east-1.openshiftapps.com/request?test=asd";

	public static void main(String[] args) {}
	
	
	static String getResponseFromServer(HashMap map){
		String responsestr = null;
		System.out.println("==>"+map);
		if(map.size()>0){
			ArrayList<NameValuePair> postParameters= new ArrayList<NameValuePair>();
		for(Object keys  : map.keySet()){
			postParameters.add(new BasicNameValuePair(keys.toString(), map.get(keys).toString()));
		}
		
		String responseFromServer = null;
		HttpClient client = new DefaultHttpClient();
		HttpPost postRequest = new HttpPost(SERVER_PATH);
		HttpResponse response;
		try {
			response = client.execute(postRequest);
			if (response != null) {
				System.out.println("Response : " + response);
				int code = response.getStatusLine().getStatusCode();
				System.out.println(response.getStatusLine().getStatusCode());
				if (code == 200) {
					   InputStream ips  = response.getEntity().getContent();
				        BufferedReader buf = new BufferedReader(new InputStreamReader(ips,"UTF-8"));
		 		        StringBuilder sb = new StringBuilder();
				        String s;
				        while(true )
				        {
				            s = buf.readLine();
				            if(s==null || s.length()==0)
				                break;
				            sb.append(s);
				        }
				        responsestr = sb.toString();
				        System.out.println(responsestr);
				        buf.close();
				        ips.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			client.getConnectionManager().shutdown();
		}}else {
			System.out.println("Map is empty");
		}
		return responsestr;
	}

	void postgres() {

		System.out.println("-------- PostgreSQL " + "JDBC Connection Testing ------------");

		try {

			Class.forName("org.postgresql.Driver");

		} catch (ClassNotFoundException e) {

			System.out.println("Where is your PostgreSQL JDBC Driver? " + "Include in your library path!");
			e.printStackTrace();
			return;

		}

		System.out.println("PostgreSQL JDBC Driver Registered!");

		Connection connection = null;

		try {

			connection = DriverManager.getConnection("jdbc:postgresql://172.30.0.1:5432/samajappdb", "samajappdb",
					"samajappdb");

		} catch (SQLException e) {

			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;

		}

		if (connection != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}

	}

}
