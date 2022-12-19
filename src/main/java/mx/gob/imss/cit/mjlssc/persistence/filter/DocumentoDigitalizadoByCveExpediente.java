package mx.gob.imss.cit.mjlssc.persistence.filter;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import mx.gob.imss.cit.mjlssc.persistence.entity.DocumentoDigitalizado;
import mx.gob.imss.cit.nmlssc.support.persistence.BaseSpecification;

public class DocumentoDigitalizadoByCveExpediente extends BaseSpecification<DocumentoDigitalizado> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Long value;
	  
	public DocumentoDigitalizadoByCveExpediente(Long value) {  
		this.value = value;
	}

	@Override
	public Predicate toPredicate(Root<DocumentoDigitalizado> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		
		return null;
	}

	public Long getValue() {
		return value;
	}

}
