package com.study.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.study.dao.Conexao;
import com.study.dao.jdbc.PedidoDao;
import com.study.model.Pedido;

@Path("/pedido")
public class PedidoResource extends Conexao{

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Pedido get(@PathParam("id") int id) {

		Pedido pedido = new Pedido();
		
		PedidoDao pedidoDao = new PedidoDao(criaConexao());
		
		try {
			pedido.setNumeroPedido(pedidoDao.get(id).getNumeroPedido());
			pedido.setDataPedido(pedidoDao.get(id).getDataPedido());
			pedido.setCliente(pedidoDao.get(id).getCliente());
			pedido.setItemPedidolista(pedidoDao.get(id).getItemPedidolista());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pedido;
	}
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Pedido> list() {
		
		List<Pedido> pedidos = new ArrayList<Pedido>();
		
		PedidoDao pedidoDao = new PedidoDao(criaConexao());
		
		try {
			pedidos.addAll(pedidoDao.listAll());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pedidos;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String post(Pedido pedido) {
		
		PedidoDao pedidoDao = new PedidoDao(criaConexao());

		try {
			pedidoDao.insert(pedido);
			System.out.println("Inserindo Pedido");
			return "pedido inserido";
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "Erro ao inserir";
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Pedido put(Pedido pedido) {
		
		PedidoDao pedidoDao = new PedidoDao(criaConexao());
		try {
			pedidoDao.update(pedido);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pedido;
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String delete(Pedido pedido) {
		
		PedidoDao pedidoDao = new PedidoDao(criaConexao());
		try {
			pedidoDao.delete(pedido.getNumeroPedido());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "Pedido excluido";
	}
	
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@PathParam("id") Integer id) {
		
		PedidoDao pedidoDao = new PedidoDao(criaConexao());
		
		try {
			pedidoDao.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
}
