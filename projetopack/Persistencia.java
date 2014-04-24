package br.ufpb.dce.poo.projetopack;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

import br.ufpb.dce.poo.ExceptionsProject.EmprestimoJaExisteException;
import br.ufpb.dce.poo.ExceptionsProject.LivroInexistenteException;
import br.ufpb.dce.poo.ExceptionsProject.UsuarioInexistenteException;
import br.ufpb.dce.poo.ExceptionsProject.UsuarioJaExisteException;

public class Persistencia {
	
	private static Persistencia singleton;
	
	private Persistencia() {
		
	}
	
	public static Persistencia getInstance(){
		if (singleton == null){
			Persistencia.singleton = new Persistencia();
		}
		
		return Persistencia.singleton;
	}
	
	
	
	Biblioteca biblioteca = Biblioteca.getInstance();

	public void gravarLivrosEmArquivo(String nomeArquivo) throws IOException{
		BufferedWriter gravadorLivro = null;
		
		try{
			gravadorLivro = new BufferedWriter(new FileWriter(nomeArquivo));		
			for(Livro l: biblioteca.getLivros()){
				gravadorLivro.write(l.getNome());
				gravadorLivro.newLine();
				gravadorLivro.write(l.getCodigo());
				gravadorLivro.newLine();
				gravadorLivro.write(l.getAutor());
				gravadorLivro.newLine();
				gravadorLivro.write(l.getClassificacao());
				gravadorLivro.newLine();
				gravadorLivro.write(Integer.toString(l.getQuantidade()));
				gravadorLivro.newLine();
			}		
		}
		finally{
			if(gravadorLivro != null){
				gravadorLivro.close();
			}
		}
	}
	
	public void carregarLivrosDeArquivo(String arquivoLivro)throws IOException {

		BufferedReader leitor = null; 
		try { 
			leitor = new BufferedReader(new FileReader(arquivoLivro)); 
			String nomeDoLivro = null; 
			
			do { 
				nomeDoLivro = leitor.readLine();
				if (nomeDoLivro != null) {
					String codigo = leitor.readLine(); 
					String autor = leitor.readLine(); 
					String classificacao = leitor.readLine(); 
					int quantidade = Integer.parseInt(leitor.readLine());
					
					Livro l = new Livro (nomeDoLivro, codigo, autor,quantidade,classificacao); 
					biblioteca.cadastrarLivro(l); 
				} 
			} while (nomeDoLivro!= null); 
			
		} finally { 
			if (leitor != null) { 
				leitor.close(); 
			}
		}
	}
	
	public void gravarEmprestimosEmArquivo(String nomeArquivo) throws IOException {
		BufferedWriter gravador = null;
		try {
			gravador = new BufferedWriter(new FileWriter(nomeArquivo));
			for (Emprestimo e: biblioteca.getEmprestimosAtivos()){
				gravador.write(e.getUsuario().getMatricula());
				gravador.newLine();
				gravador.write(e.getLivro().getCodigo());
				gravador.newLine();
				
				gravador.write(Integer.toString(e.getDataEmprestimo().get(Calendar.DAY_OF_MONTH)));
				gravador.newLine();
				gravador.write(Integer.toString(e.getDataEmprestimo().get(Calendar.MONTH)));;
				gravador.newLine();
				gravador.write(Integer.toString(e.getDataEmprestimo().get(Calendar.YEAR)));
				gravador.newLine();
				
				gravador.write(Integer.toString(e.getDataDevolucao().get(Calendar.DAY_OF_MONTH)));
				gravador.newLine();
				gravador.write(Integer.toString(e.getDataDevolucao().get(Calendar.MONTH)));
				gravador.newLine();
				gravador.write(Integer.toString(e.getDataDevolucao().get(Calendar.YEAR)));
				gravador.newLine();
			}
		} finally {
			if (gravador!=null){
				gravador.close();
			}
		}		
	}
	
