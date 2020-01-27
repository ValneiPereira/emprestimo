package com.emprestimo.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.emprestimo.ed.EnderecoED;
import com.emprestimo.endereco.EnderecoBD;
import com.emprestimo.util.cdi.CDIServiceLocator;

/*
  Classe para corrigir erro de conversao quando for para editar
  chamar na mesma tela
*  
*/
@FacesConverter(forClass=EnderecoED.class)
public class EnderecoConverter implements Converter {

	private EnderecoBD enderecoDAO;
	
	public EnderecoConverter() {
		this.enderecoDAO = CDIServiceLocator.getBean(EnderecoBD.class);
	}

  //recuperar o objeto
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		EnderecoED retorno = null;

		if (value != null) {
			retorno = this.enderecoDAO.buscarPeloCodigo(new Long(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		if (value != null) {
			Long codigo = ((EnderecoED) value).getCodEndereco();
			String retorno = (codigo == null ? null : codigo.toString());
			
			return retorno;
		}
		
		return "";
	}

}