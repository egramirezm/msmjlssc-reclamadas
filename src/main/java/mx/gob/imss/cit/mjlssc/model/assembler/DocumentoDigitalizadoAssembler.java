package mx.gob.imss.cit.mjlssc.model.assembler;

import org.springframework.stereotype.Component;

import mx.gob.imss.cit.mjlssc.model.model.DocumentoDigitalizadoModel;
import mx.gob.imss.cit.mjlssc.persistence.entity.DocumentoDigitalizado;
import mx.gob.imss.cit.nmlssc.support.assembler.BaseAssembler;

@Component
public class DocumentoDigitalizadoAssembler extends BaseAssembler<DocumentoDigitalizado, DocumentoDigitalizadoModel> {
	
	@Override
	public DocumentoDigitalizadoModel assemble(DocumentoDigitalizado source) {
		DocumentoDigitalizadoModel model = new DocumentoDigitalizadoModel();
	    if( source != null ){
	    	model.setId(source.getCveDocDigAmparoIndirecto());
	    	model.setRefNombreArchivo(source.getRefNombreArchivoFs());
	    	model.setRefTipo(source.getSsccTipoDocumento().getDesTipoDocumento());
	    	model.setCveAsuntoAmparoIndirecto(source.getCveAsuntoAmparoIndirecto());
	    	model.setRefHash(source.getRefHash());
	    	model.setCveJuzgado(source.getCveJuzgado());
	    	model.setIndSolicitudExcepcion(source.getIndSolicitudExcepcion());
	    	model.setIndAceptado(source.getIndAceptado());
	    	model.setIndRechazo(source.getIndRechazo());
	    	model.setIndAtendido(source.getIndAtendido());
	    	model.setNumAnio(source.getNumAnio());
	    	model.setNumFolio(source.getNumFolio());
			model.setCveTipoFlujoExcepcion(source.getCveTipoFlujoExcepcion());
	    }
	    
	    return model;
	}

}
