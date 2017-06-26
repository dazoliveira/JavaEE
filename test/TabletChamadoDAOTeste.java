package br.com.metro.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.metro.dao.ChamadoDAO;
import br.com.metro.dao.TabletDAO;
import br.com.metro.dao.TabletChamadoDAO;
import br.com.metro.domain.Chamado;
import br.com.metro.domain.Tablet;
import br.com.metro.domain.TabletChamado;
import br.com.metro.filter.ChamadoFilter;

public class TabletChamadoDAOTeste {

	@Test
	@Ignore
	public void salvar() {

		TabletDAO tabletDAO = new TabletDAO();
		Tablet tablet = tabletDAO.buscarPorCodigo(3);

		ChamadoDAO chamadoDAO = new ChamadoDAO();
		Chamado chamado = chamadoDAO.buscarPorCodigo(3);

		TabletChamado tabletChamado = new TabletChamado();
		tabletChamado.setChamado(chamado);
		tabletChamado.setTablet(tablet);
		tabletChamado.setHorarioChamada(new Date());
		tabletChamado.setQtdParcialChamado(10);

		TabletChamadoDAO dao = new TabletChamadoDAO();
		dao.salvar(tabletChamado);
	}

	@Test
	@Ignore
	public void listar() {
		TabletChamadoDAO dao = new TabletChamadoDAO();
		List<TabletChamado> tabletsEstacoes = dao.listar();

		System.out.println(tabletsEstacoes);
	}

	@Test
	@Ignore
	public void buscarPorCodigo() {
		TabletChamadoDAO dao = new TabletChamadoDAO();

		TabletChamado tabletChamado = dao.buscarPorCodigo(1);

		System.out.println(tabletChamado);
	}

	@Test
	@Ignore
	public void excluir() {
		TabletChamadoDAO dao = new TabletChamadoDAO();

		TabletChamado tabletChamado = dao.buscarPorCodigo(4);

		dao.excluir(tabletChamado);
	}

	@Test
	@Ignore
	public void editar() {
		TabletChamadoDAO dao = new TabletChamadoDAO();
		TabletChamado tabletChamado = dao.buscarPorCodigo(4);

		TabletDAO tabletDAO = new TabletDAO();
		Tablet tablet = tabletDAO.buscarPorCodigo(2);

		ChamadoDAO chamadoDAO = new ChamadoDAO();
		Chamado chamado = chamadoDAO.buscarPorCodigo(2);

		tabletChamado.setChamado(chamado);
		tabletChamado.setTablet(tablet);

		dao.editar(tabletChamado);
	}

	@Test
	@Ignore
	public void busscar() throws ParseException {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

		ChamadoFilter filtro = new ChamadoFilter();
		filtro.setDataInicial(formato.parse("24/04/2017"));
		filtro.setDataFinal(formato.parse("10/05/2017"));

		TabletChamadoDAO dao = new TabletChamadoDAO();
		List<TabletChamado> tabletChamados = dao.buscar(filtro);

		for (TabletChamado tabletChamado : tabletChamados) {
			System.out.println(tabletChamado);
		}
	}
}
