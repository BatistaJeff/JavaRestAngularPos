package com.study.dao;

import java.util.List;

import com.study.model.Cliente;

public interface IClienteDao extends IDao<Cliente> {

	List<Cliente> findByNome(String nome) 
			throws Exception;
}
