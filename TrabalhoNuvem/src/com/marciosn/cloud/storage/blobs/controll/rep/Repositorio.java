package com.marciosn.cloud.storage.blobs.controll.rep;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
@ManagedBean
@SessionScoped
public class Repositorio {
	
	public Repositorio(){	
	}
	
	List<Blob> lista = new ArrayList<Blob>();
	List<Blob> listamusics = new ArrayList<Blob>();
	List<Blob> listvideos = new ArrayList<Blob>();
	List<Blob> listaIMAGES = new ArrayList<Blob>();
	List<Blob> listaMUSICAS = new ArrayList<Blob>();
	List<Blob> listaVIDEOS = new ArrayList<Blob>();
	List<Blob> listaUris = new ArrayList<Blob>();
	public List<Blob> getLista() {
		return lista;
	}

	public void setLista(List<Blob> lista) {
		this.lista = lista;
	}
	
	public List<Blob> getListamusics() {
		return listamusics;
	}

	public void setListamusics(List<Blob> listamusics) {
		this.listamusics = listamusics;
	}

	public List<Blob> getListvideos() {
		return listvideos;
	}

	public void setListvideos(List<Blob> listvideos) {
		this.listvideos = listvideos;
	}
	public String insere(Blob b){
		lista.add(b);
		return "lista";
	}
	public List<Blob> getListaUris() {
		return listaUris;
	}

	public void setListaUris(List<Blob> listaUris) {
		this.listaUris = listaUris;
	}

	public List<Blob> getListaIMAGES() {
		return listaIMAGES;
	}

	public void setListaIMAGES(List<Blob> listaIMAGES) {
		this.listaIMAGES = listaIMAGES;
	}

	public List<Blob> getListaMUSICAS() {
		return listaMUSICAS;
	}

	public void setListaMUSICAS(List<Blob> listaMUSICAS) {
		this.listaMUSICAS = listaMUSICAS;
	}

	public List<Blob> getListaVIDEOS() {
		return listaVIDEOS;
	}

	public void setListaVIDEOS(List<Blob> listaVIDEOS) {
		this.listaVIDEOS = listaVIDEOS;
	}
}
