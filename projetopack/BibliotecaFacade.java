package br.ufpb.dce.poo.projetopack;

import java.io.IOException;

import br.ufpb.dce.poo.ExceptionsProject.*;


public class BibliotecaFacade {
	
	private Biblioteca biblioteca = Biblioteca.getInstance();
	
	public BibliotecaFacade(){
		
	}
	
	public void cadastrarUsuario(Usuario usuario) throws UsuarioJaExisteException{
		
		this.biblioteca.cadastrarUsuario(usuario);
	}
	
	public void cadastrarLivro(Livro livro){
		
		this.biblioteca.cadastrarLivro(livro);
		
	}
	
	public void emprestarLivro(Usuario usuario, Livro livro) throws MaximoDeLivrosEmprestadosException, UsuarioEmAtrasoException, QuantidadeDeLivrosInsuficienteException, UsuarioInexistenteException, LivroInexistenteException{
		this.biblioteca.emprestarLivro(usuario, livro);
		
	}
	
	public double calcularMulta(Usuario usuario) throws UsuarioInexistenteException{
		
		return this.biblioteca.calcularMulta(usuario);
		
	}
	
	public void devolverLivro(Usuario usuario, Livro livro) throws EmprestimoInexistenteException, UsuarioInexistenteException, LivroInexistenteException{
		
		this.biblioteca.devolverLivro(usuario, livro);
		
	}
	
	public void gravarSistema() throws IOException{
		
		biblioteca.gravarSistema();
		
	}
	
	public void carregarSistema() throws UsuarioJaExisteException, IOException, EmprestimoJaExisteException, UsuarioInexistenteException, LivroInexistenteException{
		
		biblioteca.carregarSistema();
		
	}
}
