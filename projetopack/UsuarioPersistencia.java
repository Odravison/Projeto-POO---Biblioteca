package br.ufpb.dce.poo.projetopack;

import java.io.FileNotFoundException;
import java.io.IOException;

import br.ufpb.dce.poo.ExceptionsProject.UsuarioJaExisteException;

public interface UsuarioPersistencia {
	
	public void gravarUsuariosEmArquivo(String nomeArquivo) throws IOException;
	public void carregarUsuariosDeArquivo(String nomeArquivo) throws FileNotFoundException, IOException, UsuarioJaExisteException;
}
