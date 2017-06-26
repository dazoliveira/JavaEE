package br.com.metro.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.metro.dao.EstacaoDAO;
import br.com.metro.domain.Estacao;
import br.com.metro.util.FacesUtil;

@ManagedBean
@ViewScoped
public class EstacaoBean {
	private Estacao estacaoCadastro;
	private List<Estacao> listaEstacoes;
	private List<Estacao> listaEstacoesFiltrados;

	private String acao;
	private int codEstcao;

	public Estacao getEstacaoCadastro() {
		if (estacaoCadastro == null) {
			estacaoCadastro = new Estacao();
		}
		return estacaoCadastro;
	}

	public void setEstacaoCadastro(Estacao estacaoCadastro) {
		this.estacaoCadastro = estacaoCadastro;
	}

	public List<Estacao> getListaEstacoes() {
		return listaEstacoes;
	}

	public void setListaEstacoes(List<Estacao> listaEstacoes) {
		this.listaEstacoes = listaEstacoes;
	}

	public List<Estacao> getListaEstacoesFiltrados() {
		return listaEstacoesFiltrados;
	}

	public void setListaEstacoesFiltrados(List<Estacao> listaEstacoesFiltrados) {
		this.listaEstacoesFiltrados = listaEstacoesFiltrados;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public int getCodEstcao() {
		return codEstcao;
	}

	public void setCodEstcao(int codEstcao) {
		this.codEstcao = codEstcao;
	}

	public void novo() {

		estacaoCadastro = new Estacao();
	}

	public void salvar() {

		try {
			EstacaoDAO dao = new EstacaoDAO();
			dao.salvar(estacaoCadastro);

			estacaoCadastro = new Estacao();

			FacesUtil.addMsgInfo("Estação salva com sucesso.");
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesUtil.addMsgErro("Erro ao tentar incluir a estação: " + ex.getMessage());
		}
	}

	public void carregar() {
		try {
			EstacaoDAO estacaoDAO = new EstacaoDAO();
			listaEstacoes = estacaoDAO.listar();
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao tentar listar as Estações: " + ex.getMessage());
		}

	}

	public void carregarCadastro() {
		try {
			if (codEstcao != 0) {
				EstacaoDAO dao = new EstacaoDAO();
				estacaoCadastro = dao.buscarPorCodigo(codEstcao);
			} else {
				estacaoCadastro = new Estacao();
			}

		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao tentar obter os dados da estação: " + ex.getMessage());
		}
	}

	public void excluir() {

		try {
			EstacaoDAO dao = new EstacaoDAO();
			dao.excluir(estacaoCadastro);

			FacesUtil.addMsgInfo("Estação removido com sucesso.");
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesUtil.addMsgErro("Erro ao tentar excluir a estação: " + ex.getMessage());
		}
	}

	public void editar() {

		try {
			EstacaoDAO dao = new EstacaoDAO();
			dao.editar(estacaoCadastro);

			FacesUtil.addMsgInfo("Estação editada com sucesso.");
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesUtil.addMsgErro("Erro ao tentar editar a estação: " + ex.getMessage());
		}
	}

}
