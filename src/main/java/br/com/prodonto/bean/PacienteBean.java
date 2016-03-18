package br.com.prodonto.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import org.omnifaces.util.Messages;

import br.com.prodonto.dao.PacienteDAO;
import br.com.prodonto.domain.Paciente;

@ManagedBean
@ViewScoped
public class PacienteBean {

	private Paciente paciente;
	private List<Paciente> pacientes;
	
	public void novo() {
		paciente = new Paciente();
	}
	
	@PostConstruct
	public void listar() {
		try {
			PacienteDAO pacienteDAO = new PacienteDAO();
			pacientes = pacienteDAO.listar();
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar listar os pacientes!");
			erro.printStackTrace();
		}
	}
	
	public void salvar() {

		try {
			PacienteDAO cidadeDAO = new PacienteDAO();
			cidadeDAO.salvar(paciente);

			novo();
			listar();

			Messages.addGlobalInfo("Paciente foi salvo!");
		} catch (RuntimeException erro) {
			Messages.addGlobalError("Ocorreu um erro ao tentar salvar o paciente!");
			erro.printStackTrace();
		}
	}
	
	public void excluir(ActionEvent evento) {
		try {
			paciente = (Paciente) evento.getComponent().getAttributes().get("pacienteSelecionada");
			PacienteDAO pacienteDAO = new PacienteDAO();
			pacienteDAO.excluir(paciente);
			
			listar();

			Messages.addGlobalInfo("Paciente removida com sucesso!");
		} catch (RuntimeException erro) {
			Messages.addFlashGlobalError("Ocorreu um erro ao tentar remover o paciente!");
			erro.printStackTrace();
		}
		
	}
	
	public void editar(ActionEvent evento) {
		paciente = (Paciente) evento.getComponent().getAttributes().get("pacienteSelecionada");
	}
	
	

	public Paciente getPaciente() {
		if (paciente ==null) {
			paciente = new Paciente();
		}
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public List<Paciente> getPacientes() {
		return pacientes;
	}

	public void setPacientes(List<Paciente> pacientes) {
		this.pacientes = pacientes;
	}
	
	
	
}
