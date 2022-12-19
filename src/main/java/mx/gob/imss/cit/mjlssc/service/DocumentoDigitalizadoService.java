package mx.gob.imss.cit.mjlssc.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.logging.Level;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.gob.imss.cit.mjlssc.model.assembler.DocumentoDigitalizadoAssembler;
import mx.gob.imss.cit.mjlssc.model.model.DocumentoDigitalizadoModel;
import mx.gob.imss.cit.mjlssc.model.model.ParamsDTO;
import mx.gob.imss.cit.mjlssc.persistence.entity.ConfigParams;
import mx.gob.imss.cit.mjlssc.persistence.entity.DocumentoDigitalizado;
import mx.gob.imss.cit.mjlssc.persistence.entity.SsccTipoDocumento;
import mx.gob.imss.cit.mjlssc.persistence.entity.SsctAsuntoAmparoIndirecto;
import mx.gob.imss.cit.mjlssc.persistence.repository.ConfigParamsRepository;
import mx.gob.imss.cit.mjlssc.persistence.repository.DocumentoDigitalizadoRepository;
import mx.gob.imss.cit.mjlssc.persistence.repository.SsctAsuntoAmparoIndirectoRepository;
import mx.gob.imss.cit.nmlssc.support.exception.CustomException;
import mx.gob.imss.cit.nmlssc.support.service.BaseService;

@Transactional
@Service
public class DocumentoDigitalizadoService extends BaseService {

	@Autowired
	private DocumentoDigitalizadoRepository repository;

	@Autowired
	private DocumentoDigitalizadoAssembler assembler;

	@Autowired
	private ConfigParamsRepository configParamsRepository;

	@Autowired
	private SsctAsuntoAmparoIndirectoRepository asuntoAmparoIndirectoRepository;

	@Value("${files.filePathTmp}")
	private String filePathTmp;
	
	private static final String ERROR_DOC = "Error documento no encontrado.";

	public DocumentoDigitalizado inicializaDocumentoDigitalizado(String fileName, String checksum, Long cveUsuario,
			Long cveTipoDocumento) {
		String fileType = fileName.substring(fileName.lastIndexOf(".") + 1);
		DocumentoDigitalizado entity = new DocumentoDigitalizado();
		entity.setRefNombreArchivoFs(fileName);
		entity.setRefTipo(fileType);
		entity.setRefHash(checksum);
		entity.setCveUsuarioAlta(String.valueOf(cveUsuario));
		entity.setFecAlta(Timestamp.from(Instant.now()));
		SsccTipoDocumento ssccTipoDocumento = new SsccTipoDocumento();
		ssccTipoDocumento.setCveTipoDocumento(cveTipoDocumento);
		entity.setSsccTipoDocumento(ssccTipoDocumento);
		entity.setCveUsuarioAlta(String.valueOf(cveUsuario));

		return repository.save(entity);
	}

	public DocumentoDigitalizadoModel nuevaExcepcion(DocumentoDigitalizadoModel request) {
		DocumentoDigitalizado entity = repository.findById(request.getId()).orElse(null);
		if (entity == null) {
			throw new CustomException("Error nuevaExcepcion documento no encontrado.", HttpStatus.BAD_REQUEST);
		}
		if (request.getCveAsuntoAmparoIndirecto() != null)
			entity.setCveAsuntoAmparoIndirecto(request.getCveAsuntoAmparoIndirecto());
		if (request.getCveJuzgado() != null)
			entity.setCveJuzgado(request.getCveJuzgado());
		entity.setCveUsuarioAlta(String.valueOf(request.getCveUsuario()));
		entity.setCveUsuarioModifica(String.valueOf(request.getCveUsuario()));
		entity.setCveTipoFlujoExcepcion(request.getCveTipoFlujoExcepcion());
		entity.setNumAnio(request.getNumAnio());
		entity.setNumFolio(request.getNumFolio());
		entity.setIndSolicitudExcepcion(true);
		entity.setFecModifica((Timestamp.from(Instant.now())));
		if (request.getCveAsuntoAmparoIndirecto() != null) {
			SsctAsuntoAmparoIndirecto asunto = asuntoAmparoIndirectoRepository
					.findById(request.getCveAsuntoAmparoIndirecto()).orElse(null);
			if (asunto != null) {
				asunto.setIndSolicitudExcepcion(Integer.valueOf(1));
				entity.setCveTipoAsuntoEtapaConfig(asunto.getCveTipoAsuntoEtapaConfig());
				asunto.setCveUsuarioModifica(String.valueOf(request.getCveUsuario()));
				asunto.setFecModifica((Timestamp.from(Instant.now())));
				asuntoAmparoIndirectoRepository.save(asunto);
			}
		}
		this.repository.save(entity);
		return assembler.assemble(entity);
	}

