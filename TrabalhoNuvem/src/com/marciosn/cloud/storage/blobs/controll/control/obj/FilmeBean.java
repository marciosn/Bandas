package com.marciosn.cloud.storage.blobs.controll.control.obj;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import com.marciosn.cloud.storage.blobs.dao.FilmeJPADAO;
import com.marciosn.cloud.storage.blobs.model.Filme;
@ManagedBean
public class FilmeBean {
	private Filme filme = new Filme();
	private FilmeJPADAO filmeDAO = new FilmeJPADAO();
	private List<Filme> filmes = new ArrayList<Filme>();

	public String InsereFilme(){
		try {
			filmeDAO.beginTransaction();
			filmeDAO.save(filme);
			filmeDAO.commit();
		} catch (Exception e) {
			filmeDAO.rollback();
			e.printStackTrace();
		} finally{
			filmeDAO.close();
		}
	return "/pages/listaFilme?faces-redirect=true";	
	}
	
	public List<Filme> getFilmeBanco(){
		List<Filme> filmes = filmeDAO.find();
		return filmes;
	}
	
	public Filme getFilme() {
		return filme;
	}

	public void setFilme(Filme filme) {
		this.filme = filme;
	}

	public List<Filme> getFilmes() {
		return filmes;
	}

	public void setFilmes(List<Filme> filmes) {
		this.filmes = filmes;
	}

}
