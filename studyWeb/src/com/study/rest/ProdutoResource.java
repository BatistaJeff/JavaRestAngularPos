package com.study.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.study.dao.Conexao;
import com.study.dao.jdbc.ProdutoDao;
import com.study.model.Produto;

@Path("/produto")
public class ProdutoResource extends Conexao {

	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Produto get(@PathParam("id") int id) {

		Produto produto = new Produto();
		ProdutoDao produtoDao = new ProdutoDao(criaConexao());
		try {
			produto.setCodigo(produtoDao.get(id).getCodigo());
			produto.setNome(produtoDao.get(id).getNome());
			System.out.println("WebService - Pegando PRODUTO por Id");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return produto;
	}

	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Produto> getAll() {

		List<Produto> produtos = new ArrayList<Produto>();

		ProdutoDao produtoDao = new ProdutoDao(criaConexao());
		try {
			produtos.addAll(produtoDao.listAll());
			System.out.println("WebService - Listando PRODUTO");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return produtos;
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String post(Produto produto) {

		ProdutoDao produtoDao = new ProdutoDao(criaConexao());

		try {
			produtoDao.insert(produto);
			System.out.println("WebService - Inserindo PRODUTO");
			return "Produto inserido";
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "Erro ao inserir";
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Produto put(Produto produto) {

		ProdutoDao produtoDao = new ProdutoDao(criaConexao());

		try {
			produtoDao.update(produto);
			System.out.println("WebService - Alterando PRODUTO");
		} catch (Exception e) {
			System.out.println("Erro ao alterar produto");
			e.printStackTrace();
		}
		return produto;
	}

	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String delete(Produto produto) {

		ProdutoDao produtoDao = new ProdutoDao(criaConexao());

		try {
			produtoDao.delete(produto.getCodigo());
			System.out.println("WebService - Deletando PRODUTO");
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println(produto.getCodigo());

		return "Produto excluido com sucesso";
	}

	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@PathParam("id") Integer id) {

		ProdutoDao produtoDao = new ProdutoDao(criaConexao());

		try {
			produtoDao.delete(id);
			System.out.println("WebService - Deletando Produto");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
