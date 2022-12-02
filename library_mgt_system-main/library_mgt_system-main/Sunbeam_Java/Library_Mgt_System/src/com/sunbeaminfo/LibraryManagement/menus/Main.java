package com.sunbeaminfo.LibraryManagement.menus;

import java.util.Scanner;

import com.sunbeaminfo.LibraryManagement.enums.eOwner;
import com.sunbeaminfo.LibraryManagement.enums.eSignIn;
import com.sunbeaminfo.LibraryManagement.pojo.Book;
import com.sunbeaminfo.LibraryManagement.pojo.User;
import com.sunbeaminfo.LibraryManagement.utils.Constants;


public class Main 
{
	
	public static int menuList() 
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Welcome to Library Management System\n");
		System.out.println("0.Exit");
		System.out.println("1.SignIn");
		System.out.println("2.SignUP");
		System.out.println("\nEnter your Choice :");
		return sc.nextInt();
	}

	public static void main(String args[]) throws Exception
	{
		
		int choice = 1;
		Scanner sc = new Scanner(System.in);
		User user = new User();
		Book book = new Book();
//		while (choice != 0)
		while ((choice = Main.menuList()) != 0) 
		{

			switch (choice) {
			case 1:
				user.signIn();
				if(user.getId()!=0)
				if(!user.getRole().equals("")) 
				if(user.getRole().equals(Constants.ROLE_OWNER))
					OwnerMenu.ownerMenue(user);
				else if(user.getRole().equals(Constants.ROLE_LIBRARIAN))
					LibrarianMenu.librarianMenu(user);
				else 
					UserMenu.userMenu(user);
				else 
					System.out.println("Invalid email and password");
				
				break;
				
			case 2:
				user.setRole(Constants.ROLE_MEMBER);
				user.addUser();
			    break;
			    
			   
			}
			
		}
	}

	
}