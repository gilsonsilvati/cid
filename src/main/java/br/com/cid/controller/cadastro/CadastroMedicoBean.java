package br.com.cid.controller.cadastro;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.cid.model.Medico;
import br.com.cid.model.UF;
import br.com.cid.service.GestaoMedicos;
import br.com.cid.service.NegocioException;
import br.com.cid.util.jsf.FacesMessages;

@Named
@RequestScoped
public class CadastroMedicoBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Medico medico;
	
	private List<UF> ufs;
	
	@Inject
	private GestaoMedicos gestaoMedicos;
	
	@Inject
	private FacesMessages facesMessages;
	
	@PostConstruct
	public void inicializar() {
		if (this.medico == null) {
			this.limpar();
		}
		
		this.ufs = Arrays.asList(UF.values());
	}

	public void salvar() {
		try {
			this.gestaoMedicos.salvar(this.medico);
			this.facesMessages.info("médico " + this.medico.getNome() + " salvo com sucesso!");
			limpar();
		} catch (NegocioException e) {
			facesMessages.error(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			facesMessages.error("Falha ao cadastrar usuário.");
		}
	}
	
	private void limpar() {
		this.medico = new Medico();
	}
	
	public List<UF> getUfs() {
		return ufs;
	}

	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
		
		if (this.medico == null) {
			medico = new Medico();
		}
	}
	
	public boolean isEditando() {
		return this.medico.getId() != null;
	}
	
}
