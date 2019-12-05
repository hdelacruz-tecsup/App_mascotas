package pe.edu.tecsup.Resmascota.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.tecsup.Resmascota.entities.Usuario;
import pe.edu.tecsup.Resmascota.repositories.UsuarioRepository;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public Usuario findByUsernameAndPassword(String nombre_usu, String password_usu) {
		return usuarioRepository.findByUsernameAndPassword(nombre_usu, password_usu);
	}

	@Override
	public Usuario findByUsername(String nombre_usu) {
		return usuarioRepository.findByUsername(nombre_usu);
	}

	@Override
	public List<Usuario> findAll() {
		return (List<Usuario>) usuarioRepository.findAll();
	}

	@Override
	public Usuario findById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Usuario usuarios) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteById(Long id) {
		// TODO Auto-generated method stub
		
	}
}

