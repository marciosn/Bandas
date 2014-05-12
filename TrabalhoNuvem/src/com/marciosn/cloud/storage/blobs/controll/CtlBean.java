package com.marciosn.cloud.storage.blobs.controll;

import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import org.primefaces.event.RateEvent;

import com.marciosn.cloud.storage.blobs.controll.rep.Blob;
import com.marciosn.cloud.storage.blobs.controll.rep.Repositorio;
import com.marciosn.cloud.storage.blobs.dao.UsuarioJPADAO;
import com.marciosn.cloud.storage.blobs.model.Usuario;
import com.microsoft.windowsazure.services.blob.client.CloudBlobClient;
import com.microsoft.windowsazure.services.blob.client.CloudBlobContainer;
import com.microsoft.windowsazure.services.blob.client.ListBlobItem;
import com.microsoft.windowsazure.services.core.storage.CloudStorageAccount;
import com.microsoft.windowsazure.services.core.storage.StorageException;

@ManagedBean
@SessionScoped
public class CtlBean {
@ManagedProperty(value="#{repositorio}")
private Repositorio repositorio;
private Integer rating2; 
private String nomeContainer2;
private UsuarioJPADAO usuarioDAO = new UsuarioJPADAO();
private List<Usuario> usuarios = new ArrayList<Usuario>();
StorageControll sc = new StorageControll();
	 public static final String storageConnectionString = 
	            "DefaultEndpointsProtocol=http;" + 
	               "AccountName=portalvhdsjtq29274knmm2;" + 
	               "AccountKey=ywi91c425cNVBnpFuQs0ieA1UzkUIF/nF5KZ0BpUc9fXh0xBs36IaO8w039MRvtRLineI1iMgcKGlXsOq51vKg=="; 
	 
	 public List<Usuario> getListaBanda(){
			usuarios = usuarioDAO.find();
			return usuarios;
		}
	 
	 public String ListarBlobs(){
		 System.out.println("Parametro recebido é: " + nomeContainer2);
		
		if(nomeContainer2 == null || nomeContainer2.contains(" ")){
			System.out.println("Parametro null");
			Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
    		flash.setKeepMessages(true);
    		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage	(FacesMessage.SEVERITY_WARN, "Não pode existir espaços no nome do container", null));
			return "/pages/listaBandas";
		}
		 
		while(repositorio.getListaIMAGES().size() > 0 || repositorio.getListaMUSICAS().size() > 0
	    			|| repositorio.getListaVIDEOS().size() > 0){
	    		sc.LimpaLista(repositorio.getListaIMAGES());
	    		sc.LimpaLista(repositorio.getListaMUSICAS());
	    		sc.LimpaLista(repositorio.getListaVIDEOS());
	    	}
		 
	 CloudStorageAccount storageAccount;
		try{	
		storageAccount = CloudStorageAccount.parse(storageConnectionString);
		CloudBlobClient blobClient = storageAccount.createCloudBlobClient();
		CloudBlobContainer container = blobClient.getContainerReference(nomeContainer2);
		if(container.exists()){
		for (ListBlobItem blobItem : container.listBlobs()) {
		  	String uri = blobItem.getUri().toString();
		   	Blob blob = new Blob(uri);
		        	if(uri.contains(".jpg") || uri.contains(".png") || uri.contains(".gif") || uri.contains(".jpeg")){
		        		repositorio.getListaIMAGES().add(blob);
		        	}else
		        		if(uri.contains(".mp3")){
		        			repositorio.getListaMUSICAS().add(blob);
		        		}else
		        			if(uri.contains(".mp4") || uri.contains(".avi") || uri.contains(".3gp") || uri.contains(".mkv")){
		        				repositorio.getListaVIDEOS().add(blob);
		        			}
		    	}
		}
		else
			return "/pages/exception";
		
	 } catch (InvalidKeyException invalidKeyException) {
			System.out.print("InvalidKeyException encontrado no metodo ListarBlobs(): ");
			System.out.println(invalidKeyException.getMessage());
			return "/pages/exception";
         //System.exit(-1);

		} catch (URISyntaxException uriSyntaxException) {
			System.out.print("URISyntaxException encontrado no metodo ListarBlobs(): ");
	        System.out.println(uriSyntaxException.getMessage());
	        return "/pages/exception";
	        //System.exit(-1);
		} catch (StorageException storageException) {
			System.out.print("StorageException encontrado no metodo ListarBlobs(): ");
			System.out.println(storageException.getMessage());
			return "/pages/exception";
			//System.exit(-1);
		}
	
			return "/pages/paginaBanda2?faces-redirect=true";
			
  }
	     
    public List<Usuario> getUsuarios() {
	return usuarios;
}
public void setUsuarios(List<Usuario> usuarios) {
	this.usuarios = usuarios;
}

public void onrate(RateEvent rateEvent) {
	FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Rate Event", "You rated:" + ((Integer) rateEvent.getRating()).intValue()); 
	FacesContext.getCurrentInstance().addMessage(null, message);
}
	     
public void oncancel() {
      FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cancel Event", "Rate Reset");
     FacesContext.getCurrentInstance().addMessage(null, message);
   }     
public Integer getRating2() {
    return rating2;
}
	 
public void setRating2(Integer rating2) {
   this.rating2 = rating2;
}
public String getNomeContainer2() {
	return nomeContainer2;
}

public void setNomeContainer2(String nomeContainer2) {
	this.nomeContainer2 = nomeContainer2;
}

public Repositorio getRepositorio() {
	return repositorio;
}

public void setRepositorio(Repositorio repositorio) {
	this.repositorio = repositorio;
}
}
