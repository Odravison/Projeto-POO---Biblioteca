package br.ufpb.dce.poo.projetopack;


public class Professor extends Usuario{
	
	private String departamento;

	public Professor(String nome, String matricula, String cpf, String departamento){
		super(nome, matricula, cpf);
		this.departamento = departamento;
	}

	public int getQuantDiasEmprestimo() {
		return Configuracao.getInstance().getDiasEmprestimoProfessor();
	}
	
	public String getDepartamento(){
		return this.departamento;
	}
	
	public void setDepartamento(String departamento){
		this.departamento = departamento;
	}

}
