/**
 * 
 */
package mx.gob.imss.cit.mjlssc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;
import mx.gob.imss.cit.mjlssc.model.entity.SsccReclamadaDto;
import mx.gob.imss.cit.mjlssc.service.ReclamadaService;

/***
 * 
 * @author
 *
 */
@Log4j2
@RestController
public class ReclamacionController {

	@Autowired
	private ReclamadaService reclamadaService;

	@GetMapping("/getReclamadas")
	public ResponseEntity<?> getReclamadas() {
		log.info("Inicio prueba Modulo Juicio Laboral");
		return reclamadaService.getReclamadas();
	}
	
	@GetMapping("/getReclamadas/{cveClasAccion}")
	public ResponseEntity<?> getReclamadasByCveClasAccion(@PathVariable Integer cveClasAccion) {
		log.info("Inicio prueba Modulo Juicio Laboral");
		log.info(">>>ReclamacionController cveClasAccion : '" + cveClasAccion);
		return reclamadaService.findByCveClasAccionReclamada(cveClasAccion);
	}
	
	@GetMapping("/getReclamacion/{id}")
	public ResponseEntity<?> getReclamacionById(@PathVariable Integer id) {
		log.info("Inicio prueba Modulo Juicio Laboral");
		return reclamadaService.getReclamacionById(id);
	}
	
	@DeleteMapping("/removeReclamacion/{id}")
	public ResponseEntity<?> removeReclamacionById(@PathVariable Integer id) {
		log.info("Inicio prueba Modulo Juicio Laboral");
		return reclamadaService.removeReclamacionById(id);
	}

	
	@PostMapping("/saveReclamadas")
	public ResponseEntity<?> saveReclamadas(SsccReclamadaDto  ssccReclamadaDto) {
		log.info("Inicio prueba Modulo Juicio Laboral");
		return reclamadaService.saveReclamas(ssccReclamadaDto);
	}

}
