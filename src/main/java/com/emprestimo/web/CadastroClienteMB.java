package com.emprestimo.web;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.emprestimo.cliente.ClienteRN;
import com.emprestimo.ed.ClienteED;
import com.emprestimo.service.NegocioException;
import com.emprestimo.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroClienteMB implements Serializable {

  private static final long serialVersionUID = 1L;

  @Inject
  private ClienteRN cadastroClienteService;

  private ClienteED cliente;
  
  private boolean clienteEspecial;

  @PostConstruct
  public void init() {
    this.limpar();

  }

  /* Método para salvar e limpar apos salvar */
  public void salvar() {
    try {
      this.cadastroClienteService.verificarRiscos(cliente);
      this.cadastroClienteService.verificarTipoCliente(cliente);
      this.cadastroClienteService.salvar(cliente);
      FacesUtil.addSuccessMessage("Cliente salvo com sucesso!");

      this.limpar();
    } catch (NegocioException e) {
      FacesUtil.addErrorMessage(e.getMessage());
    }
  }

  public void limpar() {
    this.cliente = new ClienteED();
  }

  public ClienteED getCliente() {
    return cliente;
  }

  public void setCliente(ClienteED cliente) {
    this.cliente = cliente;
  }
  
  public boolean isClienteEspecial() {
    cliente.getTipoCliente().equals("E");
    return clienteEspecial;
  }

  public void setClienteEspecial(boolean clienteEspecial) {
    
    this.clienteEspecial = clienteEspecial;
  }
  
 
  

}
