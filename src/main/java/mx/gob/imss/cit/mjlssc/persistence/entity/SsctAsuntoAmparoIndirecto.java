package mx.gob.imss.cit.mjlssc.persistence.entity;
// Generated 13 may. 2022 00:34:33 by Hibernate Tools 3.6.0.Final

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.CreationTimestamp;

/**
 * SsctAsuntoAmparoIndirecto generated by hbm2java
 */
@Entity
@Table(name = "SSCT_ASUNTO_AMPARO_INDIRECTO", uniqueConstraints = @UniqueConstraint(columnNames = { "CVE_DELEGACION",
		"NUM_JUICIO_AMPARO", "NUM_ANIO_JUICIO_AMPARO", "CVE_JUZGADO_DISTRITO" }))
public class SsctAsuntoAmparoIndirecto implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8037721921324367359L;
	private Long cveAsuntoAmparoIndirecto;
	private Long cveJuzgadoDistrito;

	private Long cveTipoAsuntoEtapaConfig;
	private Integer cveDelegacion;
	private Long numFolio;
	private String desAmparoActo;
	private Boolean indSentidoAcuerdoAdmite;
	private Boolean indSentidoAcuerdoPreviene;
	private Long numJuicioAmparo;
	private Long numAnioJuicioAmparo;
	private Date fecNotificacion;
	private Date fecAlta;
	private Date fecBaja;
	private Date fecModifica;
	private String cveUsuarioAlta;
	private String cveUsuarioBaja;
	private String cveUsuarioModifica;
	private Long cveRepresentante;
	private Integer indSolicitudExcepcion;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "CVE_ASUNTO_AMPARO_INDIRECTO", unique = true, nullable = false)
	public Long getCveAsuntoAmparoIndirecto() {
		return this.cveAsuntoAmparoIndirecto;
	}

	public void setCveAsuntoAmparoIndirecto(Long cveAsuntoAmparoIndirecto) {
		this.cveAsuntoAmparoIndirecto = cveAsuntoAmparoIndirecto;
	}

	@Column(name = "CVE_DELEGACION")
	public Integer getCveDelegacion() {
		return this.cveDelegacion;
	}

	public void setCveDelegacion(Integer cveDelegacion) {
		this.cveDelegacion = cveDelegacion;
	}

	@Column(name = "NUM_FOLIO", nullable = false)
	public Long getNumFolio() {
		return this.numFolio;
	}

	public void setNumFolio(Long numFolio) {
		this.numFolio = numFolio;
	}

	@Column(name = "DES_AMPARO_ACTO", nullable = false, length = 1200)
	public String getDesAmparoActo() {
		return this.desAmparoActo;
	}

	public void setDesAmparoActo(String desAmparoActo) {
		this.desAmparoActo = desAmparoActo;
	}

	@Column(name = "IND_SENTIDO_ACUERDO_ADMITE")
	public Boolean getIndSentidoAcuerdoAdmite() {
		return this.indSentidoAcuerdoAdmite;
	}

	public void setIndSentidoAcuerdoAdmite(Boolean indSentidoAcuerdoAdmite) {
		this.indSentidoAcuerdoAdmite = indSentidoAcuerdoAdmite;
	}

	@Column(name = "IND_SENTIDO_ACUERDO_PREVIENE")
	public Boolean getIndSentidoAcuerdoPreviene() {
		return this.indSentidoAcuerdoPreviene;
	}

	public void setIndSentidoAcuerdoPreviene(Boolean indSentidoAcuerdoPreviene) {
		this.indSentidoAcuerdoPreviene = indSentidoAcuerdoPreviene;
	}

	@Column(name = "NUM_JUICIO_AMPARO")
	public Long getNumJuicioAmparo() {
		return this.numJuicioAmparo;
	}

	public void setNumJuicioAmparo(Long numJuicioAmparo) {
		this.numJuicioAmparo = numJuicioAmparo;
	}

	@Column(name = "NUM_ANIO_JUICIO_AMPARO")
	public Long getNumAnioJuicioAmparo() {
		return this.numAnioJuicioAmparo;
	}

	public void setNumAnioJuicioAmparo(Long numAnioJuicioAmparo) {
		this.numAnioJuicioAmparo = numAnioJuicioAmparo;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "FEC_NOTIFICACION", length = 10)
	public Date getFecNotificacion() {
		return this.fecNotificacion;
	}

	public void setFecNotificacion(Date fecNotificacion) {
		this.fecNotificacion = fecNotificacion;
	}

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FEC_ALTA", nullable = false, length = 26)
	public Date getFecAlta() {
		return this.fecAlta;
	}

	public void setFecAlta(Date fecAlta) {
		this.fecAlta = fecAlta;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "FEC_BAJA", length = 26)
	public Date getFecBaja() {
		return this.fecBaja;
	}

	public void setFecBaja(Date fecBaja) {
		this.fecBaja = fecBaja;
	}

	@Temporal(TemporalType.TIMESTAMP)
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

	@Column(name = "CVE_REPRESENTANTE", length = 11, insertable = false, updatable = false)
	public Long getCveRepresentante() {
		return cveRepresentante;
	}

	public void setCveRepresentante(Long cveRepresentante) {
		this.cveRepresentante = cveRepresentante;
	}

	@Column(name = "CVE_JUZGADO_DISTRITO")
	public Long getCveJuzgadoDistrito() {
		return cveJuzgadoDistrito;
	}

	public void setCveJuzgadoDistrito(Long cveJuzgadoDistrito) {
		this.cveJuzgadoDistrito = cveJuzgadoDistrito;
	}

	@Column(name = "IND_SOLICITUD_EXCEPCION")
	public Integer getIndSolicitudExcepcion() {
		return indSolicitudExcepcion;
	}

	public void setIndSolicitudExcepcion(Integer indSolicitudExcepcion) {
		this.indSolicitudExcepcion = indSolicitudExcepcion;
	}

	@Column(name = "CVE_TIPO_ASUNTO_ETAPA_CONFIG")
	public Long getCveTipoAsuntoEtapaConfig() {
		return cveTipoAsuntoEtapaConfig;
	}

	public void setCveTipoAsuntoEtapaConfig(Long cveTipoAsuntoEtapaConfig) {
		this.cveTipoAsuntoEtapaConfig = cveTipoAsuntoEtapaConfig;
	}

}