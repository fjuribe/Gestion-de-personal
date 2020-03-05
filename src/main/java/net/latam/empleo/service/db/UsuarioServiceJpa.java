package net.latam.empleo.service.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import net.latam.empleo.model.Usuario;
import net.latam.empleo.repository.UsuarioRepository;
import net.latam.empleo.service.IUsuariosService;

@Service
@Primary
public class UsuarioServiceJpa implements IUsuariosService{
	
	@Autowired
	private UsuarioRepository usuarioRepo;

	@Override
	public void guardar(Usuario usuario) {
		// TODO Auto-generated method stub
		usuarioRepo.save(usuario);
	}

	@Override
	public void eliminar(Integer idUsuario) {
		// TODO Auto-generated method stub
		usuarioRepo.deleteById(idUsuario);
	}

	@Override
	public List<Usuario> buscarTodos() {
		// TODO Auto-generated method stub
		return usuarioRepo.findAll();
	}

	@Override
	public Usuario buscarPorUsername(String username) {
		// TODO Auto-generated method stub
		return usuarioRepo.findByUsername(username);
	}

}
