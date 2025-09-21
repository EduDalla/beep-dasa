package br.com.beep.repositorio;

import java.util.List;

import br.com.beep.model.UsuarioModel.Usuario;

public interface UsuarioRepositorio {

	public void criar(Usuario usuario); // Create

	public Usuario obter(int id); // Read
		
	public void atualizar(Usuario usuario); // Update
	
	public void remover(Usuario usuario); // Delete
	
	public List<Usuario> listar(); 
	

}
