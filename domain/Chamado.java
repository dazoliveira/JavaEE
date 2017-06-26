package br.com.metro.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "chamado")
@NamedQueries({ @NamedQuery(name = "Chamado.listar", query = "SELECT chamado FROM Chamado chamado"),
		@NamedQuery(name = "Chamado.buscarPorCodigo", query = "SELECT chamado FROM Chamado chamado WHERE chamado.codChamado = :codChamado") })

public class Chamado {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "codChamado")
	private int codChamado;

	@Temporal(value = TemporalType.DATE)
	@Column(name = "dataChamado", nullable = false)
	private Date dataChamado;

	@Column(name = "qtdTotalChamado", nullable = false)
	private int qtdTotalChamado;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "codFunc", referencedColumnName = "codFunc", nullable = false)
	private Funcionario funcionario;
	
	//inserir parametros que não são persistidos video aula 131, ex: quantidade total de produtos
	// @Transient
	// private int
	
	public int getCodChamado() {
		return codChamado;
	}

	public void setCodChamado(int codChamado) {
		this.codChamado = codChamado;
	}

	public Date getDataChamado() {
		return dataChamado;
	}

	public void setDataChamado(Date dataChamado) {
		this.dataChamado = dataChamado;
	}

	public int getQtdTotalChamado() {
		return qtdTotalChamado;
	}

	public void setQtdTotalChamado(int qtdTotalChamado) {
		this.qtdTotalChamado = qtdTotalChamado;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	@Override
	public String toString() {
		return "Chamado [codChamado=" + codChamado + ", dataChamado=" + dataChamado + ", qtdTotalChamado="
				+ qtdTotalChamado + ", funcionario=" + funcionario + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codChamado;
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
		Chamado other = (Chamado) obj;
		if (codChamado != other.codChamado)
			return false;
		return true;
	}
	
	

}
