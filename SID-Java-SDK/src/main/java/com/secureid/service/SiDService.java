package com.secureid.service;

import java.util.HashMap;
import java.util.Map;

import com.secureid.dto.SiDRequestOptions;

/**
 * Description of SecureID
 * 
 * These classes provides methods to Authenticate with the SecureId.
 * 
 * These classes are converting from PHP to java with the reference of Kiran
 * Kumar.M (kiran.mentam@gritinnovation.com)'s Code.
 * 
 * @author Sreevidya Ette (sreevidya.ette@gritinnovation.com)
 */

public class SiDService {

	String data;

	public Map<String, Object> params = new HashMap<String, Object>();
	public static String key = "46";
	public static String secret = "911911911";
	public static String endpoint = "http://pay2u.grit.im:90/api/1.0/json/requests/secureAuthRequest";
	public static String request_type = null;
	public static String security_salt = "Uakxx32wzX358WX920B67010j9TjXa35";
	public static String redirect_url = "http://localhost:8080/Secureid-SDK/SecureIDSDK/appAuthCallback.jsp";

	public String request(SiDRequestOptions opt, String request_type, String execute) {
		execute = "SUCCESS";

		String redirect_headers = "Content-Type: application/json";
		String request_to = opt.getRequest_to();
		String senderid = opt.getSenderid();
		System.out.println("senderid" + senderid); 
		params.put("request_to", request_to);
		String secure_request_no = opt.getSecure_request_no();
		params.put("secure_request_no", secure_request_no);
		params.put("senderid", senderid);
		params.put("sender", secret);
		params.put("request_type", request_type);
		params.put("redirect_url", redirect_url);
		params.put("redirect_headers", redirect_headers);
		params.put("sidUrl", opt.getSidUrl());

		System.out.println("SID Server URL - " + opt.getSidUrl());

		SiDRequestService request = new SiDRequestService();
		request.setSidConfig(params);

		data = request.execute();
		return data;
	}

	public String authRequest(SiDRequestOptions opt) {
		System.out.println("authRequest");
		Map<String, Object> checkresponse = null;
		checkresponse = new HashMap<String, Object>();
		checkresponse = paramsValidations(opt, "AUTH");
		if (checkresponse.get("status").equals("SUCCESS")) {
			request(opt, "AUTH", "SUCCESS");

		} else {
			request(opt, "AUTH", "Failure");
		}
		return data;
	}

	public String payRequest(SiDRequestOptions opt) {
		Map<String, Object> checkresponse = null;
		checkresponse = new HashMap<String, Object>();
		checkresponse = paramsValidations(opt, "PAY");
		if (checkresponse.get("status").equals("SUCCESS")) {
			return request(opt, "AUTH", "SUCCESS");

		} else {
			return request(opt, "AUTH", "FAILURE");
		}

	}

	public Map<String, Object> paramsValidations(SiDRequestOptions opt, String type) {
		System.out.println("paramsValidations");
		Map<String, Object> responce = null;
		responce = new HashMap<String, Object>();
		if (opt.getRequest_to() == null && !opt.getRequest_to().equals("")) {
			responce = new HashMap<String, Object>();
			responce.put("status", "ERROR");
			responce.put("message", "Invalid params request_to");
			return responce;
		}
		if (opt.getSecure_request_no() == null && !opt.getSecure_request_no().equals("")) {
			responce = new HashMap<String, Object>();
			responce.put("status", "ERROR");
			responce.put("message", "Invalid params secure_request_no");
			return responce;
		}
		if (type == "PAY") {
			if (opt.getAmount() != null && !opt.getAmount().equals("")) {
				responce = new HashMap<String, Object>();
				responce.put("status", "ERROR");
				responce.put("message", "Invalid params secure_request_no");
				return responce;
			}
		}
		responce.put("status", "SUCCESS");
		return responce;

	}

}