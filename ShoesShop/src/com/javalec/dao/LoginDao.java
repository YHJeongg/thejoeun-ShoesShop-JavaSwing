package com.javalec.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.javalec.dto.LoginDto;
import com.javalec.util.DBConnect;
import com.javalec.util.LoginCustId;

public class LoginDao {

	String custid;
	String cpassword;
	String cname;
	Date cinitdate;
	Date cupdatedate;
	Date cdeletedate;

	public LoginDao() {
		// TODO Auto-generated constructor stub
	}

	public LoginDao(String custid, String cpassword, String cname, Date cinitdate) {
		super();
		this.custid = custid;
		this.cpassword = cpassword;
		this.cname = cname;
		this.cinitdate = cinitdate;
	}

	public LoginDao(String custid, String cpassword, String cname) {
		super();
		this.custid = custid;
		this.cpassword = cpassword;
		this.cname = cname;
	}
	public LoginDao(String custid) {
		super();
		this.custid = custid;
	}

	// 회원가입 등록
	public boolean insertAction() {

		PreparedStatement ps = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// DB불러오기
			Connection conn_mysql = DriverManager.getConnection(DBConnect.url_mysql, DBConnect.id_mysql,
					DBConnect.pw_mysql);// 선언
			Statement stmt_mysql = conn_mysql.createStatement();
			// 불러올 때 java.sql을 불러와야함.

			String query = "insert into customer (custid,cpassword, cname, cinitdate) ";
			String query2 = "values (?,?,?,now())";
			ps = conn_mysql.prepareStatement(query + query2);
			ps.setString(1, custid);
			ps.setString(2, cpassword);
			ps.setString(3, cname);

			ps.executeUpdate();

			conn_mysql.close();// 불러온 DB를 닫아 줘야함.

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public ArrayList<LoginDto> loginAction(String custid) {

		ArrayList<LoginDto> dtoList = new ArrayList<LoginDto>();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// DB불러오기
			Connection conn_mysql = DriverManager.getConnection(DBConnect.url_mysql, DBConnect.id_mysql,
					DBConnect.pw_mysql);// 선언
			Statement stmt_mysql = conn_mysql.createStatement();
			// 불러올 때 java.sql을 불러와야함.

			String query = "select custid, cpassword from customer where custid = " + "'" + custid + "'" + " and cdeletedate IS NULL";

			ResultSet rs = stmt_mysql.executeQuery(query);

			while (rs.next()) {

				String wkCustid = rs.getString(1);
				String wkCpassword = rs.getString(2);

				LoginDto dto = new LoginDto(wkCustid, wkCpassword);
				dtoList.add(dto);

			}

			conn_mysql.close();// 불러온 DB를 닫아 줘야함.

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dtoList;

	}

	public ArrayList<LoginDto> checkDuplicate() {
		
		ArrayList<LoginDto> dtoList = new ArrayList<LoginDto>();

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// DB불러오기
			Connection conn_mysql = DriverManager.getConnection(DBConnect.url_mysql, DBConnect.id_mysql,
					DBConnect.pw_mysql);// 선언
			Statement stmt_mysql = conn_mysql.createStatement();
			// 불러올 때 java.sql을 불러와야함.

			String query = "select custid from customer";

			ResultSet rs = stmt_mysql.executeQuery(query);

			while (rs.next()) {

				String wkCustid = rs.getString(1);

				LoginDto dto = new LoginDto(wkCustid);
				dtoList.add(dto);

			}

			conn_mysql.close();// 불러온 DB를 닫아 줘야함.

		} catch (Exception e) {
			e.printStackTrace();
		}
		return dtoList;
	}
	
}// End
