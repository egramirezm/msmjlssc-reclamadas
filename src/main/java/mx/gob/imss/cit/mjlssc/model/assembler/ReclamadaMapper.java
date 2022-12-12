package mx.gob.imss.cit.mjlssc.model.assembler;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import mx.gob.imss.cit.mjlssc.model.entity.SsccReclamadaDto;
import mx.gob.imss.cit.mjlssc.persistence.entity.SsccReclamada;

@Mapper(componentModel = "spring")
public interface ReclamadaMapper {
	
	ReclamadaMapper INSTANCE = Mappers.getMapper(ReclamadaMapper.class);

	SsccReclamada toEntity(SsccReclamadaDto dto);

	SsccReclamadaDto toDto(SsccReclamada entity);

	List<SsccReclamadaDto> toLstDto(List<SsccReclamada> entity);

	List<SsccReclamada> toLstEntity(List<SsccReclamadaDto> dto);
}
