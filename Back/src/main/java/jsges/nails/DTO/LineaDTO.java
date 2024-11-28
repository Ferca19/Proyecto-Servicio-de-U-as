package jsges.nails.DTO;

import jsges.nails.model.Linea;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LineaDTO extends TipoObjetoDTO {

    private String denominacion;
    private int estado;
    private String observacion;

    public LineaDTO(Linea linea) {
        super();
        this.id = linea.getId();
        this.denominacion = linea.getDenominacion();
        this.estado = linea.getEstado();
        this.observacion = linea.getObservacion();
    }
}