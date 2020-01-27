package com.emprestimo.endereco;

import java.io.Serializable;

import javax.inject.Inject;

import com.emprestimo.ed.EnderecoED;
import com.emprestimo.service.NegocioException;
import com.emprestimo.util.jpa.Transactional;


public class EnderecoRN implements Serializable {

  private static final long serialVersionUID = 1L;
  
  @Inject
	private EnderecoBD enderecoDAO;

	@Transactional
	public void salvar(EnderecoED endereco) throws NegocioException {
		if (endereco.getNomeRua() == null || endereco.getNomeRua().trim().equals("")) {
			throw new NegocioException("O endereço é obrigatório");
		}

		this.enderecoDAO.salvar(endereco);
	}

	}
