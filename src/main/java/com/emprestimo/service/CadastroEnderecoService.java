package com.emprestimo.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.emprestimo.dao.EnderecoDAO;
import com.emprestimo.modelo.Endereco;
import com.emprestimo.util.jpa.Transactional;


public class CadastroEnderecoService implements Serializable {

  private static final long serialVersionUID = 1L;
  
  @Inject
	private EnderecoDAO enderecoDAO;

	@Transactional
	public void salvar(Endereco endereco) throws NegocioException {
		if (endereco.getNomeRua() == null || endereco.getNomeRua().trim().equals("")) {
			throw new NegocioException("O endereço é obrigatório");
		}

		this.enderecoDAO.salvar(endereco);
	}

	}
