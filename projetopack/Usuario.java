package br.ufpb.dce.poo.projetopack;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import br.ufpb.dce.poo.ExceptionsProject.UsuarioJaExisteException;

public abstract class Usuario{
	private List<Emprestimo> emprestimos;
	private String nome;
	private String matricula;
	private String cpf;
	
	public Usuario(String nome, String matricula, String cpf){
		this.nome = nome;
		this.matricula = matricula;
		this.cpf = cpf;
		this.emprestimos = new LinkedList<Emprestimo>();
	}
	
	public void adicionarEmprestimo(Emprestimo e){
		this.emprestimos.add(e);
	}
	public void removerEmprestimo(Emprestimo emprestimo){
		for(Emprestimo e: this.emprestimos){
			if(e.equals(emprestimo)){
				this.emprestimos.remove(e);
				break;
			}
		}
	}
	
		
	public String getNome(){
		return this.nome;
	}
	
	public String getMatricula(){
		return this.matricula;
	}
	
	public String getCPF(){
		return this.cpf;
	}
	
	public List<Emprestimo> getEmprestimos(){
		return this.emprestimos;
	}
	
	// Professor e Aluno

	public abstract int getQuantDiasEmprestimo(); 
	
	public abstract void gravarUsuariosEmArquivo(String nomeArquivo) throws IOException;
	
	public abstract void carregarUsuariosDeArquivo(String nomeArquivo) throws FileNotFoundException, IOException, UsuarioJaExisteException;

}
