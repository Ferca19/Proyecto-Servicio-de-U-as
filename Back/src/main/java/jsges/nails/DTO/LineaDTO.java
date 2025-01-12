package jsges.nails.DTO;

import jsges.nails.model.Linea;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class LineaDTO {

    public Integer id;
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