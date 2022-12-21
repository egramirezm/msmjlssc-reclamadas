package mx.gob.imss.cit.mjlssc.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mx.gob.imss.cit.mjlssc.persistence.entity.SsccReclamada;

@Repository
public interface SsccReclamadaRepository extends JpaRepository<SsccReclamada, Integer> {

        List<SsccReclamada> findByCveClasAccionReclamada(Integer cveClasAccionReclamada);
}
