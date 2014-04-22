package br.ufpb.dce.poo.projetopack;

public class Aluno extends Usuario {
	
	private String periodoIngresso;
	private String curso;
	
	public Aluno(String nome, String matricula, String cpf, String curso, String periodoIngresso){
		super(nome, matricula, cpf);
		this.curso = curso;
		this.periodoIngresso = periodoIngresso;
	}

	public int getQuantDiasEmprestimo() {
		return Configuracao.getInstance().getDiasEmprestimoAluno();
	}
	
	public void setPeriodoIngresso(String periodo){
		this.periodoIngresso = periodo;
	}
	
	public String getPeriodoIngresso(){
		return this.periodoIngresso;	
	}
	
	public void setCurso(String curso){
		this.curso = curso;
	}
	
	public String getCurso(){
		return this.curso;
	}
}
