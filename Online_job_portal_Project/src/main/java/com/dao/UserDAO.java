package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.entity.User;
import com.mysql.cj.protocol.Resultset;

public class UserDAO {
	
	private Connection conn;

	public UserDAO(Connection conn) {
		super();
		this.conn = conn;
	}
	
	public boolean addUser(User user) {
		boolean f = false;
		
		try {
			String query = "insert into user(name, qualification, email, password, role) values(?,?,?,?,?)";
			
			PreparedStatement ps = conn.prepareStatement(query);
			
			ps.setString(1, user.getName());
			ps.setString(2, user.getQualification());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getPassword());
			ps.setString(5, "user");
			
			int i = ps.executeUpdate();
			if(i == 1) {
				f = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return f;
	}
	
	public User loginUser(String email, String password) {
		
		User user = null;
		
		try {
			String query = "select * from user where email=? and password=?";
			
			PreparedStatement ps = conn.prepareStatement(query);
			
			ps.setString(1, email);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				
				user = new User();
				user.setId(rs.getInt(1));
				user.setName(rs.getString(2));
				user.setEmail(rs.getString(3));
				user.setPassword(rs.getString(4));
				user.setQualification(rs.getString(5));
				user.setRole(rs.getString(6));
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return user;
	}
	
	public boolean UpdateUser(User user) {
		boolean f = false;
		
		try {
			String query = "update user set name=?, qualification=?, email=?, password=? where id =?";
			
			PreparedStatement ps = conn.prepareStatement(query);
			
			ps.setString(1, user.getName());
			ps.setString(2, user.getQualification());
			ps.setString(3, user.getEmail());
			ps.setString(4, user.getPassword());
			ps.setInt(5, user.getId());
			
			int i = ps.executeUpdate();
			
			if(i == 1) {
				f = true;
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		return f;
	}
}
