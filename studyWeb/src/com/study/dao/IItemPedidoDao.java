package com.study.dao;

import java.util.List;

import com.study.model.ItemPedido;

public interface IItemPedidoDao extends IDao<ItemPedido> {
	List<ItemPedido> vinculados() throws Exception;
}
