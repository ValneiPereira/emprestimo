package com.emprestimo.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.emprestimo.modelo.Cliente;
import com.emprestimo.service.NegocioException;
import com.emprestimo.util.jpa.Transactional;


public class ClienteDAO implements Serializable {
	
  private static final long serialVersionUID = 1L;
  
  @Inject
	private EntityManager em;

	public void salvar(Cliente cliente) {
		 em.merge(cliente);		
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> buscarTodos() {
		return em.createQuery("from Cliente").getResultList();
	}
	@Transactional
  public void excluir(Cliente cliente) throws NegocioException {
    Cliente clienteTemp = em.find(Cliente.class, cliente.getCodCliente());
    
    em.remove(clienteTemp);
    em.flush();
  }

  public Cliente buscarPeloCodigo(Long codigo) {
    return em.find(Cliente.class, codigo);
  }

}
