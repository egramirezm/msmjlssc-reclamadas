package mx.gob.imss.cit.mjlssc.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.gob.imss.cit.mjlssc.persistence.entity.SsccReclamada;

@Repository
public interface SsccReclamadaRepository extends JpaRepository<SsccReclamada, Integer> {

	<T> T findByRefAbreviacion(String refAbreviacion, Class<T> type);

}
