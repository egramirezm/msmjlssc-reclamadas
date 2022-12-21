package mx.gob.imss.cit.mjlssc.model.assembler;

import org.springframework.stereotype.Component;

import mx.gob.imss.cit.mjlssc.model.entity.SsccReclamadaDto;
import mx.gob.imss.cit.mjlssc.persistence.entity.SsccReclamada;
import mx.gob.imss.cit.nmlssc.support.assembler.BaseAssembler;

@Component
public class ReclamadaAssembler extends BaseAssembler<SsccReclamada, SsccReclamadaDto> {

	@Override
	public SsccReclamadaDto assemble(SsccReclamada source) {
		SsccReclamadaDto model = new SsccReclamadaDto();
		if (source != null) {
			model = ReclamadaMapper.INSTANCE.toDto(source);
		}

		return model;
	}

	public SsccReclamada assembleEntity(SsccReclamadaDto model) {
		SsccReclamada source = new SsccReclamada();
		if (source != null) {
			source = ReclamadaMapper.INSTANCE.toEntity(model);
		}

		return source;
	}


}
