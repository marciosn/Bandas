package com.marciosn.cloud.storage.blobs.login;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.hibernate.mapping.UnionSubclass;

import com.marciosn.cloud.storage.blobs.dao.UsuarioJPADAO;
import com.marciosn.cloud.storage.blobs.model.Filme;
import com.marciosn.cloud.storage.blobs.model.Usuario;

@ManagedBean
@RequestScoped
public class Login implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3984002432649039239L;
	private String username;
	private String password;
	private UsuarioJPADAO usuarioDAO = new UsuarioJPADAO();
	private List<Usuario> usuarios = new ArrayList<Usuario>();
	String n = "Márcio Souza";


	public Login(){
		HttpSession s = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		if(s != null){
			s.invalidate();
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
} 			catch(Exception e){
			e.printStackTrace();
		}
		return "/login";
	}
	
	public List<Usuario> getUsuarioBanco(){
		List<Usuario> usuarios = usuarioDAO.find();
		return usuarios;
	}
	
	public String getNome() {
		return username;
	}
	public void setNome(String nome) {
		this.username = nome;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public UsuarioJPADAO getUsuarioDAO() {
		return usuarioDAO;
	}
	public void setUsuarioDAO(UsuarioJPADAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
}
