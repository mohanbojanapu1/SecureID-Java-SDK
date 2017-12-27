package com.secureid;

import java.util.Map;
import java.util.Random;

import com.secureid.dto.SiDRequestOptions;
import com.secureid.service.SiDService;

public class SecureID {
	
	String data;
	
	/**
	 * Auth Request method for the Secure ID
	 * - Sends the authentication request to secure id
	 * 
	 * @param user_mobileno
	 * @return
	 */
	public String authRequest(Map<String, Object> sidConfig, String userMobileNo) {

		System.out.println("Request to - " + sidConfig);
		
		Random random = new Random(); // Generate a number from 1-10 using java.util.Random
		int randomNumber = random.nextInt(1000) + 1; // nextInt gives you a number from 0-9 & adding 1 gives you 1-10
		System.out.println("Transaction Number - " + randomNumber);
		
		SiDRequestOptions opt = new SiDRequestOptions();
		opt.setRequest_to(userMobileNo);
		opt.setSender(sidConfig.get("merchantIdMobNo").toString());
		opt.setSenderid(sidConfig.get("merchantId").toString());
		opt.setSidUrl(sidConfig.get("sidServerURL").toString());
		opt.setSecure_request_no(String.valueOf(randomNumber));
		
		SiDService req = new SiDService();
		data = req.authRequest(opt);
		
		System.out.println("Response Data - " + data);
		
		return data;
	}

}
