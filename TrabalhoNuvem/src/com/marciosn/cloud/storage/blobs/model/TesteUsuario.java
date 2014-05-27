package com.marciosn.cloud.storage.blobs.model;

import java.util.List;

import com.marciosn.cloud.storage.blobs.dao.UsuarioJPADAO;

public class TesteUsuario {
	public static void main(String[] args) {
		UsuarioJPADAO usuarioDAO = new UsuarioJPADAO();
		List<Usuario> usuarios = usuarioDAO.find();
		
		for(Usuario u: usuarios){
			System.out.println("ID: " + u.getId());
			System.out.println("Nome: " + u.getNome());
			System.out.println("Senha: " + u.getSenha());
			System.out.println("Email: " + u.getNome());
			System.out.println("=========================");
		}
		usuarioDAO.close();
	}

}
