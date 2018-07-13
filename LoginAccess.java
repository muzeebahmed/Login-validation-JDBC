package org.btm.loginAccess;
import java.sql.*;
import java.util.Scanner;
public class LoginAccess 
{
	public static void main(String[] args)  
	{
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		String qry="select username from muzeeb.user where user=? and password=?";
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter UserName");
		String name=sc.next();
		System.out.println("Enter Password");
		String password=sc.next();
		sc.close();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306?user=root&password=muzeeb");
			pstmt=con.prepareStatement(qry);
			//SET THE VALUES FOR PLACEHOLDER//
			pstmt.setString(1,name);
			pstmt.setString(2, password);
			rs=pstmt.executeQuery();
			if(rs.next())
			{
				String uname=rs.getString(1);
				System.out.println("WELCOME "+uname);
			}
			else
			{
				System.err.println("Invalid username/password");
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		finally
		{
			if(rs!=null)
			{
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(pstmt!=null)
			{
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(con!=null)
			{
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
