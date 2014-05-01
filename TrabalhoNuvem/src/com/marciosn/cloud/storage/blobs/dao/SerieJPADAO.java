package com.marciosn.cloud.storage.blobs.dao;

import com.marciosn.cloud.storage.blobs.model.Serie;

public class SerieJPADAO extends GenericJPADAO<Serie> implements SerieDAO{
	public SerieJPADAO(){
		this.persistentClass = Serie.class;
	}

}
