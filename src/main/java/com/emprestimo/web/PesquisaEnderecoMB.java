package com.emprestimo.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.emprestimo.dao.EnderecoDAO;
import com.emprestimo.modelo.Endereco;
import com.emprestimo.service.NegocioException;
import com.emprestimo.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaEnderecoMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	EnderecoDAO enderecoDAO;

	private List<Endereco> enderecos = new ArrayList<>();

	private Endereco enderecoSelecionado;

	public List<Endereco> getclientes() {
		return enderecos;
	}

	public void excluir() {
		try {
			enderecoDAO.excluir(enderecoSelecionado);
			this.enderecos.remove(enderecoSelecionado);
			FacesUtil.addSuccessMessage("Endereco " + enderecoSelecionado.getCodEndereco() + " excluído com sucesso.");
		} catch (NegocioException e) {
			FacesUtil.addErrorMessage(e.getMessage());
		}
	}

	public Endereco getEnderecoSelecionado() {
		return enderecoSelecionado;
	}

	public void setEnderecoSelecionado(Endereco enderecoSelecionado) {
		this.enderecoSelecionado = enderecoSelecionado;
	}

	@PostConstruct
	public void inicializar() {
		enderecos = enderecoDAO.buscarTodos();
	}
}
