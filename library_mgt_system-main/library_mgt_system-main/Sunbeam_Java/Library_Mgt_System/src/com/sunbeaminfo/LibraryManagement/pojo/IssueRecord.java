package com.sunbeaminfo.LibraryManagement.pojo;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;

import com.sunbeaminfo.LibraryManagement.daos.BookCopyDao;
import com.sunbeaminfo.LibraryManagement.daos.IssueRecordDao;
import com.sunbeaminfo.LibraryManagement.utils.Constants;

public class IssueRecord
{
	private int id;
	private int copy_id;
	private int member_id;
	private LocalDate issue_date;
	private LocalDate return_duedate;
	private LocalDate return_date;
	private double fine_amount;
	
	public IssueRecord() {
		
		this.id = 0;
		this.copy_id = 0;
		this.member_id = 0;
		this.issue_date =LocalDate.now();
		this.return_duedate = LocalDate.now().plusDays(Constants.RETURN_DAYS);
		this.return_date = LocalDate.of(1991, 03,05);
		this.fine_amount =0.0;
	}

	public IssueRecord(int id, int copy_id, int member_id, LocalDate issue_date, LocalDate return_duedate, LocalDate return_date,
			double fine_amount) {
		this.id = id;
		this.copy_id = copy_id;
		this.member_id = member_id;
		this.issue_date = issue_date;
		this.return_duedate = return_duedate;
		this.return_date = return_date;
		this.fine_amount = fine_amount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCopy_id() {
		return copy_id;
	}

	public void setCopy_id(int copy_id) {
		this.copy_id = copy_id;
	}

	public int getMember_id() {
		return member_id;
	}

	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}

	public LocalDate getIssue_date() {
		return issue_date;
	}

	public void setIssue_date(LocalDate issue_date) {
		this.issue_date = issue_date;
	}

	public LocalDate getReturn_duedate() {
		return return_duedate;
	}

	public void setReturn_duedate(LocalDate return_duedate) {
		this.return_duedate = return_duedate;
	}

	public LocalDate getReturn_date() {
		return return_date;
	}

	public void setReturn_date(LocalDate date) {
		this.return_date = return_date;
	}

	public double getFine_amount() {
		return fine_amount;
	}

	public void setFine_amount(double fine_amount) {
		this.fine_amount = fine_amount;
	}

