package com.study.dao;

import java.util.List;

public interface IDao<T> {

	void insert(T model) throws Exception;
	void update(T model) throws Exception;
	void delete(Object id) throws Exception;
	List<T> listAll() throws Exception;
	T get(Object id) throws Exception;
	

}
