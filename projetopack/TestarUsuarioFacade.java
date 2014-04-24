package br.ufpb.dce.poo.projetopack;


public class TestarUsuarioFacade {
	
	public static void main(String [] args){
		BibliotecaFacade biblioteca = new BibliotecaFacade();
		Biblioteca bibliotecca = Biblioteca.getInstance();
		
		try {
			//biblioteca.carregarSistema();
			//Biblioteca b = biblioteca.getBiblioteca();
			//System.out.println(b.getUsuario("3838").getMatricula());
			//System.out.println(b.getLivro("456").getNome());
			
			biblioteca.cadastrarUsuario(new Aluno( "Luender","3838", "89843", "SI", "2012.1"));
			biblioteca.cadastrarUsuario(new Professor("Rodrigo", "333", "37236", "CCAE"));
			biblioteca.cadastrarLivro(new Livro ("POO", "456", "Luender Lima", 50, "Programação"));
			biblioteca.emprestarLivro(bibliotecca.getUsuario("3838"), bibliotecca.getLivro("456"));
		
			biblioteca.gravarSistema();
			System.out.println("chegou");
		} catch (Exception e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}

	}
}
