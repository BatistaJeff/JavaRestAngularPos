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
import com.study.dao.jdbc.ItemPedidoDao;
import com.study.model.ItemPedido;

@Path("/itemPedido")
public class ItemPedidoResource extends Conexao{

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ItemPedido get(ItemPedido itemPedido) {
		
		ItemPedido itemPedidoNovo = new ItemPedido(itemPedido.getPedido(), itemPedido.getProduto());
		
		ItemPedidoDao itemPedidoDao = new ItemPedidoDao(criaConexao());
		
		try {
			itemPedidoNovo.setValorTotal(itemPedidoDao.get(itemPedido).getValorTotal());
			itemPedidoNovo.setValorUnitario(itemPedidoDao.get(itemPedido).getValorUnitario());
			itemPedidoNovo.setQuantidade(itemPedidoDao.get(itemPedido).getQuantidade());
			itemPedidoNovo.setPedido(itemPedidoDao.get(itemPedido).getPedido());
			itemPedidoNovo.setProduto(itemPedidoDao.get(itemPedido).getProduto());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return itemPedidoNovo;
	}
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ItemPedido> list() {
		
		List<ItemPedido> itemPedidos = new ArrayList<ItemPedido>();
	
		ItemPedidoDao itemPedidoDao = new ItemPedidoDao(criaConexao());
		try {
			itemPedidos.addAll(itemPedidoDao.listAll());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return itemPedidos;
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String post(ItemPedido itemPedido) {
		
		ItemPedidoDao itemPedidoDao = new ItemPedidoDao(criaConexao());
		
		try {
			itemPedidoDao.insert(itemPedido);
			return "Item pedido inserido";
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "Erro ao inserir";
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public ItemPedido put(ItemPedido itemPedido) {
		
		ItemPedidoDao itemPedidoDao = new ItemPedidoDao(criaConexao());
		try {
			itemPedidoDao.update(itemPedido);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return itemPedido;
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String delete(ItemPedido itemPedido) {
		
		ItemPedidoDao itemPedidoDao = new ItemPedidoDao(criaConexao());
		
		try {
			itemPedidoDao.delete(itemPedido.getPedido().getNumeroPedido());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Item Pedido excluido com sucesso";
	}
	
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@PathParam("id") Integer id) {
		
		ItemPedidoDao itemPedidoDao = new ItemPedidoDao(criaConexao());
		
		try {
			itemPedidoDao.delete(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
