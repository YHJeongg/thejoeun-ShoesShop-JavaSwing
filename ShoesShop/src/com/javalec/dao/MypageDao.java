package com.javalec.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.javalec.dto.MypageDto;
import com.javalec.dto.productListDto;
import com.javalec.util.DBConnect;
import com.javalec.util.LoginCustId;

public class MypageDao {

	String custid;
	String cpassword;
	String cname;
	String cinitdate;
	String cupdatedate;
	String cdeletedate;

	public MypageDao() {
		// TODO Auto-generated constructor stub
	}

	public MypageDao(String custid, String cpassword, String cname) {
		super();
		this.custid = custid;
		this.cpassword = cpassword;
		this.cname = cname;
	}

	public ArrayList<MypageDto> selectList() {
		ArrayList<MypageDto> dtoList = new ArrayList<MypageDto>();

		String whereStatement = "select custid, cpassword, cname from customer where custid = '" + LoginCustId.getCustid() + "'";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(DBConnect.url_mysql, DBConnect.id_mysql,
					DBConnect.pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();

			ResultSet rs = stmt_mysql.executeQuery(whereStatement);
			
			while (rs.next()) {

				String wkCid = rs.getString(1);
				String wkCp = rs.getString(2);
				String wkCn = rs.getString(3);

				MypageDto dto = new MypageDto(wkCid, wkCp, wkCn);
				dtoList.add(dto);

			}

			conn_mysql.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return dtoList;

	}

	public Boolean updateAction() {

		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(DBConnect.url_mysql, DBConnect.id_mysql,
					DBConnect.pw_mysql);

			String query = "update customer set cpassword = ?, cupdate = now() ";
			String query2 = "where custid = ? ";

			ps = conn_mysql.prepareStatement(query + query2);
			ps.setString(1, cpassword);
			ps.setString(2, LoginCustId.getCustid());
			ps.executeUpdate();

			conn_mysql.close();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}
	
	public Boolean deleteAction() {

		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(DBConnect.url_mysql, DBConnect.id_mysql,
					DBConnect.pw_mysql);

			String query = "update customer set cdeletedate = now() ";
			String query2 = "where custid = ? ";

			ps = conn_mysql.prepareStatement(query + query2);
			ps.setString(1, LoginCustId.getCustid());
			ps.executeUpdate();

			conn_mysql.close();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;

	}

}