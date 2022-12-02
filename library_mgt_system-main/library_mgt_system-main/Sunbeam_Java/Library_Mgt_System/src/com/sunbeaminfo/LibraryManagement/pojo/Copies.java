package com.sunbeaminfo.LibraryManagement.pojo;

import java.util.List;
import java.util.Scanner;

import com.sunbeaminfo.LibraryManagement.daos.BookCopyDao;
import com.sunbeaminfo.LibraryManagement.daos.BookDao;
import com.sunbeaminfo.LibraryManagement.daos.UserDao;
import com.sunbeaminfo.LibraryManagement.utils.Constants;




public class Copies 
{
	private int id;
	private int book_id;
	private int rack;
	private String status;
	
	
	public Copies() {
		this.id = 0;
		this.book_id = 0;
		this.rack = 0;
		this.status = " "; 
	}

	public Copies(int id, int book_id, int rack, String status) {
		this.id = id;
		this.book_id = book_id;
		this.rack = rack;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}

	public int getRack() {
		return rack;
	}

	public void setRack(int rack) {
		this.rack = rack;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Copies [id=" + id + ", book_id=" + book_id + ", rack=" + rack + ", status=" + status + "]";
	}
	
	public void addNewCopy() {
		this.acceptNewCopy();
		try (BookCopyDao copiesDao = new BookCopyDao()) {
			if ((copiesDao.addNewCopy(this)) > 0)
				System.out.println("Added a new Copy...");
			else
				System.out.println("Adding copy failed.. ");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void acceptNewCopy() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Book Id:	");
		this.book_id = sc.nextInt();
		System.out.println("Enter Rack:	");
		this.rack = sc.nextInt();
		this.setStatus(Constants.STATUS_AVAILABLE);
	}
	
//CHANGERACK
	
	
	
	
	public void changeRack() throws Exception {
		Scanner sc = new Scanner(System.in);
		Copies copies = new Copies();
		System.out.println("Enter Book Id : ");
		copies.setBook_id(sc.nextInt());
		try(BookCopyDao bcd = new BookCopyDao()) {
			Copies copyV = bcd.isCopiesValid(copies);
			System.out.println("Copy is valid");
			System.out.println("Enter New Rack No : ");
			copies.setRack(sc.nextInt());
			int count = bcd.update(copies);
			System.out.println(" "+ count +" Rack changed !!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
//	public void changeRack() throws Exception {
//		Scanner sc = new Scanner(System.in);
//		Copies copies = new Copies();
//		System.out.println("Enter Book Id : ");
//		copies.setBook_id(sc.nextInt());
//		try(BookCopyDao bookCopyDao = new BookCopyDao()) {
//			Copies copyV = bookCopyDao.isCopiesValid(copies);
////			System.out.println("Copy is valid");
//			System.out.println("Enter New Rack No : ");
//			copies.setRack(sc.nextInt());
//			int count = bookCopyDao.update(copies);
//			System.out.println("Rack changed successfully...");
////			System.out.println(" "+ count +" Rack changed !!");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//	}
	
	
//	public void changeRack() 
//	{
//		Scanner sc = new Scanner(System.in);
//		System.out.println("Enter Book_Id	: ");
//		this.book_id = sc.nextInt();
//		System.out.println("Enter Rack No	: ");
//		this.rack = sc.nextInt();
//	
//		try (BookCopyDao dao = new BookCopyDao()){
//			if(dao.changeRack(this)>0)
//				System.out.println("Rack no change Successfully...");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	
	
//CHECKAVAILABILITY
	
	public void checkAvailStatus() {
		Scanner sc = new Scanner(System.in);
		Copies bc = new Copies();
		System.out.println("Enter Book Id : ");
		bc.book_id = sc.nextInt();
		try (BookCopyDao bcd = new BookCopyDao()) {
			List<Copies> copiesList = bcd.checkAvailStatus(bc.book_id);
			if (!copiesList.isEmpty()) {
				for (Copies copies : copiesList)
					System.out.println(copies);
			} else
				System.out.println("No copies available !!");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
//	public void checkAvailability() 
//	{
//		Scanner sc = new Scanner(System.in);
//		System.out.println("Enter Book id :	");
//		this.book_id = sc.nextInt();
//		try(BookCopyDao bookCopyDao = new BookCopyDao()){
//			List<Copies> copiesList =bookCopyDao.checkAvailibilty(this.book_id);
//			System.out.println("Total Copies Available : "+copiesList.size());
//			if(!copiesList.isEmpty())
//				for (Copies copies : copiesList)
//					System.out.println(copies);
//			else
//				System.out.println("No Copies left");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//	}
	
//BOOKAVALABILITY	
	public void bookAvailability() 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Book id :	");
		this.book_id = sc.nextInt();
		try(BookCopyDao bookCopyDao = new BookCopyDao()){
			List<Copies> copiesList =bookCopyDao.checkAvailibilty(this.book_id);
			System.out.println("Total Book Copies Available : "+copiesList.size());
			if(!copiesList.isEmpty())
				for (Copies copies : copiesList)
					System.out.println(copies);
			else
				
				System.out.println("No Copies left");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
