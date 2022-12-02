package com.sunbeaminfo.LibraryManagement.pojo;

import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;

import com.sunbeaminfo.LibraryManagement.daos.PaymentDao;
import com.sunbeaminfo.LibraryManagement.utils.Constants;

public class Payment 
{
	private int id;
	private int user_id;
	private double amount;
	private String type;
	private LocalDate  transaction_time;
	private LocalDate nextpayment_duedate;
	
	public Payment() {
		this.id = 0;
		this.user_id = 0;
		this.amount =0.0;
		this.type = "";
		this.transaction_time=LocalDate.now();
		this.nextpayment_duedate=LocalDate.now().plusDays(Constants.MEMBERSHIP_DAYS);
	}

	public Payment(int id, int user_id, double amount, String type,LocalDate transaction_time, LocalDate nextpayment_duedate) {
		this.id = id;
		this.user_id = user_id;
		this.amount = amount;
		this.type = type;
		this.transaction_time = transaction_time;
		this.nextpayment_duedate = nextpayment_duedate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public LocalDate getTransaction_time() {
		return transaction_time;
	}

	public void setTransaction_time(LocalDate transaction_time) {
		this.transaction_time = transaction_time;
	}

	public LocalDate getNextpayment_duedate() {
		return nextpayment_duedate;
	}

	public void setNextpayment_duedate(LocalDate nextpayment_duedate) {
		this.nextpayment_duedate = nextpayment_duedate;
	}
	
	

	@Override
	public String toString() {
		return "Payment [id=" + id + ", user_id=" + user_id + ", amount=" + amount + ", type=" + type
				+ ", transaction_time=" + transaction_time + ", nextpayment_duedate=" + nextpayment_duedate + "]";
	}
	
//ACCEPTPAYMENT
	
	public Payment acceptPayment() {
		Scanner sc = new Scanner(System.in);
		System.out.print("\n1.Fees \n2.Fine \nSelect type of Fee Payment : ");
		if (Integer.parseInt(sc.nextLine()) == 1)
			this.type = Constants.TYPE_FEES;
		else {
			this.type = Constants.TYPE_FINE;
			this.nextpayment_duedate=LocalDate.of(1900, 1, 1);
		}
		System.out.println("Enter Member Id		:	");
		this.user_id = sc.nextInt();
		System.out.println("Enter Amount	:	");
		this.amount = sc.nextDouble();
		
		return this;
	}

//ADDPAYMENT
	
	public void takePayment() {
		try(PaymentDao paymentDao = new PaymentDao()) {
			if((paymentDao.addPayment(this.acceptPayment()))>0)
				System.out.println("Payment Done Successfully...");
			else
				System.out.println("Payemnt Failed...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
//FEESREPORT	
	public static void feesReport() {
		try(PaymentDao paymentDao = new PaymentDao()){
			System.out.println("total fees amount : "+ paymentDao.feesReport());
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
//FINEREPORT

	public static void fineReport() {
		try(PaymentDao paymentDao = new PaymentDao()){
			System.out.println("total fine amount : "+ paymentDao.fineReport());
		}catch(Exception e) {
			e.printStackTrace();
		}

	
	
}
	}
