package com.study.teste.jdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.study.dao.Conexao;
import com.study.dao.jdbc.ProdutoDao;
import com.study.model.Produto;

public class ClasseTestadoraProduto extends Conexao {

	public static void main(String[] args) throws SQLException {
		
		ClasseTestadoraProduto classeTestadoraProduto = new ClasseTestadoraProduto();
		
//		classeTestadoraProduto.insere("D2","teste2");
//		classeTestadoraProduto.deleta("D1");
//		classeTestadoraProduto.update("D2","teste teste");
		classeTestadoraProduto.listAll();
//		classeTestadoraProduto.get("D2");
//		classeTestadoraProduto.findByNome("teste");
		
	}
	
	//TODO METODO INSERE
	public void insere(String codigo_produto, String nome_produto){
		
		Produto produto = new Produto();
		produto.setCodigo(codigo_produto);
		produto.setNome(nome_produto);
		
		ProdutoDao produtoDao = new ProdutoDao(criaConexao());
		
		try {
			produtoDao.insert(produto);
			System.out.println("INSERINDO PRODUTO");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//TODO METODO DELETA
	public void deleta(String codigo_produto){
		
		ProdutoDao produtoDao = new ProdutoDao(criaConexao());
		
		try {
			produtoDao.delete(codigo_produto);
			System.out.println("DELETANDO PRODUTO");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//TODO METODO UPDATE
	public void update(String codigo_produto, String nome_produto) {
		
		Produto produto = new Produto();
		
		produto.setCodigo(codigo_produto);
		produto.setNome(nome_produto);
		
		ProdutoDao produtoDao = new ProdutoDao(criaConexao());
		
		try {
			produtoDao.update(produto);
			System.out.println("UPDATE PRODUTO");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//TODO METODO LISTALL
	public void listAll() {
		
		
		ProdutoDao produtoDao = new ProdutoDao(criaConexao());
		List<Produto> produtos = new ArrayList<>();
		
		try {
			produtos.addAll(produtoDao.listAll());
			System.out.println("LISTANDO PRODUTO");

			for(Produto produto : produtos) {
				
				System.out.println("Codigo do Produto: " +produto.getCodigo());
				System.out.println("Nome do produto: " +produto.getNome());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//TODO METODO GET 
	public void get(String codigo_produto) {
		
		ProdutoDao produtoDao = new ProdutoDao(criaConexao());
		
		try {
			System.out.println("GET PRODUTO");
			System.out.println(produtoDao.get(codigo_produto).getCodigo());
			System.out.println(produtoDao.get(codigo_produto).getNome());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//TODO METODO FINDBYNOME 
	public void findByNome(String nome) {

		ProdutoDao produtoDao = new ProdutoDao(criaConexao());
		List<Produto> produtos = new ArrayList<Produto>();
		
		try {
			System.out.println("FINDPORNOME");
			produtos.addAll(produtoDao.findByNome(nome));

			for(Produto produto : produtos) {
				System.out.println("Codigo Produto: " +produto.getCodigo());
				System.out.println("Nome do produto: " +produto.getNome());
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
}
