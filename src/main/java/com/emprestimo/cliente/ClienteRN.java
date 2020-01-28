package com.emprestimo.cliente;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.inject.Inject;

import com.emprestimo.ed.ClienteED;
import com.emprestimo.service.NegocioException;
import com.emprestimo.util.jpa.Transactional;

public class ClienteRN implements Serializable {

  private static final long serialVersionUID = 1L;

  @Inject
  private ClienteBD clienteDAO;

  @Transactional
  public void salvar(ClienteED cliente) throws NegocioException {
    if (cliente.getNome() == null || cliente.getNome().trim().equals("")) {
      throw new NegocioException("O nome do cliente é obrigatório");
    }

    if (cliente.getTipoCliente() == null || cliente.getTipoCliente().trim().equals("")) {
      throw new NegocioException("Tipo do cliente é obrigatório");
    }
    this.clienteDAO.salvar(cliente);
  }

  public void verificarRiscos(ClienteED cliente) {

    if (cliente.getRendimentoMensal().compareTo(new BigDecimal(2000)) == -1) {
      cliente.setRiscos("C");
    } else if ((cliente.getRendimentoMensal().compareTo(new BigDecimal(2000)) == 1) && (cliente.getRendimentoMensal().compareTo(new BigDecimal(8000)) == -1)) {
      cliente.setRiscos("B");
    } else if (cliente.getRendimentoMensal().compareTo(new BigDecimal(8000)) == 1) {
      cliente.setRiscos("A");
    }

  }

  public void verificarTipoCliente(ClienteED cliente) {

    if (cliente.getTipoCliente().equals("E")) {
      cliente.setTotalPatrimonio(cliente.getTotalPatrimonio());
    } else if (cliente.getTipoCliente().equals("P")) {
      cliente.setDividasAtuais(new BigDecimal(0));
    } else if (cliente.getTipoCliente().equals("C")) {
      cliente.isTrabalhando();
    }

  }

}