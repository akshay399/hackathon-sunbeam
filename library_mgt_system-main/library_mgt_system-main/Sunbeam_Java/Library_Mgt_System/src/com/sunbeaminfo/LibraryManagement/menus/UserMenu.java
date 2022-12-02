package com.sunbeaminfo.LibraryManagement.menus;

import java.util.Scanner;

import com.sunbeaminfo.LibraryManagement.enums.eLibrarian;
import com.sunbeaminfo.LibraryManagement.enums.eOwner;
import com.sunbeaminfo.LibraryManagement.enums.eUser;
import com.sunbeaminfo.LibraryManagement.pojo.Book;
import com.sunbeaminfo.LibraryManagement.pojo.Copies;
import com.sunbeaminfo.LibraryManagement.pojo.User;

public class UserMenu 
{
	
	private static Scanner sc=new Scanner(System.in);
//	eUser euser;
//	
//	public UserMenu(User user)
//	{
//		
//	
//	}
//	
//	public eUser getUserMenu()
//	{
//		int choice = 0;
//		System.out.print("\n****** MEMBER ****** \n\n1.Edit Profile \n2.Edit Password \n3.Find Book \n4.Check Availability");
//		System.out.print("\n\nEnter your choice: ");
//		sc.nextInt();
//		return euser;
//	}
//	
//	public void userMenu()
//	{
//		eUser choice;
//		while(!((choice = getUserMenu()).equals(eUser.EXIT)))
//		switch (choice) 
//		{
//		case EDITPROFILE:
//			user.editProfile();
//			break;
//			
//		case EDITPASSWORD:
//			user.editPassword();
//			break;	
//			
//		case FIND_BOOK:
//			
//			break;
//		
//		case CHECK_AVAILABILITY:
//			
//			break;
//		}
//
//	}

//	private static Scanner sc =new Scanner(System.in);
//	
	public static void userMenu(User user) {

		System.out.println("User"+user);
		Book book = new Book();
		Copies copies = new Copies();
		int choice=1;
		while(choice != 0)
		{
		
			System.out.print("\n****** MEMBER ****** \n\n1.Edit Profile \n2.Edit Password \n3.Find Book \n4.Check Availability");
			System.out.print("\n\nEnter your choice: ");
			choice=sc.nextInt();
			switch(choice)
			{
			case 1://Edit Profile
				  user.editProfile();
				  break;
				  
			case 2://Edit Password
				  user.editPassword();
				  break;
				  
			case 3://Find Book
				  book.findBook();
				  break;
				  
			case 4://Check Availability
				  copies.checkAvailStatus();;
				  break;
				
				  
			}

		  }
		

	}

}
