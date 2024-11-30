package jsges.nails.DTO;

import jsges.nails.model.Linea;
import jsges.nails.model.TipoServicio;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class TipoServicioDTO {

    public Integer id;
    private String denominacion;
    private int estado;
    private String codigo;
    private String detalle;

    public TipoServicioDTO(TipoServicio tipoServicio) {
        super();
        this.id = tipoServicio.getId();
        this.denominacion = tipoServicio.getDenominacion();
        this.estado = tipoServicio.getEstado();
        this.detalle = tipoServicio.getDetalle();
        this.codigo= tipoServicio.getCodigo();
    }
}
