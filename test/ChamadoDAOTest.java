package br.com.metro.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import br.com.metro.dao.ChamadoDAO;
import br.com.metro.dao.FuncionarioDAO;
import br.com.metro.domain.Chamado;
import br.com.metro.domain.Funcionario;
import br.com.metro.filter.ChamadoFilter;

public class ChamadoDAOTest {
	@Test
	@Ignore
	public void salvar() {

		Funcionario funcionario = new Funcionario();
		FuncionarioDAO daoFuncioanrio = new FuncionarioDAO();
		funcionario = daoFuncioanrio.buscarPorCodigo(3);

		Chamado chamado = new Chamado();
		chamado.setFuncionario(funcionario);
		chamado.setDataChamado(new Date());
		chamado.setQtdTotalChamado(7);

		ChamadoDAO dao = new ChamadoDAO();
		dao.salvar(chamado);
	}

	@Test
	@Ignore
	public void listar() {
		ChamadoDAO dao = new ChamadoDAO();
		List<Chamado> chamados = dao.listar();

		System.out.println(chamados);
	}

	@Test
	@Ignore
	public void buscarPorCodigo() {
		ChamadoDAO dao = new ChamadoDAO();

		Chamado chamado = dao.buscarPorCodigo(1);

		System.out.println(chamado);
	}

	@Test
	@Ignore
	public void excluir() {
		ChamadoDAO dao = new ChamadoDAO();

		Chamado chamado = dao.buscarPorCodigo(4);

		dao.excluir(chamado);
	}

	@Test
	@Ignore
	public void editar() {
		ChamadoDAO dao = new ChamadoDAO();
		Chamado chamado = dao.buscarPorCodigo(1);
		
		Funcionario funcionario = new Funcionario();
		FuncionarioDAO daoFunc = new FuncionarioDAO();
		funcionario = daoFunc.buscarPorCodigo(1);
		
		
		chamado.setFuncionario(funcionario);
		
		dao.editar(chamado);
	}
	
	@Test 
	@Ignore
	public void busscar() throws ParseException{
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		
		ChamadoFilter filtro = new ChamadoFilter();		
		filtro.setDataInicial(formato.parse("24/04/2017"));
		filtro.setDataFinal(formato.parse("04/05/2017"));
		
		ChamadoDAO dao= new ChamadoDAO();
		List<Chamado> chamados = dao.buscar(filtro);
		
		for (Chamado chamado: chamados){
			System.out.println(chamado);
		}
	}
}
