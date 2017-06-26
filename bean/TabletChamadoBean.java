package br.com.metro.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.metro.dao.TabletChamadoDAO;
import br.com.metro.domain.Chamado;
import br.com.metro.domain.Tablet;
import br.com.metro.domain.TabletChamado;
import br.com.metro.filter.ChamadoFilter;
import br.com.metro.util.FacesUtil;

@ManagedBean
@ViewScoped
public class TabletChamadoBean {

	private List<TabletChamado> listaTabletsChamados;
	private List<TabletChamado> listaTabletsChamaddosFiltrados;

	private Tablet tablet;
	private Chamado tabletChamadoCadastro;
	private List<Tablet> listaTablet;

	@ManagedProperty(value = "#{autenticacaoBean}")
	private AutenticacaoBean autenticacaoBean;

	private ChamadoFilter filtro;

	public List<TabletChamado> getListaTabletsChamados() {
		return listaTabletsChamados;
	}

	public void setListaTabletsChamados(List<TabletChamado> listaTabletsChamados) {
		this.listaTabletsChamados = listaTabletsChamados;
	}

	public List<TabletChamado> getListaTabletsChamaddosFiltrados() {
		return listaTabletsChamaddosFiltrados;
	}

	public void setListaTabletsChamaddosFiltrados(List<TabletChamado> listaTabletsChamaddosFiltrados) {
		this.listaTabletsChamaddosFiltrados = listaTabletsChamaddosFiltrados;
	}

	public Tablet getTablet() {
		return tablet;
	}

	public void setTablet(Tablet tablet) {
		this.tablet = tablet;
	}

	public Chamado getTabletChamadoCadastro() {
		return tabletChamadoCadastro;
	}

	public void setTabletChamadoCadastro(Chamado tabletChamadoCadastro) {
		this.tabletChamadoCadastro = tabletChamadoCadastro;
	}

	public List<Tablet> getListaTablet() {
		return listaTablet;
	}

	public void setListaTablet(List<Tablet> listaTablet) {
		this.listaTablet = listaTablet;
	}

	public AutenticacaoBean getAutenticacaoBean() {
		return autenticacaoBean;
	}

	public void setAutenticacaoBean(AutenticacaoBean autenticacaoBean) {
		this.autenticacaoBean = autenticacaoBean;
	}

	public ChamadoFilter getFiltro() {
		if (filtro == null) {
			filtro = new ChamadoFilter();
		}
		return filtro;
	}

	public void setFiltro(ChamadoFilter filtro) {
		this.filtro = filtro;
	}

	public void carregarTabletsChamados() {
		try {
			TabletChamadoDAO tabletChamadoDAO = new TabletChamadoDAO();
			listaTabletsChamados = tabletChamadoDAO.listar();
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao tentar listar Tablets Chamados: " + ex.getMessage());
		}
	}

	public void buscar() {
		try {
			TabletChamadoDAO tabletChamadoDAO = new TabletChamadoDAO();
			listaTabletsChamados = tabletChamadoDAO.buscar(filtro);

		} catch (RuntimeException ex) {
			// TODO: handle exception
			FacesUtil.addMsgErro("Erro ao tentar pesquisar tablets chamados: " + ex.getMessage());
		}
	}

}