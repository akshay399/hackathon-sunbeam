package com.sunbeaminfo.LibraryManagement.daos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.sunbeaminfo.LibraryManagement.pojo.Payment;
import com.sunbeaminfo.LibraryManagement.utils.DBUtil;


public class PaymentDao implements AutoCloseable {
	private Connection connection;

	public PaymentDao() throws Exception {
		this.connection = DBUtil.getConnection();
	}

	
	
	@Override
	public void close() throws Exception {
		if(this.connection!=null)
			this.connection.close();
	}


//ADDPAYMENT
	
	public int addPayment(Payment payment)throws Exception {
	String sql = "INSERT INTO payments(user_id,amount,type,transaction_time,nextpayment_duedate) values(?,?,?,?,?)";
	try(PreparedStatement insertPayment = this.connection.prepareStatement(sql)){
		insertPayment.setInt(1, payment.getUser_id());
		insertPayment.setDouble(2, payment.getAmount());
		insertPayment.setString(3, payment.getType());
		insertPayment.setDate(4, Date.valueOf(payment.getTransaction_time()));
		insertPayment.setDate(5, Date.valueOf(payment.getNextpayment_duedate()));
		return insertPayment.executeUpdate();
	}
		
	}
	
	
//FEESREPORT	
	
	public Double feesReport()throws Exception 
	{
		String sql="select sum(amount) as fees from payments where type = 'fees'";
		try(PreparedStatement selectfee = this.connection.prepareStatement(sql))
		{
			ResultSet rs = selectfee.executeQuery();
			if(rs.next()) {
				return rs.getDouble("fees");
			}
			return 0.0;
			
		}
	}

//FINEREPORT
	
	public double fineReport() throws Exception {
		String sql="select sum(amount) as fine from payments where type = 'fine'";
		try(PreparedStatement selectfee = this.connection.prepareStatement(sql)){
			ResultSet rs = selectfee.executeQuery();
			if(rs.next()) {
				return rs.getDouble("fine");
			}
			return 0.0;
			
		}
	}

}
