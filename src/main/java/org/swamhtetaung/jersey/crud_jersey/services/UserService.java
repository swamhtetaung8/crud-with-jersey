package org.swamhtetaung.jersey.crud_jersey.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
	
	public List<UserModel> getUsers(){
		List<UserModel> users = new ArrayList<>();
		
		String retrieveQuery = "SELECT * FROM USERS";
		try {
			PreparedStatement ps = conn.prepareStatement(retrieveQuery);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				UserModel user = new UserModel();
				user.setId(rs.getInt("id"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setEmail(rs.getString("email"));
				user.setAge(rs.getInt("age"));
				users.add(user);
			}
		} catch (Exception e) {
			System.out.println(e + "Data retrieving failed.");
		}
		
		return users;
	}
	
	public UserModel getUserById(int userId) {
		UserModel userResult = null;
		String retrieveQuery = "SELECT * FROM USERS WHERE id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(retrieveQuery);
			ps.setLong(1, userId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				UserModel user = new UserModel();
				user.setId(rs.getInt("id"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setEmail(rs.getString("email"));
				user.setAge(rs.getInt("age"));
				userResult = user;
			}
		} catch (Exception e) {
			System.out.println(e + "Data retrieving failed.");
		}
		return userResult;
	}
	
	public UserModel updateUser(int userId,UserModel user) {
		user.setId(userId);
		String updateQuery = "UPDATE USERS SET first_name=? , last_name=? , email=? , age=? WHERE id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(updateQuery);
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setString(3, user.getEmail());
			ps.setInt(4, user.getAge());
			ps.setLong(5, user.getId());
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println(e + "Updating failed.");
		}
		return user;
	}
	
	public void deleteUser(int userId) {
		String deleteQuery = "DELETE FROM USERS WHERE id=?";
		try {
			PreparedStatement ps = conn.prepareStatement(deleteQuery);
			ps.setInt(1,userId);
			ps.execute();
		} catch (Exception e) {
			System.out.println(e + "Deleting failed.");
		}	
	}
}
