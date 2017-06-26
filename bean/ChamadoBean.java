package br.com.metro.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.com.metro.dao.ChamadoDAO;
import br.com.metro.dao.FuncionarioDAO;
import br.com.metro.dao.TabletChamadoDAO;
import br.com.metro.dao.TabletDAO;
import br.com.metro.domain.Chamado;
import br.com.metro.domain.Funcionario;
import br.com.metro.domain.Tablet;
import br.com.metro.domain.TabletChamado;
import br.com.metro.filter.ChamadoFilter;
import br.com.metro.util.FacesUtil;

@ManagedBean
@ViewScoped
public class ChamadoBean {
	private List<Tablet> listaTablets;
	private List<Tablet> listaTabletsFiltrados;

	private TabletChamado tabletChamado;
	private Chamado chamadoCadastro;
	private List<TabletChamado> listaTabletChamado;

	@ManagedProperty(value = "#{autenticacaoBean}")
	private AutenticacaoBean autenticacaoBean;

	private ChamadoFilter filtro;
	private List<Chamado> listaChamados;
	
	public TabletChamado getTabletChamado() {
		return tabletChamado;
	}

	public void setTabletChamado(TabletChamado tabletChamado) {
		this.tabletChamado = tabletChamado;
	}

	public List<TabletChamado> getListaTabletChamado() {
		if (listaTabletChamado == null) {
			listaTabletChamado = new ArrayList<>();
		}
		return listaTabletChamado;
	}

	public Chamado getChamadoCadastro() {
		if (chamadoCadastro == null) {
			chamadoCadastro = new Chamado();
			chamadoCadastro.setQtdTotalChamado(0);
		}
		return chamadoCadastro;
	}

	public void setChamadoCadastro(Chamado chamadoCadastro) {
		this.chamadoCadastro = chamadoCadastro;
	}

	public void setListaTabletChamado(List<TabletChamado> listaTabletChamado) {
		this.listaTabletChamado = listaTabletChamado;
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
	
	public List<Chamado> getListaChamados() {
		return listaChamados;
	}
	
	public void setListaChamados(List<Chamado> listaChamados) {
		this.listaChamados = listaChamados;
	}

	public void carregarTablets() {
		try {
			TabletDAO tabletDAO = new TabletDAO();
			listaTablets = tabletDAO.listar();
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao tentar listar Tablets: " + ex.getMessage());
		}
	}

	public void adicionar(Tablet tabletSelecionado) {
		int posicaoEncontrada = -1;

		for (int pos = 0; pos < listaTabletChamado.size() && posicaoEncontrada < 0; pos++) {
			TabletChamado tabletChamadoTemp = listaTabletChamado.get(pos);
			if (tabletChamadoTemp.getTablet().equals(tabletSelecionado)) {
				posicaoEncontrada = pos;
			}
		}

		TabletChamado tabletChamado = new TabletChamado();
		tabletChamado.setTablet(tabletSelecionado);
		tabletChamado.setHorarioChamada(new Date());

		if (posicaoEncontrada < 0) {
			tabletChamado.setQtdParcialChamado(1);
			listaTabletChamado.add(tabletChamado);
		} else {
			TabletChamado tabletChamadoTemp = listaTabletChamado.get(posicaoEncontrada);

			tabletChamado.setQtdParcialChamado(tabletChamadoTemp.getQtdParcialChamado() + 1);
			// tabletChamado.setValor(talet.getPreco().multiply(tabletChamado.getQuantidade));
			listaTabletChamado.set(posicaoEncontrada, tabletChamado);
		}

		// chamadoCadastro.setValor(chamadoCadastro.getValor().add(produto.getPreco()));
		chamadoCadastro.setQtdTotalChamado(chamadoCadastro.getQtdTotalChamado() + tabletSelecionado.getStatus());
	}

	public void remover(TabletChamado tabletChamado) {

		int posicaoEncontrada = -1;

		for (int pos = 0; pos < listaTabletChamado.size() && posicaoEncontrada < 0; pos++) {
			TabletChamado tabletChamadoTemp = listaTabletChamado.get(pos);
			if (tabletChamadoTemp.getTablet().equals(tabletChamado.getTablet())) {
				posicaoEncontrada = pos;
			}
		}
		if (posicaoEncontrada > -1) {
			listaTabletChamado.remove(posicaoEncontrada);
			chamadoCadastro
					.setQtdTotalChamado(chamadoCadastro.getQtdTotalChamado() - tabletChamado.getQtdParcialChamado());
		}

	}

	public void carregarDadosChamado() {

		chamadoCadastro.setDataChamado(new Date());

		FuncionarioDAO dao = new FuncionarioDAO();
		Funcionario funcionario = dao.buscarPorCodigo(autenticacaoBean.getFuncionarioLogado().getCodFunc());
		chamadoCadastro.setFuncionario(funcionario);
	}

	public void salvar() {
		try {
			ChamadoDAO chamadoDAO = new ChamadoDAO();
			int codigoChamado = chamadoDAO.salvar(chamadoCadastro);
			Chamado chamadoFK = chamadoDAO.buscarPorCodigo(codigoChamado);

			for (TabletChamado tabletChamado : listaTabletChamado) {
				tabletChamado.setChamado(chamadoFK);

				TabletChamadoDAO tabletChamadoDAO = new TabletChamadoDAO();
				tabletChamadoDAO.salvar(tabletChamado);
			}

			chamadoCadastro = new Chamado();
			chamadoCadastro.setQtdTotalChamado(0);

			listaTabletChamado = new ArrayList<TabletChamado>();
			FacesUtil.addMsgInfo("Chamado salvo com sucesso");
		} catch (RuntimeException ex) {
			// TODO: handle exception
			FacesUtil.addMsgErro("Erro ao tentar salvar a venda: " + ex.getMessage());
		}
	}

	public void buscar() {
		try {
			ChamadoDAO chamadoDAO = new ChamadoDAO();
			listaChamados = chamadoDAO.buscar(filtro);
			
			
		} catch (RuntimeException ex) {
			// TODO: handle exception
			FacesUtil.addMsgErro("Erro ao tentar salvar a venda: " + ex.getMessage());
		}
	}

}
