package mx.gob.imss.cit.mjlssc.model.model;

import lombok.Getter;
import lombok.Setter;
import mx.gob.imss.cit.nmlssc.support.model.IdentityBaseModel;

@Setter
@Getter
public class DocumentoDigitalizadoModel extends IdentityBaseModel<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2735079722680378099L;
	private String refNombreArchivo;
	private String refTipo;
	private Long cveAsuntoAmparoIndirecto;
	private String refHash;
	private Boolean indSolicitudExcepcion;
	private Long cveTipoFlujoExcepcion;
	private Long cveJuzgado;
	private Long numFolio;
	private Long numAnio;
	private Boolean indAceptado;
	private Boolean indRechazo;
	private Boolean indAtendido;
	private Long cveTipoAsunto;
	private String refComentarioRechazo;
	private Long cveTipoAsuntoEtapaConfig;
	private Long cveUsuario;
}
