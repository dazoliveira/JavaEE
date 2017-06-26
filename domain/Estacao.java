package br.com.metro.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "estacao")
@NamedQueries({ 
		@NamedQuery(name = "Estacao.listar", query = "SELECT estacao FROM Estacao estacao"),
		@NamedQuery(name = "Estacao.buscarPorCodigo", query = "SELECT estacao FROM Estacao estacao WHERE estacao.codEstacao = :codEstacao") 
})
public class Estacao {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "codEstacao")
	private int codEstacao;
	
	@NotEmpty(message = "O campo nome é obrigatório")
	@Size(min = 2, max = 50, message="Tamnho inválido para o campo nome (2 - 50)")
	@Column(name = "nome", length = 50, nullable = false)
	private String nome;

	@Column(name = "status")
	private int status;

	
	public int getCodEstacao() {
		return codEstacao;
	}

	public void setCodEstacao(int codEstacao) {
		this.codEstacao = codEstacao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Estacao [codEstacao=" + codEstacao + ", nome=" + nome + ", status=" + status + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codEstacao;
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
		Estacao other = (Estacao) obj;
		if (codEstacao != other.codEstacao)
			return false;
		return true;
	}
	
	

}
