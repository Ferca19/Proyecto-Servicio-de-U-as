package jsges.nails.DTO;


import jsges.nails.model.ArticuloVenta;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class ArticuloVentaDTO extends TipoObjetoDTO {

    private Integer id;
    private String denominacion;
    private Integer linea;

    public ArticuloVentaDTO( ArticuloVenta model) {
        this.id = model.getId();
        this.denominacion=model.getDenominacion();
        this.linea=model.getLinea().getId();
    }

}
