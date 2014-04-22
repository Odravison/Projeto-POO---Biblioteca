package br.ufpb.dce.poo.projetopack;

import java.io.IOException;

import br.ufpb.dce.poo.ExceptionsProject.*;


public class BibliotecaFacade {
	
	private Biblioteca biblioteca;
	
	public BibliotecaFacade(){
		
		this.biblioteca = Biblioteca.getInstance();
		
	}
	
	public void cadastrarUsuario(String nome, String matricula, String cpf){  
	
		// refazer os construtores com o factory
	}
	
	public void cadastrarAluno(String nome, String matricula, String cpf, String curso, String periodoIngresso) throws UsuarioJaExisteException{
		
		this.biblioteca.cadastrarUsuario(new Aluno(nome, matricula, cpf, curso, periodoIngresso));
	}
	
	public void cadastrarProfessor(String nome, String matricula, String cpf, String departamento) throws UsuarioJaExisteException{
		
		this.biblioteca.cadastrarUsuario(new Professor(nome, matricula, cpf, departamento));
	}
	
	public void cadastrarLivro(String nome, String codigo, String autor, int quantidade, String classificacao){
		
		this.biblioteca.cadastrarLivro(new Livro(nome, codigo, autor, quantidade, classificacao));
		
	}
	
	public void emprestarLivro(String matricula, String codigoLivro) throws MaximoDeLivrosEmprestadosException, UsuarioEmAtrasoException, QuantidadeDeLivrosInsuficienteException, UsuarioInexistenteException, LivroInexistenteException{
		
		this.biblioteca.emprestarLivro(this.biblioteca.getUsuario(matricula), this.biblioteca.getLivro(codigoLivro));
		
	}
	
	public double calcularMulta(String matricula) throws UsuarioInexistenteException{
		
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