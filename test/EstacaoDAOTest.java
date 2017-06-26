package br.com.metro.test;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.metro.dao.EstacaoDAO;
import br.com.metro.domain.Estacao;


public class EstacaoDAOTest {
	@Test
	@Ignore
	public void salvar() {

		Estacao e1 = new Estacao();
		e1.setNome("Trianon-Masp");
		e1.setStatus(1);
		
		EstacaoDAO dao = new EstacaoDAO();
		dao.salvar(e1);
	}

	@Test
	@Ignore
	public void listar() {
		EstacaoDAO dao = new EstacaoDAO();
		List<Estacao> estacoes = dao.listar();
		
			System.out.println(estacoes);
	
	}

	@Test
	@Ignore
	public void buscarPorCodigo() {
		EstacaoDAO dao = new EstacaoDAO();
		Estacao e1 = dao.buscarPorCodigo(1);
		System.out.println(e1);
	}

	@Test
	@Ignore
	public void excluir() {
		EstacaoDAO dao = new EstacaoDAO();
		
		Estacao estacao = dao.buscarPorCodigo(4);
		
		dao.excluir(estacao);
	}

	@Test
	@Ignore
	public void editar() {
		EstacaoDAO dao =  new EstacaoDAO();
		
		Estacao estacao = dao.buscarPorCodigo(1);
		
		estacao.setNome("Vila Madalena");
		
		dao.editar(estacao);
	}

}