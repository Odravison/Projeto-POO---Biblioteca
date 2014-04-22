package br.ufpb.dce.poo.projetopack;

import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.Test;

import br.ufpb.dce.poo.ExceptionsProject.UsuarioInexistenteException;

public class BibliotecaTestes extends TestCase {
	Biblioteca bib = Biblioteca.getInstance();

	public void testCadastrarUsuario() {
		Usuario u = new Aluno("Anderson","8121","12345","SI","2012.1");
		Usuario u1 = new Aluno("Anderson","8121","12345","SI","2012.1");
		try{
			bib.cadastrarUsuario(u);
			
		}catch(Exception e){
			assertEquals("O usuario ja existe", e.getMessage());
		}
		try{
			bib.cadastrarUsuario(u1);
			System.out.println("Não");
		}catch(Exception e){
			assertEquals("O usuario ja existe", e.getMessage());
		}
	}
//
//	@Test
//	public void testCadastrarLivro() {
//		fail("Not yet implemented");
//	}
//
	
	public void testGetUsuario() throws UsuarioInexistenteException {
		Usuario usu = new Aluno("Pedro", "8121", "876", "SI", "2012.1");
		Usuario usua = bib.getUsuario("8121");
		System.out.println(usu.getMatricula());
		assertEquals("8121", usua.getMatricula());
	}
//
//	@Test
//	public void testGetLivro() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testListarEmprestimosEmAtraso() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testCalcularMulta() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testDiasEntre() {
//		fail("Not yet implemented");
//		
//	}
//
//	@Test
//	public void testEmprestarLivro() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testDevolverLivro() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGravarUsuariosEmAquivo() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testCarregarAlunosDeArquivo() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testCarregarProfessoresDeArquivo() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGravarLivrosEmArquivo() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testCarregarLivrosDeArquivo() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGravarEmprestimosEmArquivo() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testCarregarEmprestimosDeArquivo() {
//		fail("Not yet implemented");
//	}

}
