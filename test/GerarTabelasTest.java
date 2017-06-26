package br.com.metro.test;

import org.junit.Ignore;
import org.junit.Test;

import br.com.metro.util.HibernateUtil;

public class GerarTabelasTest {
		@Test
		@Ignore
		public void gerar(){
			HibernateUtil.getSessionFactory();
			HibernateUtil.getSessionFactory().close();			
		}

}
