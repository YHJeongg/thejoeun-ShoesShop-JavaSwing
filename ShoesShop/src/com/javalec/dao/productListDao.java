package com.javalec.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.javalec.dto.productListDto;
import com.javalec.util.DBConnect;
import com.javalec.util.LoginCustId;

public class productListDao {

	String productid;
	String pbrand;
	int pprice;
	String custid;
	int osale;
	String odate;
	int ocount;
	String conname;
	String condata;

	public productListDao() {
		// TODO Auto-generated constructor stub
	}

	// Table Click
	public productListDao(String productid) {
		super();
		this.productid = productid;
	}
	
	public productListDao(String conname, String condata) {
		super();
		this.conname = conname;
		this.condata = condata;
	}

	// 검색 결과를 Table
	public ArrayList<productListDto> selectList() {

		ArrayList<productListDto> dtoList = new ArrayList<productListDto>();

		String whereStatement = "select productid, pbrand, pprice from product";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(DBConnect.url_mysql, DBConnect.id_mysql,
					DBConnect.pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			ResultSet rs = stmt_mysql.executeQuery(whereStatement);

			while (rs.next()) {
				String wkPid = rs.getString(1);
				String skPbrand = rs.getString(2);
				int wkPprice = rs.getInt(3);

				productListDto productListDto = new productListDto(wkPid, skPbrand, wkPprice);
				dtoList.add(productListDto);
			}

			conn_mysql.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return dtoList;

	}

	// 테이블 클릭하였을 때
	public productListDto tableClick() {
		productListDto dto = null;

		String whereStatement = "select productid, pbrand, pprice from product ";
		String whereStatement2 = "where productid = " + "'" + productid + "'";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(DBConnect.url_mysql, DBConnect.id_mysql,
					DBConnect.pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			ResultSet rs = stmt_mysql.executeQuery(whereStatement + whereStatement2);

			if (rs.next()) {
				String wkPid = rs.getString(1);
				String skPbrand = rs.getString(2);
				int wkPprice = rs.getInt(3);

				dto = new productListDto(wkPid, skPbrand, wkPprice);
			}

			conn_mysql.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return dto;
	}

	public ArrayList<productListDto> conditionList() {

		ArrayList<productListDto> dtoList = new ArrayList<productListDto>();

		String whereStatement = "select productid, pbrand, pprice from product ";
		String whereStatement2 = "where " + conname + " like '%" + condata + "%'";

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(DBConnect.url_mysql, DBConnect.id_mysql,
					DBConnect.pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			ResultSet rs = stmt_mysql.executeQuery(whereStatement + whereStatement2);

			while (rs.next()) {
				String wkPid = rs.getString(1);
				String skPbrand = rs.getString(2);
				int wkPprice = rs.getInt(3);
				
				productListDto dto = new productListDto(wkPid, skPbrand, wkPprice);
				dtoList.add(dto);
			}

			conn_mysql.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return dtoList;

	}
	
	// 입력
	public ArrayList<productListDto> insertAction(String productid, String osale, String ocount) {
		
		ArrayList<productListDto> dtoList = new ArrayList<productListDto>();
		
		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(DBConnect.url_mysql, DBConnect.id_mysql, DBConnect.pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			
			String query = "insert into orders (productid, custid, osale, ocount, odate) ";
			String query2 = "values (?, ?, ?, ?, now())";
			
			ps = conn_mysql.prepareStatement(query + query2);
			ps.setString(1, productid);
			ps.setString(2, LoginCustId.getCustid());
			ps.setInt(3, Integer.parseInt(osale) * Integer.parseInt(ocount));
			ps.setInt(4, Integer.parseInt(ocount));
			ps.executeUpdate();
			JOptionPane.showMessageDialog(null, "구매가 완료되었습니다.");
			conn_mysql.close();
			
		} catch (Exception e) {
			e.printStackTrace();
			if(productid.equals("")) {
				JOptionPane.showMessageDialog(null, "상품을 클릭해 주세요.");
			} else {
				JOptionPane.showMessageDialog(null, "구매 수량이 잘못되었습니다.");
			}
		}
		
		return dtoList;
		
	}

}
