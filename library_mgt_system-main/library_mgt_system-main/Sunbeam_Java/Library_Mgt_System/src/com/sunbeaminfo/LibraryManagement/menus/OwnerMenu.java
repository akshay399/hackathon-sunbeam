package com.sunbeaminfo.LibraryManagement.menus;


import java.util.Scanner;
import com.sunbeaminfo.LibraryManagement.enums.eOwner;
import com.sunbeaminfo.LibraryManagement.pojo.Copies;
import com.sunbeaminfo.LibraryManagement.pojo.Payment;
import com.sunbeaminfo.LibraryManagement.pojo.User;
import com.sunbeaminfo.LibraryManagement.utils.Constants;

public class OwnerMenu
{
//	eOwner eowner;
//	User user=new User();
//	
//	public OwnerMenu(User user)
//	{
//		
//	
//	}
//	
//	public eOwner getOwnerMenu()
//	{
//		int choice;
//		System.out.print("\n****** OWNER ****** \n\n1.Appoint Librarian \n2.Edit Profile \n3.Edit Password \n4.Fees Report "
//				+ "\n5.Fine Report \n6.Books Categories \n7.Books Availability");
//		System.out.println("Enter your choice:");
//		sc.nextInt();
//		return eowner;
//	}
//	
//	public void ownerMenu()
//	{
//		eOwner choice;
//		while(!((choice = getOwnerMenu()).equals(eOwner.EXIT)))
//		switch (choice) 
//		{
//		case EDITPROFILE:
//			user.editProfile();
//			break;
//			
//		case EDITPASSWORD:
//			user.editProfile();
//			break;	
//			
//		case APPOINT_LIBRARIAN:
//			
//			break;
//		
//		case FEES_REPORT:
//			
//			break;
//
//		case FINE_REPORT:
//			
//			break;
//			
//		case BOOK_AVAILABILITY:
//			
//			break;
//		
//		}
//	}
	
	public static void ownerMenue(User user) 
	{
		Scanner sc=new Scanner(System.in);
		Copies copies=new Copies();
		Payment payment=new Payment();

//		User user =new User();
		int choice=1;
		while(choice != 0)
	
		{
			System.out.print("\n****** OWNER ****** \n\n1.Appoint Librarian \n2.Edit Profile \n3.Edit Password \n4.Fees Report "
					+ "\n5.Fine Report \n6.Books Categories \n7.Books Availability");
			System.out.print("\n\nEnter your choice: ");
			choice=sc.nextInt();
			switch(choice)
			{
			case 1://Appoint Lib
				  user.signUp(user, Constants.ROLE_LIBRARIAN);
				  break;

			case 2://Edit Profile
				  user.editProfile();
				  break;
				  
			case 3://Edit Password
				  user.editPassword();
				  break;
				  
			case 4://Fees Report
				  payment.feesReport();
				  break;
				  
			case 5://Fine Report
				  payment.fineReport();
				  break;
				  
			case 6://Books Category
				
				  break;
				  
			case 7://Books Availability
				  copies.bookAvailability();
				  break;
			}

		} 
		
	}	
		
}


