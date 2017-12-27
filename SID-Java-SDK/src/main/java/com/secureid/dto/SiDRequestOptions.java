package com.secureid.dto;

import java.io.Serializable;

public class SiDRequestOptions implements Serializable {

	private String senderid;
	private String sender;
	private String request_type;
	private String redirect_url;
	private String salt;
	private String redirect_headers;
	private String request_to;// mobile_no;
	private String secure_request_no;
	private String amount;
	public String getSidUrl() {
		return sidUrl;
	}

	public void setSidUrl(String sidUrl) {
		this.sidUrl = sidUrl;
	}

	private String sidUrl;
	
	public String getSenderid() {
		return senderid;
	}

	public String setSenderid(String senderid) {
		return this.senderid = senderid;
	}

	public String getSender() {
		return sender;
	}

	public String setSender(String sender) {
		return this.sender = sender;
	}

	public String getRequest_type() {
		return request_type;
	}

	public String setRequest_type(String request_type) {
		return this.request_type = request_type;
	}

	public String getRedirect_url() {
		return redirect_url;
	}

	public String setRedirect_url(String redirect_url) {
		return this.redirect_url = redirect_url;
	}

	public String getSalt() {
		return salt;
	}

	public String setSalt(String salt) {
		return this.salt = salt;
	}

	public String getRedirect_headers() {
		return redirect_headers;
	}

	public String setRedirect_headers(String redirect_headers) {
		return this.redirect_headers = redirect_headers;
	}

	public String getRequest_to() {
		return request_to;
	}

	public void setRequest_to(String request_to) {
		this.request_to = request_to;
	}

	public String getSecure_request_no() {
		return secure_request_no;
	}

	public void setSecure_request_no(String secure_request_no) {
		this.secure_request_no = secure_request_no;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}
}