package com.sunbeaminfo.LibraryManagement.menus;

import java.util.Scanner;

import com.sunbeaminfo.LibraryManagement.enums.eLibrarian;
import com.sunbeaminfo.LibraryManagement.enums.eOwner;
import com.sunbeaminfo.LibraryManagement.pojo.Book;
import com.sunbeaminfo.LibraryManagement.pojo.Copies;
import com.sunbeaminfo.LibraryManagement.pojo.IssueRecord;
import com.sunbeaminfo.LibraryManagement.pojo.Payment;
import com.sunbeaminfo.LibraryManagement.pojo.User;
import com.sunbeaminfo.LibraryManagement.utils.Constants;

public class LibrarianMenu
{
	
	private static Scanner sc=new Scanner(System.in);
//	Book book = new Book();
//	eLibrarian elibrarian;
//	
//	public LibrarianMenu(User user) 
//	{
//		// TODO Auto-generated constructor stub
//	}
//	
//	public eLibrarian getLibrarianMenu()
//	{
//		int choice;
//		System.out.print("\n****** LIBRARIAN ****** \n\n1.Edit Profile \n2.Edit Password \n3.Return Copy \n4.Issue Copy "
//							+ "\n5.Find Book \n6.Edit Book \n7.Add New Book \n8.Check Availability \n9.Add New Copy  "
//							+ "\n10.Change Rack \n11.Add New Member \n12.Take Payment");
//		System.out.print("\n\nEnter your choice: ");
//		sc.nextInt();
//		return elibrarian;
//	}
//	
//	public void librarianMenu()
//	{
//		User user = new User();
//		eLibrarian choice;
//		while(!((choice = getLibrarianMenu()).equals(eLibrarian.EXIT)))
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
//		case RETURN_COPY:
//			
//			break;
//		
//		case ISSUE_COPY:
//			
//			break;
//
//		case FIND_BOOK:
//			
//			break;
//			
//		case EDITBOOK:
//			book.editBook();
//			break;
//			
//		case ADD_NEW_BOOK:
//			
//			break;
//			
//		case CHECK__AVAILABILTY:
//			
//			break;
//			
//		case ADD_NEW_COPY:
//			
//			break;
//			
//		case CHANGE_RACK:
//			
//			break;
//			
//		case ADD_NEW_MEMBER:
//	
//			break;
//			
//		case TAKE_PAYMENT:
//			
//			break;
//		
//		}
//	}
	
	
	
	public static void librarianMenu(User user) throws Exception
	{
	   System.out.println("User"+user);
		Book book = new Book();
		Copies copies = new Copies();
		Payment payment = new Payment();
		IssueRecord issueRecord = new IssueRecord();
		int choice=1;
		while(choice != 0)
		
		{
			System.out.print("\n****** LIBRARIAN ****** \n\n1.Edit Profile \n2.Edit Password \n3.Return Copy \n4.Issue Copy "
							+ "\n5.Find Book \n6.Edit Book \n7.Add New Book \n8.Check Availability \n9.Add New Copy  "
							+ "\n10.Change Rack \n11.Add New Member \n12.Take Payment");
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
				  
			case 3://Return Copy
				  issueRecord.bookcopy_return();
				  break;
				  
			case 4://Issue Copy
				issueRecord.bookcopy_issue();

				  break;
				  
			case 5://Find Book 
				  book.findBook();
				  break;
				  
			case 6://Edit Book
				  book.editBook();
				  break;
				  
			case 7://Add New Book
				  book.addBook();
				  break;
				  
			case 8://Check Availability
				  copies.checkAvailStatus();
				  break;
				  
			case 9://Add New Copy
				  copies.addNewCopy();
				  break;
				  
			case 10://Change Rack
				  copies.changeRack();
				  break;
				  
			case 11://Add New Member
				  user.setRole(Constants.ROLE_MEMBER);
				  user.addUser();
				  break;
				  
			case 12://Take Payment
				  payment.takePayment();
				  break;
			}

		}
	}
		
}


