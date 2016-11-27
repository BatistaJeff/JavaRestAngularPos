package com.study.dao.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.study.dao.AbstractDao;
import com.study.dao.IItemPedidoDao;
import com.study.model.ItemPedido;
import com.study.model.ItemPedidoPK;
import com.study.model.Pedido;
import com.study.model.Produto;

public class ItemPedidoDao extends AbstractDao implements IItemPedidoDao {

	public ItemPedidoDao(Connection conn) {
		super(conn);
	}

	@Override
	public void insert(ItemPedido model) throws Exception {
		final String SQL_INSERT = "insert into itens_pedidos(quantidade, valor_unitario, valor_total , numero_pedido, codigo_produto) values(?,?,?,?,?)";

		PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT);
		preparedStatement.setBigDecimal(1, model.getQuantidade());
		preparedStatement.setBigDecimal(2, model.getValorUnitario());
		preparedStatement.setBigDecimal(3, model.getValorTotal());
		preparedStatement.setInt(4, model.getPedido().getNumeroPedido());
		preparedStatement.setString(5, model.getProduto().getCodigo());

		preparedStatement.execute();
		preparedStatement.close();
	}

	@Override
	public void update(ItemPedido model) throws Exception {
		final String SQL_UPDATE = "update itens_pedidos set quantidade=?, valor_unitario=?, valor_total=? , numero_pedido=? , codigo_produto=? where numero_pedido=?";

		PreparedStatement preparedStatement = conn.prepareStatement(SQL_UPDATE);
		preparedStatement.setBigDecimal(1, model.getQuantidade());
		preparedStatement.setBigDecimal(2, model.getValorUnitario());
		preparedStatement.setBigDecimal(3, model.getValorTotal());
		preparedStatement.setInt(4, model.getPedido().getNumeroPedido());
		preparedStatement.setString(5, model.getProduto().getCodigo());
		preparedStatement.setInt(6, model.getPedido().getNumeroPedido());

		preparedStatement.execute();
		preparedStatement.close();
	}

	@Override
	public void delete(Object id) throws Exception {
		final String SQL_DELETE = "delete from itens_pedidos where numero_pedido=?";

		PreparedStatement preparedStatement = conn.prepareStatement(SQL_DELETE);
		preparedStatement.setInt(1, (Integer) id);
		preparedStatement.execute();
		preparedStatement.close();
	}

	@Override
	public List<ItemPedido> listAll() throws Exception {
		final String SQL_SELECT_ALL = "select * from itens_pedidos";

		PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT_ALL);
		ResultSet rs = (ResultSet) preparedStatement.executeQuery();

		List<ItemPedido> itemPedidos = new ArrayList<>();
		ItemPedido itemPedido = null;
		Pedido pedido = null;
		Produto produto = null;

		while (rs.next()) {

			itemPedido = new ItemPedido(pedido, produto);
			pedido = new Pedido();
			produto = new Produto();

			pedido.setNumeroPedido(rs.getInt("numero_pedido"));
			produto.setCodigo(rs.getString("codigo_produto"));

			itemPedido.setPedido(pedido);
			itemPedido.setProduto(produto);
			itemPedido.setQuantidade(rs.getBigDecimal("quantidade"));
			itemPedido.setValorTotal(rs.getBigDecimal("valor_total"));
			itemPedido.setValorUnitario(rs.getBigDecimal("valor_unitario"));
			itemPedidos.add(itemPedido);
		}

		return itemPedidos;
	}

	@Override
	public ItemPedido get(Object id) throws Exception {
		ItemPedido result = null;

		final String SQL = "select * from itens_pedidos where numero_pedido =? and codigo_produto =?";

		PreparedStatement ps = conn.prepareStatement(SQL);

		ItemPedidoPK pk = (ItemPedidoPK) id;

		ps.setInt(1, pk.getPedido().getNumeroPedido());
		ps.setString(2, pk.getProduto().getCodigo());
		ResultSet rs = ps.executeQuery();

		if (rs.next()) {

			result = new ItemPedido(pk.getPedido(), pk.getProduto());

			result.setQuantidade(rs.getBigDecimal("quantidade"));
			result.setValorUnitario(rs.getBigDecimal("valor_unitario"));
			result.setValorTotal(rs.getBigDecimal("valor_total"));
		}
		return result;
	}

	@Override
	public List<ItemPedido> vinculados() throws Exception {
		final String SQL_SELECT_ALL = "select * from itens_pedidos";

		PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT_ALL);
		ResultSet rs = (ResultSet) preparedStatement.executeQuery();

		List<ItemPedido> itemPedidos = new ArrayList<>();
		ItemPedido itemPedido = null;
		Pedido pedido = null;
		Produto produto = null;

		while (rs.next()) {

			itemPedido = new ItemPedido(pedido, produto);
			pedido = new Pedido();
			produto = new Produto();

			pedido.setNumeroPedido(rs.getInt("numero_pedido"));
			produto.setCodigo(rs.getString("codigo_produto"));

			itemPedido.setPedido(pedido);
			itemPedido.setProduto(produto);
			itemPedido.setQuantidade(rs.getBigDecimal("quantidade"));
			itemPedido.setValorTotal(rs.getBigDecimal("valor_total"));
			itemPedido.setValorUnitario(rs.getBigDecimal("valor_unitario"));
			itemPedidos.add(itemPedido);
		}

		return itemPedidos;
	}

}
