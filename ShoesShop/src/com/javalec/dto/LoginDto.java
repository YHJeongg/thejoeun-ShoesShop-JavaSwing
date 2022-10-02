package com.javalec.dto;

import java.sql.Timestamp;

public class LoginDto {
	
	String custid;
	String cpassword;
	String cname;
	java.sql.Timestamp cinitdate;
	java.sql.Timestamp cupdatedate;
	java.sql.Timestamp cdeletedate;
	
	
	
	public LoginDto() {
		// TODO Auto-generated constructor stub
	}



	public LoginDto(String custid, String cpassword, String cname, Timestamp cinitdate, Timestamp cupdatedate,
			Timestamp cdeletedate) {
		super();
		this.custid = custid;
		this.cpassword = cpassword;
		this.cname = cname;
		this.cinitdate = cinitdate;
		this.cupdatedate = cupdatedate;
		this.cdeletedate = cdeletedate;
	}
	



	public LoginDto(String custid, String cpassword) {
		super();
		this.custid = custid;
		this.cpassword = cpassword;
	}

	

	public LoginDto(String custid) {
		super();
		this.custid = custid;
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



	public java.sql.Timestamp getCinitdate() {
		return cinitdate;
	}



	public void setCinitdate(java.sql.Timestamp cinitdate) {
		this.cinitdate = cinitdate;
	}



	public java.sql.Timestamp getCupdatedate() {
		return cupdatedate;
	}



	public void setCupdatedate(java.sql.Timestamp cupdatedate) {
		this.cupdatedate = cupdatedate;
	}



	public java.sql.Timestamp getCdeletedate() {
		return cdeletedate;
	}



	public void setCdeletedate(java.sql.Timestamp cdeletedate) {
		this.cdeletedate = cdeletedate;
	}
	
	
	
		
}
