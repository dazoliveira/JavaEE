package br.com.metro.domain;

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
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tablet")
@NamedQueries({ @NamedQuery(name = "Tablet.listar", query = "SELECT tablet FROM Tablet tablet"),
		@NamedQuery(name = "Tablet.buscarPorCodigo", query = "SELECT tablet FROM Tablet tablet WHERE tablet.codTablet = :codTablet") })

public class Tablet {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "codTablet")
	private int codTablet;

	@NotNull(message = "O campo Nº de Registro é obrigatório")
	@Digits(fraction=0, message="informe um valor válido para o campo registro, 4 digitos, Exe.:1010", integer = 4)
	@Column(name = "numTablet", nullable = false)
	private Long numTablet;

	@Column(name = "status", nullable = true)
	private int status;

	@NotNull(message = "O campo estação é obrigatório")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "codEstacao", referencedColumnName = "codEstacao", nullable = false)
	private Estacao estacao;

	public int getCodTablet() {
		return codTablet;
	}

	public void setCodTablet(int codigo) {
		this.codTablet = codigo;
	}

	public Long getNumTablet() {
		return numTablet;
	}

	public void setNumTablet(Long numTablet) {
		this.numTablet = numTablet;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Estacao getEstacao() {
		return estacao;
	}

	public void setEstacao(Estacao estacao) {
		this.estacao = estacao;
	}

	@Override
	public String toString() {
		return "Tablet [codTablet=" + codTablet + ", numTablet=" + numTablet + ", status=" + status + ", estacao="
				+ estacao + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codTablet;
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
		Tablet other = (Tablet) obj;
		if (codTablet != other.codTablet)
			return false;
		return true;
	}

}
