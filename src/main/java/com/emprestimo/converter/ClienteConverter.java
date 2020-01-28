package com.emprestimo.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.emprestimo.cliente.ClienteBD;
import com.emprestimo.ed.ClienteED;
import com.emprestimo.util.cdi.CDIServiceLocator;

/*
  Classe para corrigir erro de conversao quando for para editar
  chamar na mesma tela
*  
*/
@FacesConverter(forClass=ClienteED.class)
public class ClienteConverter implements Converter {

	private ClienteBD clienteDAO;
	
	public ClienteConverter() {
		this.clienteDAO = CDIServiceLocator.getBean(ClienteBD.class);
	}

  //recuperar o objeto
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		ClienteED retorno = null;

		if (value != null) {
			retorno = this.clienteDAO.buscarPeloCodigo(new Long(value));
		}

		return retorno;
	}

	@Override
  public String getAsString(FacesContext context, UIComponent component, Object value) {
    if (value != null) {
      Long codigo = ((ClienteED) value).getCodCliente();
      String retorno = (codigo == null ? null : codigo.toString());
      
      return retorno;
    }
    
    return "";
  }
}