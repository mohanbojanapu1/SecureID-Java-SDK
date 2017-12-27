package com.secureid.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CurlRequest {

	public String method = "POST";
	public String url = null;
	public String headers = "Content-Type: application/json";
	public String fields;
	public String files[];
	public String curlOptions[];

	public Object construct() {
		System.out.println("CurlRequest");

		Map<String, String> myMap1 = new HashMap<String, String>();

		String key = "";
		String val = "";

		List<Map<String, String>> attributes = new ArrayList<Map<String, String>>();

		myMap1.put("key", "key");
		myMap1.put("val", "val");

		attributes.add(myMap1);

		for (Map<String, String> map : attributes) {
			key = map.get(key);
			val = map.get(val);
		}

		System.out.println("Key: " + key + "Value: " + val);
		return attributes;
	}

}
