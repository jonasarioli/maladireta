package com.matera.maladireta;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.matera.maladireta.domain.Aluno;
import com.matera.maladireta.domain.Disciplina;
import com.matera.maladireta.domain.MalaDireta;
import com.matera.maladireta.domain.Nota;
import com.matera.maladireta.domain.service.AlunoService;
import com.matera.maladireta.domain.service.MalaDiretaService;

@RunWith(SpringRunner.class)
public class MalaDiretaServiceTest {

	
	private MalaDiretaService malaDiretaService;

	@MockBean
	private AlunoService alunoService;
	
	@Before
	public void setUp() {
	    Aluno alex = new Aluno();
	    
	    alex.setNome("Alex");
	    alex.setDocumento("111.111.111-11");
	    alex.setEndereco("Rua do Alex, 111. Campinas, SP");
	    alex.setCep("11.111-111");
	 
	    Mockito.when(alunoService.getAllAlunos())
	      .thenReturn(Arrays.asList(alex));
	    
	    Disciplina math = new Disciplina();
	    math.setNome("Matematica");
	    math.setNota(7.0);
	    
	    Disciplina quimica = new Disciplina();
	    quimica.setNome("Quimica");
	    quimica.setNota(6.99);
	    
	    Nota nota = new Nota();
	    nota.setCpf(alex.getDocumento());
	    nota.setNotas(Arrays.asList(math, quimica));
	    
	    Mockito.when(alunoService.getNotaByCpf(alex.getDocumento()))
	      .thenReturn(nota);	    
	}
	
	@Test
	public void whenValidName_thenEmployeeShouldBeFound() {
	    malaDiretaService = new MalaDiretaService(alunoService);
	    
	    
	    MalaDireta input = new MalaDireta();
	    
	    input.setNome("Alex");
	    input.setEndereco("Rua do Alex, 111. Campinas, SP");
	    input.setCep("11.111-111");
	    
	    Integer numeroDeEmails = malaDiretaService.process(Arrays.asList(input));
	  
	    Assert.assertTrue(numeroDeEmails == 1 );
	 }
}
