package br.com.cid.controller.consulta;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.hibernate.validator.constraints.br.CPF;

import br.com.cid.model.Usuario;
import br.com.cid.repository.Usuarios;
import br.com.cid.service.GestaoUsuarios;
import br.com.cid.util.jsf.FacesMessages;

@Named
@ViewScoped
public class ConsultaUsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Usuario usuario;
	
	private List<Usuario> todosUsuarios;
	
	private Usuario usuarioSelecionado;
	
	@CPF
	private String cpf;
	
	@Inject
	private Usuarios usuarios;
	
	@Inject
	private GestaoUsuarios gestaoUsuarios;
	
	@Inject
	private FacesMessages facesMessages;
	
	@PostConstruct
	public void inicializar() {
		this.todosUsuarios = usuarios.todos();
		this.usuario = new Usuario();
	}
	
	public void excluir() {
		this.gestaoUsuarios.excluir(this.usuarioSelecionado);
		this.facesMessages.info("usuário " + this.usuarioSelecionado.getNome() + " excluído com sucesso!");
		
		inicializar();
	}
	
	public void pesquisar() {
		this.todosUsuarios = usuarios.porCPF(cpf);
		this.facesMessages.info("resultado da pesquisa!");
	}
	
	public List<Usuario> getUsuarios() {
		return todosUsuarios;
	}

	public Usuario getUsuarioSelecionado() {
		return usuarioSelecionado;
	}

	public void setUsuarioSelecionado(Usuario usuarioSelecionado) {
		this.usuarioSelecionado = usuarioSelecionado;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}
