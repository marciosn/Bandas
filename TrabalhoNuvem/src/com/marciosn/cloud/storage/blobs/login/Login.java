package com.marciosn.cloud.storage.blobs.login;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.marciosn.cloud.storage.blobs.dao.UsuarioJPADAO;
import com.marciosn.cloud.storage.blobs.model.Usuario;

@ManagedBean
@RequestScoped
@SessionScoped
public class Login implements Serializable{

	private static final long serialVersionUID = -3984002432649039239L;	
	private Usuario usuario;
	private String username;
	private String password;
	private UsuarioJPADAO usuarioDAO;
	private List<Usuario> usuarios;
	
	public Login(){
		usuario = new Usuario();
		usuarioDAO = new UsuarioJPADAO();
		usuarios = new ArrayList<Usuario>();
		HttpSession httpSession = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		if(httpSession != null){
			httpSession.invalidate();
		}
	}
	
	public String loginBean(){
		System.out.println("Entrou em LoginBean");
		HttpSession s = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		try{
		//if(username.equals("blob") && password.equals("2014")){
		for(Usuario u : getUsuarioBanco()){
			//System.out.println("Nome: "+ u.getNome());
		if(u.getNome().contains(username)){
			if(s == null){
				s = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
			}
			//s.setAttribute("username", username);
			s.setAttribute("username", username);
			return "/index?faces-redirect=true";
		}else{
			if(s != null){
				s.invalidate();
			}
		}
		}
		} catch(Exception e){
			e.printStackTrace();
		}
		return "/login";
	}
	public List<Usuario> getUsuarioBanco(){
		List<Usuario> usuarios = usuarioDAO.find();
		return usuarios;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
