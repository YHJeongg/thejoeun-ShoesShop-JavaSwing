package com.javalec.util;

public class LoginCustId {
	
	public static String custid;
	
	public LoginCustId() {
	
	}

	public static String getCustid() {
		return custid;
	}

	public static String setCustid(String custid) {
		LoginCustId.custid = custid;
		return custid;
	}
	
	
}
