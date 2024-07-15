package com.ithaka.ufabc.clientcam.rest;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.util.Base64;
import java.util.Calendar;
import java.util.List;

import org.springframework.web.client.RestClient;

import com.ithaka.ufabc.clientcam.request.RequestCamFrames;

public class ClientWrapperGate {
	
	public static void main(String[] args) {
		
		RestClient restClient = RestClient.create();
		
		@SuppressWarnings("unchecked")
		List<String> imagesString = restClient.get()
			.uri("http://localhost:8081/findframes/" + args[0])
			.retrieve()
			.body(List.class);
		
		int imgCt = 0;
		long timestamp = Calendar.getInstance().getTimeInMillis();
		for (String imgBase64 : imagesString) {
			
			byte[] imgBytes = Base64.getDecoder().decode(imgBase64);
			try{
				File fileImage = new File("D:\\Breno\\TCC_Breno\\images\\"+ imgCt + ".jpg");
			    FileOutputStream fos = new FileOutputStream(fileImage);
			     fos.write(imgBytes);
			     FileDescriptor fd = fos.getFD();
			     fos.flush();
			     fd.sync();
			     fos.close(); 
			     
			 }
			 catch(Exception e){
			    String erro = e.toString();
			}
		
			imgCt++;
		}
		
	}

}
