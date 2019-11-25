package com.emprestimo.service;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.inject.Inject;

import com.emprestimo.dao.ClienteDAO;
import com.emprestimo.modelo.Cliente;
import com.emprestimo.util.jpa.Transactional;


public class CadastroClienteService implements Serializable {

  private static final long serialVersionUID = 1L;
  
  @Inject
	private ClienteDAO clienteDAO;

	@Transactional
	public void salvar(Cliente cliente) throws NegocioException {
		if (cliente.getNome() == null || cliente.getNome().trim().equals("")) {
			throw new NegocioException("O nome do cliente é obrigatório");
		}
		if (cliente.getCpf() == null || cliente.getCpf().trim().equals("")) {
      throw new NegocioException("O CPF do cliente é obrigatório");
    }
		if (cliente.getSexo() == null || cliente.getSexo().trim().equals("")) {
      throw new NegocioException("O Sexo do cliente é obrigatório");
    }
		if (cliente.getTipoCliente() == null || cliente.getTipoCliente().trim().equals("")) {
      throw new NegocioException("Tipo do cliente é obrigatório");
    }
		this.clienteDAO.salvar(cliente);
	}
	
	public void verificarRiscos(Cliente cliente) {
	  
	  if(cliente.getRendimentoMensal().compareTo(new BigDecimal(2000))== -1){
	   cliente.setRiscos("C");  
	  } else if((cliente.getRendimentoMensal().compareTo(new BigDecimal(2000))== 1) &&
	           (cliente.getRendimentoMensal().compareTo(new BigDecimal(8000))== -1)) {
	    cliente.setRiscos("B");   
	  } else if(cliente.getRendimentoMensal().compareTo( new BigDecimal(8000))== 1) {
	    cliente.setRiscos("A");
	  }
	   this.clienteDAO.salvar(cliente);

	}
	public void verificarTipoCliente(Cliente cliente) {
	  
	  if(cliente.getTipoCliente().equals("E")) {
	    cliente.setTotalPatrimonio(cliente.getTotalPatrimonio());
	  }else if(cliente.getTipoCliente().equals("P")) {
	    cliente.setDividasAtuais(new BigDecimal(0));;
	  }else if(cliente.getTipoCliente().equals("C")) {
	    cliente.isTrabalhando();
	  }
	  this.clienteDAO.salvar(cliente);
	  
	
	}
	
	
}