package com.study.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.study.app.Resource;

public class ConnectionFactory {
	
	public Connection getConnection() throws SQLException {
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Connection result = DriverManager.getConnection(Resource.getDbUrl(), Resource.getDbUser(), Resource.getDbPwd());
		
		return result;
	}
	
}
