package com.emprestimo.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.emprestimo.dao.ClienteDAO;
import com.emprestimo.modelo.Cliente;
import com.emprestimo.service.NegocioException;
import com.emprestimo.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaClienteMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	ClienteDAO clienteDAO;

	private List<Cliente> clientes = new ArrayList<>();

	private Cliente clienteSelecionado;

	public List<Cliente> getclientes() {
		return clientes;
	}

	public void excluir() {
		try {
			clienteDAO.excluir(clienteSelecionado);
			this.clientes.remove(clienteSelecionado);
			FacesUtil.addSuccessMessage("Cliente " + clienteSelecionado.getNome() + " excluído com sucesso.");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public Cliente getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(Cliente clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}

	@PostConstruct
	public void inicializar() {
		clientes = clienteDAO.buscarTodos();
	}
}
