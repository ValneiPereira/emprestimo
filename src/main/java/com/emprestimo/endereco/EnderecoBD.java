package com.emprestimo.endereco;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import com.emprestimo.ed.EnderecoED;
import com.emprestimo.service.NegocioException;
import com.emprestimo.util.jpa.Transactional;


public class EnderecoBD implements Serializable {
	
  private static final long serialVersionUID = 1L;
  @Inject
	private EntityManager em;

	public void salvar(EnderecoED endereco) {
		 em.merge(endereco);		
	}

	@SuppressWarnings("unchecked")
	public List<EnderecoED> buscarTodos() {
		return em.createQuery("from EnderecoED").getResultList();
	}
	@Transactional
  public void excluir(EnderecoED endereco) throws NegocioException {
    EnderecoED enderecoTemp = em.find(EnderecoED.class, endereco.getCodEndereco());
    
    em.remove(enderecoTemp);
    em.flush();
  }

  public EnderecoED buscarPeloCodigo(Long codigo) {
    return em.find(EnderecoED.class, codigo);
  }

}
