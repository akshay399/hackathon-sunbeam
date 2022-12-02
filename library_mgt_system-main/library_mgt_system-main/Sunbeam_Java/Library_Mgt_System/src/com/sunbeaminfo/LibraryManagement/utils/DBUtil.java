package com.sunbeaminfo.LibraryManagement.utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBUtil 
{
	private static Properties prop;
	static {
		try {
			prop = new Properties();
			try(InputStream in = DBUtil.class.getResourceAsStream("/jdbc.properties")) {
				prop.load(in);
				Class.forName( prop.getProperty("DRIVER") );
			}
		}catch (Exception cause) {
			cause.printStackTrace();
			System.exit(1);
		}
	}
	//user defined method
	public static Connection getConnection( ) throws Exception {
		return DriverManager.getConnection(prop.getProperty("URL"), prop.getProperty("USER"), prop.getProperty("PASSWORD"));
	}

}
