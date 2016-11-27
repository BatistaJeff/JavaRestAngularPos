package com.study.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.study.dao.AbstractDao;
import com.study.dao.IProdutoDao;
import com.study.model.Produto;

public class ProdutoDao extends AbstractDao implements IProdutoDao{

	public ProdutoDao(Connection conn) {
		super(conn);
	}

	
	@Override
	public void insert(Produto model) throws Exception {
		final String SQL_INSERT = "insert into produtos(codigo_produto, nome_produto) values(?,?)";
		
		PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT);
		
		preparedStatement.setString(1, model.getCodigo());
		preparedStatement.setString(2, model.getNome());
		preparedStatement.execute();
		preparedStatement.close();
		
	}

	@Override
	public void update(Produto model) throws Exception {
		final String SQL_UPDATE = "update produtos set codigo_produto=? , nome_produto=? where codigo_produto=?";
		
		PreparedStatement preparedStatement = conn.prepareStatement(SQL_UPDATE);
		
		preparedStatement.setString(1, model.getCodigo());
		preparedStatement.setString(2, model.getNome());
		preparedStatement.setString(3, model.getCodigo());
		preparedStatement.execute();
		preparedStatement.close();
		
	}

	@Override
	public void delete(Object id) throws Exception {
		final String SQL_DELETE = "delete from produtos where codigo_produto =?";
		
		PreparedStatement preparedStatement = conn.prepareStatement(SQL_DELETE);
		
		preparedStatement.setString(1 , (String) id);
		preparedStatement.execute();
		preparedStatement.close();
		
	}

	@Override
	public List<Produto> listAll() throws Exception {
		final String SQL_SELECT_ALL = "select * from produtos";
		
		PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT_ALL);
		ResultSet rs = (ResultSet) preparedStatement.executeQuery();
		
		Produto produto = null;
		List<Produto> produtos = new ArrayList<>();
		
		while(rs.next()) {
			produto = new Produto();
			
			produto.setCodigo(rs.getString("codigo_produto"));
			produto.setNome(rs.getString("nome_produto"));
			
			produtos.add(produto);
		}
		
		rs.close();
		preparedStatement.close();
		
		return produtos;

	}

	@Override
	public Produto get(Object id) throws Exception {
		final String SQL_SELECT_BY_ID = "select * from produtos where codigo_produto = ?";
		
		PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT_BY_ID);
		preparedStatement.setString(1, (String) id);
		
		ResultSet rs = (ResultSet) preparedStatement.executeQuery();
		
		Produto produto = new Produto();
		
		if(rs.next()) {
			
			produto.setCodigo(rs.getString("codigo_produto"));
			produto.setNome(rs.getString("nome_produto"));
		}
		
		rs.close();
		preparedStatement.close();
		
		return produto;
	}


	@Override
	public List<Produto> findByNome(String nome) throws Exception {
		
		final String SQL_FIND_BY_PRODUTO = "select * from produtos where nome_produto like ?";
		
		PreparedStatement preparedStatement = conn.prepareStatement(SQL_FIND_BY_PRODUTO);
		preparedStatement.setString(1, nome);
		
		ResultSet rs = (ResultSet) preparedStatement.executeQuery();
		
		Produto produto = null;
		List<Produto> produtos = new ArrayList<>();
		
		while(rs.next()) {
			produto = new Produto();
			
			produto.setCodigo(rs.getString("codigo_produto"));
			produto.setNome(rs.getString("nome_produto"));
			
			produtos.add(produto);
		}
		
		return produtos;
	}

}
