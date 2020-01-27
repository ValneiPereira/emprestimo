package com.emprestimo.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.emprestimo.ed.EnderecoED;
import com.emprestimo.endereco.EnderecoBD;
import com.emprestimo.service.NegocioException;
import com.emprestimo.util.jsf.FacesUtil;

@Named
@ViewScoped
public class PesquisaEnderecoMB implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	EnderecoBD enderecoDAO;

	private List<EnderecoED> enderecos = new ArrayList<>();

	private EnderecoED enderecoSelecionado;

	public List<EnderecoED> getclientes() {
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

	public EnderecoED getEnderecoSelecionado() {
		return enderecoSelecionado;
	}

	public void setEnderecoSelecionado(EnderecoED enderecoSelecionado) {
		this.enderecoSelecionado = enderecoSelecionado;
	}

	@PostConstruct
	public void inicializar() {
		enderecos = enderecoDAO.buscarTodos();
	}
}
