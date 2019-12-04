package pe.edu.tecsup.Resmascota.controllers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.tecsup.Resmascota.entities.Mascota;
import pe.edu.tecsup.Resmascota.services.MascotaService;

@RestController
public class MascotaController {

private static final Logger logger = LoggerFactory.getLogger(MascotaController.class);
	
	@Value("${app.storage.path}")
	private String STORAGEPATH;
	
	@Autowired
	private MascotaService productoService;
	
	@GetMapping("/productos")
	public List<Mascota> productos() {
		logger.info("call productos");
		
		List<Mascota> productos = productoService.findAll();
		logger.info("productos: " + productos);
		
		return productos;
	}
	
	@GetMapping("/productos/images/{filename:.+}")
	public ResponseEntity<Resource> files(@PathVariable String filename) throws Exception{
		logger.info("call images: " + filename);
		
		Path path = Paths.get(STORAGEPATH).resolve(filename);
		logger.info("Path: " + path);
		
		if(!Files.exists(path)) {
			return ResponseEntity.notFound().build();
		}
		
		Resource resource = new UrlResource(path.toUri());
		logger.info("Resource: " + resource);
		
		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\""+resource.getFilename()+"\"")
				.header(HttpHeaders.CONTENT_TYPE, Files.probeContentType(Paths.get(STORAGEPATH).resolve(filename)))
				.header(HttpHeaders.CONTENT_LENGTH, String.valueOf(resource.contentLength()))
				.body(resource);
	}
}

