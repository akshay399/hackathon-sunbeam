package com.sunbeaminfo.LibraryManagement.daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sunbeaminfo.LibraryManagement.pojo.IssueRecord;
import com.sunbeaminfo.LibraryManagement.pojo.Payment;
import com.sunbeaminfo.LibraryManagement.utils.DBUtil;

public class IssueRecordDao implements AutoCloseable
{
	private Connection connection;
	
	public IssueRecordDao() throws Exception
	{
		this.connection = DBUtil.getConnection();
	}


	@Override
	public void close() throws Exception 
	{
		if(this.connection!=null)
			this.connection.close();	
	}
	
	
	

	public int changeCopyStatus(int id, String status) throws SQLException
	{
		String sql="UPDATE copies SET status=? WHERE id=? ";
		try(PreparedStatement updateCopies=this.connection.prepareStatement(sql))
		{
			updateCopies.setString(1, status);
			updateCopies.setInt(2, id);
			return updateCopies.executeUpdate();

		}
	}
	
	public int checkUser(int id) throws Exception{

		String sql="SELECT DATEDIFF(nextpayment_duedate,now()) as days FROM  payments WHERE user_id=?";
		
		try(PreparedStatement selectUser=this.connection.prepareStatement(sql))
		{
			selectUser.setInt(1, id);
			ResultSet rs =  selectUser.executeQuery();
			if(rs.next()) {
				System.out.println(rs.getInt("days"));
				return rs.getInt("days");
		 }
		}
		return 0;
	}
	
	
//ISSUEBOOKCOPY
	
//	public int book_issue(IssueRecord issueRecord) throws Exception {
//		if (checkActiveUser(issueRecord.getMember_id()) > 0) {
//			String sql = "INSERT INTO issue_record(copy_id,member_id,issue_date,return_duedate,return_date,fine_amount) VALUES(?,?,?,?,?,?)";
//			try (PreparedStatement insertbookissue = this.connection.prepareStatement(sql)) {
//				insertbookissue.setInt(1, issueRecord.getCopy_id());
//				insertbookissue.setInt(2, issueRecord.getMember_id());
//				insertbookissue.setDate(3, Date.valueOf(issueRecord.getIssue_date()));
//				insertbookissue.setDate(4, Date.valueOf(issueRecord.getReturn_duedate()));
//				insertbookissue.setDate(5, Date.valueOf(issueRecord.getReturn_date()));
//				insertbookissue.setDouble(6, issueRecord.getFine_amount());
//
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		} else
//			System.out.println("User's Membership expired");
//		return 0;
//	}
	
	public int book_issue(IssueRecord issueRecord) throws Exception {
		if (checkActiveUser(issueRecord.getMember_id()) > 0) {
			String sql = "INSERT INTO issuerecord(copy_id,member_id,issue_date,return_duedate,return_date,fine_amount) VALUES(?,?,?,?,?,?)";
			try (PreparedStatement insertbookissue = this.connection.prepareStatement(sql)) {
				insertbookissue.setInt(1, issueRecord.getCopy_id());
				insertbookissue.setInt(2, issueRecord.getMember_id());
				insertbookissue.setDate(3, Date.valueOf(issueRecord.getIssue_date()));
				insertbookissue.setDate(4, Date.valueOf(issueRecord.getReturn_duedate()));
				insertbookissue.setDate(5, Date.valueOf(issueRecord.getReturn_date()));
				insertbookissue.setDouble(6, issueRecord.getFine_amount());
				return insertbookissue.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else
			System.out.println("User's Membership expired");
		return 0;
	}


	private int checkActiveUser(int member_id) throws Exception {
		String sql = "SELECT DATEDIFF(nextpayment_duedate,now()) as days from payments where user_id = ?";
		try (PreparedStatement selectUser = this.connection.prepareStatement(sql)) {
			selectUser.setInt(1, member_id);
			ResultSet rs = selectUser.executeQuery();
			if (rs.next()) {
				System.out.println(rs.getInt("days"));
				return rs.getInt("days");
			}
		}
		return 0;
	}


	
	
//RETURNCOPY
	
	public int updateCopyStatus(int id, String statusAvailable) throws Exception{
		String sql = "UPDATE copies SET status=? Where id=?";
		try(PreparedStatement updateCopy = this.connection.prepareStatement(sql)){
			updateCopy.setString(1, statusAvailable);
			updateCopy.setInt(2, id);
			return updateCopy.executeUpdate();		
		}
	}

	
	
	public int finddaydiff(IssueRecord issueRecord) throws Exception {
//		String sql = "SELECT DATEDIFF('2021/09/12',return_duedate) as days from issuerecord where member_id = ?";
		String sql = "SELECT DATEDIFF(now(),return_duedate) as days from issuerecord where member_id = ?";

		try (PreparedStatement selectUser = this.connection.prepareStatement(sql)) {
			selectUser.setInt(1, issueRecord.getMember_id());
			ResultSet rs = selectUser.executeQuery();
			if (rs.next()) {
				System.out.println(rs.getInt("days"));
				return rs.getInt("days");
			}
		}
		return 0;
	}

	public int updateIssueRecord(IssueRecord issueRecord) throws Exception {
		String sql = "UPDATE issuerecord SET return_date = ?, fine_amount = ? WHERE id = ?";
		try (PreparedStatement updateRecord = this.connection.prepareStatement(sql)) {
			updateRecord.setDate(1, Date.valueOf(issueRecord.getReturn_date()));
			updateRecord.setDouble(2, issueRecord.getFine_amount());
			updateRecord.setInt(3, issueRecord.getId());
			return updateRecord.executeUpdate();
		}
	}

	public List<IssueRecord> getIssueRecordByUserId(int member_id) throws Exception{
		List<IssueRecord> issueRecord = new ArrayList<IssueRecord>();
		String sql = "SELECT * from issuerecord WHERE member_id=?";
		try(PreparedStatement selectUser = this.connection.prepareStatement(sql)){
			selectUser.setInt(1, member_id);
			ResultSet rs = selectUser.executeQuery();
			while(rs.next()) 
				issueRecord.add(new IssueRecord(
						rs.getInt("id"),
						rs.getInt("copy_id"),
						rs.getInt("member_id"),
						rs.getDate("issue_date").toLocalDate(),
						rs.getDate("return_duedate").toLocalDate(),
						rs.getDate("return_date").toLocalDate(),
						rs.getDouble("fine_amount")));
			}
		return issueRecord;
	}


	public void getIssueBookById(IssueRecord issueRecord) throws Exception{
		String sql = "SELECT * from issuerecord WHERE id=?";
		try(PreparedStatement selectRecord = this.connection.prepareStatement(sql)){
			selectRecord.setInt(1, issueRecord.getId());
			ResultSet rs = selectRecord.executeQuery();
			if(rs.next()){
				issueRecord.setCopy_id(rs.getInt("copy_id"));
				issueRecord.setIssue_date(rs.getDate("issue_date").toLocalDate());
				issueRecord.setReturn_duedate(rs.getDate("return_duedate").toLocalDate());
			}
			else
				System.out.println("Issue id is invalid !!!");
		}
	}

	
	

//	public List<IssueRecord> getIssueRecordByUserId (int id)throws Exception 
//	{
//		List<IssueRecord> issueRecord = new ArrayList<IssueRecord>();
//		String sql = "SELECT *FROM issuerecord WHERE user_id=?";
//		try(PreparedStatement selectUser= this.connection.prepareStatement(sql))
//		{
//			selectUser.setInt(1, id);
//			ResultSet rs=selectUser.executeQuery();
//			while(rs.next())
//				IssueRecord.add(new IssueRecord(
//						rs.getInt("id"),
//						rs.getInt("copy_id"),
//						rs.getInt("user_id"),
//						rs.getDate("issue_date").toLocalDate(),
//						rs.getDate("return_due_date").toLocalDate(),
//						rs.getDate("return_date").toLocalDate(),
//						rs.getDouble("fine_amount")));
//		}
//		return issueRecord;
//	}
//			
//
//	
//	
//	public void  getIssueBookById(IssueRecord issueRecord)throws Exception 
//	{
//		String sql = "SELECT *FROM issuerecord WHERE user_id=?";
//		try(PreparedStatement selectIssueRecord = this.connection.prepareStatement(sql))
//		{
//			
//			selectIssueRecord.setInt(1, issueRecord.getId());
//			ResultSet rs=selectIssueRecord.executeQuery();
//			if(rs.next()) 
//			{
//			    issueRecord.setCopy_id(rs.getInt("copy_id"));
//			    issueRecord.setIssue_date(rs.getDate("issue_date").toLocalDate());
//			    issueRecord.setReturn_duedate(rs.getDate("return_due_date").toLocalDate());
//		}
//			else
//				System.out.println("Issue id is invalid");
//		}
//			
//}
	
}
	
