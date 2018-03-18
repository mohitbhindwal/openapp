package com.mohit.openapp;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * @author nidhi.neema Client Upload Job thread. It ONLY reads new files from
 *         root folder. It writes status of already proceeded file to file
 *         Processed.txt and add only new files by comparing the entries exist
 *         in Processed.txt. It uses timestamp and file path to test unique.
 */

@SuppressWarnings("deprecation")
public class Test {

	 public static void main(String[] args) {
		 

		 	MultipartEntity multipartEntity = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE);
              File file  = new File("D:/Koala.jpg");
					try {
						multipartEntity.addPart(file.getName(),
								new ByteArrayBody(Files.readAllBytes(Paths.get(file.getAbsolutePath())),
										ContentType.APPLICATION_OCTET_STREAM, file.getName()));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				String urlString = "http://openapp-bhawsarapp.1d35.starter-us-east-1.openshiftapps.com/test";
				HttpClient client = new DefaultHttpClient();
				HttpPost postRequest = new HttpPost(urlString);

				postRequest.setEntity(multipartEntity);
				HttpResponse response;

				try {
					response = client.execute(postRequest);
					if (response != null) {
						System.out.println("Response : " + response);
						int code = response.getStatusLine().getStatusCode();
						System.out.println(response.getStatusLine().getStatusCode());
						if (code == 200) {
							// write all uploaded files to Processed.file
						 
						}
					}
					client.getConnectionManager().shutdown();
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
}
