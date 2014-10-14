package com.marciosn.cloud.storage.blobs.model;

import java.util.List;

import com.marciosn.cloud.storage.blobs.dao.FilmeJPADAO;

public class testeFilme{
	public static void main(String[] args) {
		FilmeJPADAO filmeDAO = new FilmeJPADAO();
		
		List<Filme> lista = filmeDAO.find();
		
		for(Filme f: lista){
			System.out.println("ID: " + f.getId());
			System.out.println("Titulo: " + f.getTitulo());
			System.out.println("Ano de Lan�amento: " + f.getAnoLancamento());
			System.out.println("Genero: " + f.getGenero());
			System.out.println("Sinopse: " + f.getSinopse());
			System.out.println("Data de Estreia: " + f.getDataEstreia());
			System.out.println("Dire��o do Filme: " + f.getDirecao());
			System.out.println("Observa��o: " + f.getObservacao());
			System.out.println("Nota Media: " + f.getMedia());
			System.out.println("Dura��o do Filme: " + f.getDuracao());
			
		}
		filmeDAO.close();
	}

}
