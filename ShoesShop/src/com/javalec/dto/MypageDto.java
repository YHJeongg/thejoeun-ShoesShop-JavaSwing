package com.javalec.dto;

public class MypageDto {
	// fields
	String custid;
	String cpassword;
	String cname;

	public MypageDto() {

	}

	public MypageDto(String custid, String cpassword, String cname) {
		super();
		this.custid = custid;
		this.cpassword = cpassword;
		this.cname = cname;
	}

	public String getCustid() {
		return custid;
	}

	public void setCustid(String custid) {
		this.custid = custid;
	}

	public String getCpassword() {
		return cpassword;
	}

	public void setCpassword(String cpassword) {
		this.cpassword = cpassword;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}



}