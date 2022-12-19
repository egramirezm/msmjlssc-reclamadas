package mx.gob.imss.cit.mjlssc.persistence.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="\"SSCT_DOCUMENTO_DIGITALIZADO_AMPARO_INDIRECTO\"")
public class DocumentoDigitalizado implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6132321290903321384L;
	private Long cveDocDigAmparoIndirecto;
	private SsccTipoDocumento ssccTipoDocumento;
	private Long cveTipoFlujoExcepcion;
	private Long cveTipoAsuntoEtapaConfig;
	private Long cveAsuntoAmparoIndirecto;
	private String refNombreArchivoUsr;
	private String refNombreArchivoFs;
	private String refTipo;
	private String refHash;
	private Boolean indSolicitudExcepcion;
	private Long numFolio;
	private Long numAnio;
	private Boolean indAceptado;
	private Boolean indRechazo;
	private Boolean indAtendido;
	private String refComentarioRechazo;
	private Long cveJuzgado;
	private Date fecAlta;
	private Date fecBaja;
	private Date fecModifica;
	private String cveUsuarioAlta;
	private String cveUsuarioBaja;
	private String cveUsuarioModifica;
	

	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "CVE_DOC_DIG_AMPARO_INDIRECTO", unique = true, nullable = false)
	public Long getCveDocDigAmparoIndirecto() {
		return this.cveDocDigAmparoIndirecto;
	}

	public void setCveDocDigAmparoIndirecto(Long cveDocDigAmparoIndirecto) {
		this.cveDocDigAmparoIndirecto = cveDocDigAmparoIndirecto;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CVE_TIPO_DOCUMENTO")
	public SsccTipoDocumento getSsccTipoDocumento() {
		return this.ssccTipoDocumento;
	}

	public void setSsccTipoDocumento(SsccTipoDocumento ssccTipoDocumento) {
		this.ssccTipoDocumento = ssccTipoDocumento;
	}

	@Column(name = "REF_NOMBRE_ARCHIVO_USR", length = 200)
	public String getRefNombreArchivoUsr() {
		return this.refNombreArchivoUsr;
	}

	public void setRefNombreArchivoUsr(String refNombreArchivoUsr) {
		this.refNombreArchivoUsr = refNombreArchivoUsr;
	}

	@Column(name = "REF_NOMBRE_ARCHIVO_FS", length = 200)
	public String getRefNombreArchivoFs() {
		return this.refNombreArchivoFs;
	}

	public void setRefNombreArchivoFs(String refNombreArchivoFs) {
		this.refNombreArchivoFs = refNombreArchivoFs;
	}

	@Column(name = "REF_TIPO", length = 3)
	public String getRefTipo() {
		return this.refTipo;
	}

	public void setRefTipo(String refTipo) {
		this.refTipo = refTipo;
	}

	@Column(name = "REF_HASH", length = 60)
	public String getRefHash() {
		return this.refHash;
	}

	public void setRefHash(String refHash) {
		this.refHash = refHash;
	}

	@Column(name = "IND_SOLICITUD_EXCEPCION")
	public Boolean getIndSolicitudExcepcion() {
		return this.indSolicitudExcepcion;
	}

	public void setIndSolicitudExcepcion(Boolean indSolicitudExcepcion) {
		this.indSolicitudExcepcion = indSolicitudExcepcion;
	}

	@Column(name = "NUM_FOLIO")
	public Long getNumFolio() {
		return this.numFolio;
	}

	public void setNumFolio(Long numFolio) {
		this.numFolio = numFolio;
	}

	@Column(name = "NUM_ANIO")
	public Long getNumAnio() {
		return this.numAnio;
	}

	public void setNumAnio(Long numAnio) {
		this.numAnio = numAnio;
	}

	@Column(name = "IND_ACEPTADO")
	public Boolean getIndAceptado() {
		return this.indAceptado;
	}

	public void setIndAceptado(Boolean indAceptado) {
		this.indAceptado = indAceptado;
	}

	@Column(name = "IND_RECHAZO")
	public Boolean getIndRechazo() {
		return this.indRechazo;
	}

	public void setIndRechazo(Boolean indRechazo) {
		this.indRechazo = indRechazo;
	}

	@Column(name = "IND_ATENDIDO")
	public Boolean getIndAtendido() {
		return this.indAtendido;
	}

	public void setIndAtendido(Boolean indAtendido) {
		this.indAtendido = indAtendido;
	}

	@Column(name = "REF_COMENTARIO_RECHAZO", length = 65535)
	public String getRefComentarioRechazo() {
		return this.refComentarioRechazo;
	}

	public void setRefComentarioRechazo(String refComentarioRechazo) {
		this.refComentarioRechazo = refComentarioRechazo;
	}

	//@Temporal(TemporalType.Date)
	@Column(name = "FEC_ALTA", nullable = false, length = 26)
	public Date getFecAlta() {
		return this.fecAlta;
	}

	public void setFecAlta(Date fecAlta) {
		this.fecAlta = fecAlta;
	}

	//@Temporal(TemporalType.DATE)
	@Column(name = "FEC_BAJA", length = 26)
	public Date getFecBaja() {
		return this.fecBaja;
	}

	public void setFecBaja(Date fecBaja) {
		this.fecBaja = fecBaja;
	}

	//@Temporal(TemporalType.Date)
	@Column(name = "FEC_MODIFICA", length = 26)
	public Date getFecModifica() {
		return this.fecModifica;
	}

	public void setFecModifica(Date fecModifica) {
		this.fecModifica = fecModifica;
	}

	@Column(name = "CVE_USUARIO_ALTA", nullable = false, length = 60)
	public String getCveUsuarioAlta() {
		return this.cveUsuarioAlta;
	}

	public void setCveUsuarioAlta(String cveUsuarioAlta) {
		this.cveUsuarioAlta = cveUsuarioAlta;
	}

	@Column(name = "CVE_USUARIO_BAJA", length = 60)
	public String getCveUsuarioBaja() {
		return this.cveUsuarioBaja;
	}

	public void setCveUsuarioBaja(String cveUsuarioBaja) {
		this.cveUsuarioBaja = cveUsuarioBaja;
	}

	@Column(name = "CVE_USUARIO_MODIFICA", length = 60)
	public String getCveUsuarioModifica() {
		return this.cveUsuarioModifica;
	}

	public void setCveUsuarioModifica(String cveUsuarioModifica) {
		this.cveUsuarioModifica = cveUsuarioModifica;
	}

	@Column(name = "CVE_TIPO_FLUJO_EXCEPCION")
	public Long getCveTipoFlujoExcepcion() {
		return cveTipoFlujoExcepcion;
	}

	public void setCveTipoFlujoExcepcion(Long cveTipoFlujoExcepcion) {
		this.cveTipoFlujoExcepcion = cveTipoFlujoExcepcion;
	}

	@Column(name = "CVE_TIPO_ASUNTO_ETAPA_CONFIG")
	public Long getCveTipoAsuntoEtapaConfig() {
		return cveTipoAsuntoEtapaConfig;
	}

	public void setCveTipoAsuntoEtapaConfig(Long cveTipoAsuntoEtapaConfig) {
		this.cveTipoAsuntoEtapaConfig = cveTipoAsuntoEtapaConfig;
	}

	@Column(name = "CVE_ASUNTO_AMPARO_INDIRECTO")
	public Long getCveAsuntoAmparoIndirecto() {
		return cveAsuntoAmparoIndirecto;
	}

	public void setCveAsuntoAmparoIndirecto(Long cveAsuntoAmparoIndirecto) {
		this.cveAsuntoAmparoIndirecto = cveAsuntoAmparoIndirecto;
	}

	@Column(name = "CVE_JUZGADO")
	public Long getCveJuzgado() {
		return cveJuzgado;
	}

	public void setCveJuzgado(Long cveJuzgado) {
		this.cveJuzgado = cveJuzgado;
	}

}
