/**
 * 
 */
package mx.gob.imss.cit.mjlssc.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import mx.gob.imss.cit.mjlssc.model.assembler.ReclamadaMapper;
import mx.gob.imss.cit.mjlssc.model.entity.SsccReclamadaDto;
import mx.gob.imss.cit.mjlssc.persistence.entity.SsccReclamada;
import mx.gob.imss.cit.mjlssc.persistence.repository.SsccReclamadaRepository;
import mx.gob.imss.cit.mjlssc.service.ReclamadaService;

/**
 * @author
 *
 */
@Log4j2
@Service
public class ReclamadaServiceImpl implements ReclamadaService {

	@Autowired
	private ReclamadaMapper reclamadaMapper;

	@Autowired
	private SsccReclamadaRepository ssccReclamadaRepository;

	/*
	 * solo prueba revisar y definir uso de mappers y demas
	 */
	@Override
	public ResponseEntity<?> getReclamadas() {
		log.info("Inicio ReclamadaService getReclamadas");
		List<SsccReclamadaDto> reclamadaDtos = new ArrayList<>();
		try {
			// ejemplo projection
			List<SsccReclamada> reclamadas = ssccReclamadaRepository.findAll();
			// delegacionDtos =
			// ObjectMapperUtils.mapAll(delegaciones,SsccDelegacionDto.class);
			reclamadaDtos = reclamadaMapper.toLstDto(reclamadas);
		} catch (Exception e) {
			log.error("Exception ReclamadaService getReclamadas", e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(reclamadaDtos, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> saveReclamas(SsccReclamadaDto ssccReclamadaDto) {
		log.info("Inicio ReclamadaService saveReclamas");
		SsccReclamadaDto reclamadaDto = new SsccReclamadaDto();
		try {
			// ejemplo projection

			SsccReclamada entity = reclamadaMapper.toEntity(ssccReclamadaDto);
			ssccReclamadaRepository.save(entity);
			// delegacionDtos =
			// ObjectMapperUtils.mapAll(delegaciones,SsccDelegacionDto.class);

		} catch (Exception e) {
			log.error("Exception ReclamadaService saveReclamas", e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(ssccReclamadaDto, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getReclamacionById(Integer idReclamacion) {

		log.info("Inicio ReclamadaService getReclamadas");
		SsccReclamadaDto reclamadaDto = new SsccReclamadaDto();
		try {
			// ejemplo projection
			Optional<SsccReclamada> option = ssccReclamadaRepository.findById(idReclamacion);
			SsccReclamada entity = new SsccReclamada();
			if (option.isPresent()) {
				entity = option.get();
			}
			reclamadaDto = reclamadaMapper.toDto(entity);
		} catch (Exception e) {
			log.error("Exception ReclamadaService getReclamacionById", e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(reclamadaDto, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> removeReclamacionById(Integer idReclamacion) {
		log.info("Inicio ReclamadaService getReclamadas");
		SsccReclamadaDto reclamadaDto = new SsccReclamadaDto();
		try {
			// ejemplo projection
			ssccReclamadaRepository.deleteById(idReclamacion);
		} catch (Exception e) {
			log.error("Exception ReclamadaService getReclamacionById", e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(reclamadaDto, HttpStatus.OK);
	}

}
