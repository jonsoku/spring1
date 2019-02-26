package com.edu.kimschool.test;

import java.sql.Connection;
import java.sql.DriverManager;

import org.junit.Test;

public class DB_Test {
	@Test
	public void test() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver"); // Oracle

		Connection con = DriverManager.getConnection("jdbc:oracle:thin:@kimschool.caf92v23jkfm.us-east-2.rds.amazonaws.com:1521:ORCL", "master",
				"kimschool");// Oracle
		System.out.println(con);
	}
}
