package com.marciosn.cloud.storage.blobs.login;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;

import com.google.gson.Gson;
import com.marciosn.cloud.storage.blobs.model.Usuario;

@ManagedBean
@RequestScoped
@SessionScoped
public class Login implements Serializable{

	private static final long serialVersionUID = -3984002432649039239L;	
	private Usuario usuario = new Usuario();
	
	public Login(){
		HttpSession httpSession = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		if(httpSession != null){
			httpSession.invalidate();
		}
	}
	
	@SuppressWarnings("deprecation")
	public String autenticarUsuario(){	
		String url = "http://138.91.120.126:8080/WSBandasRest/usuario/getAutentica/";
		url.trim();
		
		HttpSession httpSession = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		
		System.out.println(url + usuario.getNome() + "/" + usuario.getSenha());
		
		@SuppressWarnings("resource")
		HttpClient httpCliente = new DefaultHttpClient();
		HttpUriRequest httpUriRequest = new HttpGet(url + usuario.getNome() + "/" + usuario.getSenha());
		
		String usuarioJson = null;
		try {
			HttpResponse httpResponse = httpCliente.execute(httpUriRequest);
			InputStream inputStream = httpResponse.getEntity().getContent();
			Reader reader = new InputStreamReader(inputStream);

			Gson gson = new Gson();

			usuarioJson = gson.fromJson(reader, String.class);
				
			System.out.println(usuarioJson);
			
			if (usuarioJson.equals("true")){
				System.out.println("Entrei aqui");
				
				if(httpSession == null){
					System.out.println("Pegando a sessão");
					httpSession = (HttpSession)FacesContext.getCurrentInstance().getExternalContext().getSession(true);
				}
				System.out.println(usuario.getNome());
				httpSession.setAttribute("username", usuario.getNome());								
				return "/index?faces-redirect=true";
				
			}else{
				if(httpSession != null){
					httpSession.invalidate();
				}
			}			
			
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "/login";
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
