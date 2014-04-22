package br.ufpb.dce.poo.projetopack;

import java.io.IOException;

import br.ufpb.dce.poo.ExceptionsProject.*;


public class TestarUsuarioFacade {
	
	public static void main(String [] args){
		BibliotecaFacade biblioteca = new BibliotecaFacade();
		
		try {
			//biblioteca.carregarSistema();
			//Biblioteca b = biblioteca.getBiblioteca();
			//System.out.println(b.getUsuario("3838").getMatricula());
			//System.out.println(b.getLivro("456").getNome());
			
			biblioteca.cadastrarAluno("Luender","3838", "89843", "SI", "2012.1");
			biblioteca.cadastrarProfessor("Rodrigo", "333", "37236", "CCAE");
			biblioteca.cadastrarLivro("POO", "456", "Luender Lima", 50, "Programação");
			biblioteca.emprestarLivro("3838", "456");
		
			biblioteca.gravarSistema();
			System.out.println("chegou");
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}

	}

}
		
		return this.biblioteca.calcularMulta(this.biblioteca.getUsuario(matricula));
		
	}
	
	public void DevolverLivro(String matricula, String codigoLivro) throws EmprestimoInexistenteException, UsuarioInexistenteException, LivroInexistenteException{
		
		this.biblioteca.devolverLivro(this.biblioteca.getUsuario(matricula), this.biblioteca.getLivro(codigoLivro));
		
	}
	
	public void gravarSistema() throws IOException{
		
		this.biblioteca.gravarUsuariosEmAquivo("alunos.txt", "professores.txt");
		this.biblioteca.gravarLivrosEmArquivo("livros.txt");
		this.biblioteca.gravarEmprestimosEmArquivo("emprestimos.txt");
		
	}
	
	public void carregarSistema() throws UsuarioJaExisteException, IOException, EmprestimoJaExisteException, UsuarioInexistenteException, LivroInexistenteException{
		
		this.biblioteca.carregarAlunosDeArquivo("alunos.txt");
		this.biblioteca.carregarProfessoresDeArquivo("professores.txt");
		this.biblioteca.carregarLivrosDeArquivo("livros.txt");
		this.biblioteca.carregarEmprestimosDeArquivo("emprestimos.txt");
		
	}
	
	
	public Biblioteca getBiblioteca(){
		return this.biblioteca;
	}

}