package br.com.metro.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.metro.dao.EstacaoDAO;
import br.com.metro.dao.TabletDAO;
import br.com.metro.domain.Estacao;
import br.com.metro.domain.Tablet;
import br.com.metro.util.FacesUtil;

@ManagedBean
@ViewScoped
public class TabletBean {
	private Tablet tabletCadastro;
	private List<Tablet> listaTablets;
	private List<Tablet> listaTabletsFiltrados;

	private String acao;
	private int codTablet;

	private List<Estacao> listaEstacoes;

	public Tablet getTabletCadastro() {
		return tabletCadastro;
	}

	public void setTabletCadastro(Tablet tabletCadastro) {
		this.tabletCadastro = tabletCadastro;
	}

	public List<Tablet> getListaTablets() {
		return listaTablets;
	}

	public void setListaTablets(List<Tablet> listaTablets) {
		this.listaTablets = listaTablets;
	}

	public List<Tablet> getListaTabletsFiltrados() {
		return listaTabletsFiltrados;
	}

	public void setListaTabletsFiltrados(List<Tablet> listaTabletsFiltrados) {
		this.listaTabletsFiltrados = listaTabletsFiltrados;
	}

	public int getCodTablet() {
		return codTablet;
	}

	public void setCodTablet(int codTablet) {
		this.codTablet = codTablet;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public List<Estacao> getListaEstacoes() {
		return listaEstacoes;
	}

	public void setListaEstacoes(List<Estacao> listaEstacoes) {
		this.listaEstacoes = listaEstacoes;
	}

	public void novo() {

		tabletCadastro = new Tablet();
	}

	public void salvar() {

		try {
			TabletDAO dao = new TabletDAO();
			dao.salvar(tabletCadastro);

			tabletCadastro = new Tablet();

			FacesUtil.addMsgInfo("Tablet salvo com sucesso.");
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesUtil.addMsgErro("Erro ao tentar incluir Tablet: " + ex.getMessage());
		}
	}

	public void carregar() {
		try {
			TabletDAO tabletDAO = new TabletDAO();
			listaTablets = tabletDAO.listar();
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao tentar listar Tablets: " + ex.getMessage());
		}

	}

	public void carregarCadastro() {
		try {
			if (codTablet != 0) {
				TabletDAO dao = new TabletDAO();
				tabletCadastro = dao.buscarPorCodigo(codTablet);
			} else {
				tabletCadastro = new Tablet();
			}

			EstacaoDAO estacaoDAO = new EstacaoDAO();
			listaEstacoes = estacaoDAO.listar();

		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao tentar obter os dados do Tablet: " + ex.getMessage());
		}
	}

	public void excluir() {

		try {
			TabletDAO dao = new TabletDAO();
			dao.excluir(tabletCadastro);

			FacesUtil.addMsgInfo("Tablet removido com sucesso.");
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesUtil.addMsgErro("Erro ao tentar excluir Tablet: " + ex.getMessage());
		}
	}

	public void editar() {

		try {
			TabletDAO dao = new TabletDAO();
			dao.editar(tabletCadastro);

			FacesUtil.addMsgInfo("Tablet editado com sucesso.");
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesUtil.addMsgErro("Erro ao tentar editar Tablet: " + ex.getMessage());
		}
	}

}
