package com.mohit.openapp;
 
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class FileUploadUtility {

    static String SERVER_PATH = "http://localhost:7070/ROOT/sdstestsdsd";
    
    public static void main(String[] args) {
		doFileUpload("D:/SAVol5ColorPart1.pdf");
	}

    public static void doFileUpload(final String selectedPath ) {

       
                HttpURLConnection conn = null;
                DataOutputStream dos = null;
                DataInputStream inStream = null;
                String lineEnd = "rn";
                String twoHyphens = "--";
                String boundary = "*****";
                int bytesRead, bytesAvailable, bufferSize;
                byte[] buffer;
                int maxBufferSize = 1 * 1024 * 1024;
                String responseFromServer = "";
                try {
                    //------------------ CLIENT REQUEST
                    FileInputStream fileInputStream = new FileInputStream(new File(selectedPath));
                    // open a URL connection to the Servlet
                    URL url = new URL(SERVER_PATH);
                    // Open a HTTP connection to the URL
                    conn = (HttpURLConnection) url.openConnection();
                    // Allow Inputs
                    conn.setDoInput(true);
                    // Allow Outputs
                    conn.setDoOutput(true);
                    // Don't use a cached copy.
                    conn.setUseCaches(false);
                    // Use a post method.
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Connection", "Keep-Alive");
                    conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);
                    dos = new DataOutputStream(conn.getOutputStream());
                    dos.writeBytes(twoHyphens + boundary + lineEnd);
                    dos.writeBytes("Content-Disposition: form-data; name=\"uploadedfile\";filename=\""
                            + selectedPath + "\"" + lineEnd);
                    dos.writeBytes(lineEnd);
                    // create a buffer of maximum size
                    bytesAvailable = fileInputStream.available();
                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    buffer = new byte[bufferSize];
                    // read file and write it into form...
                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                    while (bytesRead > 0) {
                        dos.write(buffer, 0, bufferSize);
                        bytesAvailable = fileInputStream.available();
                        bufferSize = Math.min(bytesAvailable, maxBufferSize);
                        bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                    }
                    // send multipart form data necesssary after file data...
                    dos.writeBytes(lineEnd);
                    dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);
                    // close streams
                    System.out.println( "File is written");
                    fileInputStream.close();
                    dos.flush();
                    dos.close();

                } catch (MalformedURLException ex) {
                	ex.printStackTrace();
                    return;
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                	return;
                    
                }finally {
					 conn.disconnect();
				}
                 
            }
}
