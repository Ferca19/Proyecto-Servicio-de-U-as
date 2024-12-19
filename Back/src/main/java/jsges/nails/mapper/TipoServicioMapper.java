package jsges.nails.mapper;

import jsges.nails.DTO.TipoServicioDTO;
import jsges.nails.model.TipoServicio;

public class TipoServicioMapper {

    // Método para convertir de TipoServicio a TipoServicioDTO
    public static TipoServicioDTO toDTO(TipoServicio model) {
        if (model == null) {
            return null;
        }
        TipoServicioDTO dto = new TipoServicioDTO();
        dto.setId(model.getId());
        dto.setCodigo(model.getCodigo());
        dto.setDenominacion(model.getDenominacion());
        dto.setEstado(model.getEstado());
        dto.setDetalle(model.getDetalle());
        return dto;
    }

    // Método para convertir de TipoServicioDTO a TipoServicio
    public static TipoServicio toEntity(TipoServicioDTO dto) {
        if (dto == null) {
            return null;
        }
        TipoServicio model = new TipoServicio();
        model.setId(dto.getId());
        model.setCodigo(dto.getCodigo());
        model.setDenominacion(dto.getDenominacion());
        model.setEstado(dto.getEstado());
        model.setDetalle(dto.getDetalle());
        return model;
    }
}
