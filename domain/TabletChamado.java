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
@Table(name = "tabletChamado")
@NamedQueries({
		@NamedQuery(name = "TabletChamado.listar", query = "SELECT tabletChamado FROM TabletChamado tabletChamado"),
		@NamedQuery(name = "TabletChamado.buscarPorCodigo", query = "SELECT tabletChamado FROM TabletChamado tabletChamado WHERE tabletChamado.codTabletChamado = :codTabletChamado") })

public class TabletChamado {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "codTabletChamado")
	private int codTabletChamado;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "codChamado", referencedColumnName = "codChamado", nullable = false)
	private Chamado chamado;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "codTablet", referencedColumnName = "codTablet", nullable = false)
	private Tablet tablet;

	@Column(name = "qtdParcialChamado", nullable = false)
	private int qtdParcialChamado;

	@Temporal(value = TemporalType.TIMESTAMP)
	@Column(name = "horarioChamada", nullable = false)
	private Date horarioChamada;

	public int getCodTabletChamado() {
		return codTabletChamado;
	}

	public void setCodTabletChamado(int codTabletChamado) {
		this.codTabletChamado = codTabletChamado;
	}

	public Chamado getChamado() {
		return chamado;
	}

	public void setChamado(Chamado chamado) {
		this.chamado = chamado;
	}

	public int getQtdParcialChamado() {
		return qtdParcialChamado;
	}

	public void setQtdParcialChamado(int qtdParcialChamado) {
		this.qtdParcialChamado = qtdParcialChamado;
	}

	public Date getHorarioChamada() {
		return horarioChamada;
	}

	public void setHorarioChamada(Date horarioChamada) {
		this.horarioChamada = horarioChamada;
	}

	public Tablet getTablet() {
		return tablet;
	}

	public void setTablet(Tablet tablet) {
		this.tablet = tablet;
	}

	@Override
	public String toString() {
		return "TabletChamado [codTabletChamado=" + codTabletChamado + ", chamado=" + chamado + ", tablet=" + tablet
				+ ", qtdParcialChamado=" + qtdParcialChamado + ", horarioChamada=" + horarioChamada + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + codTabletChamado;
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
		TabletChamado other = (TabletChamado) obj;
		if (codTabletChamado != other.codTabletChamado)
			return false;
		return true;
	}
	
	
}
