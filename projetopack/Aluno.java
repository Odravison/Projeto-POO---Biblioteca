package br.ufpb.dce.poo.projetopack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import br.ufpb.dce.poo.ExceptionsProject.UsuarioJaExisteException;

public class Aluno extends Usuario {
	
	private String periodoIngresso;
	private String curso;

	public Aluno(String nome, String matricula, String cpf, String curso, String periodoIngresso){
		super(nome, matricula, cpf);
		this.setPeriodoIngresso(periodoIngresso);
		this.setCurso(curso);
	}

	public int getQuantDiasEmprestimo() {
		return Configuracao.getInstance().getDiasEmprestimoAluno();
	}
	
	public void setPeriodoIngresso(String periodoIngresso){
		this.periodoIngresso = periodoIngresso;
	}
	
	public void setCurso (String curso){
		this.curso = curso;
	}
	
	public String getCurso (){
		return this.curso;
	}
	
	public String getPeriodoIngresso(){
		return this.periodoIngresso;
	}

	
	
	@Override
	public void gravarUsuariosEmArquivo(String nomeArquivo) throws IOException {
		BufferedWriter gravadorAluno = null;
		try{
			gravadorAluno = new BufferedWriter(new FileWriter(nomeArquivo));
			gravadorAluno.write(super.getNome());
			gravadorAluno.newLine();
			gravadorAluno.write(super.getMatricula());
			gravadorAluno.newLine();
			gravadorAluno.write(super.getCPF());
			gravadorAluno.newLine();
			gravadorAluno.write(this.getPeriodoIngresso());
			gravadorAluno.newLine();
			gravadorAluno.write(this.getCurso());
			gravadorAluno.newLine();
		}
		finally{
			if(gravadorAluno != null){
				gravadorAluno.close();
			}
		}
		
		
		
	}

	@Override
	public void carregarUsuariosDeArquivo(String nomeArquivo) throws FileNotFoundException, IOException, UsuarioJaExisteException {
		
		Biblioteca biblioteca = Biblioteca.getInstance();
		BufferedReader leitorAluno = null;
		
		try{
			leitorAluno = new BufferedReader(new FileReader(nomeArquivo));
			String nomeAluno = null;

			do{
				nomeAluno = leitorAluno.readLine();
				if(nomeAluno != null){
					String matricula = leitorAluno.readLine();
					String cpf = leitorAluno.readLine();
					String periodoIngresso = leitorAluno.readLine();
					String curso = leitorAluno.readLine();
					Usuario u = new Aluno(nomeAluno, matricula, cpf, curso, periodoIngresso);
					biblioteca.cadastrarUsuario(u);
				}
			}while(nomeAluno != null);
		}
		finally{
			if(leitorAluno != null){
				leitorAluno.close();
			}
		}
		
	}

}
