package org.swamhtetaung.jersey.crud_jersey.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.swamhtetaung.jersey.crud_jersey.models.UserModel;

public class UserService {
	Connection conn;
	
	public UserService(){
		try {
			String url = String.format("jdbc:mysql://127.0.0.1:3306/jersey_crud");
			String username = "root";
			String password = "";
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection(url,username,password);
		} catch (Exception e) {
			System.out.println(e + "Database connection failed");
		}
	}
	
	public UserModel storeUser(UserModel user) {
		String insertQuery = "INSERT INTO users (first_name,last_name,email,age) VALUES(?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(insertQuery);
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setString(3, user.getEmail());
			ps.setInt(4, user.getAge());
			ps.execute();
			} 
		catch (Exception e) {
			System.out.println(e + "Data insertion failed.");
		}
		return user;
	}
}