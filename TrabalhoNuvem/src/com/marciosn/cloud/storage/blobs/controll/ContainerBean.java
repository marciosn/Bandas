package com.marciosn.cloud.storage.blobs.controll;

import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.microsoft.windowsazure.services.blob.client.CloudBlobClient;
import com.microsoft.windowsazure.services.blob.client.CloudBlobContainer;
import com.microsoft.windowsazure.services.blob.client.CloudBlockBlob;
import com.microsoft.windowsazure.services.blob.models.ListContainersResult;
import com.microsoft.windowsazure.services.blob.models.ListContainersResult.Container;
import com.microsoft.windowsazure.services.core.storage.CloudStorageAccount;
import com.sun.xml.bind.v2.schemagen.xmlschema.List;

@ManagedBean
@SessionScoped
public class ContainerBean {
	   public static final String storageConnectionString = 
	            "DefaultEndpointsProtocol=http;" + 
	               "AccountName=portalvhdsjtq29274knmm2;" + 
	               "AccountKey=ywi91c425cNVBnpFuQs0ieA1UzkUIF/nF5KZ0BpUc9fXh0xBs36IaO8w039MRvtRLineI1iMgcKGlXsOq51vKg==";
	   
	   public void ListaContainer() throws InvalidKeyException, URISyntaxException{
		   
		   System.out.println("Entrou no método");
		   CloudStorageAccount account;
           CloudBlobClient serviceClient;
           CloudBlobContainer container;
           CloudBlockBlob blob;
           
           account = CloudStorageAccount.parse(storageConnectionString);
           serviceClient = account.createCloudBlobClient();
           
		   CloudStorageAccount acc = CloudStorageAccount.getDevelopmentStorageAccount();
		        // We need to access blobs now, so create a CloudBlobClient
		        CloudBlobClient blobClient = acc.createCloudBlobClient();
		        Iterable<CloudBlobContainer> containers =  blobClient.listContainers();
		        
		        for(CloudBlobContainer cb : containers){
		        	System.out.println(cb.getName());
		        }
	   }	   

}
