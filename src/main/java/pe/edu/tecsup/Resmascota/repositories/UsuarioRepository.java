package pe.edu.tecsup.Resmascota.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import pe.edu.tecsup.Resmascota.entities.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long>  {

	
	@Override
	List<Usuario> findAll();

}
