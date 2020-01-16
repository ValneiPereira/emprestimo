package com.emprestimo.util.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.emprestimo.modelo.Cliente;

public class ConsultaCriteria {

  public static void main(String[] args) {

    EntityManager manager = JPAUtil.createEntityManager();
    
    //selec  from  where like... -> from(), where()..
    //JPQL from  Cliente
    //JPQL SELECT C FROM CLIENTE WHERE C.NOME LIKE= 'valnei%'
    CriteriaBuilder        builder       = manager.getCriteriaBuilder();
    CriteriaQuery<Cliente> criteriaQuery = builder.createQuery(Cliente.class);
    
    //SELECT C FROM CLIENTE
    Root<Cliente>          c             = criteriaQuery.from(Cliente.class);
    criteriaQuery.select(c);
    criteriaQuery.where(builder.like(c.<String>get("nome"), "valnei%"));
    
    List<Cliente> clientes = manager.createQuery(criteriaQuery).getResultList();
    
    for (Cliente cliente : clientes) {
      System.out.println("Codigo :" + cliente.getCodCliente());
      System.out.println("Nome: " + cliente.getNome());
    }
    manager.close();
  }

}
