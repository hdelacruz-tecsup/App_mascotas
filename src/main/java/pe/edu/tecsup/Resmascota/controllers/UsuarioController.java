package pe.edu.tecsup.Resmascota.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.tecsup.Resmascota.entities.Usuario;
import pe.edu.tecsup.Resmascota.services.UsuarioService;

@RestController
@RequestMapping("/auth")
public class UsuarioController {
	
	
	@Autowired
    private UsuarioService usuarioService;
	
	@GetMapping("/usuarios")
	public List<Usuario> usuarios() {
		logger.info("call usuarios");
		
		List<Usuario> usuarios = usuarioService.findAll();
		logger.info("usuarios: " + usuarios);
		
		return usuarios;
	}

		private static final Logger logger = LoggerFactory.getLogger(UsuarioController.class);
		
		
		@PostMapping("login")
		public Usuario login(@RequestParam String nombre_usu, @RequestParam String password_usu) throws Exception{
			logger.info("login("+nombre_usu+", "+password_usu+")");
			
			Usuario usuario = usuarioService.findByUsernameAndPassword(nombre_usu, password_usu);
			logger.info("Login success: " + usuario);
			
			if(usuario == null)
				throw new Exception("Usuario y/o clave invalido");
			return usuario;
		}
	
	
	
	
}