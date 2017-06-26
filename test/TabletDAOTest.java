package br.com.metro.test;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.metro.dao.EstacaoDAO;
import br.com.metro.dao.TabletDAO;
import br.com.metro.domain.Estacao;
import br.com.metro.domain.Tablet;


public class TabletDAOTest {
	@Test
	@Ignore
	public void salvar() {
		
		Estacao estacao = new Estacao();
		EstacaoDAO daoEstacao = new EstacaoDAO();
		estacao = daoEstacao.buscarPorCodigo(3);
		
		Tablet tablet = new Tablet();
		tablet.setEstacao(estacao);
		tablet.setNumTablet(404041L);
		tablet.setStatus(1);
		
		TabletDAO dao = new TabletDAO(); 
		dao.salvar(tablet);
	}

	@Test
	@Ignore
	public void listar() {
		TabletDAO dao = new TabletDAO();
		List<Tablet> tablets = dao.listar();
		
			System.out.println(tablets);
	}

	@Test
	@Ignore
	public void buscarPorCodigo() {
		TabletDAO dao = new TabletDAO();
		
		Tablet tablet = dao.buscarPorCodigo(3);
		
		System.out.println(tablet);
	}

	@Test
	@Ignore
	public void excluir() {
		TabletDAO dao = new TabletDAO();
		
		Tablet tablet = dao.buscarPorCodigo(4);
				
		dao.excluir(tablet);
	}

	@Test
	@Ignore
	public void editar() {
		TabletDAO dao = new TabletDAO();
		
		Tablet tablet = dao.buscarPorCodigo(4);
		
		tablet.setNumTablet(101010L);
		tablet.setStatus(0);
		
		dao.editar(tablet);
	}

}

