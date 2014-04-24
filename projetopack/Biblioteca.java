package br.ufpb.dce.poo.projetopack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Calendar;

import br.ufpb.dce.poo.ExceptionsProject.*;

public class Biblioteca {
	
	private Configuracao configuracao = Configuracao.getInstance();
	private List<Livro> livros;
	private List<Emprestimo> emprestimosAtivos;
	private List<Usuario> usuarios;
	private static Biblioteca singleton;
	
	private Biblioteca() {
		this.livros = new LinkedList<Livro>();
		this.emprestimosAtivos = new LinkedList<Emprestimo>();
		this.usuarios = new LinkedList<Usuario>();
	}
	
	public static Biblioteca getInstance(){
		if (singleton == null){
			singleton = new Biblioteca();
		}
		return singleton;
	}
	
	public void cadastrarUsuario (Usuario u) throws UsuarioJaExisteException{
		for (Usuario usuario: this.usuarios){
			if (usuario.getMatricula().equals(u.getMatricula())){
				throw new UsuarioJaExisteException ("Este usuário já existe.");
			}
		}
		this.usuarios.add(u);
	}
	
	public void cadastrarLivro(Livro livro){
		for (Livro lv: this.livros){
			if (lv.getCodigo().equals(livro.getCodigo())){
				lv.setQuantidade(lv.getQuantidade()+livro.getQuantidade());
			}
			
		}
		this.livros.add(livro);
	}
	
	public List<Usuario> getUsuarios (){
		return this.usuarios;
	}
	
	public List<Livro> getLivros(){
		return this.livros;
	}
	
	public List<Emprestimo> getEmprestimosAtivos(){
		return this.emprestimosAtivos;
	}
	public Usuario getUsuario(String mat) throws UsuarioInexistenteException{
		
		for (Usuario u: this.usuarios){
			if (u.getMatricula().equals(mat)){
				return u;
			}
		}
		throw new UsuarioInexistenteException ("Este usuário não existe.");
	}
	
	public Livro getLivro (String codLivro) throws LivroInexistenteException{
		for (Livro l: this.livros){
			System.out.println();
			if(l.getCodigo().equals(codLivro)){
				return l;
			}
		}
		throw new LivroInexistenteException ("Este livro nao esta cadastrado");
	}
	
	public List<Emprestimo> listarEmprestimosEmAtraso(){
		
		Calendar DiaDeHoje = Calendar.getInstance();
		List<Emprestimo> atrasos = new LinkedList<Emprestimo>();
		for (Emprestimo e: this.emprestimosAtivos){
			if (e.getDataDevolucao().before(DiaDeHoje)){
				atrasos.add(e);
			}
		}

		return atrasos;
		
	}
	
	public double calcularMulta(Usuario u){
		
		long diasAtraso = 0;
		for (Emprestimo e: this.listarEmprestimosEmAtraso()){
			if (e.getUsuario().getMatricula().equals(u.getMatricula())){
				diasAtraso += diasEntre(e.getDataDevolucao(), Calendar.getInstance());
			}
			
		}
		
		return diasAtraso * this.configuracao.getValorMulta();
		
	}
	
	public long diasEntre(Calendar dInicial, Calendar dFinal){ 
		
		int tempoDia = 1000 * 60 * 60 * 24;
		long diferenca = dFinal.getTimeInMillis() - dInicial.getTimeInMillis();
		return diferenca / tempoDia;
		
	}
	
	public void emprestarLivro (Usuario usuario, Livro livro) throws MaximoDeLivrosEmprestadosException, UsuarioEmAtrasoException, QuantidadeDeLivrosInsuficienteException{
		
		int quantidadeMaxEmprestimo = 3;
		if (usuario.getEmprestimos().size() == quantidadeMaxEmprestimo){
			throw new MaximoDeLivrosEmprestadosException ("Usuario atingiu limite de emprestimos");
		}
		for (Emprestimo e: this.listarEmprestimosEmAtraso()){
			if (e.getUsuario().getMatricula().equals(usuario.getMatricula())){
				throw new UsuarioEmAtrasoException ("Usuário está em débito com a biblioteca");
			}
		}
		for (Livro l: this.livros){
			if (l.getCodigo().equals(livro.getCodigo()) && l.getQuantidade() == 1){
				throw new QuantidadeDeLivrosInsuficienteException("Quantidade de livros insuficiente para emprestimo.");
			}
		}
		
		Calendar diaDevolucao = Calendar.getInstance();
		diaDevolucao.add(Calendar.DAY_OF_MONTH, usuario.getQuantDiasEmprestimo());
		Emprestimo novoEmprestimo = new Emprestimo (usuario, livro, Calendar.getInstance(), diaDevolucao);
		for (Livro lv: this.livros){
			if (lv.getCodigo().equals(livro.getCodigo())){
				lv.setQuantidade(lv.getQuantidade()-1);
			}
		}
		this.emprestimosAtivos.add(novoEmprestimo);
		usuario.adicionarEmprestimo(novoEmprestimo);		
			
	}
	
