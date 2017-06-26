package br.com.metro.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import br.com.metro.domain.Chamado;
import br.com.metro.filter.ChamadoFilter;
import br.com.metro.util.HibernateUtil;

public class ChamadoDAO {

	public int salvar(Chamado chamado) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;
		int codigo = 0;
		try {
			transacao = sessao.beginTransaction();
			codigo = (Integer) sessao.save(chamado);
			transacao.commit();
		} catch (RuntimeException ex) {
			if (transacao != null) {
				transacao.rollback();
			}
			throw ex;
		} finally {
			sessao.close();
		}
		return codigo;
	}

	@SuppressWarnings("unchecked")
	public List<Chamado> listar() {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		List<Chamado> chamados = null;
		try {
			Query consulta = sessao.getNamedQuery("Chamado.listar");
			chamados = consulta.list();
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}
		return chamados;
	}

	public Chamado buscarPorCodigo(int codChamado) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Chamado chamado = null;
		try {
			Query consulta = sessao.getNamedQuery("Chamado.buscarPorCodigo");
			consulta.setInteger("codChamado", codChamado);

			chamado = (Chamado) consulta.uniqueResult();
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}
		return chamado;
	}

	public void excluir(Chamado chamado) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.delete(chamado);
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

	public void editar(Chamado chamado) {
		Session sessao = HibernateUtil.getSessionFactory().openSession();
		Transaction transacao = null;

		try {
			transacao = sessao.beginTransaction();
			sessao.update(chamado);
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
	public List<Chamado> buscar(ChamadoFilter filtro) {
		List<Chamado> chamados = null;

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT chamado FROM Chamado chamado ");

		if (filtro.getDataInicial() != null && filtro.getDataFinal() != null) {
			sql.append("WHERE chamado.dataChamado BETWEEN :dataInicial AND :dataFinal ");
		}

		sql.append("ORDER BY chamado.dataChamado ");

		Session sessao = HibernateUtil.getSessionFactory().openSession();
		try {
			Query consulta = sessao.createQuery(sql.toString());
			if (filtro.getDataInicial() != null && filtro.getDataFinal() != null) {
				consulta.setDate("dataInicial", filtro.getDataInicial());
				consulta.setDate("dataFinal", filtro.getDataFinal());
			}
			chamados = consulta.list();
		} catch (RuntimeException ex) {
			throw ex;
		} finally {
			sessao.close();
		}
		return chamados;
	}

}
