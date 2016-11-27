package com.study.teste.jdbc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.study.dao.Conexao;
import com.study.dao.jdbc.ItemPedidoDao;
import com.study.dao.jdbc.PedidoDao;
import com.study.model.ItemPedido;
import com.study.model.Pedido;
import com.study.model.Produto;

public class ClasseTestadoraItemPedido extends Conexao {

	public static void main(String[] args) {

		ClasseTestadoraItemPedido classeTestadoraItemPedido = new ClasseTestadoraItemPedido();

		// classeTestadoraItemPedido.insere();
		// classeTestadoraItemPedido.delete(1);
		// classeTestadoraItemPedido.update();
		classeTestadoraItemPedido.listAll();

	}

	// TODO INSERE
	public void insere() {

		Pedido pedido = new Pedido();
		pedido.setNumeroPedido(1);

		Produto produto = new Produto();
		produto.setCodigo("1");

		BigDecimal big1 = new BigDecimal("00.00");

		ItemPedido itemPedido = new ItemPedido(pedido, produto);

		itemPedido.setQuantidade(big1);
		itemPedido.setValorTotal(big1);
		itemPedido.setValorUnitario(big1);
		itemPedido.setPedido(pedido);
		itemPedido.setProduto(produto);

		ItemPedidoDao itemPedidoDao = new ItemPedidoDao(criaConexao());

		try {
			itemPedidoDao.insert(itemPedido);
			System.out.println("INSERINDO PRODUTO");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// TODO DELETE
	public void delete(Integer id) {

		PedidoDao pedidoDao = new PedidoDao(criaConexao());

		try {
			pedidoDao.delete(id);
			System.out.println("DELETANDO PEDIDO");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// TODO UPDATE
	public void update() {
		Pedido pedido = new Pedido();
		pedido.setNumeroPedido(1);

		Produto produto = new Produto();
		produto.setCodigo("1");

		BigDecimal big1 = new BigDecimal("11.00");

		ItemPedido itemPedido = new ItemPedido(pedido, produto);

		itemPedido.setPedido(pedido);
		itemPedido.setProduto(produto);
		itemPedido.setQuantidade(big1);
		itemPedido.setValorTotal(big1);
		itemPedido.setValorUnitario(big1);

		ItemPedidoDao itemPedidoDao = new ItemPedidoDao(criaConexao());

		try {
			System.out.println("UPDATE");
			itemPedidoDao.update(itemPedido);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// TODO LISTALL
	public void listAll() {

		ItemPedidoDao itemPedidoDao = new ItemPedidoDao(criaConexao());
		List<ItemPedido> itemPedidos = new ArrayList<ItemPedido>();

		try {
			itemPedidos.addAll(itemPedidoDao.listAll());
			System.out.println("Listando");

			for (ItemPedido itemPedido : itemPedidos) {
				System.out.println("Valor Unitario: " + itemPedido.getValorUnitario());
				System.out.println("Valor Total:" + itemPedido.getValorTotal());
				System.out.println("Quantidade:" + itemPedido.getQuantidade());
				System.out.println("Numero Pedido:" + itemPedido.getPedido().getNumeroPedido());
				System.out.println("Codigo Produto:" + itemPedido.getProduto().getCodigo());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
