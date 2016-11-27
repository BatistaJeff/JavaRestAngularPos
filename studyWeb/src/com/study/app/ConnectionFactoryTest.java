package com.study.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactoryTest {

	public static void main(String[] args) throws ClassNotFoundException {

		try {
			Class.forName("com.mysql.jdbc.Driver");

			Connection result = DriverManager.getConnection(Resource.getDbUrl(), Resource.getDbUser(),
					Resource.getDbPwd());

			System.out.println(result.toString());

			result.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
