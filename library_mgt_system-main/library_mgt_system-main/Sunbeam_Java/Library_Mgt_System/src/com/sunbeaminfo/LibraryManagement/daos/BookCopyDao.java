package com.sunbeaminfo.LibraryManagement.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sunbeaminfo.LibraryManagement.pojo.Book;
import com.sunbeaminfo.LibraryManagement.pojo.Copies;
import com.sunbeaminfo.LibraryManagement.utils.Constants;
import com.sunbeaminfo.LibraryManagement.utils.DBUtil;

public class BookCopyDao implements AutoCloseable
{
	private Connection connection;
	
	public BookCopyDao() throws Exception
	{
		this.connection = DBUtil.getConnection();
	}

//ADDBOOKCOPY
//	
//	public int addBookCopy(Copies copies) throws Exception 
//	{
//		String sql = "INSERT INTO copies(book_id,rack) VALUES(?,?)";
//		try(PreparedStatement insertBookCopy = this.connection.prepareStatement(sql)){
//			insertBookCopy.setInt(1, copies.getBook_id());
//			insertBookCopy.setInt(2, copies.getRack());
//				
//			return insertBookCopy.executeUpdate();
//		}
//	}
		
	
	public int addNewCopy(Copies copies) throws Exception {
		String sql = "INSERT INTO copies (book_id,rack,status)VALUES(?,?,?)";
		try (PreparedStatement insertCopy = this.connection.prepareStatement(sql)) {
			insertCopy.setInt(1, copies.getBook_id());
			insertCopy.setInt(2, copies.getRack());
			insertCopy.setString(3, copies.getStatus());
			return insertCopy.executeUpdate();
		}
	}
	
	
//CHECKAVAIL

	public List<Copies> checkAvailibilty(int book_id)throws Exception {
		String sql = "SELECT * FROM copies where book_id=? and status=?";
		List<Copies> copiesList = new ArrayList<Copies>();
		try(PreparedStatement selectCopies = this.connection.prepareStatement(sql)){
			selectCopies.setInt(1, book_id);
			selectCopies.setString(2,Constants.STATUS_AVAILABLE);
			ResultSet rs= selectCopies.executeQuery();
			while(rs.next()) {
				copiesList.add(new Copies(rs.getInt("id"),rs.getInt("book_id"),rs.getInt("rack"),rs.getString("status")));
			}
			return copiesList;
			
		}
	}
	
	
//BOOKAVAILABILITY

	 public List<Copies> bookAvailibilty(int book_id)throws Exception {
		String sql = "SELECT * FROM copies where book_id=? and status=?";
		List<Copies> copiesList = new ArrayList<Copies>();
		try(PreparedStatement selectCopies = this.connection.prepareStatement(sql)){
			selectCopies.setInt(1, book_id);
			selectCopies.setString(2,Constants.STATUS_AVAILABLE);
			ResultSet rs= selectCopies.executeQuery();
			while(rs.next()) {
				copiesList.add(new Copies(rs.getInt("id"),rs.getInt("book_id"),rs.getInt("rack"),rs.getString("status")));
			}
			return copiesList;
				
		 }
		}
	

//CHANGERACK
		
		public int changeRack(Copies copies) throws Exception 
		{
			String sql = "UPDATE copies set rack=? WHERE id=?";
			try(PreparedStatement updateCopy = this.connection.prepareStatement(sql)){
				updateCopy.setInt(1, copies.getId());
				updateCopy.setInt(2, copies.getRack());
					
				return updateCopy.executeUpdate();
			}
		}
		
//UPDATECOPYSTATUS
		
		public int updateCopyStatus(int id,String status ) throws Exception {
			String sql = "UPDATE copies SET status = ? WHERE id = ?";
			try (PreparedStatement updateCopy = this.connection.prepareStatement(sql)) {
				
				updateCopy.setString(1, status);
				updateCopy.setInt(2, id);
				int rs = updateCopy.executeUpdate();
				return rs;
			}
		}
		
//CHANGERACK
		
		public List<Copies> checkAvailStatus(int book_id) throws Exception {
			String sql = "SELECT * FROM copies WHERE book_id=? and status=?";
			List<Copies> copiesList = new ArrayList<Copies>();
			try (PreparedStatement selectcopies = this.connection.prepareStatement(sql)) {
				selectcopies.setInt(1, book_id);
				selectcopies.setString(2, Constants.STATUS_AVAILABLE);
				ResultSet rs = selectcopies.executeQuery();
				while (rs.next()) {
					copiesList.add(
							new Copies(rs.getInt("id"), rs.getInt("book_id"), rs.getInt("rack"), rs.getString("status")));
					/*
					 * copy.setId(rs.getInt("id")); copy.setRack(rs.getInt("rack"));
					 * copy.setStatus(rs.getString("status")); return bc.toString();
					 */}
				return copiesList;
			}
		}
		
		
		
		
		

		public Copies isCopiesValid(Copies copies) throws Exception {
			String sql = "SELECT * FROM copies WHERE book_id=?";
			try (PreparedStatement selectcopy = this.connection.prepareStatement(sql)) {
				selectcopy.setInt(1, copies.getBook_id());
				ResultSet rs = selectcopy.executeQuery();
				if (rs.next()) {
					copies.setId(rs.getInt("id"));
					copies.setRack(rs.getInt("rack"));
					copies.setStatus(rs.getString("status"));
					
					return copies;
				}
			}
			return null;
		}

		public int update(Copies copies) throws Exception {
			String sql = "UPDATE copies SET book_id=?, rack=?, status=? WHERE id=?";
			try (PreparedStatement selectcopy = this.connection.prepareStatement(sql)) {
				selectcopy.setInt(1, copies.getBook_id());
				selectcopy.setInt(2, copies.getRack());
				selectcopy.setString(3, copies.getStatus());
				selectcopy.setInt(4, copies.getId());
				int rs = selectcopy.executeUpdate();
				return rs;
			}
		}

	
	@Override
	public void close() throws Exception {
		
	}

}
