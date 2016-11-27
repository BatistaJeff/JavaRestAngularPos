package com.study.app;

public final class Resource {

	public static String getDbUser() {

		return "root";
	}

	public static String getDbPwd() {

		return "root";
	}

	public static String getDbHostName() {

		return "localhost";

	}

	public static int getDbPort() {

		return 3306;
	}

	public static String getDbName() {

		return "lojadb";

	}

	public static String getDbUrl() {

		String url = "jdbc:mysql://" + getDbHostName() + ":" + getDbPort() + "/" + getDbName();

		return url;
	}
	
}
