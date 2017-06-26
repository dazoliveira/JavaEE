package br.com.metro.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.metro.domain.Estacao;
import br.com.metro.util.HibernateUtil;

public class EstacaoDAO {

	public void salvar(Estacao estacao) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.save(estacao);
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
	public List<Estacao> listar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Estacao> estacoes = null;
		try {
			Query consulta = sessao.getNamedQuery("Estacao.listar");
			estacoes = consulta.list();
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}
		return estacoes;
	}

	public Estacao buscarPorCodigo(int codEstacao) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Estacao estacao = null;
		try {
			Query consulta = sessao.getNamedQuery("Estacao.buscarPorCodigo");
			consulta.setInteger("codEstacao", codEstacao);

			estacao = (Estacao) consulta.uniqueResult();
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}
		return estacao;
	}

	public void excluir(Estacao estacao) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.delete(estacao);
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

	public void editar(Estacao estacao) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.update(estacao);
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
