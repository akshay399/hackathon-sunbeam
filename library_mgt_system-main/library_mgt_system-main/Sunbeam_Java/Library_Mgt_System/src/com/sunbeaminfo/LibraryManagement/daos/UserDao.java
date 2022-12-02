package com.sunbeaminfo.LibraryManagement.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.sunbeaminfo.LibraryManagement.pojo.User;
import com.sunbeaminfo.LibraryManagement.utils.DBUtil;

public class UserDao implements AutoCloseable
{
	
	private Connection connection;
	
	public UserDao() throws Exception
	{
		this.connection = DBUtil.getConnection();
	}
	
	public int addUser(User user) throws Exception 
	{
		String sql = "INSERT INTO users(name,email,phone,password,role) VALUES(?,?,?,?,?)";
		try(PreparedStatement insertUser = this.connection.prepareStatement(sql)){
			insertUser.setString(1, user.getName());
			insertUser.setString(2, user.getEmail());
			insertUser.setString(3, user.getPhone());
			insertUser.setString(4, user.getPassword());
			insertUser.setString(5, user.getRole());
			return insertUser.executeUpdate();
		}
	}
	
	public void signIn(User user) throws Exception {
		String sql = "SELECT * FROM users WHERE email=? AND password = ?";
		try(PreparedStatement selectUser = this.connection.prepareStatement(sql)){
			selectUser.setString(1, user.getEmail());
			selectUser.setString(2, user.getPassword());
			ResultSet rs = selectUser.executeQuery();
			if(rs.next()) {
				user.setId(rs.getInt("id"));
				user.setName(rs.getString("name"));
				user.setPhone(rs.getString("phone"));
				user.setRole(rs.getString("role"));
	
			}
		}
	}
	
	
//EDITPROFILE
	
	public int editProfile(User user) throws Exception{
		System.out.println("Result : "+user);
		String sql = "UPDATE users SET name=?,email=?,phone=? WHERE id=?";
		try(PreparedStatement updateUser = this.connection.prepareStatement(sql)){
			updateUser.setString(1, user.getName());
			updateUser.setString(2, user.getEmail());
			updateUser.setString(3, user.getPhone());
			updateUser.setInt(4, user.getId());
			return updateUser.executeUpdate();
		}
	}
	
//EDITPASSWORD
	
	public int editPassword(User user) throws Exception{
		System.out.println("Result : "+user);
		String sql = "UPDATE users SET password=? WHERE id=?";
		try(PreparedStatement updatePassword = this.connection.prepareStatement(sql)){
			updatePassword.setString(1, user.getPassword());
			updatePassword.setInt(2, user.getId());
			return updatePassword.executeUpdate();
		}
		
	}
	
	@Override
	public void close() throws Exception 
	{
		if(this.connection!=null)
			this.connection.close();
		
	}
	

}