	public void devolverLivro (Usuario usuario, Livro livro)throws EmprestimoInexistenteException{
		boolean emprestou = false;
		for (Emprestimo emprestimoUsuario: usuario.getEmprestimos()){
			if (emprestimoUsuario.getLivro().getCodigo().equals(livro.getCodigo())){
				for (Emprestimo emprestimoBiblioteca: this.emprestimosAtivos){
					if (emprestimoBiblioteca.getLivro().getCodigo().equals(livro.getCodigo()) && emprestimoBiblioteca.getUsuario().getMatricula().equals(usuario.getMatricula())){
						this.emprestimosAtivos.remove(emprestimoBiblioteca);
						usuario.getEmprestimos().remove(emprestimoUsuario);
						emprestou = true;
						for (Livro lv : this.livros){
							if (lv.getCodigo().equals(livro.getCodigo())){
								lv.setQuantidade(lv.getQuantidade()+1);
							}
						}
					}
				}
			}
		}
		if (emprestou == false){
			throw new EmprestimoInexistenteException ("O usuario nao possui o emprestimo referente.");
		}
		
	}
	
	
	public static void main(String[] args) throws IOException, UsuarioJaExisteException, EmprestimoJaExisteException, UsuarioInexistenteException, LivroInexistenteException, EmprestimoInexistenteException, MaximoDeLivrosEmprestadosException, UsuarioEmAtrasoException, QuantidadeDeLivrosInsuficienteException {
	Biblioteca biblioteca = Biblioteca.getInstance();
	biblioteca.configuracao.setDiasEmprestimoAluno(40);
	biblioteca.configuracao.setDiasEmprestimoProf(20);
	biblioteca.configuracao.setValorMulta(0.5);
	
	biblioteca.cadastrarLivro(new Livro ("Java", "2345", "Martin", 10, "Programacao"));
	biblioteca.cadastrarUsuario(new Aluno ("Odravison", "12345", "12345678901", "Sistema de informação", "2013.1"));
	biblioteca.cadastrarUsuario(new Professor ("Ayla", "1234","12345678901","DCE" ));
	biblioteca.emprestarLivro(biblioteca.getUsuario("12345"), biblioteca.getLivro("2345"));
	biblioteca.emprestarLivro(biblioteca.getUsuario("1234"), biblioteca.getLivro("2345"));
	
	
	biblioteca.gravarEmprestimosEmArquivo("Emprestimos.txt");
	biblioteca.gravarLivrosEmArquivo("Livros.txt");
	biblioteca.gravarUsuariosEmAquivo("Alunos.txt", "Professores.txt");
	biblioteca.gravarConfiguracoesEmArquivo("Configuracao.txt");
	
//	biblioteca.carregarLivrosDeArquivo("Livros.txt");
//	biblioteca.carregarProfessoresDeArquivo("Professores.txt");
//	biblioteca.carregarAlunosDeArquivo("Alunos.txt");
//	biblioteca.carregarEmprestimosDeArquivo("Emprestimos.txt");
//	biblioteca.carregarConfiguracoesDeArquivo("Configuracao.txt");
	
	System.out.println(biblioteca.getLivro("2345").getNome());
	System.out.println(biblioteca.getLivro("2345").getAutor());
	System.out.println(biblioteca.getLivro("2345").getClassificacao());
	System.out.println("Quantidade do livro: " + biblioteca.getLivro("2345").getQuantidade());
	
	System.out.println(biblioteca.getUsuario("12345").getNome());
	System.out.println(biblioteca.getUsuario("12345").getCPF());
	System.out.println(biblioteca.getUsuario("12345").getMatricula());
	System.out.println(biblioteca.getUsuario("12345").getCurso());
	
	System.out.println(biblioteca.getUsuario("1234").getNome());
	System.out.println(biblioteca.getUsuario("1234").getDepartamento());
	System.out.println(biblioteca.getUsuario("1234").getCPF());
	System.out.println(biblioteca.getUsuario("1234").getMatricula());
	
	for (Usuario u1: biblioteca.usuarios){
		
		for(Emprestimo e: u1.getEmprestimos()){
			System.out.println(e.getDataEmprestimo().getTime());
			System.out.println(e.getDataDevolucao().getTime());
		}
	}
	
	System.out.println(biblioteca.getLivro("2345").getQuantidade());
	biblioteca.devolverLivro(biblioteca.getUsuario("12345"),biblioteca.getLivro("2345"));
	System.out.println(biblioteca.getLivro("2345").getQuantidade());
	biblioteca.devolverLivro(biblioteca.getUsuario("1234"),biblioteca.getLivro("2345"));
	System.out.println(biblioteca.getLivro("2345").getQuantidade());
	
}

}



