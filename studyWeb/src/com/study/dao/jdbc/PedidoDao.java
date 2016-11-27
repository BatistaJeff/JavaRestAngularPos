package com.study.dao.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.study.dao.AbstractDao;
import com.study.dao.IPedidoDao;
import com.study.model.Cliente;
import com.study.model.Pedido;

public class PedidoDao extends AbstractDao implements IPedidoDao {

	public PedidoDao(Connection conn) {
		super(conn);
	}

	@Override
	public void insert(Pedido pedido) throws Exception {
		final String SQL_INSERT = "insert into pedidos(data_pedido, codigo_cliente) values(?,?)";

		PreparedStatement preparedStatement = conn.prepareStatement(SQL_INSERT);
		preparedStatement.setDate(1, pedido.getDataPedido());
		preparedStatement.setInt(2, pedido.getCliente().getCodigo());
		preparedStatement.execute();
		preparedStatement.close();

	}

	@Override
	public void update(Pedido model) throws Exception {
		final String SQL_UPDATE = "update pedidos set data_pedido=?, codigo_cliente=?  where numero_pedido=?";

		PreparedStatement preparedStatement = conn.prepareStatement(SQL_UPDATE);
		preparedStatement.setDate(1, model.getDataPedido());
		preparedStatement.setInt(2, model.getCliente().getCodigo());
		preparedStatement.setInt(3, model.getNumeroPedido());
		preparedStatement.execute();
		preparedStatement.close();

	}

	@Override
	public void delete(Object id) throws Exception {
		final String SQL_DELETE = "delete from pedidos where numero_pedido =?";

		PreparedStatement preparedStatement = conn.prepareStatement(SQL_DELETE);
		preparedStatement.setInt(1, (Integer) id);
		preparedStatement.execute();
		preparedStatement.close();

	}

	@Override
	public List<Pedido> listAll() throws Exception {
		final String SQL_SELECT_ALL = "select * from pedidos";

		PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT_ALL);
		ResultSet rs = (ResultSet) preparedStatement.executeQuery();

		List<Pedido> pedidos = new ArrayList<>();
		Pedido pedido = null;
		Cliente cliente = null;
		while (rs.next()) {

			pedido = new Pedido();
			cliente = new Cliente();

			pedido.setDataPedido(rs.getDate("data_pedido"));
			pedido.setNumeroPedido(rs.getInt("numero_pedido"));
			cliente.setCodigo(rs.getInt("codigo_cliente"));

			pedido.setCliente(cliente);

			pedidos.add(pedido);
		}
		rs.close();
		preparedStatement.close();

		return pedidos;
	}

	@Override
	public Pedido get(Object id) throws Exception {
		final String SQL_SELECT_BY_ID = "select * from pedidos where numero_pedido = ?";

		PreparedStatement preparedStatement = conn.prepareStatement(SQL_SELECT_BY_ID);
		preparedStatement.setInt(1, (Integer) id);

		ResultSet rs = (ResultSet) preparedStatement.executeQuery();

		Pedido pedido = null;

		if (rs.next()) {
			pedido = new Pedido();

			pedido.setDataPedido(rs.getDate("data_pedido"));
			pedido.setNumeroPedido(rs.getInt("numero_pedido"));
		}
		rs.close();
		preparedStatement.close();

		return pedido;
	}

	@Override
	public List<Pedido> findByDataPedido(Date dataPedido) throws Exception {
		final String SQL_FIND_BY_PEDIDO = "select * from pedidos where data_pedido like ?";

		PreparedStatement preparedStatement = conn.prepareStatement(SQL_FIND_BY_PEDIDO);
		preparedStatement.setDate(1, (Date) dataPedido);

		ResultSet rs = (ResultSet) preparedStatement.executeQuery();

		Pedido pedido = null;
		List<Pedido> pedidos = null;

		while (rs.next()) {
			pedido = new Pedido();
			pedidos = new ArrayList<>();

			pedido.setDataPedido(rs.getDate("data_pedido"));
			pedido.setNumeroPedido(rs.getInt("numero_pedido"));

			pedidos.add(pedido);
		}

		return pedidos;
	}

}
