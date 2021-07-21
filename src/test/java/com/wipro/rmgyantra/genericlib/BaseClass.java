package com.wipro.rmgyantra.genericlib;

import java.sql.SQLException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class BaseClass implements IEndPoints {
public DataBaseUtilies dblib=new DataBaseUtilies();
//public String baseURL;

	@BeforeSuite
	public void configBS() throws SQLException {
		String baseURL = "http://localhost:8084";
		dblib.connectToDB();
	}
	
	@AfterSuite
	public void ConfigAS() throws SQLException {
		dblib.closeDb();
	}
}

