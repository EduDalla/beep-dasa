package br.com.beep.repositorio;

import br.com.beep.model.UsuarioModel.Usuario;
import br.com.beep.cliente.DAO.UsuarioDAO;

import java.util.List;

public class UsuarioRepositorioImpl implements UsuarioRepositorio {
	private final UsuarioDAO usuarioDAO = new UsuarioDAO();

	@Override
	public void criar(Usuario usuario) {
		usuarioDAO.criar(usuario);
	}

	@Override
	public Usuario obter(int id) {
		return usuarioDAO.obter(id);
	}

	@Override
	public void atualizar(Usuario usuario) {
		usuarioDAO.atualizar(usuario);
	}

	@Override
	public void remover(Usuario usuario) {
		usuarioDAO.remover(usuario);
	}

	@Override
	public List<Usuario> listar() {
		return usuarioDAO.listar();
	}
}