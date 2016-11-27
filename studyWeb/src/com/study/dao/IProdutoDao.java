package com.study.dao;

import java.util.List;

import com.study.model.Produto;

public interface IProdutoDao extends IDao<Produto>{

	List<Produto> findByNome(String nome)
		throws Exception;
}
