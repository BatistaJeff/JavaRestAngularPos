package com.study.teste.jdbc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.study.dao.Conexao;
import com.study.dao.jdbc.ClienteDao;
import com.study.model.Cliente;

public class ClasseTestadoraCliente extends Conexao{

	public static void main(String[] args) throws SQLException {
		
		ClasseTestadoraCliente classeTestadoraCliente = new ClasseTestadoraCliente();
		
//		classeTestadoraCliente.insere("adonis 122", "inscr112");
//		classeTestadoraCliente.deleta(1);
//		classeTestadoraCliente.update(3, "adonis", "H1");
		classeTestadoraCliente.listAll();
//		classeTestadoraCliente.get(3);
//		classeTestadoraCliente.findByNome("H1");
		
	}
	
	//TODO METODO INSERE CLIENTE
	public void insere(String nome, String inscricaoFederal){
		
		Cliente cliente = new Cliente();
		cliente.setInscricaoFederal(nome);
		cliente.setNome(inscricaoFederal);
		
		ClienteDao clienteDao = new ClienteDao(criaConexao());
		
		try {
			clienteDao.insert(cliente);
			System.out.println("INSERINDO CLIENTE");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//TODO METODO DELETA CLIENTE
	public void deleta(Integer codigo){
		
		ClienteDao clienteDao = new ClienteDao(criaConexao());
		
		try {
			clienteDao.delete(codigo);
			System.out.println("DELETANDO CLIENTE");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//TODO METODO UPDATE CLIENTE
	public void update(Integer codigo,String inscricaoFederal, String nome) {
		
		Cliente cliente = new Cliente();
		
		cliente.setCodigo(codigo);
		cliente.setInscricaoFederal(inscricaoFederal);
		cliente.setNome(nome);
		
		ClienteDao clienteDao = new ClienteDao(criaConexao());
		
		try {
			clienteDao.update(cliente);
			System.out.println("UPDATE CLIENTE");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//TODO METODO LISTALL CLIENTE
	public void listAll() {
		
		
		ClienteDao clienteDao = new ClienteDao(criaConexao());
		List<Cliente> clientes = new ArrayList<>();
		
		try {
			clientes.addAll(clienteDao.listAll());
			System.out.println("LISTANDO CLIENTE");

			for(Cliente cliente : clientes) {
				
				System.out.println("Codigo: " +cliente.getCodigo());
				System.out.println("Nome: " +cliente.getNome());
				System.out.println("Inscrição: " +cliente.getInscricaoFederal());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//TODO METODO GET 
	public void get(Integer codigo) {
		
		ClienteDao clienteDao = new ClienteDao(criaConexao());
		
		try {
			System.out.println("GET CLIENTE");
			System.out.println(clienteDao.get(codigo).getCodigo());
			System.out.println(clienteDao.get(codigo).getInscricaoFederal());
			System.out.println(clienteDao.get(codigo).getNome());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//TODO METODO FINDBYNOME 
	public void findByNome(String nome) {

		ClienteDao clienteDao = new ClienteDao(criaConexao());
		List<Cliente> clientes = new ArrayList<Cliente>();
		
		try {
			System.out.println("FINDBYNOME");
			clientes.addAll(clienteDao.findByNome(nome));

			for(Cliente cliente : clientes) {
				System.out.println("Codigo: " +cliente.getCodigo());
				System.out.println("Inscricao Federal: " +cliente.getInscricaoFederal());
				System.out.println("Nome: " +cliente.getNome());
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
