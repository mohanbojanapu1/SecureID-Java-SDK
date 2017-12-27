package com.secureid.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.grit.secureid.CryptoService.EncryptionDecryptionHelper;
import com.secureid.utils.CurlRequest;

/**
 * Description of SecureID
 * 
 * These classes provide methods to Authenticate with the SecureID.
 * 
 * These classes are converted from PHP to java with the reference of Kiran
 * Kumar.M (kiran.mentam@gritinnovation.com)'s Code.
 * 
 * @author Sreevidya Ette (sreevidya.ette@gritinnovation.com)
 */

public class SiDRequestService extends CurlRequest {

	public static String key = "";
	public static String secret = "";
	public static String endpoint = "";
	public String path = "";
	public static String params = "";
	public static String salt = "Uakxx32wzX358WX920B67010j9TjXa35";
	public static String expires = "+2 hours";

	JSONObject data = new JSONObject();
	String request_data = "";
	String content = "";
	String Data1 = "";

	HttpURLConnection conn = null;
	StringEntity input = null;
	String output;

	public void setMethodAndPath(String method, String path) {
		this.method = method;
		this.path = path;
	}

	public String prepare() {
		
		String ss = (String) sidConfig.get("");
		System.out.println("ss+++" + ss);
		data.putAll(sidConfig);
		Data1=data.toJSONString();
		System.out.println("request_data+++" + request_data);
		this.configureUrl();
		return output;
	}

	public String configureUrl() {

		try {
			
			
			HttpClient httpClient = HttpClientBuilder.create().build();
			HttpPost postRequest = new HttpPost((String) sidConfig.get("sidUrl"));
			
			System.out.println("postRequest" + postRequest);
			
			String rawData = Data1;
			String encryptString = EncryptionDecryptionHelper.encrypt(rawData);
			
			System.out.println("encryptString" + encryptString);
			JSONObject reqObj = new JSONObject();
			reqObj.put("request_data", encryptString);
			
			System.out.println("Encrypted Object - " + reqObj);
			
			input = new StringEntity(reqObj.toJSONString());
			
			input.setContentType("application/json");
			postRequest.setEntity(input);
			HttpResponse response = httpClient.execute(postRequest);
			
			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException(
						"Failed : HTTP error code Code : " + response.getStatusLine().getStatusCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader((response.getEntity().getContent())));

			System.out.println("Output from Server .... \n");
			if ((output = br.readLine()) != null) {
				System.out.println("output - " + output);
			}
			
			JSONObject jsonObject = (JSONObject) JSONValue.parse(output);
			String content = (String) jsonObject.get("content");
			
			String decryptStr = EncryptionDecryptionHelper.decrypt(content);
			
			jsonObject.put("content", decryptStr);
			
			output = jsonObject.toJSONString();
			
			System.out.println("Final Response - " + output);

			httpClient.getConnectionManager().shutdown();

		} catch (MalformedURLException e) {

			e.printStackTrace();

		} catch (IOException e) {

			e.printStackTrace();

		}

		System.out.println("endpoint+++" + endpoint);
		System.out.println("+++path" + path);
		return output;
	}

	public String execute() {
		this.prepare();
		return output;
	}

	private Map<String, Object> sidConfig;

	public Map<String, Object> getSidConfig() {
		return sidConfig;
	}

	public void setSidConfig(Map<String, Object> sidConfig) {
		this.sidConfig = sidConfig;
	}


}