package br.com.metro.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


import br.com.metro.domain.Tablet;
import br.com.metro.util.HibernateUtil;

public class TabletDAO {
	public void salvar(Tablet tablet) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(tablet);
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
	public List<Tablet> listar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Tablet> tablets = null;
		try {
			Query consulta = sessao.getNamedQuery("Tablet.listar");
			tablets = consulta.list();
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}
		return tablets;
	}

	public Tablet buscarPorCodigo(int codTablet) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Tablet tablet = null;
		try {
			Query consulta = sessao.getNamedQuery("Tablet.buscarPorCodigo");
			consulta.setInteger("codTablet", codTablet);

			tablet = (Tablet) consulta.uniqueResult();
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}
		return tablet;
	}

	public void excluir(Tablet tablet) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.delete(tablet);
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

	public void editar(Tablet tablet) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.update(tablet);
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

}

