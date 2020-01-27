package com.emprestimo.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.emprestimo.cliente.ClienteRN;
import com.emprestimo.ed.ClienteED;
import com.emprestimo.ed.EnderecoED;
import com.emprestimo.service.NegocioException;
import com.emprestimo.util.jsf.FacesUtil;


@Named
@ViewScoped
public class CadastroClienteMB implements Serializable {

  private static final long serialVersionUID = 1L;

  @Inject
  private ClienteRN cadastroClienteService;
  
  private EnderecoED endereco = new EnderecoED();

  private ClienteED cliente;
  
  private boolean clienteEspecial;
  
  private List<EnderecoED> enderecosSelecionado = new ArrayList<>();
  
  
  public EnderecoED getEndereco() {
    return endereco;
  }

  public void setEndereco(EnderecoED endereco) {
    this.endereco = endereco;
  }
  
  public ClienteED getCliente() {
    return cliente;
  }

  public void setCliente(ClienteED cliente) {
    this.cliente = cliente;
  }
  
  public List<EnderecoED> getEnderecosSelecionado() {
    return enderecosSelecionado;
  }

  public void setEnderecosSelecionado(List<EnderecoED> enderecosSelecionado) {
    this.enderecosSelecionado = enderecosSelecionado;
  }

  
  @PostConstruct
  public void init() {
    this.limpar();
    if (!FacesContext.getCurrentInstance().isPostback()) {
      
      if (cliente.getListaEnderecos() == null) {
        cliente.setListaEnderecos(new LinkedHashSet<EnderecoED>());
      }
      if (endereco == null) {
        endereco = new EnderecoED();
      }
      
    }
    

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

  
  
  public boolean isClienteEspecial() {
    cliente.getTipoCliente().equals("E");
    return clienteEspecial;
  }

  public void setClienteEspecial(boolean clienteEspecial) {
    
    this.clienteEspecial = clienteEspecial;
  }
  
    public void incluirEnderecos() {
      cliente.getListaEnderecos().add(endereco);
      endereco = new EnderecoED(); 
    }
  
  
  public void removerEnderecos() {
    if (enderecosSelecionado == null && enderecosSelecionado.isEmpty()) {
      throw new RuntimeException("projeto.preliminar.nenhum.item");
    }
    for (EnderecoED enderecoED : enderecosSelecionado) {
      if (enderecoED.getCodEndereco() != null) {
        cliente.getListaEnderecosExclusao().add(enderecoED);
      }
      cliente.getListaEnderecos().remove(enderecoED);
    }
  }

  

  
  
 
  

}
