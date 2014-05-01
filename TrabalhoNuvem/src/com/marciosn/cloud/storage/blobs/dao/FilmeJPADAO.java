package com.marciosn.cloud.storage.blobs.dao;

import com.marciosn.cloud.storage.blobs.model.Filme;

public class FilmeJPADAO extends GenericJPADAO<Filme> implements FilmeDAO{
	public FilmeJPADAO(){
		this.persistentClass = Filme.class;
	}

}
