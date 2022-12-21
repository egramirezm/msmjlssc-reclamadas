/**
 * 
 */
package mx.gob.imss.cit.mjlssc.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import lombok.extern.log4j.Log4j2;
import mx.gob.imss.cit.mjlssc.model.assembler.ReclamadaAssembler;
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
	private ReclamadaAssembler reclamadaAssembler;

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
			if(!CollectionUtils.isEmpty(reclamadas)) {
				reclamadaDtos = reclamadaAssembler.assembleList(reclamadas);
			}
			
		} catch (Exception e) {
			log.error("Exception ReclamadaService getReclamadas", e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(reclamadaDtos, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> saveReclamas(SsccReclamadaDto ssccReclamadaDto) {
		log.info("Inicio ReclamadaService saveReclamas");
		try {
			// ejemplo projection

			SsccReclamada entity = reclamadaAssembler.assembleEntity(ssccReclamadaDto);
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
			reclamadaDto = reclamadaAssembler.assemble(entity);
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

	@Override
	public ResponseEntity<?> findByCveClasAccionReclamada(Integer cveClasAccionReclamada) {
		log.info("Inicio ReclamadaService findByCveClasAccionReclamada");
		List<SsccReclamadaDto> reclamadaDtos = new ArrayList<>();
		try {
			List<SsccReclamada> reclamadas = ssccReclamadaRepository.findByCveClasAccionReclamada(cveClasAccionReclamada);
			if(!CollectionUtils.isEmpty(reclamadas)) {
				reclamadaDtos = reclamadaAssembler.assembleList(reclamadas);
			}
			
		} catch (Exception e) {
			log.error("Exception ReclamadaService getReclamacionById", e);
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(reclamadaDtos, HttpStatus.OK);

	}

}
