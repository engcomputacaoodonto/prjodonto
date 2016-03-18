package br.com.prodonto.dao;

import org.junit.Test;

import br.com.prodonto.domain.Paciente;

public class PacienteDAOTest {

	@Test
	//@Ignore
	public void salvar() {
		Paciente paciente = new Paciente();
		paciente.setNome("Rio Grande do Sul");
		

		PacienteDAO pacienteDAO = new PacienteDAO();
		pacienteDAO.salvar(paciente);
	}
	
}
