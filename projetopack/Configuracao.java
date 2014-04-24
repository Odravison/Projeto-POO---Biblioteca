package br.ufpb.dce.poo.projetopack;

public class Configuracao {
	private static Configuracao singleton;
	
	private double valorMulta = 0;
	private int diasEmprestimoProf = 0;
	private int diasEmprestimoAluno = 0;
	// private String caminhoArquivoEmprestimo = 
	
	private Configuracao (){
		
	}
		
	public static Configuracao getInstance(){
		if (Configuracao.singleton == null){
			Configuracao.singleton = new Configuracao();
		}
		return Configuracao.singleton;
	}
	
	public int getDiasEmprestimoProf() {
		return diasEmprestimoProf;
	}

	public void setDiasEmprestimoProf(int diasEmprestimoProf) {
		this.diasEmprestimoProf = diasEmprestimoProf;
	}

	public void setDiasEmprestimoAluno(int diasEmprestimoAluno) {
		this.diasEmprestimoAluno = diasEmprestimoAluno;
	}
	
	public int getDiasEmprestimoProfessor(){
		return this.diasEmprestimoProf;
	}
	
	public int getDiasEmprestimoAluno(){
		return this.diasEmprestimoAluno;
	}
	
	public void setValorMulta (double valor){
		this.valorMulta = valor;
	}
	
	public double getValorMulta (){
		return this.valorMulta;
	}
	

	
	
}
