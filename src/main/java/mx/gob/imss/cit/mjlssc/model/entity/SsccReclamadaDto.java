package mx.gob.imss.cit.mjlssc.model.entity;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A DTO for the {@link SsccReclamada} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SsccReclamadaDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 128052803305186026L;
	private Integer cveReclamada;
	private String desReclamada;
	private String refAbreviacion;
	private Date fecAlta;
	private Date fecBaja;
	private Date fecModifica;
	private String cveUsuarioAlta;
	private String cveUsuarioBaja;
	private String cveUsuarioModifica;

}