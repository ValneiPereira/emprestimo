package com.emprestimo.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.emprestimo.dao.ClienteDAO;
import com.emprestimo.modelo.Cliente;
import com.emprestimo.util.cdi.CDIServiceLocator;

/*
  Classe para corrigir erro de conversao quando for para editar
  chamar na mesma tela
*  
*/
@FacesConverter(forClass=Cliente.class)
public class ClienteConverter implements Converter {

	private ClienteDAO clienteDAO;
	
	public ClienteConverter() {
		this.clienteDAO = CDIServiceLocator.getBean(ClienteDAO.class);
	}

  //recuperar o objeto
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Cliente retorno = null;

		if (value != null) {
			retorno = this.clienteDAO.buscarPeloCodigo(new Long(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long codigo = ((Cliente) value).getCodCliente();
			String retorno = (codigo == null ? null : codigo.toString());
			
			return retorno;
		}
		
		return "";
	}

}