package br.com.metro.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.metro.dao.FuncionarioDAO;
import br.com.metro.domain.Funcionario;
import br.com.metro.util.FacesUtil;

@ManagedBean
@SessionScoped
public class AutenticacaoBean {
	private Funcionario funcionarioLogado;

	public Funcionario getFuncionarioLogado() {
		if (funcionarioLogado == null) {
			funcionarioLogado = new Funcionario();
		}
		return funcionarioLogado;
	}

	public void setFuncionarioLogado(Funcionario funcionarioLogado) {
		this.funcionarioLogado = funcionarioLogado;
	}

	public String autenticar() {
		try {
			FuncionarioDAO dao = new FuncionarioDAO();
			// Criptografia
			// funcionarioLogado = dao.autenticar(funcionarioLogado.getEmail(),
			// DigestUtils.md2Hex(funcionarioLogado.getSenha()));

			funcionarioLogado = dao.autenticar(funcionarioLogado.getEmail(), funcionarioLogado.getSenha());
			if (funcionarioLogado == null) {
				FacesUtil.addMsgErro("Email e/ou senha inválido");
				return null;
			} else {
				FacesUtil.addMsgInfo("Funcionário logado!");
				return "/pages/principal.xhtml?faces-redirect=true";
			}

		} catch (RuntimeException ex) {
			FacesUtil.addMsgErro("Erro ao tentar auntenticar no sistema: " + ex.getMessage());
			//return null = permanece na pagina
			return null;
		}

	}

	public String sair() {
		funcionarioLogado = null;
		return "/pages/autenticacao.xhtml?faces-redirect=true";
	}
}
