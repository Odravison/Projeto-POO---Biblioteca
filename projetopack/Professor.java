package br.ufpb.dce.poo.projetopack;


public class Aluno extends Usuario {
	
	public Aluno(String nome, String matricula, String cpf, String curso, String periodoIngresso){
		super(nome, matricula, cpf);
		super.setPeriodoIngresso(periodoIngresso);
		super.setCurso(curso);
	}

	public int getQuantDiasEmprestimo() {
		return Configuracao.getInstance().getDiasEmprestimoAluno();
	}

}
