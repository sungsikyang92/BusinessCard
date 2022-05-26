package kr.or.connect.bCard;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class BusinessCardDao {
	private static String dbUrl = "jdbc:mysql://localhost:3306/private?serverTimezone=UTC";
	private static String dbUser = "ssy";
	private static String dbPassword = "1234";

	Scanner sc = new Scanner(System.in);
	BusinessCard businessCard = new BusinessCard();
	
	public void mainMenu() {
		while (true) {		
			System.out.println("-----------------------");
			System.out.println("1. 명함입력 ");
			System.out.println("2. 명함검색 ");
			System.out.println("3. 종료 ");
			System.out.println("4. 전체출력");
			System.out.println("-----------------------");
			System.out.println("메뉴를 입력하세요 ");
			int sel = sc.nextInt();
			sc.nextLine();
			switch (sel) {
			case 1:
				System.out.println("이름을 입력하세요 : ");
				String i_name = sc.nextLine();
				System.out.println("전화번호를 입력하세요 : ");
				String i_phone = sc.nextLine();
				System.out.println("회사 이름을 입력하세요 : ");
				String i_companyName = sc.nextLine();
				businessCard = new BusinessCard(i_name, i_phone, i_companyName);
				addBusinessCard(businessCard);
				break;
			case 2:
				System.out.println("검색할 이름을 입력하세요. (like검색) : ");
				String s_name = sc.nextLine();
				System.out.println(searchByName(s_name));
				break;
			case 3: 
				System.out.println("전체 출력합니다. "); 
				printAll();
				break;
			case 4: System.out.println("종료합니다. "); return;
			default: System.out.println("잘못된 입력입니다. "); break;
			}
		}
	}
	
	public void printAll() {
		businessCard = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String sql = "SELECT name, phone, companyName, createDate FROM business_card";
		try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
				PreparedStatement ps = con.prepareStatement(sql)){
			try (ResultSet rs = ps.executeQuery()){
				while(rs.next()) {
					String a_name = rs.getString(1);
					String a_phone = rs.getString(2);
					String a_companyName = rs.getString(3);
					Date a_createDate = rs.getDate(4); 
					businessCard = new BusinessCard(a_name, a_phone, a_companyName, a_createDate);
					System.out.println(businessCard);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public BusinessCard searchByName(String s_name) {
		businessCard = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String sql = "SELECT name, phone, companyName, createDate FROM business_card WHERE name LIKE ?";
		try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
				PreparedStatement ps = con.prepareStatement(sql)){
				ps.setString(1, s_name + "%");
				try (ResultSet rs = ps.executeQuery()) {
					while(rs.next()) {
						String g_name = rs.getString(1);
						String g_phone = rs.getString(2);
						String g_companyName = rs.getString(3);
						Date g_createDate = rs.getDate(4);
						businessCard = new BusinessCard(g_name, g_phone, g_companyName, g_createDate);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return businessCard;
	}



	public void addBusinessCard(BusinessCard businessCard) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		String sql = "INSERT INTO business_card (name, phone, companyName, createDate) VALUES (?,?,?,now())";
		try (Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
				PreparedStatement ps = con.prepareStatement(sql)) {
				ps.setString(1, businessCard.getName());
				ps.setString(2, businessCard.getPhone());
				ps.setString(3, businessCard.getCompanyName());
				ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
