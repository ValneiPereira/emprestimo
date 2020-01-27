package com.emprestimo.ed;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "endereco")
public class EnderecoED implements Serializable{

  private static final long serialVersionUID = 1L;
  
  @Id
  @SequenceGenerator(name = "ENDERECO_SEQ", sequenceName = "endereco_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ENDERECO_SEQ")
  @Column(name = "cod_endereco")
  private Long   codEndereco;
  
  @Column(name = "nome_rua")
  private String nomeRua;
  
  @Column(name = "cep")
  private String cep;
  
  @Column(name = "numero_endereco")
  private String numero;
  
  @Column(name = "bairro")
  private String bairro;
  
  @Column(name = "cidade")
  private String cidade;
  
  @Column(name = "estado")
  private String estado;
  
  @ManyToOne
  @JoinColumn(name = "cod_cliente")
  private ClienteED        cliente;

  public Long getCodEndereco() {
    return codEndereco;
  }

  public void setCodEndereco(Long codEndereco) {
    this.codEndereco = codEndereco;
  }

  public String getNomeRua() {
    return nomeRua;
  }

  public void setNomeRua(String nomeRua) {
    this.nomeRua = nomeRua;
  }

  public String getCep() {
    return cep;
  }

  public void setCep(String cep) {
    this.cep = cep;
  }

  public String getNumero() {
    return numero;
  }

  public void setNumero(String numero) {
    this.numero = numero;
  }

  public String getBairro() {
    return bairro;
  }

  public void setBairro(String bairro) {
    this.bairro = bairro;
  }

  public String getCidade() {
    return cidade;
  }

  public void setCidade(String cidade) {
    this.cidade = cidade;
  }

  public String getEstado() {
    return estado;
  }

  public void setEstado(String estado) {
    this.estado = estado;
  }

  public ClienteED getCliente() {
    return cliente;
  }

  public void setCliente(ClienteED cliente) {
    this.cliente = cliente;
  }

  @Override
  public int hashCode() {
    final int prime  = 31;
    int       result = 1;
    result = prime * result + ((cep == null) ? 0 : cep.hashCode());
    result = prime * result + ((codEndereco == null) ? 0 : codEndereco.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    EnderecoED other = (EnderecoED) obj;
    if (cep == null) {
      if (other.cep != null)
        return false;
    } else if (!cep.equals(other.cep))
      return false;
    if (codEndereco == null) {
      if (other.codEndereco != null)
        return false;
    } else if (!codEndereco.equals(other.codEndereco))
      return false;
    return true;
  }
  
  
  
}