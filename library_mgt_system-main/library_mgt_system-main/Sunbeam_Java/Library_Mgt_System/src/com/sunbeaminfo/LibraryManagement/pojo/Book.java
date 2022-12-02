package com.sunbeaminfo.LibraryManagement.pojo;

import java.util.List;
import java.util.Scanner;

import com.sunbeaminfo.LibraryManagement.daos.BookDao;
import com.sunbeaminfo.LibraryManagement.daos.UserDao;

public class Book 
{
	private int id;
	private String name;
	private String author;
	private String subject;
	private double price;
	private int isbn;
	
	
	public Book() {
		this.id = 0;
		this.name = " ";
		this.author = " ";
		this.subject = " ";
		this.price = 0.0;
		this.isbn = 0;
		
	}


	public Book(int id, String name, String author, String subject, double price, int isbn) {
		this.id = id;
		this.name = name;
		this.author = author;
		this.subject = subject;
		this.price = price;
		this.isbn = isbn;
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


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}


	public int getIsbn() {
		return isbn;
	}


	public void setIsbn(int isbn) {
		this.isbn = isbn;
	}


	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", author=" + author + ", subject=" + subject + ", price=" + price
				+ ", isbn=" + isbn + "]";
	}
	
	public void addBook() {
		this.acceptBook();
		try(BookDao bookDao = new BookDao()){
			if((bookDao.addBook(this))>0)
				System.out.println("Book Added Successfully...");
			else
				System.out.println(" Failed...");
		}
		catch (Exception e) {
		e.printStackTrace();
		}
	}
	
	public Book acceptBook() 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Book Name	: ");
		this.name = sc.nextLine();
		System.out.println("Enter Book Author : ");
		this.author = sc.nextLine();
		System.out.println("Enter Book Subject : ");
		this.subject = sc.nextLine();
		System.out.println("Enter Book Price : ");
		this.price = sc.nextDouble();
		System.out.println("Enter Book ISBN No : ");
		this.isbn = sc.nextInt();
		return this;
	}
	
//EDITBOOK
	
	
	public void editBook() throws Exception{
		Scanner sc = new Scanner(System.in);
		Book book = new Book();
		BookDao bd = new BookDao();
		System.out.println("Enter Book id: ");
		book.setId(sc.nextInt());
		Book bookV = bd.isBookValid(book);
		book.editBookOp(bookV);
	}

	private void editBookOp(Book bookV) {
		System.out.println("Enter New Book Details: ");
		this.acceptBook();
		try (BookDao bookDao = new BookDao()) {
			if ((bookDao.editBook(this)) > 0)
				System.out.println("Edit book Successful...");
			else
				System.out.println("Edit book Failed.. :(");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public void listBook() {
		try (BookDao bd = new BookDao()) {
			List<Book> bookList = bd.bookList();
			if (!bookList.isEmpty()) {
				for (Book book : bookList)
					System.out.println(book);
			} else
				System.out.println("No book available !!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	

	
//FINDBOOK
	
//	public void findBook() {
//		Scanner sc = new Scanner(System.in);
//		System.out.println("Enter Book Name to be searched : ");
//		this.name = sc.nextLine();
//		Book book = new Book();
//		try(BookDao bookDao = new BookDao()) {
//			Book bookV = bookDao.findBook(book);
//			System.out.println(bookV.toString());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	
	public void findBook() 
	{
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter Book Name to be searched : ");
		this.name = sc.nextLine();
			
		try (BookDao dao = new BookDao())
		{
			if((dao.findBook(this)))
				System.out.println("Book Found...\n"+this);
			else
				System.out.println("Book Not Found");
		} catch (Exception e) {
				e.printStackTrace();
		}

	}
	

	
		
	
}