	public void gravarConfiguracoesEmArquivo(String nomeArquivoConfiguracao) throws IOException{
		BufferedWriter gravador = null;
		try {
			gravador = new BufferedWriter(new FileWriter(nomeArquivoConfiguracao));
			
			gravador.write(Integer.toString(Configuracao.getInstance().getDiasEmprestimoAluno()));
			gravador.write(Integer.toString(Configuracao.getInstance().getDiasEmprestimoProfessor()));
			gravador.write(Double.toString(Configuracao.getInstance().getValorMulta()));
			
		
		} finally {
			if (gravador!=null){
				gravador.close();
			}
		}
	}
	
	public void carregarConfiguracoesDeArquivo (String nomeArquivoConfiguracao) throws IOException{
		BufferedReader leitor = null;
		
		try {
			leitor = new BufferedReader(new FileReader(nomeArquivoConfiguracao));
			String diasEmprestimoAluno = null;
			
			do {
				diasEmprestimoAluno = leitor.readLine();
				if (diasEmprestimoAluno != null){
					String diasEmprestimoProfessor = leitor.readLine();
					String valorMulta = leitor.readLine();
					
					Configuracao.getInstance().setDiasEmprestimoAluno(Integer.parseInt(diasEmprestimoAluno));
					Configuracao.getInstance().setDiasEmprestimoProf(Integer.parseInt(diasEmprestimoProfessor));
					System.out.println(Double.parseDouble(valorMulta));
					Configuracao.getInstance().setValorMulta(Double.parseDouble(valorMulta));
				}
				
				
			} 
			while(diasEmprestimoAluno != null); 
		} 
		finally {
			if (leitor!=null){
				leitor.close();
			}
		}
	}
	
	public void carregarEmprestimosDeArquivo(String nomeArquivo) throws IOException, EmprestimoJaExisteException, UsuarioInexistenteException, LivroInexistenteException{
		BufferedReader leitor = null;
		try {
			leitor = new BufferedReader(new FileReader(nomeArquivo));
			String matricula = null;
			String codigoLivro;
			
			String diaEmprestimo;
			String mesEmprestimo;
			String anoEmprestimo;
			
			String diaDevolucao;
			String mesDevolucao;
			String anoDevolucao;
			
			
			do {
				matricula = leitor.readLine();
				if (matricula != null){
					codigoLivro = leitor.readLine();
					
					diaEmprestimo = leitor.readLine();
					mesEmprestimo = leitor.readLine();
					anoEmprestimo = leitor.readLine();
					
					diaDevolucao = leitor.readLine();
					mesDevolucao = leitor.readLine();
					anoDevolucao = leitor.readLine();
					
					
					
					Usuario usuario = biblioteca.getUsuario(matricula);
					Livro livro = biblioteca.getLivro(codigoLivro);
					
					Calendar dataEmprestimo = Calendar.getInstance();
					dataEmprestimo.set(Integer.parseInt(anoEmprestimo),Integer.parseInt(mesEmprestimo),Integer.parseInt(diaEmprestimo));
					
					Calendar dataDevolucao = Calendar.getInstance();
					dataDevolucao.set(Integer.parseInt(anoDevolucao), Integer.parseInt(mesDevolucao), Integer.parseInt(diaDevolucao));
					
					Emprestimo emprestimo = new Emprestimo(usuario, livro, dataEmprestimo, dataDevolucao);
					
					usuario.adicionarEmprestimo(emprestimo);
					biblioteca.getEmprestimosAtivos().add(emprestimo);
				}
				
				
			} 
			while(matricula != null); 
		} 
		finally {
			if (leitor!=null){
				leitor.close();
			}
		}
		
	}
	
	public void gravarUsuariosEmArquivo (String nomeArquivo){
		for (Usuario u: biblioteca.getUsuarios()){
			try {
				u.gravarUsuariosEmArquivo(nomeArquivo);
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
	}
	
	public void carregarUsuariosDeArquivo (String nomeArquivo){
		
		for (Usuario u: biblioteca.getUsuarios()){
			try {
				u.carregarUsuariosDeArquivo(nomeArquivo);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			} catch (UsuarioJaExisteException e) {
				e.printStackTrace();
				System.out.println(e.getMessage());
			}
		}
	}
}
