package com.marciosn.cloud.storage.blobs.controll;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import com.marciosn.cloud.storage.blobs.dao.UsuarioJPADAO;
import com.marciosn.cloud.storage.blobs.model.Usuario;

@ManagedBean
@SessionScoped
public class RegistroBean {
	private Usuario usuario = new Usuario();
	private UsuarioJPADAO usuarioDAO = new UsuarioJPADAO();
	private List<Usuario> usuarios = new ArrayList<Usuario>();
	
	public String CadastraUsuario(){
		if(usuario.getNome() == null || usuario.getNome().contains(" ")){
			System.out.println("Parametro null");
			Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
    		flash.setKeepMessages(true);
    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage	(FacesMessage.SEVERITY_WARN, "O Username NÃO PODE conter espaços!!!!", null));
			return "registro";
		}
		try {
			usuarioDAO.beginTransaction();
			usuarioDAO.save(usuario);
			usuarioDAO.commit();
		} catch (Exception e) {
			usuarioDAO.rollback();
			e.printStackTrace();
		} finally{
			usuarioDAO.close();
		}
		ListaUsuario();
		return "login";
	}
	public void ListaUsuario(){
		List<Usuario> usuarios = usuarioDAO.find();
		for(Usuario u: usuarios){
			System.out.println("Nome "+ u.getNome());
			System.out.println("Senha "+ u.getSenha());
			System.out.println("Email "+ u.getEmail());
			System.out.println("=====================");
		}
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
}
