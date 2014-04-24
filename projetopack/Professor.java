package br.ufpb.dce.poo.projetopack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import br.ufpb.dce.poo.ExceptionsProject.UsuarioJaExisteException;


public class Professor extends Usuario implements UsuarioPersistencia{
	private String departamento;
	

	public Professor(String nome, String matricula, String cpf, String departamento){
		super(nome, matricula, cpf);
		this.setDepartamento(departamento);
	}
	
	public void setDepartamento(String departamento){
		this.departamento = departamento;
	}
	
	public String getDepartamento(){
		return this.departamento;
	}

	public int getQuantDiasEmprestimo() {
		return Configuracao.getInstance().getDiasEmprestimoProfessor();
	}

	@Override
	public void gravarUsuariosEmArquivo(String nomeArquivo) throws IOException {
		BufferedWriter gravadorProfessor = null;
		try{
			gravadorProfessor = new BufferedWriter(new FileWriter(nomeArquivo));
			gravadorProfessor.write(super.getNome());
			gravadorProfessor.newLine();
			gravadorProfessor.write(super.getMatricula());
			gravadorProfessor.newLine();
			gravadorProfessor.write(super.getCPF());
			gravadorProfessor.newLine();
			gravadorProfessor.write(this.getDepartamento());
			gravadorProfessor.newLine();
			}
		finally{
			if(gravadorProfessor != null){
				gravadorProfessor.close();
			}
		}
		
	}

	@Override
	public void carregarUsuariosDeArquivo(String nomeArquivo) throws FileNotFoundException, IOException, UsuarioJaExisteException {
		Biblioteca bib = Biblioteca.getInstance();
		BufferedReader leitorProfessor = null; 
		try{ 
			leitorProfessor = new BufferedReader(new FileReader (nomeArquivo)); 
			String nomeProfessor = null; 
			
			do{ 
				nomeProfessor = leitorProfessor.readLine();
				if(nomeProfessor!= null){  
					String matriculaProfessor = leitorProfessor.readLine(); 
					String cpfProfessor = leitorProfessor.readLine(); 
					String departamento = leitorProfessor.readLine(); 
					Usuario u = new Professor(nomeProfessor, matriculaProfessor, cpfProfessor, departamento); 
					bib.cadastrarUsuario(u); 
				} 
			} while(nomeProfessor != null); 
			
		} finally{ 
			if(leitorProfessor != null){ 
				leitorProfessor.close(); 
			} 
		} 
		
	}

}
