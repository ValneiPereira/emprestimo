package com.emprestimo.web;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.emprestimo.modelo.Endereco;
import com.emprestimo.service.CadastroEnderecoService;
import com.emprestimo.service.NegocioException;
import com.emprestimo.util.jsf.FacesUtil;

@Named
@ViewScoped
public class CadastroEnderecoMB implements Serializable {

  private static final long serialVersionUID = 1L;

  @Inject
  private CadastroEnderecoService cadastroEnderecoService;

  private Endereco endereco;

  @PostConstruct
  public void init() {
    this.limpar();

  }

  /* Método para salvar e limpar apos salvar */
  public void salvar() {
    try {
      this.cadastroEnderecoService.salvar(endereco);
      FacesUtil.addSuccessMessage("Endereco salvo com sucesso!");

      this.limpar();
    } catch (NegocioException e) {
      FacesUtil.addErrorMessage(e.getMessage());
    }
  }

  public void limpar() {
    this.endereco = new Endereco();
  }

  public  Endereco getEndereco() {
    return endereco;
  }

  public void setEndereco(Endereco cliente) {
    this.endereco = cliente;
  }

}
