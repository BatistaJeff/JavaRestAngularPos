package com.study.model;

import java.io.Serializable;

public class Produto implements Serializable {

	private static final long serialVersionUID = -1116179937758523182L;

	private String codigo;
	private String nome;
	
	
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
	
}
