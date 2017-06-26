package br.com.metro.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.metro.dao.EstacaoDAO;
import br.com.metro.domain.Estacao;

@FacesConverter("tabletConverter")
public class TabletConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext facesContext, UIComponent component, String valor) {

		try {
			int codigo = Integer.parseInt(valor);
			EstacaoDAO estacaoDAO = new EstacaoDAO();
			Estacao estacao = estacaoDAO.buscarPorCodigo(codigo);
			return estacao;
		} catch (RuntimeException ex) {
			return null;
		}
	}

	@Override
	public String getAsString(FacesContext facesContext, UIComponent component, Object objeto) {
		try {
			Estacao estacao = (Estacao) objeto;
			String codigo = Integer.toString(estacao.getCodEstacao());
			return codigo;
		} catch (RuntimeException ex) {
			// TODO: handle exception
		}
		return null;
	}

}
