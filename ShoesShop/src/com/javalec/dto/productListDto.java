package com.javalec.dto;

public class productListDto {

	String productid;
	String pbrand;
	int pprice;
	String custid;
	int osale;
	String odate;
	int ocount;

	public productListDto() {
		// TODO Auto-generated constructor stub
	}

	// Select List
	public productListDto(String productid, String pbrand, int pprice) {
		super();
		this.productid = productid;
		this.pbrand = pbrand;
		this.pprice = pprice;
	}

	public String getProductid() {
		return productid;
	}

	public void setProductid(String productid) {
		this.productid = productid;
	}

	public String getPbrand() {
		return pbrand;
	}

	public void setPbrand(String pbrand) {
		this.pbrand = pbrand;
	}

	public int getPprice() {
		return pprice;
	}

	public void setPprice(int pprice) {
		this.pprice = pprice;
	}

	public String getCustid() {
		return custid;
	}

	public void setCustid(String custid) {
		this.custid = custid;
	}

	public int getOsale() {
		return osale;
	}

	public void setOsale(int osale) {
		this.osale = osale;
	}

	public String getOdate() {
		return odate;
	}

	public void setOdate(String odate) {
		this.odate = odate;
	}

	public int getOcount() {
		return ocount;
	}

	public void setOcount(int ocount) {
		this.ocount = ocount;
	}

}
