package com.sunbeaminfo.LibraryManagement.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.sunbeaminfo.LibraryManagement.pojo.Book;
import com.sunbeaminfo.LibraryManagement.pojo.User;
import com.sunbeaminfo.LibraryManagement.utils.DBUtil;

public class BookDao implements AutoCloseable
{
private Connection connection;
	
	public BookDao() throws Exception
	{
		this.connection = DBUtil.getConnection();
	}

	
//ADDBOOK
	
	public int addBook(Book book) throws Exception 
	{
		System.out.println("Result : "+book);

		String sql = "INSERT INTO books(name,author,subject,price,isbn) VALUES(?,?,?,?,?)";
		try(PreparedStatement insertBook = this.connection.prepareStatement(sql)){
			insertBook.setString(1, book.getName());
			insertBook.setString(2, book.getAuthor());
			insertBook.setString(3, book.getSubject());
			insertBook.setDouble(4, book.getPrice());
			insertBook.setInt(5, book.getIsbn());
			return insertBook.executeUpdate();
		}
	}
	
	
//EDITBOOK
		
	public int editBook(Book book) throws Exception 
	{
		System.out.println("Result : "+book);

		String sql = "UPDATE books SET name=?, author=?, subject=?, price=?, isbn=? WHERE id=?";
		try (PreparedStatement editBook = this.connection.prepareStatement(sql)) {
			editBook.setString(1, book.getName());
			editBook.setString(2, book.getAuthor());
			editBook.setString(3, book.getSubject());
			editBook.setDouble(4, book.getPrice());
			editBook.setInt(5, book.getIsbn());
			editBook.setInt(6, book.getId());
			return editBook.executeUpdate();
		}
	}

	public Book isBookValid(Book book) throws Exception {
		String sql = "SELECT * FROM books WHERE id=?";
		try (PreparedStatement selectBook = this.connection.prepareStatement(sql)) {
			selectBook.setInt(1, book.getId());
			ResultSet rs = selectBook.executeQuery();
			if (rs.next()) {
				book.setName(rs.getString("name"));
				book.setAuthor(rs.getString("author"));
				book.setSubject(rs.getString("subject"));
				book.setPrice(rs.getDouble("price"));
				book.setIsbn(rs.getInt("isbn"));
				return book;
				}
		}
		return null;
	}

	public List<Book> bookList() throws Exception {
		String sql = "SELECT * FROM books";
		List<Book> bookList = new ArrayList<Book>();
		try (PreparedStatement selectbook = this.connection.prepareStatement(sql)) {
		//	selectcopies.setInt(1, book_id);
		//	selectcopies.setString(2, Constants.STATUS_AVAILABLE);
			ResultSet rs = selectbook.executeQuery();
			while (rs.next()) {
				bookList.add(new Book(rs.getInt("id"), rs.getString("name"), rs.getString("author"), rs.getString("subject"), rs.getDouble("price"),rs.getInt("isbn")));
				}
			return bookList;
		}
	}
	
	
	
	
//	 public int editBook(Book book) throws Exception
//	 {
//			String sql = "UPDATE books set author=?,price=?,isbn=? WHERE id=?";
//			try(PreparedStatement updateBook = this.connection.prepareStatement(sql)){
//				updateBook.setString(1, book.getAuthor());
//				updateBook.setDouble(2, book.getPrice());
//				updateBook.setInt(3, book.getIsbn());
//				updateBook.setInt(4, book.getId());
//				return updateBook.executeUpdate();
//			}
//		}
	 
//FINDBOOK
	 
	 public boolean findBook(Book book) throws Exception
	 {
			String sql ="SELECT *FROM books WHERE name=?";
			try(PreparedStatement findBook = this.connection.prepareStatement(sql))
			{
				findBook.setString(1, book.getName());
				
				ResultSet rs = findBook.executeQuery();
				if(rs.next())
				{
					book.setId(rs.getInt("id"));
					book.setName(rs.getString("name"));
					book.setAuthor(rs.getString("author"));
					book.setSubject(rs.getString("subject"));
					book.setPrice(rs.getDouble("price"));
					book.setIsbn(rs.getInt("isbn"));

					
					return true;
				}
				else
				{
					return false;
				}
			}
		}
	 
	 @Override
	 public void close() throws Exception 
	 {
		 if(this.connection!=null)
				this.connection.close();
				
	 }

}
