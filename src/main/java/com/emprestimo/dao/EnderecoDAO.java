package com.emprestimo.dao;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.emprestimo.modelo.Endereco;
import com.emprestimo.service.NegocioException;
import com.emprestimo.util.jpa.Transactional;


public class EnderecoDAO implements Serializable {
	
  private static final long serialVersionUID = 1L;
  @Inject
	private EntityManager em;

	public void salvar(Endereco endereco) {
		 em.merge(endereco);		
	}

	@SuppressWarnings("unchecked")
	public List<Endereco> buscarTodos() {
		return em.createQuery("from Endereco").getResultList();
	}
	@Transactional
  public void excluir(Endereco endereco) throws NegocioException {
    Endereco enderecoTemp = em.find(Endereco.class, endereco.getCodEndereco());
    
    em.remove(enderecoTemp);
    em.flush();
  }

  public Endereco buscarPeloCodigo(Long codigo) {
    return em.find(Endereco.class, codigo);
  }

}
