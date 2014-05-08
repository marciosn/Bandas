package com.marciosn.cloud.storage.blobs.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
@Entity
@Table (name = "Usuario")
@NamedQuery (name = "Usuario.findUsuario", query = "from Usuario")
public class Usuario implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8197371596722447364L;
	private static int ultimoID = 0;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String nome;
	private String senha;
	private String email;
	
	public Usuario(){
		this.id = ultimoID++;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
