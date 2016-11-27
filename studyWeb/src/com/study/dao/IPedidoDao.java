package com.study.dao;

import java.sql.Date;
import java.util.List;

import com.study.model.Pedido;

public interface IPedidoDao extends IDao<Pedido> {

	List<Pedido> findByDataPedido(Date dataPedido) 
		throws Exception;
		
}
