package com.emprestimo.modelo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "cliente")
public class Cliente implements Serializable {
  //Os objetos seram convertidos para uma sequencia de bytes
  private static final long serialVersionUID = 1L;

  @Id
  @SequenceGenerator(name = "CLIENTE_SEQ", sequenceName = "cliente_seq", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CLIENTE_SEQ")
  @Column(name = "cod_cliente")
  private Long              codCliente;

  @NotNull
  @Column(name = "nome_cliente")
  private String            nome;

  @Column(name = "sexo")
  private String            sexo;

  @NotNull
  @Column(name = "cpf_cnpj")
  private String            cpf;

  @Column(name = "tipo_cliente")
  private String            tipoCliente;

  @Column(name = "rendimento_mensal")
  private BigDecimal        rendimentoMensal;

  @Column(name = "riscos")
  private String            riscos;

  @Column(name = "total_patrimonio")
  private BigDecimal        totalPatrimonio;

  @Column(name = "atualmente_trabalhando")
  private boolean           trabalhando;

  @Column(name = "dividas_atuais")
  private BigDecimal        dividasAtuais;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "cliente")
  private List<Endereco>    enderecos        = new ArrayList<>();

  public Long getCodCliente() {
    return codCliente;
  }

  public void setCodCliente(Long codCliente) {
    this.codCliente = codCliente;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }

  public String getSexo() {
    return sexo;
  }

  public void setSexo(String sexo) {
    this.sexo = sexo;
  }

  public String getCpf() {
    return cpf;
  }

  public void setCpf(String cpf) {
    this.cpf = cpf;
  }

  public String getTipoCliente() {
    return tipoCliente;
  }

  public void setTipoCliente(String tipoCliente) {
    this.tipoCliente = tipoCliente;
  }

  public BigDecimal getRendimentoMensal() {
    return rendimentoMensal;
  }

  public void setRendimentoMensal(BigDecimal rendimentoMensal) {
    this.rendimentoMensal = rendimentoMensal;
  }

  public String getRiscos() {
    return riscos;
  }

  public void setRiscos(String riscos) {
    this.riscos = riscos;
  }

  public List<Endereco> getEnderecos() {
    return enderecos;
  }

  public void setEnderecos(List<Endereco> enderecos) {
    this.enderecos = enderecos;
  }

  public boolean isTrabalhando() {
    return trabalhando;
  }

  public void setTrabalhando(boolean trabalhando) {
    this.trabalhando = trabalhando;
  }

  public BigDecimal getTotalPatrimonio() {
    return totalPatrimonio;
  }

  public void setTotalPatrimonio(BigDecimal totalPatrimonio) {
    this.totalPatrimonio = totalPatrimonio;
  }

  public BigDecimal getDividasAtuais() {
    return dividasAtuais;
  }

  public void setDividasAtuais(BigDecimal dividasAtuais) {
    this.dividasAtuais = dividasAtuais;
  }

  /*Pra que 2 objetos possam ser comparados pelo seu conteudo e nao pelo 
  seu ponteiro de memoria. O objteo tem que ter implementacao equals e hasCode*/

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((codCliente == null) ? 0 : codCliente.hashCode());
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
    Cliente other = (Cliente) obj;
    if (codCliente == null) {
      if (other.codCliente != null)
        return false;
    } else if (!codCliente.equals(other.codCliente))
      return false;
    return true;
  }

}