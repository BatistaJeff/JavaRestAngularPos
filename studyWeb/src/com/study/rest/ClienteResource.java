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
import com.study.dao.jdbc.ClienteDao;
import com.study.model.Cliente;

@Path("/cliente")
public class ClienteResource extends Conexao {

	
	@GET
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Cliente get(@PathParam("id") int id) {
		
		Cliente cliente = new Cliente();
		
		ClienteDao clienteDao = new ClienteDao(criaConexao());
		try {
		cliente.setInscricaoFederal(clienteDao.get(id).getInscricaoFederal());
		cliente.setNome(clienteDao.get(id).getNome());
		cliente.setCodigo(clienteDao.get(id).getCodigo());
		System.out.println("WebService - Pegando CLIENTE por id");
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return cliente;
	}
	
	@GET
	@Path("/all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Cliente> list() {
		
		List<Cliente> clientes = new ArrayList<Cliente>();
		
		ClienteDao clienteDao = new ClienteDao(criaConexao());
		
		try {
			clientes.addAll(clienteDao.listAll());
			System.out.println("WebService - Listando CLIENTE");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return clientes;
	}
	
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String post(Cliente cliente) {
		
		ClienteDao clienteDao = new ClienteDao(criaConexao());
		
		try {
			clienteDao.insert(cliente);
			System.out.println("WebService - Inserindo CLIENTE");
			return "Usuario inserido";
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "Erro ao inserir";
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Cliente put(Cliente cliente) {
			
		ClienteDao clienteDao = new ClienteDao(criaConexao());
		
		try {
			clienteDao.update(cliente);
			System.out.println("WebService - Alterando CLIENTE");
		} catch (Exception e) {
			System.out.println("Erro ao alterar Cliente");
			e.printStackTrace();
		}
		return cliente;
	}
	
	@DELETE
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String delete(Cliente cliente) {
		
		ClienteDao clienteDao = new ClienteDao(criaConexao());
		
		try {
			clienteDao.delete(cliente.getCodigo());
			System.out.println("WebService - Deletando CLIENTE");
			return "Excluido com sucesso";
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(cliente.getCodigo());
		
		return "Erro ao deletar";
	}
	
	@DELETE
	@Path("{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public void delete(@PathParam("id") Integer id) {
		
		ClienteDao clienteDao = new ClienteDao(criaConexao());
		
		try {
			clienteDao.delete(id);
			System.out.println("WebService - Deletando CLIENTE");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
