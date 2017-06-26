package br.com.metro.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import br.com.metro.dao.FuncionarioDAO;
import br.com.metro.domain.Funcionario;

public class FuncionarioDAOTeste {
	
	@Test
	@Ignore
	public void salvar() {
		Funcionario funcionario = new Funcionario();
		funcionario.setEmail("xxxs");
		funcionario.setFuncao("xxxs");
		funcionario.setNome("xxxxs");
		funcionario.setNumFunc(4446L);
		funcionario.setSenha("444446");
		funcionario.setStatus(0);
		
		FuncionarioDAO dao1 = new FuncionarioDAO();
		dao1.salvar(funcionario);
	}

	@Test
	@Ignore
	public void listar() {
		FuncionarioDAO dao = new FuncionarioDAO();
		List<Funcionario> funcionarios = dao.listar();
		
			System.out.println(funcionarios);
	}

	@Test
	@Ignore
	public void buscarPorCodigo() {
		FuncionarioDAO dao = new FuncionarioDAO();
		
		Funcionario funcionario = dao.buscarPorCodigo(3);
		
		System.out.println(funcionario);
	}

	@Test
	@Ignore
	public void excluir() {
		FuncionarioDAO dao = new FuncionarioDAO();
		
		Funcionario funcionario = dao.buscarPorCodigo(5);
				
		dao.excluir(funcionario);
	}

	@Test
	@Ignore
	public void editar() {
		FuncionarioDAO dao = new FuncionarioDAO();
		
		Funcionario funcionario = dao.buscarPorCodigo(1);
		
		funcionario.setNome("Douglas Zanquetta");
		
		dao.editar(funcionario);
	}
	
	@Test
	@Ignore
	public void autenticar(){
		FuncionarioDAO dao = new FuncionarioDAO();
		
		Funcionario funcionario = dao.autenticar("maria@apearc", "21112");
		
		Assert.assertNotNull(funcionario);
	}
}

