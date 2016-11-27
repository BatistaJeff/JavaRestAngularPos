package com.study.dao;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class Conexao {

	public Connection criaConexao(){
		Connection conn = null;
		try {
			conn = new ConnectionFactory().getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
