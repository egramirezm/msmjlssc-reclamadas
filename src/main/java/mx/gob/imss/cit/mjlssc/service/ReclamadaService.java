/**
 * 
 */
package mx.gob.imss.cit.mjlssc.service;

import org.springframework.http.ResponseEntity;

import mx.gob.imss.cit.mjlssc.model.entity.SsccReclamadaDto;

/**
 * @author
 *
 */
public interface ReclamadaService {

	ResponseEntity<?> getReclamadas();
	public ResponseEntity<?> getReclamacionById(Integer idReclamacion) ;
	public ResponseEntity<?> removeReclamacionById(Integer idReclamacion) ;
	public ResponseEntity<?> saveReclamas(SsccReclamadaDto ssccReclamadaDto) ;
	public ResponseEntity<?> findByCveClasAccionReclamada(Integer cveClasAccionReclamada);
}
