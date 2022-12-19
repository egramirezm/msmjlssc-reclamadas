package mx.gob.imss.cit.mjlssc.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import mx.gob.imss.cit.nmlssc.support.entity.LogicDeletedEntity;
import mx.gob.imss.cit.mjlssc.component.DataBaseShemaConf;

@Data
@Entity
@Table(name="\"SSCC_CONFIG_PARAMS\"",catalog =DataBaseShemaConf.SCHEMANML)
public class ConfigParams extends LogicDeletedEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CVE_CONFIG_PARAMS")
    private Long cveConfigParams;

    @Column(name = "NOM_VALUE")
    private String nomValue;

    @Column(name = "DES_DESCRIPCION")
    private String desDescripcion;
}
