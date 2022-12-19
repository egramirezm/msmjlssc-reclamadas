package mx.gob.imss.cit.mjlssc.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import mx.gob.imss.cit.mjlssc.persistence.entity.SsctAsuntoAmparoIndirecto;


@Repository
public interface SsctAsuntoAmparoIndirectoRepository
		extends JpaRepository<SsctAsuntoAmparoIndirecto, Long>, JpaSpecificationExecutor<SsctAsuntoAmparoIndirecto> {

	@Query("SELECT a FROM SsctAsuntoAmparoIndirecto a WHERE a.id=?1 AND a.fecBaja IS NULL")
	Optional<SsctAsuntoAmparoIndirecto> findById(Long id);

}