	@Override
	public String toString() {
		return "IssueRecord [id=" + id + ", copy_id=" + copy_id + ", member_id=" + member_id + ", issue_date="
				+ issue_date + ", return_duedate=" + return_duedate + ", return_date=" + return_date + ", fine_amount="
				+ fine_amount + "]";
	}
	
	
	
//ISSUERECORD
	
//	public void bookcopy_issue() throws Exception {
//		Scanner sc = new Scanner(System.in);
//		System.out.println("Enter copy Id:  ");
//		this.copy_id = sc.nextInt();
//		System.out.println("Enter Member id:   ");
//		this.member_id = sc.nextInt();
//		try (IssueRecordDao ird = new IssueRecordDao()) {
//			if (ird.book_issue(this) > 0)
//				if ((ird.changeCopyStatus(this.copy_id, Constants.STATUS_NOT_AVAILABLE) > 0))
//					System.out.println("Book issue successful !!!!");
//				else
//					System.out.println("Book issue Failed !!");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	
	
//	public void copy_issue() {
//		Scanner sc = new Scanner(System.in);
//		System.out.println("Enter Copy id : ");
//		this.copy_id = sc.nextInt();
//		System.out.println("enter member id : ");
//		this.member_id = sc.nextInt();
//		try(IssueRecordDao issDao = new IssueRecordDao()){
//			if(issDao.bookissue(this)>0)
//				if((issDao.changeCopyStatus(this.copy_id,Constants.STATUS_NOT_AVAILABLE))>0)
//					System.out.println("BOOK ISSUE successful");
//				else
//					System.out.println("no successful ");
//			
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//	}

//	public void bookcopy_Issue() 
//	{
//		Scanner sc = new Scanner(System.in);
//		System.out.println("Enter Copy Id : ");
//		this.copy_id = sc.nextInt();
//		System.out.println("Enter Member Id : ");
//		this.member_id = sc.nextInt();
//		
//		try (IssueRecordDao issueRecordDao = new IssueRecordDao()){
//			if((issueRecordDao.bookissue(this))>0)
//			if(issueRecordDao.changeCopyStatus(this.copy_id,Constants.STATUS_NOT_AVAILABLE)>0)
//
//				System.out.println("Book Issued  Successful...");
//			else
//				System.out.println("Book Issued Failed...");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	
	
//	public void bookcopy_issue() throws Exception {
//		Scanner sc = new Scanner(System.in);
//		System.out.println("Enter copy Issue id:  ");
//		this.copy_id = sc.nextInt();
//		System.out.println("Enter Member id:   ");
//		this.member_id = sc.nextInt();
//		try (IssueRecordDao ird = new IssueRecordDao()) {
//			if (ird.book_issue(this) > 0)
//				if ((ird.changeCopyStatus(this.copy_id, Constants.STATUS_NOT_AVAILABLE) > 0))
//					System.out.println("Book issue successful !!!!");
//				else
//					System.out.println("Book issue Failed !!");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	public void bookcopy_issue() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter copy Issue id:  ");
		this.copy_id = sc.nextInt();
		System.out.println("Enter Member id:   ");
		this.member_id = sc.nextInt();
		try (IssueRecordDao issueDao = new IssueRecordDao()) {
			if (issueDao.book_issue(this) > 0)
				if ((issueDao.changeCopyStatus(this.copy_id, Constants.STATUS_NOT_AVAILABLE) > 0))
					System.out.println("Book Issued successfully...!!!");
				else
					System.out.println("Book issue Failed...!!!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	
	
	
	
	
//RETURNBOOK
	
	public void bookcopy_return() throws Exception {
		Scanner sc = new Scanner(System.in);
//		BookCopyDao bookCopyDao= new BookCopyDao();
		System.out.println("Enter Member id:    	");
		this.member_id = sc.nextInt();
		try(IssueRecordDao issueRecordDao = new IssueRecordDao()){
			List<IssueRecord> issueRecord = issueRecordDao.getIssueRecordByUserId(this.member_id);
			if(!issueRecord.isEmpty()) {
				issueRecord.forEach((record) -> {
					System.out.println(record.toString());	
				});
			}
		System.out.println("Enter Issue id: 	");
		this.id = sc.nextInt();
//		this.return_date = LocalDate.now().plusDays(12);
		this.return_date = LocalDate.now();


		int diffDay = issueRecordDao.finddaydiff(this);
		if (diffDay > 0) {
			System.out.println("Pay fine of Rs. " + ((diffDay) * 5.0));
			this.fine_amount = (diffDay) * 5.0;
			Payment payment = new Payment();
			payment.takePayment();
		}
		if (issueRecordDao.updateIssueRecord(this) > 0) {
			System.out.println("Book issue record updated !!");
			
			issueRecordDao.getIssueBookById(this);
			if (this.getCopy_id()!= 0)
			{	
				if (issueRecordDao.updateCopyStatus(this.getCopy_id(),Constants.STATUS_AVAILABLE) > 0)
					System.out.println("Book copy record updated !!!");
				else
					System.out.println("Book copy record update failed !!!");
			}
		}
		else
			System.out.println("Book issue record update failed !!!");
	
				}
		}
	
	
	
//	public void bookcopy_return() throws Exception {
//		Scanner sc = new Scanner(System.in);
////		BookCopyDao bookCopyDao= new BookCopyDao();
//		System.out.println("Enter Member id:    	");
//		this.member_id = sc.nextInt();
//		try(IssueRecordDao issueRecordDao = new IssueRecordDao()){
//			List<IssueRecord> issueRecord = issueRecordDao.getIssueRecordByUserId(this.member_id);
//			if(!issueRecord.isEmpty()) {
//				issueRecord.forEach((record) -> {
//					System.out.println(record.toString());	
//				});
//			}
//		System.out.println("Enter Issue id: 	");
//		this.id = sc.nextInt();
//		this.return_date = LocalDate.now();
//
//		int diffDay = issueRecordDao.finddaydiff(this);
//		if (diffDay > 7) {
//			System.out.println("Pay fine of Rs. " + ((diffDay-7) * 5.0));
//			this.fine_amount = (diffDay-7) * 5.0;
//			Payment payment = new Payment();
//			payment.takePayment();
//		}
//		if (issueRecordDao.updateIssueRecord(this) > 0) {
//			System.out.println("Book issue record updated !!");
//			
//			issueRecordDao.getIssueBookById(this);
//			if (this.getCopy_id()!= 0)
//			{	
//				if (bookCopyDao.updateCopyStatus(this.getCopy_id(),Constants.STATUS_AVAILABLE) > 0)
//					System.out.println("Book copy record updated !!!");
//				else
//					System.out.println("Book copy record update failed !!!");
//			}
//		}
//		else
//			System.out.println("Book issue record update failed !!!");
//	
//				}
//		}
//	
//	public int calculateFine()
//	{
//		this.return_date=LocalDate.now();
//		int days=(int) ChronoUnit.DAYS.between(this.return_duedate,this.issue_date);
//		if(days>0)
//			return days*Constants.FINE_AMOUNT;
//		return 0;
//	}
//	

	

}
