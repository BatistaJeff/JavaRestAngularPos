package com.study.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class Pedido implements Serializable {

	private static final long serialVersionUID = 3958412512392667343L;
	
	private Integer numeroPedido;
	private Date dataPedido;
	
	private Cliente cliente;
	
	private List<ItemPedido> itemPedidolista;
	
	
	public Integer getNumeroPedido() {
		return numeroPedido;
	}
	public void setNumeroPedido(Integer numeroPedido) {
		this.numeroPedido = numeroPedido;
	}
	public Date getDataPedido() {
		return dataPedido;
	}
	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}
	public Cliente getCliente() {
		return cliente;
	}
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	public List<ItemPedido> getItemPedidolista() {
		return itemPedidolista;
	}
	public void setItemPedidolista(List<ItemPedido> itemPedidolista) {
		this.itemPedidolista = itemPedidolista;
	}
	
	
	
	
}
