package com.study.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.study.dao.AbstractDao;
import com.study.dao.IClienteDao;
import com.study.model.Cliente;

public class ClienteDao extends AbstractDao implements IClienteDao {


	public ClienteDao(Connection conn) {
		super(conn);
	}

	@Override
	public void insert(Cliente model) throws Exception {
		final String SQL_INSERT = "insert into clientes(nome, inscricao_federal) values(?,?)";
		
		PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT);
		preparedStatement.setString(1, model.getNome());
		preparedStatement.setString(2, model.getInscricaoFederal());
		preparedStatement.execute();
		preparedStatement.close();
		
	}

	@Override
	public void update(Cliente model) throws Exception {
		final String SQL_UPDATE = "update clientes set nome=?, inscricao_federal=? where codigo=?";
		
		PreparedStatement preparedStatement = conn.prepareStatement(SQL_UPDATE);
		preparedStatement.setString(1, model.getNome());
		preparedStatement.setString(2, model.getInscricaoFederal());
		preparedStatement.setInt(3, model.getCodigo());
		preparedStatement.execute();
		preparedStatement.close();
	}

	@Override
	public void delete(Object id) throws Exception {
		final String SQL_DELETE = "delete from clientes where codigo=?";
		
		PreparedStatement preparedStatement = conn.prepareStatement(SQL_DELETE);
		preparedStatement.setInt(1, (Integer) id);
		preparedStatement.execute();
		preparedStatement.close();
	}

	@Override
	public List<Cliente> listAll() throws Exception {
		final String SQL_SELECT_ALL = "select * from clientes";
		
		PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT_ALL);
		ResultSet rs = (ResultSet) preparedStatement.executeQuery();
		
		List<Cliente> clientes = new ArrayList<>();
		
		Cliente cliente = null;
		
		while(rs.next()) {
			cliente = new Cliente();
			
			cliente.setCodigo(rs.getInt("codigo"));
			cliente.setInscricaoFederal(rs.getString("inscricao_federal"));
			cliente.setNome(rs.getString("nome"));
			
			clientes.add(cliente);
		}
		
		
		return clientes;
	}

	@Override
	public Cliente get(Object id) throws Exception {
		final String SQL_SELECT_BY_ID = "select * from clientes where codigo =?";
		
		PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT_BY_ID);
		preparedStatement.setInt(1, (Integer) id);;
		
		ResultSet rs = (ResultSet) preparedStatement.executeQuery();
		
		Cliente cliente = new Cliente();
		
		if(rs.next()) {
			
			cliente.setCodigo(rs.getInt("codigo"));
			cliente.setInscricaoFederal(rs.getString("inscricao_federal"));
			cliente.setNome(rs.getString("nome"));
		}
		preparedStatement.execute();
		preparedStatement.close();
		
		return cliente;
	}

	@Override
	public List<Cliente> findByNome(String nome) throws Exception {
		final String SQL_FIND_BY_NOME = "select * from clientes where nome like ?";

		PreparedStatement preparedStatement = conn.prepareStatement(SQL_FIND_BY_NOME);
		preparedStatement.setString(1, nome);
		
		ResultSet rs = (ResultSet) preparedStatement.executeQuery();
		Cliente cliente = null;
		List<Cliente> clientes = new ArrayList<>(); 
		
		while(rs.next()) {
			cliente = new Cliente();
			
			cliente.setCodigo(rs.getInt("codigo"));
			cliente.setInscricaoFederal(rs.getString("inscricao_federal"));
			cliente.setNome(rs.getString("nome"));
			
			clientes.add(cliente);
		}
		preparedStatement.execute();
		preparedStatement.close();
		
		return clientes;
	}
	
}
