package com.marciosn.cloud.storage.blobs.dao;

import com.marciosn.cloud.storage.blobs.model.Usuario;

public class UsuarioJPADAO extends GenericJPADAO<Usuario> implements UsuarioDAO{
	public UsuarioJPADAO(){
		this.persistentClass = Usuario.class;
	}

}
