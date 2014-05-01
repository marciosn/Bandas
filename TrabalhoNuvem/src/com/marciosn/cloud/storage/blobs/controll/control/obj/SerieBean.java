package com.marciosn.cloud.storage.blobs.controll.control.obj;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import com.marciosn.cloud.storage.blobs.dao.SerieJPADAO;
import com.marciosn.cloud.storage.blobs.model.Serie;
@ManagedBean
public class SerieBean {
	private Serie serie = new Serie();
	private SerieJPADAO serieDAO = new SerieJPADAO();
	private List<Serie> series = new ArrayList<Serie>();
	
	public String InsereSerie(){
		try {
			serieDAO.beginTransaction();
			serieDAO.save(serie);
			serieDAO.commit();
		} catch (Exception e) {
			serieDAO.rollback();
			e.printStackTrace();
		} finally{
			serieDAO.close();
		}
		return "/pages/listaSerie?faces-redirect=true";	
	}
	public List<Serie> getSerieBanco(){
		List<Serie> series = serieDAO.find();
		return series;
	}
	public Serie getSerie() {
		return serie;
	}
	public void setSerie(Serie serie) {
		this.serie = serie;
	}
	public List<Serie> getSeries() {
		return series;
	}
	public void setSeries(List<Serie> series) {
		this.series = series;
	}
	
}
