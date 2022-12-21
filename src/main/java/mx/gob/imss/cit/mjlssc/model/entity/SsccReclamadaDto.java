package mx.gob.imss.cit.mjlssc.model.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mx.gob.imss.cit.mjlssc.persistence.entity.SsccReclamada;
import mx.gob.imss.cit.nmlssc.support.model.BaseModel;

/**
 * A DTO for the {@link SsccReclamada} entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SsccReclamadaDto extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 128052803305186026L;
	private Integer cveReclamada;
	private String desReclamada;
	private Float impEstimado;
	private Float numFactorCalculo;
	private Date fecAlta;
	private Date fecBaja;
	private Date fecModifica;
	private String cveUsuarioAlta;
	private String cveUsuarioBaja;
	private String cveUsuarioModifica;
    private Integer cveClasAccionReclamada;

}