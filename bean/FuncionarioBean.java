package br.com.metro.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;



import br.com.metro.dao.FuncionarioDAO;
import br.com.metro.domain.Funcionario;
import br.com.metro.util.FacesUtil;

@ManagedBean
@ViewScoped
public class FuncionarioBean {
	private Funcionario funcionarioCadastro;

	private List<Funcionario> listaFuncionarios;
	private List<Funcionario> listaFuncionariosFiltrados;

	private String acao;
	private int codFunc;

	public Funcionario getFuncionarioCadastro() {

		if (funcionarioCadastro == null) {
			funcionarioCadastro = new Funcionario();
		}
		return funcionarioCadastro;
	}

	public void setFuncionarioCadastro(Funcionario funcionarioCadastro) {
		this.funcionarioCadastro = funcionarioCadastro;
	}

	public List<Funcionario> getListaFuncionarios() {
		return listaFuncionarios;
	}

	public void setListaFuncionarios(List<Funcionario> listaFuncionarios) {
		this.listaFuncionarios = listaFuncionarios;
	}

	public List<Funcionario> getListaFuncionariosFiltrados() {
		return listaFuncionariosFiltrados;
	}

	public void setListaFuncionariosFiltrados(List<Funcionario> listaFuncionariosFiltrados) {
		this.listaFuncionariosFiltrados = listaFuncionariosFiltrados;
	}

	public int getCodFunc() {
		return codFunc;
	}

	public void setCodFunc(int codFunc) {
		this.codFunc = codFunc;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}

	public void novo() {

		funcionarioCadastro = new Funcionario();
	}

	public void salvar() {

		try {
			FuncionarioDAO dao = new FuncionarioDAO();
			//ciptografia deve ter o string com 32
			//funcionarioCadastro.setSenha(DigestUtils.md5Hex(funcionarioCadastro.getSenha()));
			dao.salvar(funcionarioCadastro);
			
			funcionarioCadastro = new Funcionario();

			FacesUtil.addMsgInfo("Funcionário salvo com sucesso.");
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesUtil.addMsgErro("Erro ao tentar incluir o funcionário: " + ex.getMessage());
		}
	}

	public void carregar() {
		try {
			FuncionarioDAO funcionarioDAO = new FuncionarioDAO();
			listaFuncionarios = funcionarioDAO.listar();
		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao tentar listar os Funcionários: " + ex.getMessage());
		}

	}

	public void carregarCadastro() {
		try {
			if (codFunc != 0) {
				FuncionarioDAO dao = new FuncionarioDAO();
				funcionarioCadastro = dao.buscarPorCodigo(codFunc);
			} else {
				funcionarioCadastro = new Funcionario();
			}

		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao tentar obter os dados do Funcionário: " + ex.getMessage());
		}
	}

	public void excluir() {

		try {
			FuncionarioDAO dao = new FuncionarioDAO();
			dao.excluir(funcionarioCadastro);

			FacesUtil.addMsgInfo("Funcionário removido com sucesso.");
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesUtil.addMsgErro("Erro ao tentar excluir o funcionário: " + ex.getMessage());
		}
	}

	public void editar() {

		try {
			FuncionarioDAO dao = new FuncionarioDAO();
			//Criptografia
			//funcionarioCadastro.setSenha(DigestUtils.md5Hex(funcionarioCadastro.getSenha()));
			dao.editar(funcionarioCadastro);

			FacesUtil.addMsgInfo("Funcionario editado com sucesso.");
		} catch (RuntimeException ex) {
			ex.printStackTrace();
			FacesUtil.addMsgErro("Erro ao tentar editar o funcionário: " + ex.getMessage());
		}
	}

}
