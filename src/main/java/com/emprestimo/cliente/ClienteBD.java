package com.emprestimo.cliente;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.emprestimo.ed.ClienteED;
import com.emprestimo.service.NegocioException;
import com.emprestimo.util.jpa.Transactional;


public class ClienteBD implements Serializable {
	
  private static final long serialVersionUID = 1L;
  
  @Inject
	private EntityManager em;

	public void salvar(ClienteED cliente) {
		 em.merge(cliente);		
	}

	@SuppressWarnings("unchecked")
	public List<ClienteED> buscarTodos() {
		return em.createQuery("from ClienteED").getResultList();
	}
	@Transactional
  public void excluir(ClienteED cliente) throws NegocioException {
    ClienteED clienteTemp = em.find(ClienteED.class, cliente.getCodCliente());
    
    em.remove(clienteTemp);
    em.flush();
  }

  public ClienteED buscarPeloCodigo(Long codigo) {
    return em.find(ClienteED.class, codigo);
  }

}
