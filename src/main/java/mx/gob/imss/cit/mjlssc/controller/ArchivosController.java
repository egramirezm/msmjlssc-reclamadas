package mx.gob.imss.cit.mjlssc.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;




import mx.gob.imss.cit.mjlssc.model.model.DocumentoDigitalizadoModel;
import mx.gob.imss.cit.mjlssc.model.model.ParamsDTO;
import mx.gob.imss.cit.mjlssc.persistence.entity.DocumentoDigitalizado;
import mx.gob.imss.cit.mjlssc.service.DocumentoDigitalizadoService;
import mx.gob.imss.cit.mjlssc.utils.CheckSum;
import mx.gob.imss.cit.nmlssc.support.exception.CustomException;

@RestController
public class ArchivosController {
	
	protected final Logger log = Logger.getLogger( getClass().getName() );
	
	@Value("${files.filePathTmp}")
	private String filePathTmp;
	
	@Autowired
	private DocumentoDigitalizadoService service;
	
	@PostMapping("/upload")
	public ResponseEntity<DocumentoDigitalizado> uploadDocumentoDigitalizado(@RequestParam("file") MultipartFile file, @RequestParam("cveUsuario") Long cveUsuario, @RequestParam("cveTipoDocumento") Long cveTipoDocumento) {
		log.info("Inicio upload MAI ");
		StringUtils.cleanPath(file.getOriginalFilename());
		DocumentoDigitalizado documento = null;
		try {
			MessageDigest mdigest = MessageDigest.getInstance("MD5");
			String checksum = CheckSum.checksum(mdigest, file.getInputStream()); 
			documento = service.inicializaDocumentoDigitalizado(file.getOriginalFilename(), checksum, cveUsuario, cveTipoDocumento);

			Path path = Paths.get(filePathTmp + '/' + documento.getCveDocDigAmparoIndirecto()+ '.' +documento.getRefTipo());
			Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			log.info("Termino de proceso upload MAI .... " + path.getParent() + " " + documento.getRefNombreArchivoFs() );
		} catch (Exception e) {
			log.severe("error al upload documento mai" + e);
			 throw new  CustomException("Error al crear el documento", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(documento, HttpStatus.OK);
	}
	
	@PostMapping(value ="/nuevaExcepcion")
	public ResponseEntity<DocumentoDigitalizadoModel> nuevaExcepcion(@RequestBody DocumentoDigitalizadoModel request) {
		DocumentoDigitalizadoModel response = new DocumentoDigitalizadoModel();
		try {
			response = service.nuevaExcepcion(request);
		} catch (Exception e) {
			log.severe(">>>nuevaExcepcion ERROR=" + e.getMessage());
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping(value ="/aceptarExcepcion")
	public ResponseEntity<DocumentoDigitalizadoModel> aceptarExcepcion(@RequestBody DocumentoDigitalizadoModel request) {
		DocumentoDigitalizadoModel response = new DocumentoDigitalizadoModel();
		try {
			response = service.aceptarExcepcion(request);
		} catch (Exception e) {
			log.severe(">>>aceptarExcepcion ERROR=" + e.getMessage());
			throw new  CustomException("Error en aceptarExcepcion", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping(value ="/rechazarExcepcion")
	public ResponseEntity<DocumentoDigitalizadoModel> rechazarExcepcion(@RequestBody DocumentoDigitalizadoModel request) {
		DocumentoDigitalizadoModel response = new DocumentoDigitalizadoModel();
		try {
			response = service.rechazarExcepcion(request);
			Path path = Paths.get(filePathTmp + '/' + response.getId() + ".pdf");
			Files.delete(path);
		} catch (Exception e) {
			log.severe(">>>rechazarExcepcion ERROR=" + e.getMessage());
			throw new  CustomException("Error en rechazarExcepcion", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping(value ="/eliminarExcepcion")
	public ResponseEntity<DocumentoDigitalizadoModel> eliminarExcepcion(@RequestBody DocumentoDigitalizadoModel request) {
		DocumentoDigitalizadoModel response = new DocumentoDigitalizadoModel();
		try {
			response = service.eliminarExcepcion(request);
			Path path = Paths.get(filePathTmp + '/' + request.getId() + ".pdf");
			Files.delete(path);
		} catch (Exception e) {
			log.severe(">>>eliminarExcepcion ERROR=" + e.getMessage());
			throw new  CustomException("Error en eliminarExcepcion", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
	@PostMapping(value = "/download/{cveDocDigAmparoIndirecto}")
	public ResponseEntity<byte[]> downloadDocumentoDigitalizado(@PathVariable Long cveDocDigAmparoIndirecto) {
		log.log(Level.INFO ,"Inicio download documento MAI:: {}", cveDocDigAmparoIndirecto);
		byte[] file = null;
		String fileName = null;
		DocumentoDigitalizado documento = null; 
		try {
			 documento=service.getById(cveDocDigAmparoIndirecto);
			fileName = documento.getRefNombreArchivoFs();
			Path path = Paths.get(filePathTmp + '/' + documento.getCveDocDigAmparoIndirecto() + '.' +documento.getRefTipo());
			file = Files.readAllBytes(path);

		} catch (Exception e) {
			throw new CustomException("Error al recuperar el documento"+cveDocDigAmparoIndirecto, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		log.info("Fin download documento MAI");
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"").body(file);
	}
	
	@PostMapping("/updateData")
	public ResponseEntity<DocumentoDigitalizadoModel> updateDocumentoDigitalizado(@RequestBody DocumentoDigitalizadoModel documento) {
		log.log(Level.INFO ,"Acualizando data : {}", documento);
		documento = service.actualizaDocumentoDigitalizado(documento);
		return new ResponseEntity<>(documento, HttpStatus.OK);
	}
	
	@PostMapping(value = "/fetchParamsLimits")
	public ResponseEntity<ParamsDTO> fetchParamLimits() {
		ParamsDTO response = new ParamsDTO();
		try {
			response = service.getConfigParams();
		} catch (Exception e) {
			log.severe(">>>fetchParamLimits ERROR=" + e.getMessage());
			throw new  CustomException("Error en updateDocumentoDigitalizado", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping(value ="/checkExcepcion")
	public ResponseEntity<List<DocumentoDigitalizadoModel>> checkExcepcion(@RequestBody DocumentoDigitalizadoModel request) {
		List<DocumentoDigitalizadoModel> response = new ArrayList<>();
		try {
			response = service.checkExcepcion(request);
		} catch (Exception e) {
			log.severe(">>>checkExcepcion ERROR=" + e.getMessage());
			throw new  CustomException("Error en checkExcepcion", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@PostMapping(value ="/checkExcepcionAceptado")
	public ResponseEntity<List<DocumentoDigitalizadoModel>> checkExcepcionAceptado(@RequestBody DocumentoDigitalizadoModel request) {
		List<DocumentoDigitalizadoModel> response = new ArrayList<>();
		try {
			response = service.checkExcepcionAceptado(request);
		} catch (Exception e) {
			log.severe(">>>checkExcepcionAceptado ERROR=" + e.getMessage());
			throw new  CustomException("Error en checkExcepcionAceptado", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@PostMapping(value ="/atenderExcepcion")
	public ResponseEntity<DocumentoDigitalizadoModel> atenderExcepcion(@RequestBody DocumentoDigitalizadoModel request) {
		DocumentoDigitalizadoModel response = new DocumentoDigitalizadoModel();
		try {
			response = service.atenderExcepcion(request);
		} catch (Exception e) {
			log.severe(">>>atenderExcepcion ERROR=" + e.getMessage());
			throw new  CustomException("Error en atenderExcepcion", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
