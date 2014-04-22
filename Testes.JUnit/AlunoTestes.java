package br.ufpb.dce.poo.projetopack;

import static org.junit.Assert.*;
import junit.framework.TestCase;

import org.junit.Test;

public class AlunoTestes extends TestCase {
	Aluno a = new Aluno("", "", "", "", "");


	public void testNome(){
		assertEquals("", a.getNome());
		a.setNome("Anderson");
		assertEquals("Anderson", a.getNome());
	}
	
	public void testMatricula(){
		assertEquals("", a.getMatricula());
		a.setMatricula("81211184");
		assertEquals("81211184", a.getMatricula());
	}
	
	public void testCpf(){
		assertEquals("", a.getCPF());
		a.setCpf("12345");
		assertEquals("12345", a.getCPF());
	}
	
	public void testCurso() {
		assertEquals("", a.getCurso());
		a.setCurso("Sistemas de Informação");
		assertEquals("Sistemas de Informação", a.getCurso());
	}
	//Crio um aluno e ele tem acesso ao departamento
	public void testPriodoIngresso(){
		assertEquals("", a.getPeriodoIngresso());
		a.setPeriodoIngresso("2012.1");
		assertEquals("2012.1", a.getPeriodoIngresso());
	}
	
	
	




}