	public DocumentoDigitalizadoModel aceptarExcepcion(DocumentoDigitalizadoModel request) {
		DocumentoDigitalizado entity = repository.findById(request.getId()).orElse(null);
		if (entity == null) {
			throw new CustomException("Error AceptarExcepcion  documento no encontrado.", HttpStatus.BAD_REQUEST);
		}
		Timestamp timestamp = null;
		timestamp = Timestamp.from(Instant.now());
		entity.setFecModifica(timestamp);
		entity.setIndAceptado(true);
		entity.setCveUsuarioModifica(String.valueOf(request.getCveUsuario()));
		this.repository.save(entity);
		return assembler.assemble(entity);
	}

	public DocumentoDigitalizadoModel rechazarExcepcion(DocumentoDigitalizadoModel request) {
		DocumentoDigitalizado entity = repository.findById(request.getId()).orElse(null);
		if (entity == null) {
			throw new CustomException(ERROR_DOC, HttpStatus.BAD_REQUEST);
		}
		Timestamp timestamp = null;
		timestamp = Timestamp.from(Instant.now());
		entity.setRefComentarioRechazo(request.getRefComentarioRechazo());
		entity.setFecBaja(timestamp);
		entity.setIndRechazo(true);
		entity.setCveUsuarioBaja(String.valueOf(request.getCveUsuario()));
		this.repository.save(entity);
		return assembler.assemble(entity);
	}

	public DocumentoDigitalizadoModel eliminarExcepcion(DocumentoDigitalizadoModel request) {
		DocumentoDigitalizado entity = repository.findById(request.getId()).orElse(null);
		if (entity == null) {
			throw new CustomException(ERROR_DOC, HttpStatus.BAD_REQUEST);
		}
		Timestamp timestamp = Timestamp.from(Instant.now());
		entity.setFecBaja(timestamp);
		entity.setCveUsuarioBaja(String.valueOf(request.getCveUsuario()));
		this.repository.save(entity);
		return assembler.assemble(entity);
	}

	public DocumentoDigitalizadoModel atenderExcepcion(DocumentoDigitalizadoModel request) {
		DocumentoDigitalizado entity = repository.findById(request.getId()).orElse(null);
		if (entity == null) {
			throw new CustomException(ERROR_DOC, HttpStatus.BAD_REQUEST);
		}
		entity.setIndAtendido(true);
		Timestamp timestamp = Timestamp.from(Instant.now());
		entity.setFecModifica(timestamp);
		entity.setCveUsuarioModifica(String.valueOf(request.getCveUsuario()));
		this.repository.save(entity);
		return assembler.assemble(entity);
	}

	public DocumentoDigitalizadoModel actualizaDocumentoDigitalizado(DocumentoDigitalizadoModel documento) {

		DocumentoDigitalizado entity = repository.findById(documento.getId()).orElse(null);
		if (entity == null) {
			throw new CustomException(ERROR_DOC, HttpStatus.BAD_REQUEST);
		}
		entity.setCveAsuntoAmparoIndirecto(documento.getCveAsuntoAmparoIndirecto());
		entity.setCveUsuarioModifica(String.valueOf(documento.getCveUsuario()));
		this.repository.save(entity);
		return assembler.assemble(entity);
	}

	public ParamsDTO getConfigParams() {
		ConfigParams pesoLimite = configParamsRepository.findById(6L).orElse(null);
		ConfigParams pesoMaximo = configParamsRepository.findById(8L).orElse(null);
		ParamsDTO response = new ParamsDTO();
		if (pesoLimite != null && pesoMaximo != null) {
			response.setPesoLimite(Integer.parseInt(pesoLimite.getNomValue()));
			response.setPesoMaximo(Integer.parseInt(pesoMaximo.getNomValue()));
		}
		return response;
	}

	public List<DocumentoDigitalizadoModel> checkExcepcion(DocumentoDigitalizadoModel request) {
		log.log(Level.INFO, ">>>checkExcepcion request: {0}{}", request);
		List<DocumentoDigitalizado> listDocs = null;
		listDocs = repository.findByCveJuzgado(request.getNumFolio(), request.getNumAnio(),
				request.getCveTipoFlujoExcepcion(), request.getCveJuzgado());
		return assembler.assembleList(listDocs);
	}

	public List<DocumentoDigitalizadoModel> checkExcepcionAceptado(DocumentoDigitalizadoModel request) {
		log.log(Level.INFO, ">>>checkExcepcionAceptado request= {}", request);
		List<DocumentoDigitalizado> listDocs = null;
		listDocs = repository.findByCveJuzgadoAceptado(request.getNumFolio(), request.getNumAnio(),
				request.getCveTipoFlujoExcepcion(), request.getCveJuzgado());
		return assembler.assembleList(listDocs);
	}

	public DocumentoDigitalizado getById(Long cveDocDigAmparoIndirecto) {
		return repository.findById(cveDocDigAmparoIndirecto).orElse(null);
	}

}
