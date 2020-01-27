package com.emprestimo.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.emprestimo.cliente.ClienteBD;
import com.emprestimo.ed.ClienteED;
import com.emprestimo.service.NegocioException;
import com.emprestimo.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaClienteMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	ClienteBD clienteDAO;

	private List<ClienteED> clientes = new ArrayList<>();

	private ClienteED clienteSelecionado;

	public List<ClienteED> getclientes() {
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

	public ClienteED getClienteSelecionado() {
		return clienteSelecionado;
	}

	public void setClienteSelecionado(ClienteED clienteSelecionado) {
		this.clienteSelecionado = clienteSelecionado;
	}

	@PostConstruct
	public void inicializar() {
		clientes = clienteDAO.buscarTodos();
	}
}
