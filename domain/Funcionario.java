package br.com.metro.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "funcionario")
@NamedQueries({ @NamedQuery(name = "Funcionario.listar", query = "SELECT funcionario FROM Funcionario funcionario"),
		@NamedQuery(name = "Funcionario.buscarPorCodigo", query = "SELECT funcionario FROM Funcionario funcionario WHERE funcionario.codFunc = :codFunc"),
		@NamedQuery(name="Funcionario.autenticar", query="SELECT funcionario FROM Funcionario funcionario WHERE funcionario.email = :email AND funcionario.senha = :senha")
})

public class Funcionario {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "codFunc")
	private int codFunc;
	
	@NotNull(message="O campo Nº de Registro é obrigatório")
	@Column(name = "numFunc", nullable = false)
	private Long numFunc;

	@NotEmpty(message="O campo nome é obrigatório")
	@Column(name = "nome", length = 50, nullable = false)
	private String nome;

	@NotEmpty(message="O campo email é obrigatório")
	@Column(name = "email", length = 100, nullable = false)
	private String email;

	@NotEmpty(message="O campo senha é obrigatório")
	@Column(name = "senha", length = 20, nullable = false, unique = true)
	private String senha;

	@NotNull(message="O campo status é obrigatório")
	@Column(name = "status", nullable = false)
	private int status;

	@NotEmpty(message="O campo função é obrigatório")
	@Column(name = "funcao", length = 50, nullable = false)
	private String funcao;

	public int getCodFunc() {
		return codFunc;
	}

	public void setCodFunc(int codFunc) {
		this.codFunc = codFunc;
	}

	public Long getNumFunc() {
		return numFunc;
	}

	public void setNumFunc(Long numFunc) {
		this.numFunc = numFunc;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getFuncao() {
		return funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	@Override
	public String toString() {
		return "Funcionario [codFunc=" + codFunc + ", numFunc=" + numFunc + ", nome=" + nome + ", email=" + email
				+ ", senha=" + senha + ", status=" + status + ", funcao=" + funcao + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codFunc;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Funcionario other = (Funcionario) obj;
		if (codFunc != other.codFunc)
			return false;
		return true;
	}
	
	

}
