package com.marciosn.cloud.storage.blobs.controll;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;

import com.marciosn.cloud.storage.blobs.controll.rep.Blob;
import com.marciosn.cloud.storage.blobs.controll.rep.Repositorio;
import com.marciosn.cloud.storage.blobs.dao.FilmeJPADAO;
import com.marciosn.cloud.storage.blobs.model.Filme;
import com.microsoft.windowsazure.services.blob.client.CloudBlobClient;
import com.microsoft.windowsazure.services.blob.client.CloudBlobContainer;
import com.microsoft.windowsazure.services.blob.client.CloudBlockBlob;
import com.microsoft.windowsazure.services.blob.client.ListBlobItem;
import com.microsoft.windowsazure.services.core.storage.CloudStorageAccount;
import com.microsoft.windowsazure.services.core.storage.StorageException;
@ManagedBean
public class FilmeBean {

	@ManagedProperty(value="#{repositorio}")
	private Repositorio repositorio;
	private Filme filme = new Filme();
	private FilmeJPADAO filmeDAO = new FilmeJPADAO();
	private List<Filme> filmes = new ArrayList<Filme>();
	private String capa;
	private String uriCapa;
	private String uri;
	
	public static final String storageConnectionString = 
            "DefaultEndpointsProtocol=http;" + 
               "AccountName=portalvhdsjtq29274knmm2;" + 
               "AccountKey=ywi91c425cNVBnpFuQs0ieA1UzkUIF/nF5KZ0BpUc9fXh0xBs36IaO8w039MRvtRLineI1iMgcKGlXsOq51vKg=="; 

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
    public void pegaCapa() throws InvalidKeyException, URISyntaxException, StorageException{
        CloudStorageAccount account;
        CloudBlobClient serviceClient;
        CloudBlobContainer container;
        CloudBlockBlob blob;
        
        System.out.println("A capa recebida é :" + capa);
        account = CloudStorageAccount.parse(storageConnectionString);
        serviceClient = account.createCloudBlobClient();
        container = serviceClient.getContainerReference("myimages");
    	
    	for (ListBlobItem blobItem : container.listBlobs()) {
    	    String uri = blobItem.getUri().toString();
    	    Blob blob2 = new Blob(uri);
    	    repositorio.getListaUris().add(blob2);
    	}
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
	public Repositorio getRepositorio() {
		return repositorio;
	}

	public void setRepositorio(Repositorio repositorio) {
		this.repositorio = repositorio;
	}

	public String getCapa() {
		return capa;
	}

	public void setCapa(String capa) {
		this.capa = capa;
	}

	public String getUriCapa() {
		return uriCapa;
	}

	public void setUriCapa(String uriCapa) {
		this.uriCapa = uriCapa;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}
}
