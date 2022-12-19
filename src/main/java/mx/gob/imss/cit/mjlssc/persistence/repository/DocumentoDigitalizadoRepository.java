package mx.gob.imss.cit.mjlssc.persistence.repository; 
 
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.gob.imss.cit.mjlssc.persistence.entity.DocumentoDigitalizado;


@Repository
public interface DocumentoDigitalizadoRepository extends CrudRepository<DocumentoDigitalizado, Long>, 
									JpaSpecificationExecutor<DocumentoDigitalizado> {

	@Query("SELECT dd FROM DocumentoDigitalizado dd WHERE dd.numFolio=?1 AND dd.numAnio=?2 AND dd.cveTipoFlujoExcepcion=?3 "
			+ "AND dd.cveJuzgado=?4  AND dd.fecBaja IS NULL AND dd.indAtendido IS NULL AND dd.indRechazo IS NULL")
	List<DocumentoDigitalizado> findByCveJuzgado(Long numFolio, Long numAnio, Long cveTipoFlujoExcepcion, Long cveJuzgado);

	@Query("SELECT dd FROM DocumentoDigitalizado dd WHERE dd.numFolio=?1 AND dd.numAnio=?2  " +
			"AND dd.cveTipoFlujoExcepcion=?3 AND dd.cveJuzgado=?4  AND dd.fecBaja IS NULL AND dd.indAtendido IS NULL")
	List<DocumentoDigitalizado> findByCveJuzgadoAceptado(Long numFolio, Long numAnio, Long cveTipoFlujoExcepcion,Long cveJuzgado);
	
	@Query("SELECT dd FROM DocumentoDigitalizado dd WHERE dd.cveDocDigAmparoIndirecto=?1 AND dd.fecBaja IS NULL")
	Optional<DocumentoDigitalizado> findById(Long id);

}
