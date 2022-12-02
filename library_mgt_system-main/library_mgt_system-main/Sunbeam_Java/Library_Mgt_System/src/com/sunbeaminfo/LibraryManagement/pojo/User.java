package com.sunbeaminfo.LibraryManagement.pojo;

import java.util.Scanner;

import com.sunbeaminfo.LibraryManagement.daos.UserDao;

public class User
{
	private int id;
	private String name;
	private String email;
	private String phone;
	private String password;
	private String role;
	
	public User() {
		this.id = 0;
		this.email = " ";
		this.phone = " ";
		this.password = " ";
		this.role = " ";
	}

	public User(int id, String name, String email, String phone, String password, String role) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + ", password=" + password
				+ ", role=" + role + "]";
	}
	
//ADDUSER
	public void addUser() {
		this.acceptUser();
		try(UserDao userDao = new UserDao()){
			if((userDao.addUser(this))>0)
				System.out.println("Sign Up Successfully...");
			else
				System.out.println("Sign Up Failed...");
		}
		catch (Exception e) {
		e.printStackTrace();
		}
	}
	
	
	public void signUp() {
		try (UserDao dao = new UserDao()){
			if (dao.addUser(this.acceptUser()) > 0)
				System.out.println("Sign Up Successfully....");
			else
				System.out.println("Sign Up Failed...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public User acceptUser() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Your Name	: ");
		this.name = sc.nextLine();
		System.out.println("Enter Your Email : ");
		this.email = sc.nextLine();
		System.out.println("Enter Your Password	: ");
		this.password = sc.nextLine();
		System.out.println("Enter Your Mobile No : ");
		this.phone = sc.nextLine();
		return this;
	}

	public void signIn() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Email : ");
		this.email = sc.nextLine();
		System.out.println("Enter Password	: ");
		this.password = sc.nextLine();
		try(UserDao dao = new UserDao()) {
			dao.signIn(this);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

//EDITPROFILE
	
	public void editProfile() {
		Scanner sc = new Scanner(System.in);
	
		System.out.println("Enter New Name : ");
		this.name = sc.nextLine();
		System.out.println("Enter New Email	: ");
		this.email = sc.nextLine();
		System.out.println("Enter New Mobile No : ");
		this.phone = sc.nextLine();
		
		try (UserDao userdao = new UserDao()){
			if((userdao.editProfile(this))>0)
				System.out.println("Profile Updated Successfully...");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	public void editPassword() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter new Password");
		this.password=sc.nextLine();
		try(UserDao userDao = new UserDao()){
			if((userDao.editPassword(this))>0)
				System.out.println("Password Changed Successfully...");
			else
				System.out.println("Password Changed Failed...");
		}
		catch (Exception e) {
		e.printStackTrace();
		}
	}

	//Method to add Librarian or Member from Admin Login
	public void signUp(User user,String role) {
		user.setRole(role);
		user.signUp();
	}
	
	
	
}
