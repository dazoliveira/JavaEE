package br.com.metro.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.metro.domain.TabletChamado;
import br.com.metro.filter.ChamadoFilter;
import br.com.metro.util.HibernateUtil;

public class TabletChamadoDAO {
	public void salvar(TabletChamado tabletChamado) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(tabletChamado);
			transacao.commit();
		} catch (RuntimeException ex) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw ex;
		} finally {
			sessao.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<TabletChamado> listar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<TabletChamado> tabletChamado = null;
		try {
			Query consulta = sessao.getNamedQuery("TabletChamado.listar");
			tabletChamado = consulta.list();
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}
		return tabletChamado;
	}

	public TabletChamado buscarPorCodigo(int codTabletChamado) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		TabletChamado tabletChamado = null;
		try {
			Query consulta = sessao.getNamedQuery("TabletChamado.buscarPorCodigo");
			consulta.setInteger("codTabletChamado", codTabletChamado);

			tabletChamado = (TabletChamado) consulta.uniqueResult();
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}
		return tabletChamado;
	}

	public void excluir(TabletChamado tabletChamado) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.delete(tabletChamado);
			transacao.commit();
		} catch (RuntimeException ex) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw ex;
		} finally {
			sessao.close();
		}
	}

	public void editar(TabletChamado tabletChamado) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.update(tabletChamado);
			transacao.commit();
		} catch (RuntimeException ex) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw ex;
		} finally {
			sessao.close();
		}
	}

	@SuppressWarnings("unchecked")
	public List<TabletChamado> buscar(ChamadoFilter filtro) {
		List<TabletChamado> tabletChamados = null;

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT tabletChamado FROM TabletChamado tabletChamado ");

		if (filtro.getDataInicial() != null && filtro.getDataFinal() != null) {
			sql.append("WHERE tabletChamado.chamado.dataChamado BETWEEN :dataInicial AND :dataFinal ");
		}

		sql.append("ORDER BY tabletChamado.chamado.dataChamado ");

		Session sessao = HibernateUtil.getSessionFactory().openSession();
		try {
			Query consulta = sessao.createQuery(sql.toString());
			if (filtro.getDataInicial() != null && filtro.getDataFinal() != null) {
				consulta.setDate("dataInicial", filtro.getDataInicial());
				consulta.setDate("dataFinal", filtro.getDataFinal());
			}
			tabletChamados = consulta.list();
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}
		return tabletChamados;
	}
}
