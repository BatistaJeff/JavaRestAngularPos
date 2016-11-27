package com.study.teste.jdbc;

import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import com.study.dao.Conexao;
import com.study.dao.jdbc.PedidoDao;
import com.study.model.Cliente;
import com.study.model.Pedido;

public class ClasseTestadoraPedido extends Conexao{

public static void main(String[] args) throws SQLException, ParseException {
		
		ClasseTestadoraPedido classeTestadoraPedido = new ClasseTestadoraPedido();

		java.util.Date data1 = new java.util.Date();
		java.sql.Date data2 = new java.sql.Date(data1.getTime());
		
//		classeTestadoraPedido.insere(data2);
//		classeTestadoraPedido.deleta("D1");
//		classeTestadoraPedido.update(data2, 2);
		classeTestadoraPedido.listAll();
//		classeTestadoraPedido.get(2);
//		classeTestadoraPedido.findByDataPedido(data2);
		
	}
	
	public Cliente getCliente(){
		
		Cliente cliente = new Cliente();
		cliente.setCodigo(2);
		cliente.setInscricaoFederal("B1");
		cliente.setNome("Adonis");
		
		return cliente;
	}
	
	//TODO METODO INSERE
	public void insere(Date data){

		Cliente cliente = new Cliente();
		cliente.setCodigo(1);
		Pedido pedido = new Pedido();
		pedido.setCliente(cliente);
		pedido.setDataPedido(data);
		
		PedidoDao pedidoDao = new PedidoDao(criaConexao());
		
		try {
			pedidoDao.insert(pedido);
			System.out.println("INSERINDO PRODUTO");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//TODO METODO DELETA
	public void deleta(String numero_pedido){
		
		PedidoDao pedidoDao = new PedidoDao(criaConexao());
		
		try {
			pedidoDao.delete(numero_pedido);
			System.out.println("DELETANDO PEDIDO");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//TODO METODO UPDATE
	public void update(Date data, Integer numero_pedido) {
		
		ClasseTestadoraPedido classeTestadoraPedido = new ClasseTestadoraPedido();
		Pedido pedido = new Pedido();
		
		pedido.setCliente(classeTestadoraPedido.getCliente());
		pedido.setDataPedido(data);
		pedido.setNumeroPedido(numero_pedido);
		
		PedidoDao pedidoDao = new PedidoDao(criaConexao());
		
		try {
			pedidoDao.update(pedido);
			System.out.println("UPDATE PRODUTO");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//TODO METODO LISTALL
	public void listAll() {
		
		
		PedidoDao pedidoDao = new PedidoDao(criaConexao());
		List<Pedido> pedidos = new ArrayList<>();
		Cliente cliente = new Cliente();
		
		try {
			pedidos.addAll(pedidoDao.listAll());
			System.out.println("LISTANDO PRODUTO");

			for(Pedido pedido : pedidos) {
				
				System.out.println("Numero do Pedido: " +pedido.getNumeroPedido());
				System.out.println("Nome do produto: " +pedido.getDataPedido());
				System.out.println("Codigo do Cliente: " +pedido.getCliente().getCodigo());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//TODO METODO GET 
	public void get(Integer numero_pedido) {
		
		PedidoDao pedidoDao = new PedidoDao(criaConexao());
		
		try {
			System.out.println("GET PRODUTO");
			System.out.println(pedidoDao.get(numero_pedido).getNumeroPedido());
			System.out.println(pedidoDao.get(numero_pedido).getDataPedido());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//TODO METODO FINDBYNOME 
	public void findByDataPedido(Date data) {

		PedidoDao pedidoDao = new PedidoDao(criaConexao());
		List<Pedido> pedidos = new ArrayList<Pedido>();
		
		try {
			System.out.println("FIND PRODUTO");
			pedidos.addAll(pedidoDao.findByDataPedido(data));

			for(Pedido pedido : pedidos) {
				System.out.println("Codigo: " +pedido.getNumeroPedido());
				System.out.println("nome do produto: " +pedido.getDataPedido());
			}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
