package mx.gob.imss.cit.mjlssc.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import mx.gob.imss.cit.mjlssc.persistence.entity.ConfigParams;


public interface ConfigParamsRepository extends JpaRepository<ConfigParams, Long>,
        JpaSpecificationExecutor<ConfigParams> {
}
