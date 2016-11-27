package com.study.dao;

import java.sql.Connection;
import java.sql.DriverManager;

import com.study.app.Resource;

public abstract class AbstractDao {

	protected Connection conn;

	public AbstractDao(Connection conn){
		this.conn = conn;
	}
		
}
